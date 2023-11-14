package legend.core.audio.sequencer;

import legend.core.audio.sequencer.assets.Channel;
import legend.core.audio.sequencer.assets.Instrument;
import legend.core.audio.sequencer.assets.InstrumentLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import javax.annotation.Nullable;

final class Voice {
  private static final Logger LOGGER = LogManager.getFormatterLogger();
  private static final Marker VOICE_MARKER = MarkerManager.getMarker("VOICE");
  static final short[] EMPTY = {0, 0, 0};
  private final int index;
  private final LookupTables lookupTables;
  private final VoiceCounter counter;
  private final AdsrEnvelope adsrEnvelope = new AdsrEnvelope();
  private final SoundBankEntry soundBankEntry = new SoundBankEntry();
  private Channel channel;
  private Instrument instrument;
  private InstrumentLayer layer;

  //Playing note
  private boolean used;
  private int sampleRate;
  private int note;
  private int velocity;
  private int pitchBendMultiplier;
  private boolean isModulation;
  private int modulation;
  private byte[][] breathControls;
  private int breath;
  private int breathControlIndex;
  private int breathControlPosition;

  private int priorityOrder;
  private VoicePriority priority;

  private double volumeLeft;
  private double volumeRight;

  private boolean hasSamples;
  private final short[] samples = new short[28 + EMPTY.length];

  Voice(final int index, final LookupTables lookupTables, final int interpolationBitDepth) {
    this.index = index;
    this.lookupTables = lookupTables;
    this.counter = new VoiceCounter(interpolationBitDepth);
  }

  void tick(final short[] output) {
    if(!this.used) {
      output[0] = 0;
      output[1] = 0;
      return;
    }

    this.handleModulation();

    this.adsrEnvelope.tick();

    final short sample = (short)((this.sampleVoice() * this.adsrEnvelope.getCurrentLevel()) >> 15);

    output[0] = (short)(sample * this.volumeLeft);
    output[1] = (short)(sample * this.volumeRight);
  }

  private short sampleVoice() {
    if(!this.hasSamples) {
      this.soundBankEntry.loadSamples(this.samples);

      this.hasSamples = true;
    }

    final int sampleIndex = this.counter.getCurrentSampleIndex();

    final double[] interpolationWeights = this.lookupTables.getInterpolationWeights(this.counter.getInterpolationIndex());

    final double sample = interpolationWeights[0] * this.samples[sampleIndex]
      + interpolationWeights[1] * this.samples[sampleIndex + 1]
      + interpolationWeights[2] * this.samples[sampleIndex + 2]
      + interpolationWeights[3] * this.samples[sampleIndex + 3];


    this.hasSamples = !this.counter.add(this.sampleRate);

    return (short)sample;
  }

  //see FUN_800470fc
  private void handleModulation() {
    if(!this.isModulation) {
      return;
    }

    int sixtyFourths = this.layer.getSixtyFourths();
    int note = this.note;
    int keyRoot = this.layer.getKeyRoot();

    //Swaps the offset from note to root key, since note is set to 120 down the line
    keyRoot = 120 + keyRoot - note;

    if((this.breath & 0xfff) != 120) {
      this.breathControlPosition += this.breath & 0xfff;
    } else {
      final int var = this.breath & 0xf000;
      if(var != 0) {
        this.breath = this.breath & 0xfff | var - 0x1000;
        this.breathControlIndex += this.breath & 0xfff;
      } else {
        this.breath |= 0x6000;
      }
    }

    int pitchBend = 0x80;

    if(this.breathControls != null && this.breathControls.length > 0) {
      if(this.breathControlPosition >= 0xf0) {
        this.breathControlPosition = (this.breath & 0xfff) >>> 1;
      }

      pitchBend = this.breathControls[this.breathControlIndex][this.breathControlPosition >>> 2] & 0xff;
    }

    note = 120;

    final int _64ths = (this.channel.getPitchBend() - 64) * this.pitchBendMultiplier;
    note = note + _64ths / 64;
    sixtyFourths += _64ths - (_64ths / 64) * 64;

    // Here, pitch bend is either 0x80 or the value from the breath control wave
    pitchBend = pitchBend * this.modulation / 255 - ((this.modulation + 1) / 2 - 64);

    this.sampleRate = this.calculateSampleRate(keyRoot, note, sixtyFourths, pitchBend, 1);
  }

  void keyOn(final Channel channel, final Instrument instrument, final InstrumentLayer layer, final int note, final int velocity, final byte[][] breathControls, final int playingVoices) {
    LOGGER.info(VOICE_MARKER, "Voice %d Key On", this.index);

    this.channel = channel;
    this.instrument = instrument;
    this.layer = layer;
    this.note = note;
    this.velocity = velocity;
    this.pitchBendMultiplier = this.layer.isPitchBendMultiplierFromInstrument() ? this.instrument.getPitchBendMultiplier() : this.layer.getPitchBendMultiplier();
    this.breathControls = breathControls;
    this.breath = this.channel.getBreath();
    this.breathControlPosition = 0;
    this.priority = VoicePriority.getPriority(this.layer.isHighPriority(), this.channel.getPriority());
    this.priorityOrder = playingVoices;

    this.isModulation = (this.layer.isModulation() && this.channel.getModulation() != 0);

    if(this.isModulation) {
      this.breathControlIndex = this.layer.isBreathControlIndexFromInstrument() ? this.instrument.getBreathControlIndex() : this.layer.getBreathControlIndex();
      this.modulation = this.channel.getModulation();
    } else {
      //TODO is this really necessary?
      this.modulation = 0;
    }

    this.counter.reset();
    this.adsrEnvelope.load(this.layer.getAdsr());
    this.soundBankEntry.load(this.layer.getSoundBankEntry());

    this.sampleRate = this.calculateSampleRate(this.layer.getKeyRoot(), note, this.layer.getSixtyFourths(), this.channel.getPitchBend(), this.pitchBendMultiplier);
    this.volumeLeft = this.calculateVolume(true);
    this.volumeRight = this.calculateVolume(false);

    this.used = true;
    this.hasSamples = false;
    System.arraycopy(EMPTY, 0, this.samples, 28, EMPTY.length);
  }

  void keyOff() {
    LOGGER.info(VOICE_MARKER, "Voice %d Key Off", this.index);

    this.adsrEnvelope.keyOff();

    this.priority = VoicePriority.decreasePriority(this.priority);
  }

  void clear() {
    LOGGER.info(VOICE_MARKER, "Clearing Voice %d", this.index);

    this.used = false;
    this.note = 0;
    this.channel = null;
    this.layer = null;
    this.instrument = null;
    this.isModulation = false;
    this.modulation = 0;
    this.breath = 0;
    this.priority = VoicePriority.Low;
  }

  @Nullable
  public InstrumentLayer getLayer() {
    return this.layer;
  }

  boolean isUsed() {
    return this.used;
  }

  boolean isFinished() {
    return this.adsrEnvelope.isFinished() || (this.soundBankEntry.isEnd() && this.counter.getCurrentSampleIndex() >= EMPTY.length);
  }

  boolean isLowPriority() {
    return this.priority == VoicePriority.Low;
  }

  int getPriorityOrder() {
    return this.priorityOrder;
  }

  void setPriorityOrder(final int priorityOrder) {
    this.priorityOrder = priorityOrder;
  }

  Channel getChannel() {
    return this.channel;
  }

  int getNote() {
    return this.note;
  }

  private int calculateSampleRate(final int rootKey, final int note, final int sixtyFourths, final int pitchBend, final int pitchBendMultiplier) {
    final int offsetIn64ths = (note - rootKey) * 64 + sixtyFourths + (pitchBend - 64) * pitchBendMultiplier;

    if(offsetIn64ths >= 0) {
      final int octaveOffset = offsetIn64ths / 768;
      final int sampleRateOffset = offsetIn64ths - octaveOffset * 768;
      return this.lookupTables.getSampleRate(sampleRateOffset) << octaveOffset;
    }

    final int octaveOffset = (offsetIn64ths + 1) / -768 + 1;
    final int sampleRateOffset = offsetIn64ths + octaveOffset * 768;
    return this.lookupTables.getSampleRate(sampleRateOffset) >> octaveOffset;
  }

  void updateSampleRate() {
    if(this.layer == null) {
      return;
    }

    this.sampleRate = this.calculateSampleRate(this.layer.getKeyRoot(), this.note, this.layer.getSixtyFourths(), this.channel.getPitchBend(), this.pitchBendMultiplier);
  }

  private double calculateVolume(final boolean left) {
    double volume = this.channel.getAdjustedVolume() * this.instrument.getVolume() * this.layer.getVolume() * this.velocity;
    volume /= 0x4000;
    volume *= this.calculatePan(left);

    if(this.layer.getLockedVolume() == 0) {
      return volume / 0x8000;
    }

    return (double)((this.layer.getLockedVolume() << 8) | ((int)volume >> 7)) / 0x8000;
  }

  void updateVolume() {
    if(this.layer == null) {
      return;
    }

    this.volumeLeft = this.calculateVolume(true);
    this.volumeRight = this.calculateVolume(false);
  }

  private double calculatePan(final boolean left) {
    return this.lookupTables.getPan(this.lookupTables.mergePan(this.channel.getPan(), this.lookupTables.mergePan(this.instrument.getPan(), this.layer.getPan())), left);
  }

  void setModulation(final int modulation) {
    this.isModulation = true;
    this.modulation = modulation;
  }

  void setBreath(final int breath) {
    this.breath = breath;
  }
}

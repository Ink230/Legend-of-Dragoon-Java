package legend.core.audio.sequencer;

import legend.core.audio.Fading;
import legend.core.audio.sequencer.assets.BackgroundMusic;
import legend.core.audio.sequencer.assets.BgmCommand;
import legend.core.audio.sequencer.assets.Channel;
import legend.core.audio.sequencer.assets.Instrument;
import legend.core.audio.sequencer.assets.InstrumentLayer;
import legend.game.sound.ReverbConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static legend.game.Scus94491BpeSegment_8005.reverbConfigs_80059f7c;
import static org.lwjgl.openal.AL10.AL_BUFFERS_PROCESSED;
import static org.lwjgl.openal.AL10.AL_FORMAT_STEREO16;
import static org.lwjgl.openal.AL10.AL_PLAYING;
import static org.lwjgl.openal.AL10.AL_SOURCE_STATE;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alDeleteBuffers;
import static org.lwjgl.openal.AL10.alDeleteSources;
import static org.lwjgl.openal.AL10.alGenBuffers;
import static org.lwjgl.openal.AL10.alGenSources;
import static org.lwjgl.openal.AL10.alGetSourcei;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourceQueueBuffers;
import static org.lwjgl.openal.AL10.alSourceUnqueueBuffers;

public final class Sequencer {
  private static final Logger LOGGER = LogManager.getFormatterLogger();
  private static final Marker SEQUENCER_MARKER = MarkerManager.getMarker("SEQUENCER");
  private final boolean stereo;
  private final Voice[] voices;
  private int playingVoices;
  private final short[] voiceOutputBuffer = new short[2];
  private final short[] outputBuffer;

  private final Reverberizer reverb = new Reverberizer();
  private float reverbVolumeLeft;
  private float reverbVolumeRight;

  /** Use powers of 2 to avoid % operator */
  private static final int BUFFER_COUNT = 4;
  private final int[] buffers = new int[BUFFER_COUNT];
  private int bufferIndex;
  private final int sourceId;

  private BackgroundMusic backgroundMusic;

  private final Queue<BackgroundMusic> backgroundMusicQueue = new LinkedList<>();
  private final Lock bgmLock = new ReentrantLock();
  private boolean unload;
  private float mainVolumeLeft;
  private float mainVolumeRight;
  private Fading fading = Fading.NONE;
  private int fadeInVolume;
  private int fadeOutVolumeLeft;
  private int fadeOutVolumeRight;
  private int fadeTime;
  private int fadeCounter;

  public Sequencer(final int frequency, final boolean stereo, final int voiceCount, final int interpolationBitDepth) {
    if(44_100 % frequency != 0) {
      throw new IllegalArgumentException("Sample Rate (44_100) is not divisible by frequency " + frequency);
    }

    this.outputBuffer = new short[(44_100 / frequency) * 2];

    this.stereo = stereo;

    if(interpolationBitDepth > 12) {
      throw new IllegalArgumentException("Interpolation Bit Depth must be less or equal to 12");
    }

    final LookupTables lookupTables = new LookupTables(64, interpolationBitDepth);

    this.voices = new Voice[voiceCount];

    for(int voice = 0; voice < this.voices.length; voice++) {
      this.voices[voice] = new Voice(voice, lookupTables, interpolationBitDepth);
    }

    this.sourceId = alGenSources();

    alGenBuffers(this.buffers);
  }

  public void tick() {
    //Clears processed buffers
    this.processBuffers();

    for(int sample = 0; sample < this.outputBuffer.length; sample += 2) {
      this.clearVoices();

      this.tickSequence();

      this.outputBuffer[sample] = 0;
      this.outputBuffer[sample + 1] = 0;

      float sumReverbLeft = 0.0f;
      float sumReverbRight = 0.0f;

      this.handleFadeInOut();

      for(final Voice voice : this.voices) {
        voice.tick(this.voiceOutputBuffer);

        this.outputBuffer[sample] += this.voiceOutputBuffer[0];
        this.outputBuffer[sample + 1] += this.voiceOutputBuffer[1];

        if(voice.getLayer() != null && voice.getLayer().isReverb()) {
          sumReverbLeft += this.voiceOutputBuffer[0] / 32_768f;
          sumReverbRight += this.voiceOutputBuffer[1] / 32_768f;
        }
      }

      this.reverb.processReverb(sumReverbLeft, sumReverbRight);

      this.outputBuffer[sample] += (short)((this.reverb.getOutputLeft() * this.reverbVolumeLeft) * 0x8000);
      this.outputBuffer[sample + 1] += (short)((this.reverb.getOutputRight() * this.reverbVolumeRight) * 0x8000);

      this.outputBuffer[sample] *= this.mainVolumeLeft;
      this.outputBuffer[sample + 1] *= this.mainVolumeRight;
    }

    this.bufferOutput();

    // Restarts playback, if stopped
    this.play();

    this.bgmLock.lock();
    try {
      if(this.unload) {
        this.unload = false;

        this.backgroundMusic = null;
        for(final Voice voice : this.voices) {
          voice.clear();
        }

        this.playingVoices = 0;
      }

      if(!this.backgroundMusicQueue.isEmpty()) {
        for(final Voice voice : this.voices) {
          voice.clear();
        }

        this.playingVoices = 0;

        this.backgroundMusic = this.backgroundMusicQueue.remove();
      }
    } finally {
      this.bgmLock.unlock();
    }
  }

  private void clearVoices() {
    for(final Voice voice : this.voices) {
      if(!voice.isUsed() || !voice.isFinished()) {
        continue;
      }

      if(this.playingVoices > 0) {
        this.playingVoices--;
      }

      for(final Voice otherVoice : this.voices) {
        if(otherVoice.getPriorityOrder() > voice.getPriorityOrder()) {
          otherVoice.setPriorityOrder(otherVoice.getPriorityOrder() - 1);
        }
      }

      voice.clear();
    }
  }

  private void tickSequence() {
    if(this.backgroundMusic == null) {
      return;
    }

    if(this.backgroundMusic.getSamplesToProcess() > 0) {
      this.backgroundMusic.tickSequence();
      return;
    }

    while(this.backgroundMusic.getSamplesToProcess() == 0) {
      final BgmCommand command = this.backgroundMusic.getNextCommand();

      switch(command.getMidiCommand()) {
        case KEY_OFF -> this.keyOff(command.getChannel(), command.getValue1());
        case KEY_ON -> this.keyOn(command.getChannel(), command.getValue1(), command.getValue2());
        case CONTROL_CHANGE -> this.controlChange(command.getChannel(), command.getValue1(), command.getValue2());
        case PROGRAM_CHANGE -> this.programChange(command.getChannel(), command.getValue1());
        case PITCH_BEND -> this.pitchBend(command.getChannel(), command.getValue1());
        case META -> this.meta(command.getValue1(), command.getValue2());
        default -> LOGGER.warn(SEQUENCER_MARKER, "Unhandled command %s", command.getMidiCommand());
      }

      if(this.backgroundMusic == null) {
        return;
      }

      this.backgroundMusic.setSamplesToProcess(command.getDeltaTime());
    }

    LOGGER.info(SEQUENCER_MARKER, "Delta ms %.02f", this.backgroundMusic.getSamplesToProcess() / 44.1d);
  }

  private void keyOff(final int channelIndex, final int note) {
    LOGGER.info(SEQUENCER_MARKER, "Key Off Channel: %d Note: %d", channelIndex, note);

    final Channel channel = this.backgroundMusic.getChannel(channelIndex);

    for(final Voice voice : this.voices) {
      if(voice.isUsed() && voice.getChannel() == channel && voice.getNote() == note) {
        voice.keyOff();
      }
    }
  }

  private void keyOn(final int channelIndex, final int note, final int velocity) {
    LOGGER.info(SEQUENCER_MARKER, "Ken On Channel: %d, Note %d, Velocity: %d", channelIndex, note, velocity);

    if(velocity == 0) {
      this.keyOff(channelIndex, note);
      return;
    }

    final Channel channel = this.backgroundMusic.getChannel(channelIndex);

    if(channel.getVolume() == 0) {
      return;
    }

    final Instrument instrument = channel.getInstrument();

    if(instrument == null) {
      return;
    }

    for(final InstrumentLayer layer : instrument.getLayers(note)) {
      final Voice voice = this.selectVoice();

      voice.keyOn(channel, instrument, layer, note, this.backgroundMusic.getVelocity(velocity), this.backgroundMusic.getBreathControls(), this.playingVoices);
    }
  }

  private Voice selectVoice() {
    for(final Voice voice : this.voices) {
      if(!voice.isUsed()) {
        this.playingVoices++;
        return voice;
      }
    }

    int voiceIndex = this.findLowPriorityVoice();

    if(voiceIndex == this.voices.length) {
      voiceIndex = this.findOldestVoice();
    }

    if(voiceIndex == this.voices.length) {
      throw new RuntimeException("Voice pool overflow");
    }

    for(final Voice voice : this.voices) {
      if(voiceIndex < voice.getPriorityOrder()) {
        voice.setPriorityOrder(voice.getPriorityOrder() - 1);
      }
    }

    return this.voices[voiceIndex];
  }

  private int findLowPriorityVoice() {
    int maxPriority = this.voices.length;
    int voiceIndex = this.voices.length;

    for(int voice = 0; voice < this.voices.length; voice++) {
      if(this.voices[voice].isLowPriority() && this.voices[voice].getPriorityOrder() < maxPriority) {
        maxPriority = this.voices[voice].getPriorityOrder();
        voiceIndex = voice;
      }
    }

    return voiceIndex;
  }

  private int findOldestVoice() {
    int maxPriority = this.voices.length;
    int voiceIndex = this.voices.length;

    for(int voice = 0; voice < this.voices.length; voice++) {
      if(this.voices[voice].getPriorityOrder() < maxPriority) {
        maxPriority = this.voices[voice].getPriorityOrder();
        voiceIndex = voice;
      }
    }

    return voiceIndex;
  }

  private void controlChange(final int channelIndex, final int control, final int value) {
    switch(control) {
      case 0x01 -> this.modulation(channelIndex, value);
      case 0x02 -> this.breathControl(channelIndex, value);
      case 0x06 -> this.dataEntry(value);
      case 0x07 -> this.volume(channelIndex, value);
      case 0x0a -> this.pan(channelIndex, value);
      case 0x62 -> this.dataEntryLsb(value);
      case 0x63 -> this.dataEntryMsb(value);
      default -> LOGGER.warn(SEQUENCER_MARKER, "Bad Control Change Channel: %d Command: 0x%x Value: 0x%x", channelIndex, control, value);
    }
  }

  private void modulation(final int channelIndex, final int modulation) {
    LOGGER.info(SEQUENCER_MARKER, "Control Change Modulation Channel: %d Modulation: %d", channelIndex, modulation);

    final Channel channel = this.backgroundMusic.getChannel(channelIndex);

    channel.setModulation(modulation);

    for(final Voice voice : this.voices) {
      if(voice.isUsed() && voice.getChannel() == channel) {
        voice.setModulation(modulation);
      }
    }
  }

  private void breathControl(final int channelIndex, final int value) {
    LOGGER.info(SEQUENCER_MARKER, "Control Change Breath Control Channel: %d Breath: %d", channelIndex, value);

    final int breath = 240 / (60 - value * 58 / 127);

    final Channel channel = this.backgroundMusic.getChannel(channelIndex);

    channel.setBreath(breath);

    for(final Voice voice : this.voices) {
      if(voice.isUsed() && voice.getChannel() == channel) {
        voice.setBreath(breath);
      }
    }
  }

  private void dataEntry(final int value) {
    LOGGER.info(SEQUENCER_MARKER, "Data entry NRPN: %d Value: %d", this.backgroundMusic.getNrpn(), value);

    switch(this.backgroundMusic.getNrpn()) {
      case 0x00 -> {
        this.backgroundMusic.setRepeatCount(value);
        return;
      }
      case 0x04 -> LOGGER.warn(SEQUENCER_MARKER, "Unimplemented Data Entry - Attack (linear)");
      case 0x05 -> LOGGER.warn(SEQUENCER_MARKER, "Unimplemented Data Entry - Attack (exponential)");
      case 0x06 -> LOGGER.warn(SEQUENCER_MARKER, "Unimplemented Data Entry - Decay shift");
      case 0x07 -> LOGGER.warn(SEQUENCER_MARKER, "Unimplemented Data Entry - Sustain level");
      case 0x08 -> LOGGER.warn(SEQUENCER_MARKER, "Unimplemented Data Entry - Sustain (linear)");
      case 0x09 -> LOGGER.warn(SEQUENCER_MARKER, "Unimplemented Data Entry - Sustain (exponential)");
      case 0x0a -> LOGGER.warn(SEQUENCER_MARKER, "Unimplemented Data Entry - Release (linear)");
      case 0x0b -> LOGGER.warn(SEQUENCER_MARKER, "Unimplemented Data Entry - Release (exponential)");
      case 0x0c -> LOGGER.warn(SEQUENCER_MARKER, "Unimplemented Data Entry - Sustain direction");
      case 0x0f -> this.setReverbConfig(reverbConfigs_80059f7c.get(value).config_02);
      case 0x10 -> this.setReverbVolume(value, value);
      default -> LOGGER.error(SEQUENCER_MARKER, "Unknown Data entry NRPN: 0x%x", this.backgroundMusic.getNrpn());
    }
  }

  private void volume(final int channelIndex, final int volume) {
    LOGGER.info(SEQUENCER_MARKER, "Control Change Volume Channel: %d Volume: %d", channelIndex, volume);

    final Channel channel = this.backgroundMusic.getChannel(channelIndex);

    channel.changeVolume(volume, this.backgroundMusic.getVolume());

    for(final Voice voice : this.voices) {
      if(voice.isUsed() && voice.getChannel() == channel) {
        voice.updateVolume();
      }
    }
  }

  private void pan(final int channelIndex, final int pan) {
    LOGGER.info(SEQUENCER_MARKER, "Control Change Pan Channel: %d Pan: %d", channelIndex, pan);

    final Channel channel = this.backgroundMusic.getChannel(channelIndex);

    if(!this.stereo) {
      channel.setPan(0x40);
      return;
    }

    channel.setPan(pan);

    for(final Voice voice : this.voices) {
      if(voice.isUsed() && voice.getChannel() == channel) {
        voice.updateVolume();
      }
    }
  }

  private void dataEntryLsb(final int value) {
    LOGGER.info(SEQUENCER_MARKER, "Data Entry LSB Value: 0x%x", value);

    this.backgroundMusic.dataEntryLsb(value);
  }

  private void dataEntryMsb(final int command) {
    LOGGER.info(SEQUENCER_MARKER, "Data Entry MSB Value: 0x%x", command);

    this.backgroundMusic.dataEntryMsb(command);
  }

  private void programChange(final int channelIndex, final int instrumentIndex) {
    LOGGER.info(SEQUENCER_MARKER, "Program Change Pan Channel: %d Instrument: %d", channelIndex, instrumentIndex);

    final Channel channel = this.backgroundMusic.getChannel(channelIndex);

    channel.setInstrument(instrumentIndex);
    channel.setPitchBend(0x40);
    channel.setPriority(0x40);
  }

  private void pitchBend(final int channelIndex, final int pitchBend) {
    LOGGER.info(SEQUENCER_MARKER, "Pitch Bend Channel: %d, Pitch Bend: %d", channelIndex, pitchBend);

    final Channel channel = this.backgroundMusic.getChannel(channelIndex);

    channel.setPitchBend(pitchBend);

    for(final Voice voice : this.voices) {
      if(voice.isUsed() && voice.getChannel() == channel) {
        voice.updateSampleRate();
      }
    }
  }

  private void meta(final int command, final int value) {
    switch(command) {
      case 0x51 -> {
        LOGGER.info(SEQUENCER_MARKER, "Tempo change: %d", value);

        this.backgroundMusic.setTempo(value);
      }
      case 0x2f -> {
        LOGGER.info(SEQUENCER_MARKER, "End of track.");
        this.backgroundMusic = null;
      }
      default -> LOGGER.warn(SEQUENCER_MARKER, "Unhandled meta event 0x%x value 0x%x", command, value);
    }
  }

  private void setReverbConfig(final ReverbConfig config) {
    this.reverb.setConfig(config);
  }

  private void setReverbVolume(final int reverbVolumeLeft, final int reverbVolumeRight) {
    this.reverbVolumeLeft = (reverbVolumeLeft << 8) / 32768.0f;
    this.reverbVolumeRight = (reverbVolumeRight << 8) / 32768.0f;
  }

  private void processBuffers() {
    final int processedBufferCount = alGetSourcei(this.sourceId, AL_BUFFERS_PROCESSED);

    for(int buffer = 0; buffer < processedBufferCount; buffer++) {
      final int processedBufferName = alSourceUnqueueBuffers(this.sourceId);
      alDeleteBuffers(processedBufferName);
    }

    alGenBuffers(this.buffers);
  }

  private void bufferOutput() {
    final int bufferId = this.buffers[this.bufferIndex++];
    alBufferData(bufferId, AL_FORMAT_STEREO16, this.outputBuffer, 44_100);
    alSourceQueueBuffers(this.sourceId, bufferId);
    this.bufferIndex &= BUFFER_COUNT - 1;
  }

  private void play() {
    if(alGetSourcei(this.sourceId, AL_SOURCE_STATE) == AL_PLAYING) {
      return;
    }

    alSourcePlay(this.sourceId);
  }

  public void destroy() {
    alDeleteBuffers(this.buffers);
    alDeleteSources(this.sourceId);
  }

  public synchronized void loadBackgroundMusic(final BackgroundMusic backgroundMusic) {
    this.backgroundMusicQueue.add(backgroundMusic);
  }

  public void unloadMusic() {
    this.unload = true;
  }

  public void setMainVolume(final int left, final int right) {
    this.mainVolumeLeft = left >= 0x80 ? 1 : left / 254f;

    this.mainVolumeRight = right >= 0x80 ? 1 : right / 254f;
  }

  private void handleFadeInOut() {
    if(this.fadeTime == 0) {
      return;
    }

    switch(this.fading) {
      case FADE_IN -> {
        final int volume = (this.fadeInVolume * this.fadeCounter) / this.fadeTime;
        this.fadeCounter++;
        this.setMainVolume(volume, volume);
      }
      case FADE_OUT -> {
        final int volumeLeft = (this.fadeOutVolumeLeft * (this.fadeTime - this.fadeCounter)) / this.fadeTime;
        final int volumeRight = (this.fadeOutVolumeRight * (this.fadeTime - this.fadeCounter)) / this.fadeTime;
        this.fadeCounter++;
        this.setMainVolume(volumeLeft, volumeRight);
      }
    }

    if(this.fadeCounter > this.fadeTime) {
      this.fading = Fading.NONE;
      this.fadeCounter = 0;
    }
  }

  public void fadeIn(final int time, final int volume) {
    this.fadeTime = time;
    this.fadeInVolume = volume;
    this.fading = Fading.FADE_IN;
  }

  public void fadeOut(final int time) {
    this.fadeTime = time;
    this.fadeOutVolumeLeft = (int)(this.mainVolumeLeft * 0x7e);
    this.fadeOutVolumeRight = (int)(this.mainVolumeRight * 0x7e);
    this.fading = Fading.FADE_OUT;
  }
}

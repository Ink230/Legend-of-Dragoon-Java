package legend.core.audio.sequencer;

import legend.core.audio.sequencer.assets.Channel;
import legend.core.audio.sequencer.assets.Instrument;
import legend.core.audio.sequencer.assets.InstrumentLayer;
import legend.core.audio.sequencer.assets.SoundEffects;
import legend.core.audio.sequencer.assets.sequence.Command;
import legend.core.audio.sequencer.assets.sequence.CommandCallback;
import legend.core.audio.sequencer.assets.sequence.sfx.PolyphonicKeyPressure;
import legend.core.audio.sequencer.assets.sequence.sfx.PortamentoChange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.Map;

public final class SfxSequencer {
  private static final Logger LOGGER = LogManager.getFormatterLogger();
  private static final Marker SEQUENCER_MARKER = MarkerManager.getMarker("SEQUENCER");

  private final SfxVoice[] voices;
  private int playingVoices;
  private final int[] voiceOutputBuffer = new int[2];

  private final Reverberizer reverb = new Reverberizer();
  private float reverbVolumeLeft;
  private float reverbVolumeRight;
  private final float[] reverbOutputBuffer = new float[2];

  private float mainVolumeLeft = 0.5f;
  private float mainVolumeRight = 0.5f;

  private final Map<Class, CommandCallback> commandCallbackMap = new HashMap<>();

  private SoundEffects sfx;
  private int samplesToProcess;

  public SfxSequencer(final int voiceCount, final int interpolationBitDepth) {
    if(interpolationBitDepth > 12) {
      throw new IllegalArgumentException("Interpolation Bit Depth must be less or equal to 12");
    }

    final LookupTables lookupTables = new LookupTables(interpolationBitDepth);

    this.voices = new SfxVoice[voiceCount];

    for(int voice = 0; voice < this.voices.length; voice++) {
      this.voices[voice] = new SfxVoice(voice, lookupTables, interpolationBitDepth);
    }

    this.addCommandCallback(PolyphonicKeyPressure.class, this::polyphonicKeyPressure);
    this.addCommandCallback(PortamentoChange.class, this::portamentoChange);
  }

  public void process() {

  }

  private void tickSequence() {
    if(this.samplesToProcess > 0) {
      this.samplesToProcess--;
      return;
    }

    while(this.samplesToProcess == 0) {

    }
  }

  private void polyphonicKeyPressure(final PolyphonicKeyPressure polyphonicKeyPressure) {


    if(polyphonicKeyPressure.getVelocity() == 0) {
      this.keyOffMatchingNotes();
      return;
    }

    final Channel channel = polyphonicKeyPressure.getChannel();
    final Instrument instrument = channel.getInstrument();
    final int note = polyphonicKeyPressure.getNote();
    final InstrumentLayer layer = instrument.getLayers(note).get(0);

    final SfxVoice voice = this.selectVoice();

    voice.polyphonicKeyPressure(channel, instrument, layer, note, this.sfx.getVelocityVolume(polyphonicKeyPressure.getVelocity()), this.sfx.getBreathControls(), this.playingVoices);
  }

  private SfxVoice selectVoice() {
    return this.voices[0];
  }

  private void keyOffMatchingNotes() {

  }

  private void portamentoChange(final PortamentoChange portamentoChange) {

  }

  private <T extends Command> void addCommandCallback(final Class<T> cls, final CommandCallback<T> callback) {
    this.commandCallbackMap.put(cls, callback);
  }
}

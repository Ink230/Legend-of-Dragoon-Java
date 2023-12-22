package legend.core.audio.sequencer;

import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import legend.core.audio.opus.BufferedAudio;
import legend.core.audio.sequencer.assets.Channel;
import legend.core.audio.sequencer.assets.Instrument;
import legend.core.audio.sequencer.assets.InstrumentLayer;
import legend.core.audio.sequencer.assets.SoundEffects;
import legend.core.audio.sequencer.assets.sequence.Command;
import legend.core.audio.sequencer.assets.sequence.CommandCallback;
import legend.core.audio.sequencer.assets.sequence.sfx.EndOfTrack;
import legend.core.audio.sequencer.assets.sequence.sfx.PolyphonicKeyPressure;
import legend.core.audio.sequencer.assets.sequence.sfx.PortamentoChange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.openal.AL10.AL_FORMAT_STEREO16;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alGenBuffers;
import static org.lwjgl.openal.AL10.alGenSources;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourceQueueBuffers;

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
  private boolean ending = false;
  private int endingCount;

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
    this.addCommandCallback(EndOfTrack.class, this::endOfTrack);
  }

  public void process() {
    final ShortArrayList output = new ShortArrayList();

    while(this.sfx != null) {
      this.clearFinishedVoices();

      this.tickSequence();

      int left = 0;
      int right = 0;

      for(final SfxVoice voice : this.voices) {
        voice.tick(this.voiceOutputBuffer);

        left += this.voiceOutputBuffer[0];
        right += this.voiceOutputBuffer[1];
      }

      if(this.ending) {
        if(left == 0 && right == 0) {
          this.endingCount++;

          if(this.endingCount >= 441) {
            this.sfx = null;
          }
        } else {
          this.endingCount = 0;
        }
      }

      output.add((short)left);
      output.add((short)right);
    }

    final int[] buffers = new int[1];
    final int sourceId = alGenSources();

    alGenBuffers(buffers);

    alBufferData(buffers[0], AL_FORMAT_STEREO16, output.toArray(new short[0]), 44_100);

    alSourceQueueBuffers(sourceId, buffers[0]);

    alSourcePlay(sourceId);

    System.out.println("Done");
  }

  private void clearFinishedVoices() {
    for(final SfxVoice voice : this.voices) {
      if(!voice.isUsed() || !voice.isFinished()) {
        continue;
      }

      if(this.playingVoices > 0) {
        this.playingVoices--;
      }

      for(final SfxVoice otherVoice : this.voices) {
        if(otherVoice.getPriorityOrder() > voice.getPriorityOrder()) {
          otherVoice.setPriorityOrder(otherVoice.getPriorityOrder() - 1);
        }
      }

      voice.clear();
    }
  }

  private void tickSequence() {
    if(this.samplesToProcess > 0) {
      this.samplesToProcess--;

      return;
    }

    while(this.samplesToProcess == 0) {
      if(this.ending) {
        this.sfx = null;
        return;
      }

      final Command command = this.sfx.getNextCommand();

      this.commandCallbackMap.get(command.getClass()).execute(command);

      if(this.ending) {
        return;
      }

      this.samplesToProcess = (int)(command.getDeltaTime() * this.sfx.getSamplesPerTick());
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
    return this.voices[this.playingVoices++];
  }

  private void keyOffMatchingNotes() {

  }

  private void portamentoChange(final PortamentoChange portamentoChange) {
    final Channel channel = portamentoChange.getChannel();

    for(final SfxVoice voice : this.voices) {
      if(voice.isUsed() && voice.getChannel() == channel) {
        voice.portamento(portamentoChange.getPortamento(), portamentoChange.getTotalTime());
      }
    }
  }

  private void endOfTrack(final EndOfTrack endOfTrack) {
    this.samplesToProcess = 44_100;
    this.ending = true;
  }

  public void loadSfx(final SoundEffects sfx) {
    this.sfx = sfx;
  }

  private <T extends Command> void addCommandCallback(final Class<T> cls, final CommandCallback<T> callback) {
    this.commandCallbackMap.put(cls, callback);
  }
}

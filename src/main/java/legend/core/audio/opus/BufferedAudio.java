package legend.core.audio.opus;


import legend.game.unpacker.FileData;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.opus.OpusFile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.openal.AL10.AL_BUFFERS_PROCESSED;
import static org.lwjgl.openal.AL10.AL_FORMAT_MONO16;
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

public final class BufferedAudio {
  private final ByteBuffer opusFileData;
  private final long opusFile;
  private final int channelCount;
  private final int format;
  private final short[] pcm;
  private final ShortBuffer pcmBuffer;

  /** Use powers of 2 to avoid % operator */
  private static final int BUFFER_COUNT = 4;
  private final int[] buffers = new int[BUFFER_COUNT];
  private int bufferIndex;
  private final int sourceId;

  private final long sampleCount;
  private long samplesRead;
  private boolean playing;
  private boolean end;

  public BufferedAudio(final FileData data, final int samplesPerTick) {
    try(final MemoryStack memoryStack = MemoryStack.stackPush()) {
      this.opusFileData = ByteBuffer.allocateDirect(data.size()).order(ByteOrder.nativeOrder());
      this.opusFileData.put(data.data());
      this.opusFileData.rewind();

      final IntBuffer error = memoryStack.mallocInt(1);
      this.opusFile = OpusFile.op_open_memory(this.opusFileData, error);

      //TODO check for error

      this.channelCount = OpusFile.op_channel_count(this.opusFile, -1);
      this.format = this.channelCount == 2 ? AL_FORMAT_STEREO16 : AL_FORMAT_MONO16;
      this.sampleCount = OpusFile.op_pcm_total(this.opusFile, -1);

      this.pcm = new short[samplesPerTick * this.channelCount];
      this.pcmBuffer = BufferUtils.createShortBuffer(this.pcm.length);

      this.sourceId = alGenSources();
      alGenBuffers(this.buffers);

      //This will fail on extremely short tracks, but I cannot be bothered
      this.readFile();
      this.bufferOutput();
      this.readFile();
      this.bufferOutput();
    }
  }

  public void tick() {
    if(!this.playing) {
      return;
    }

    if(this.end && alGetSourcei(this.sourceId, AL_SOURCE_STATE) != AL_PLAYING) {
      this.destroy();
      this.end = false;
    }

    this.processBuffers();
    this.readFile();
    this.bufferOutput();

    this.play();
  }

  private void processBuffers() {
    final int processedBufferCount = alGetSourcei(this.sourceId, AL_BUFFERS_PROCESSED);

    for(int buffer = 0; buffer < processedBufferCount; buffer++) {
      final int processedBufferName = alSourceUnqueueBuffers(this.sourceId);
      alDeleteBuffers(processedBufferName);
    }

    alGenBuffers(this.buffers);
  }

  private void readFile() {
    this.pcmBuffer.clear();
    OpusFile.op_read(this.opusFile, this.pcmBuffer, null);

    this.pcmBuffer.rewind();
    this.pcmBuffer.get(this.pcm);

    this.samplesRead += this.pcm.length;

    this.end = this.samplesRead >= this.sampleCount;
  }

  private void bufferOutput() {
    final int bufferId = this.buffers[this.bufferIndex++];
    alBufferData(bufferId, this.format, this.pcm, 48_000);
    alSourceQueueBuffers(this.sourceId, bufferId);
    this.bufferIndex &= BUFFER_COUNT - 1;
  }

  public void play() {
    this.playing = true;

    if(alGetSourcei(this.sourceId, AL_SOURCE_STATE) == AL_PLAYING) {
      return;
    }

    alSourcePlay(this.sourceId);
  }

  public void destroy() {
    OpusFile.op_free(this.opusFile);
    alDeleteBuffers(this.buffers);
    alDeleteSources(this.sourceId);
  }
}

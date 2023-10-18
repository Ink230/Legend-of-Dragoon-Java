package legend.core.audio;

import legend.game.unpacker.FileData;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.opus.OpusFile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

public class OpusAudio {
  private final long opusFile;
  private final int channelCount;
  private final long sampleCount;

  private final BufferedSound bufferedSound;

  private final int samplesPerTick;

  private final boolean oneTime;

  private long samplesRead;
  private boolean shouldPlay;

  OpusAudio(final FileData fileData, final int frequency, final boolean oneTime) {
    this.samplesPerTick = 480 * (100 / frequency);
    this.oneTime = oneTime;

    try(final MemoryStack memoryStack = MemoryStack.stackPush()) {
      final IntBuffer error = memoryStack.mallocInt(1);

      final ByteBuffer buff = BufferUtils.createByteBuffer(fileData.size());

      buff.put(fileData.data());
      buff.position(0);

      this.opusFile = OpusFile.op_open_memory(buff, error);

      int errorVal = error.get();

      if(errorVal != 0) {
        System.out.println("Beep");
      }

      this.channelCount = OpusFile.op_channel_count(this.opusFile, -1);

      this.sampleCount = OpusFile.op_pcm_total(this.opusFile, -1);

      this.bufferedSound = new BufferedSound(this.samplesPerTick, this.channelCount == 2, 48000);

      this.buffer();
    }
  }

  void tryBuffer() {
    if(!this.bufferedSound.isPlaying()) {
      if(this.samplesRead >= this.sampleCount) {
        if(this.oneTime) {
          //this.destroy();
        }
      }

      return;
    }

    if(this.samplesRead >= this.sampleCount) {
      return;
    }

    this.buffer();
  }

  private void buffer() {
    try(final MemoryStack memoryStack = MemoryStack.stackPush()) {
      final IntBuffer li = memoryStack.mallocInt(1);

      final ShortBuffer pcmBuffer = ByteBuffer.allocateDirect(this.samplesPerTick * this.channelCount * 2).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer();

      OpusFile.op_read(this.opusFile, pcmBuffer, li);

      final short[] pcm = new short[pcmBuffer.limit()];

      pcmBuffer.position(0);
      pcmBuffer.get(pcm);

      this.bufferedSound.bufferSamples(pcm);

      this.samplesRead += this.samplesPerTick * this.channelCount;
    }
  }


  public void play() {
    this.shouldPlay = true;
    this.bufferedSound.play();
  }

  public void shouldPlay() {
    if(this.shouldPlay && !this.bufferedSound.isPlaying()) {
      this.play();
    }
  }

  public void destroy() {
    this.bufferedSound.destroy();
    OpusFile.op_free(this.opusFile);
  }

  void processBuffers() {
    this.bufferedSound.processBuffers();
  }
}

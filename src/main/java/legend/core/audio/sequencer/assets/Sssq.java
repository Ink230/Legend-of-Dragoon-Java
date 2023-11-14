package legend.core.audio.sequencer.assets;

import legend.game.unpacker.FileData;
import legend.game.unpacker.UnpackerException;

final class Sssq {
  private final Channel[] channels;
  private final FileData[][] sequences;
  private final int volume;
  private final int ticksPerQuarterNote;
  private int tempo;
  private double ticksPerMs;

  /** Background Music */
  Sssq(final FileData data, final SoundFont soundFont) {
    //SSsq tag
    if(data.readInt(12) != 0x71735353) {
      throw new UnpackerException("Not a SSsq file!");
    }

    this.volume = data.readUByte(0x0);
    this.ticksPerQuarterNote = data.readUShort(0x2);
    final int tempo = data.readUShort(0x4);
    this.setTempo(tempo);

    this.channels = new Channel[0x10];
    for(int channel = 0; channel < this.channels.length; channel++) {
      this.channels[channel] = new Channel(data.slice(16 + channel * 16, 16), soundFont);
    }

    this.sequences = new FileData[1][];
    this.sequences[0] = new FileData[] { data.slice(0x110, data.size() - 0x110) };
  }

  //TODO SFX

  int getVolume() {
    return this.volume;
  }

  void setTempo(final int tempo) {
    this.tempo = tempo;
    this.ticksPerMs = (this.tempo * this.ticksPerQuarterNote) / 60_000d;
  }

  Channel getChannel(final int channelIndex) {
    return this.channels[channelIndex];
  }

  FileData getSequence(final int set, final int index) {
    return this.sequences[set][index];
  }

  double getTicksPerMs() {
    return this.ticksPerMs;
  }
}

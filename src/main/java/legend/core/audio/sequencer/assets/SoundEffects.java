package legend.core.audio.sequencer.assets;

import legend.core.audio.sequencer.assets.sequence.Command;
import legend.core.audio.sequencer.assets.sequence.sfx.SequenceBuilder;
import legend.game.unpacker.FileData;

import java.util.List;

public final class SoundEffects {
  private final int effectId;

  private final int volume;


  private final byte[][] breathControls = new byte[0][];
  private final byte[] velocityRamp = new byte[0x80];

  private final Command[][] seqences;
  private int sequencePosition;

  public SoundEffects(final List<FileData> files) {
    this.effectId = files.get(0).readUShort(0x0);

    final FileData sshd = files.get(2);

    sshd.copyFrom(sshd.readInt(0x14) + 2, this.velocityRamp, 0, 0x80);

    final int sssqishOffset = sshd.readInt(0x20);

    final SoundBank soundBank = new SoundBank(files.get(3));
    final SoundFont soundFont = new SoundFont(sshd.slice(0x190 + sssqishOffset), soundBank);

    this.volume = sshd.readUByte(sssqishOffset);

    final Channel[] channels = new Channel[24];
    for(int channel = 0; channel < channels.length; channel++) {
      channels[0] = new Channel(sshd.slice(sssqishOffset + 0x10 + channel * 0x10, 0x10), this.volume, soundFont);
    }

    final FileData sequenceOrders = files.get(1);
    this.seqences = new Command[sequenceOrders.size() / 2][];
    final int sequenceSubfileOffset = sshd.readInt(0x1c);

    for(int sequence = 0; sequence < this.seqences.length; sequence++) {
      final int set = sequenceOrders.readUByte(sequence * 2);
      final int index = sequenceOrders.readUByte(sequence * 2 + 1);

      final int fullOffset = sequenceSubfileOffset + sshd.readUShort(sequenceSubfileOffset + sshd.readUShort(sequenceSubfileOffset + 2 + set * 2) + index * 2 + 2);

      this.seqences[sequence] = SequenceBuilder.create(sshd, fullOffset, channels);
    }
  }

  public byte[][] getBreathControls() {
    return this.breathControls;
  }

  public int getVelocityVolume(final int velocity) {
    return this.velocityRamp[velocity];
  }

}

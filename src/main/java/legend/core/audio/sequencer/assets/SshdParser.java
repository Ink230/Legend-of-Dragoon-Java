package legend.core.audio.sequencer.assets;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import legend.game.unpacker.FileData;
import legend.game.unpacker.UnpackerException;

final class SshdParser {
  /**
   * <ul>
   *   <li>Index 0 - Instruments</li>
   *   <li>Index 1 - Velocity Ramp</li>
   *   <li>Index 2 - Breath Control wave</li>
   *   <li>Index 3 - Multi-Sequence</li>
   *   <li>Index 4 - Channels + Instruments (SFX)</li>
   * </ul>
   */
  private final Int2ObjectMap<FileData> subfiles = new Int2ObjectArrayMap<>();

  SshdParser(final FileData sshdData) {
    if(sshdData.readInt(12) != 0x64685353) {
      throw new UnpackerException("Not a SShd file!");
    }

    int lastOffset = sshdData.size();
    for(int i = 23;  i >= 0; i--) {
      final int offset = sshdData.readInt(16 + i * 4);

      if(offset != - 1) {
        this.subfiles.put(i, sshdData.slice(offset, lastOffset - offset));
        lastOffset = offset;
      }
    }
  }

  SoundFont createSoundFont(final FileData data) {
    if(this.subfiles.containsKey(0)) {
      return new SoundFont(this.subfiles.get(0), new SoundBank(data));
    }

    return new SoundFont(this.subfiles.get(4).slice(0x190), new SoundBank(data));
  }

  byte[][] getBreathControls() {
    if(!this.subfiles.containsKey(2)) {
      return new byte[0][];
    }

    final FileData data = this.subfiles.get(2);

    final int upperBound = data.readUShort(0);
    final byte[][] breathControls = new byte[upperBound + 1][];

    int nextOffset = data.size();
    for(int i = upperBound; i >= 0; i--) {
      final int startOffset = data.readShort(2 + i * 2);

      if(startOffset != -1) {
        breathControls[i] = data.slice(startOffset, nextOffset - startOffset).getBytes();
        nextOffset = startOffset;
      }
    }

    return breathControls;
  }

  byte[] getVelocityRamp() {
    final FileData data = this.subfiles.get(1);
    return data.slice(2, data.size() - 2).getBytes();
  }
}

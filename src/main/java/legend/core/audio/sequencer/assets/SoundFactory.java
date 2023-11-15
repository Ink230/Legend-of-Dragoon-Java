package legend.core.audio.sequencer.assets;

import legend.game.unpacker.FileData;
import legend.game.unpacker.Unpacker;
import legend.game.unpacker.UnpackerException;

import java.util.List;

public final class SoundFactory {
  private SoundFactory() {}

  public static BackgroundMusic backgroundMusic( final List<FileData> files, final int fileIndex) {
    final SshdParser sshdParser;
    final Sssq sssq;
    switch(files.size()) {
      case 4 -> {
        sshdParser = new SshdParser(files.get(2));

        sssq = new Sssq(files.get(1), sshdParser.createSoundFont(files.get(3)));
      }
      case 5 -> {
        final int soundBankCount = files.get(1).readUShort(0);
        final FileData[] soundBanks = new FileData[soundBankCount];

        soundBanks[0] = files.get(4);

        int totalSoundBankSize = soundBanks[0].size();
        for(int i = 1; i < soundBankCount; i++) {
          soundBanks[i] = Unpacker.loadFile("SECT/DRGN0.BIN/" + (fileIndex + i));
          totalSoundBankSize += soundBanks[i].size();
        }

        final byte[] allSoundBanks = new byte[totalSoundBankSize];
        int offset = 0;

        for(final FileData soundBank : soundBanks) {
          soundBank.copyFrom(0, allSoundBanks, offset, soundBank.size());
          offset += soundBank.size();
        }

        sshdParser = new SshdParser(files.get(3));

        sssq = new Sssq(files.get(2), sshdParser.createSoundFont(new FileData(allSoundBanks)));
      }
      default -> throw new UnpackerException("Uknwonw Sequenced Audio type. File count: " + files.size());
    }

    return new BackgroundMusic(files.get(0).readUShort(0), sssq, sshdParser.getBreathControls(), sshdParser.getVelocityRamp());
  }
}

package legend.game.unpacker.xa;

import legend.game.unpacker.FileData;

import java.io.File;

public final class Transformer {
  public static void transcode(final String name, final FileData fileData) {
    final String path = System.getProperty("user.dir") + "\\files\\" + name.replace(".XA", "");

    //Make sure we have the folder created, since we're not running this back into the unpacker
    File f = new File(path);
    f.mkdirs();

    if(name.endsWith("3.XA")) {
      creditsXa(path, fileData);
      return;
    }

    standardXa(path, fileData);
  }

  private static void standardXa(final String path, final FileData fileData) {
    final int sectorCount = fileData.size();
    final int channelSectorCount = sectorCount / 16;

    for(int subFile = 1; subFile < 16; subFile++) {
      final Track track = new Track(path + '\\' + subFile + ".ogg");

      for(int sector = 0; sector < channelSectorCount; sector++) {
        track.addAudioFrame(fileData.slice((sector * 16 + subFile) * 0x930, 0x930));

        if((fileData.readUByte((sector * 16 + subFile) * 0x930 + 18) >>> 7 & 0x01) == 1) {
          break;
        }
      }

      track.encode();
    }
  }

  private static void creditsXa(final String path, final FileData fileData) {
    final int sectorCount = fileData.size() / 0x930;
    final int channelSectorCount = sectorCount / 4;

    final Track track = new Track(path + "\\1.ogg");

    for(int sector = 0; sector <channelSectorCount; sector++) {
      track.addAudioFrame(fileData.slice((sector * 4 + 1) * 0x930, 0x930));
    }

    track.encode();
  }
}

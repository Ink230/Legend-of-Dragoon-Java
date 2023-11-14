package legend.core.audio;

import legend.core.DebugHelper;
import legend.core.audio.sequencer.assets.BackgroundMusic;
import legend.core.audio.sequencer.assets.SoundFactory;
import legend.game.unpacker.Unpacker;

public final class SoundTest {
  private SoundTest() {}

  public static void main(final String[] args) {
    final AudioThread audioThread = new AudioThread(100, true, 24, 9);

    final Thread spuThread = new Thread(audioThread);
    spuThread.setName("SPU");

    spuThread.start();

    final int fileIndex = 5820;

    final BackgroundMusic bgm = SoundFactory.backgroundMusic(Unpacker.loadDirectory("SECT/DRGN0.BIN/" + fileIndex), fileIndex);

    audioThread.loadBackgroundMusic(bgm);

//    DebugHelper.sleep(2000);

    //This somehow causes popping, seems to work fine in game
    audioThread.loadXa(Unpacker.loadFile("XA\\LODXA03.XA\\1.opus")).play();

    DebugHelper.sleep(1000000);

    audioThread.stop();
  }
}

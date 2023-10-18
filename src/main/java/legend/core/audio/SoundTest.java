package legend.core.audio;

import legend.core.DebugHelper;
import legend.core.audio.assets.BackgroundMusic;
import legend.core.audio.assets.SoundFactory;
import legend.game.unpacker.Unpacker;

public final class SoundTest {
  public static void main(final String[] args) {
    final AudioThread audioThread = new AudioThread(50, true, 24, 9);
    //final BackgroundMusic bgm = SoundFactory.backgroundMusic(702);
    //audioThread.loadBackgroundMusic(bgm);

    final OpusAudio audio = audioThread.loadXa(Unpacker.loadFile("XA\\LODXA03.XA\\1.opus"));

    final Thread spuThread = new Thread(audioThread);
    spuThread.setName("SPU");

    spuThread.start();

    //DebugHelper.sleep(5000);

    audio.play();

    DebugHelper.sleep(100000);

    audioThread.stop();
  }
}

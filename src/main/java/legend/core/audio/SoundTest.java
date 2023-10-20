package legend.core.audio;

import legend.core.DebugHelper;
import legend.core.audio.assets.BackgroundMusic;
import legend.core.audio.assets.SoundEffect;
import legend.core.audio.assets.SoundFactory;
import legend.game.unpacker.Unpacker;

public final class SoundTest {
  public static void main(final String[] args) {
    final AudioThread audioThread = new AudioThread(100, true, 24, 9);
    final BackgroundMusic bgm = SoundFactory.backgroundMusic(702);
    audioThread.loadBackgroundMusic(bgm);

    final OpusAudio audio = audioThread.loadXa(Unpacker.loadFile("XA\\LODXA00.XA\\4.opus"));

    //final SoundEffect sfx = SoundFactory.soundEffect("characters\\dart\\sounds\\combat");

    final Thread spuThread = new Thread(audioThread);
    spuThread.setName("SPU");

    spuThread.start();

    DebugHelper.sleep(10000);

    audio.play();

    DebugHelper.sleep(100000);

    audioThread.stop();
  }
}

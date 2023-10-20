package legend.core.audio.assets;

public class SoundEffect {

  private final Sssq sssq;
  private final byte[][] breathControls;
  private final byte[] velocityRamp;

  SoundEffect(final Sssq sssq, final byte[][] breathControls, final byte[] velocityRamp) {
    this.sssq = sssq;
    this.breathControls = breathControls;
    this.velocityRamp = velocityRamp;
  }
}

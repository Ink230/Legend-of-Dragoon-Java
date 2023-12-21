package legend.core.audio.sequencer.assets.sequence.sfx;

import legend.core.audio.sequencer.assets.Channel;
import legend.core.audio.sequencer.assets.sequence.Command;

public final class PolyphonicKeyPressure implements Command {
  private final Channel channel;
  private final int note;
  private final int velocity;
  private final int something;
  private final int deltaTime;

  PolyphonicKeyPressure(final Channel channel, final int note, final int velocity, final int something, final int deltaTime) {
    this.channel = channel;
    this.note = note;
    this.velocity = velocity;
    this.something = something;
    this.deltaTime = deltaTime;
  }

  public Channel getChannel() {
    return this.channel;
  }

  public int getNote() {
    return this.note;
  }

  public int getVelocity() {
    return this.velocity;
  }

  public int getSomething() {
    return this.something;
  }

  @Override
  public int getDeltaTime() {
    return this.deltaTime;
  }
}

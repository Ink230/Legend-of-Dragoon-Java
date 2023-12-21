package legend.core.audio.sequencer.assets.sequence.sfx;

import legend.core.audio.sequencer.assets.Channel;
import legend.core.audio.sequencer.assets.sequence.Command;

public final class PortamentoChange implements Command {
  private final Channel channel;
  private final int totalTime;
  private final int something;
  private final int note;
  private final int portamento;
  private final int deltaTime;

  PortamentoChange(final Channel channel, final int totalTime, final int something, final int note, final int portamento, final int deltaTime) {
    this.channel = channel;
    this.totalTime = totalTime;
    this.something = something;
    this.note = note;
    this.portamento = portamento;
    this.deltaTime = deltaTime;
  }

  public Channel getChannel() {
    return this.channel;
  }

  public int getTotalTime() {
    return this.totalTime;
  }

  public int getSomething() {
    return this.something;
  }

  public int getNote() {
    return this.note;
  }

  public int getPortamento() {
    return this.portamento;
  }

  @Override
  public int getDeltaTime() {
    return this.deltaTime;
  }
}

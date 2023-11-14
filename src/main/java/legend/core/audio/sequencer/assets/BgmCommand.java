package legend.core.audio.sequencer.assets;

import legend.core.audio.sequencer.MidiCommand;

public final class BgmCommand {
  private MidiCommand midiCommand;
  private int channel;
  private int value1;
  private int value2;
  private int deltaTime;

  public MidiCommand getMidiCommand() {
    return this.midiCommand;
  }

  void setMidiCommand(final MidiCommand midiCommand) {
    this.midiCommand = midiCommand;
  }

  public int getChannel() {
    return this.channel;
  }

  void setChannel(final int channel) {
    this.channel = channel;
  }

  public int getValue1() {
    return this.value1;
  }

  void setValue1(final int value1) {
    this.value1 = value1;
  }

  public int getValue2() {
    return this.value2;
  }

  void setValue2(final int value2) {
    this.value2 = value2;
  }

  public int getDeltaTime() {
    return this.deltaTime;
  }

  void setDeltaTime(final int deltaTime) {
    this.deltaTime = deltaTime;
  }
}

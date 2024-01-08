package legend.core.audio.sequencer.assets.sequence.sfx;

import legend.core.audio.sequencer.assets.Channel;
import legend.core.audio.sequencer.assets.sequence.Command;
import legend.game.unpacker.FileData;

import java.util.ArrayList;
import java.util.List;

public final class SequenceBuilder {
  private int position;
  private int currentEvent;
  private int previousEvent;
  private final FileData data;
  private final Channel[] channels;

  private SequenceBuilder(final FileData data, final int offset, final Channel[] channels) {
    this.data = data;
    this.channels = channels;
    this.position = offset;
  }

  private int readDeltaTime() {
    int deltaTime = 0;

    while(true) {
      final int part = this.data.readUByte(this.position++);

      deltaTime <<= 7;
      deltaTime |= part & 0x7f;

      if((part & 0x80) == 0) {
        return deltaTime;
      }
    }
  }

  private Command polyphonicKeyPressure(final int channelIndex) {
    return new PolyphonicKeyPressure(
      this.channels[channelIndex],
      this.data.readUByte(this.position++),
      this.data.readUByte(this.position++),
      this.data.readUByte(this.position++),
      this.readDeltaTime());
  }

  private Command controlChange(final int channelIndex) {
    final int commandType = this.data.readUByte(this.position++);

    return switch(commandType) {
      case 0x41 -> new PortamentoChange(
        this.channels[channelIndex],
        this.data.readUByte(this.position++),
        this.data.readUByte(this.position++),
        this.data.readUByte(this.position++),
        this.data.readUByte(this.position++),
        this.readDeltaTime());
      default -> throw new RuntimeException("Unexpected control change type 0x%x".formatted(commandType));
    };
  }

  private Command meta() {
    final int metaType = this.data.readUByte(this.position++);

    switch(metaType) {
      case 0x2f -> { return new EndOfTrack(this.readDeltaTime()); }
      default -> throw new RuntimeException("Unexpected meta type 0x%x".formatted(metaType));
    }
  }

  private Command getCommand(final int event) {
    return switch(event & 0xf0) {
      case 0xa0 -> this.polyphonicKeyPressure(event & 0x0f);
      case 0xb0 -> this.controlChange(event & 0x0f);
      case 0xf0 -> this.meta();
      default -> throw new RuntimeException("Unexpected command 0x%x at postion %d".formatted(event, this.position));
    };
  }

  private Command[] processSequence() {
    final List<Command> commands = new ArrayList<>();

    while(this.position < this.data.size()) {
      this.currentEvent = this.data.readUByte(this.position);

      if((this.currentEvent & 0x80) == 0) {
        this.currentEvent = this.previousEvent;
      } else {
        this.previousEvent = this.currentEvent;
        this.position++;
      }

      final Command command = this.getCommand(this.currentEvent);
      commands.add(command);

      if(command instanceof EndOfTrack) {
        break;
      }
    }

    return commands.toArray(new Command[0]);
  }

  public static Command[] create(final FileData data, final int offset, final Channel[] channels) {
    final SequenceBuilder sequenceBuilder = new SequenceBuilder(data, offset, channels);

    return sequenceBuilder.processSequence();
  }
}

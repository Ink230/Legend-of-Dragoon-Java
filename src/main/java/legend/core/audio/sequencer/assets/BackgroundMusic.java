package legend.core.audio.sequencer.assets;

import legend.core.audio.sequencer.MidiCommand;
import legend.game.unpacker.FileData;

public final class BackgroundMusic {
  private final Sssq sssq;
  private final byte[][] breathControls;
  private final byte[] velocityRamp;

  private final FileData sequence;
  private int sequencePosition;
  private int samplesToProcess;

  private BgmCommand currentCommand;
  private BgmCommand previousCommand;

  private int lsbType;
  private int nrpn;
  private int dataInstrumentIndex;
  private int repeatCount;
  private int repeatCounter;
  private int repeatPosition;
  private BgmCommand repeatCommand;
  private boolean repeat;

  BackgroundMusic(final Sssq sssq, final byte[][] breathControls, final byte[] velocityRamp) {
    this.sssq = sssq;
    this.breathControls = breathControls;
    this.velocityRamp = velocityRamp;

    this.sequence = this.sssq.getSequence(0, 0);
  }

  public BgmCommand getNextCommand() {
    this.previousCommand = this.currentCommand;

    final int event = this.sequence.readUByte(this.sequencePosition);

    this.currentCommand = new BgmCommand();

    if((event & 0x80) != 0) {
      this.currentCommand.setMidiCommand(MidiCommand.formEvent(event));
      this.currentCommand.setChannel(event & 0x0f);
      this.sequencePosition++;
    } else {
      this.currentCommand.setMidiCommand(this.previousCommand.getMidiCommand());
      this.currentCommand.setChannel(this.previousCommand.getChannel());
    }

    switch(this.currentCommand.getMidiCommand()) {
      case KEY_OFF, KEY_ON, CONTROL_CHANGE -> {
        this.currentCommand.setValue1(this.sequence.readUByte(this.sequencePosition++));
        this.currentCommand.setValue2(this.sequence.readUByte(this.sequencePosition++));
      }
      case PROGRAM_CHANGE, PITCH_BEND -> {
        this.currentCommand.setValue1(this.sequence.readUByte(this.sequencePosition++));
      }
      case META -> {
        this.currentCommand.setValue1(this.sequence.readUByte(this.sequencePosition++));

        if(this.currentCommand.getValue1() == 0x51) {
          this.currentCommand.setValue2(this.sequence.readUShort(this.sequencePosition));
          this.sequencePosition +=2;
        }
      }
      default -> throw new RuntimeException("Bad message: %s".formatted(this.currentCommand.getMidiCommand()));
    }

    if(this.repeat) {
      this.repeat = false;
      this.sequencePosition = this.repeatPosition;
      this.currentCommand = this.repeatCommand;
      this.currentCommand.setDeltaTime(0);
      return this.currentCommand;
    }

    this.currentCommand.setDeltaTime(this.readDeltaTime());
    return this.currentCommand;
  }

  private int readDeltaTime() {
    int deltaTime = 0;

    while(true) {
      final int part = this.sequence.readUByte(this.sequencePosition++);

      deltaTime <<= 7;
      deltaTime |= part & 0x7f;

      if((part & 0x80) == 0) {
        return deltaTime;
      }
    }
  }

  public int getSamplesToProcess() {
    return this.samplesToProcess;
  }

  public void setSamplesToProcess(final int deltaTime) {
    if(deltaTime == 0) {
      this.samplesToProcess = 0;
      return;
    }

    final double deltaMs = deltaTime / this.sssq.getTicksPerMs();
    this.samplesToProcess = (int)(deltaMs * 44.1d);
  }

  public void tickSequence() {
    this.samplesToProcess--;
  }

  public Channel getChannel(final int channelIndex) {
    return this.sssq.getChannel(channelIndex);
  }

  public int getNrpn() {
    return this.nrpn;
  }

  public void setRepeatCount(final int repeatCount) {
    this.repeatCount = repeatCount;
  }

  public int getVolume() {
    return this.sssq.getVolume();
  }

  public int getVelocity(final int velocityIndex) {
    return this.velocityRamp[velocityIndex];
  }

  public byte[][] getBreathControls() {
    return this.breathControls;
  }

  public void setTempo(final int tempo) {
    this.sssq.setTempo(tempo);
  }

  public void dataEntryLsb(final int value) {
    switch(this.lsbType) {
      case 0x00 -> {
        this.nrpn = 0x00;
        this.repeatCount = value;
      }
      case 0x01, 0x02 -> this.nrpn = value;
    }
  }

  public void dataEntryMsb(final int nrpn) {
    if(nrpn >= 0x00 && nrpn < 0x10) {
      this.lsbType = 0x10;
      this.dataInstrumentIndex = nrpn;
    } else if(nrpn == 0x10) {
      this.lsbType = 0x01;
    } else if(nrpn == 0x14) {
      this.lsbType = 0x00;
      this.nrpn = 0x00;
      this.repeatCommand = this.previousCommand;
      this.repeatPosition = this.sequencePosition;
    } else if(nrpn == 0x1e) {
      this.lsbType = 0x00;

      if(this.repeatCount == 0x7f) {
        this.repeat = true;
      } else if(this.repeatCounter < this.repeatCount) {
        this.repeatCounter++;
        this.repeat = true;
      } else {
        this.repeatPosition = 0;
        this.repeatCounter = 0;
        this.repeat = false;
      }
    } else if(nrpn == 0x7f) {
      this.lsbType = 0;
      this.dataInstrumentIndex = 0xff;
    }
  }
}

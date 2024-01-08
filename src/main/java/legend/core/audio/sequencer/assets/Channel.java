package legend.core.audio.sequencer.assets;

import legend.game.unpacker.FileData;

public final class Channel {
  private final int index;
  private Instrument instrument;
  private int volume;
  private int pan;
  private int modulation;
  private int pitchBend;
  private int priority;
  private int breath;
  private int adjustedVolume;

  private final SoundFont soundFont;

  Channel(final FileData data, final int sssqVolume, final SoundFont soundFont) {
    this.soundFont = soundFont;

    this.index = data.readUByte(0x01);

    final int instrumentIndex = data.readByte(0x02);
    if(instrumentIndex != -1) {
      this.instrument = soundFont.getInstrument(instrumentIndex);
    }

    this.changeVolume(data.readUByte(0x03), sssqVolume);
    this.pan = data.readUByte(0x04);

    this.modulation = data.readUByte(0x09);
    this.pitchBend = data.readUByte(0x0a);
    // TODO this should probably be converted to an Enum
    this.priority = data.readUByte(0x0b);
    this.breath = data.readUByte(0x0c);

    this.adjustedVolume = data.readUByte(0x0e);
  }

  public int getIndex() {
    return this.index;
  }

  public Instrument getInstrument() {
    return this.instrument;
  }

  public void setInstrument(final int instrumentIndex) {
    this.instrument = this.soundFont.getInstrument(instrumentIndex);
  }

  public int getVolume() {
    return this.volume;
  }

  public void changeVolume(final int volume, final int sssqVolume) {
    this.volume = volume;
    this.adjustedVolume = (volume * sssqVolume) / 0x80;
  }

  public void setVolume(final int volume) {
    this.volume = volume;
  }

  public int getPan() {
    return this.pan;
  }

  public void setPan(final int pan) {
    this.pan = pan;
  }

  public int getModulation() {
    return this.modulation;
  }

  public void setModulation(final int modulation) {
    this.modulation = modulation;
  }

  public int getPitchBend() {
    return this.pitchBend;
  }

  public void setPitchBend(final int pitchBend) {
    this.pitchBend = pitchBend;
  }

  public int getPriority() {
    return this.priority;
  }
  public void setPriority(final int priority) {
    this.priority = priority;
  }

  public int getBreath() {
    return this.breath;
  }

  public void setBreath(final int breath) {
    this.breath = breath;
  }



  public int getAdjustedVolume() {
    return this.adjustedVolume;
  }

  public void setAdjustedVolume(final int adjustedVolume) {
    this.adjustedVolume = adjustedVolume;
  }

}

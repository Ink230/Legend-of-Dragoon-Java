package legend.game.types;

import legend.core.memory.types.TriConsumer;
import legend.game.scripting.TempTicker;

import javax.annotation.Nullable;

/** Holds persistent data for scripts */
public class ScriptState<T> {
  /** This script's index */
  public final int index;
  public final T innerStruct_00;
  public TriConsumer<Integer, ScriptState<T>, T> ticker_04;
  public TriConsumer<Integer, ScriptState<T>, T> renderer_08;
  public TriConsumer<Integer, ScriptState<T>, T> destructor_0c;
  /** If the callback returns non-zero, it's set to null */
  public TempTicker<T> tempTicker_10;
  /** Pointer to the script file */
  public ScriptFile scriptPtr_14;
  /** Pointer to the current script command */
  public int offset_18;
  /** Return offset for each stack frame */
  public final int[] callStack_1c = new int[10];
  /**
   * <ul>
   *   <li>5 - parent script index</li>
   *   <li>6 - child script index</li>
   *   <li>
   *     <p>7 - flags bit set - which of the pointers at the start of the struct are set</p>
   *
   *     <ul>
   *       <li>Bit 17 - {@link ScriptState#scriptPtr_14} is unset</li>
   *       <li>Bit 18 - {@link ScriptState#ticker_04} is unset</li>
   *       <li>Bit 19 - {@link ScriptState#renderer_08} is unset</li>
   *       <li>Bit 20 - Child script</li>
   *       <li>Bit 21 - Parent script</li>
   *       <li>Bit 26 - {@link ScriptState#tempTicker_10} is set (note: not sure why this is backwards from the others)</li>
   *       <li>Bit 27 - {@link ScriptState#destructor_0c} is unset</li>
   *     </ul>
   *
   *     <ul>
   *       <li>If bits 17 and 20 are not set, the script will be executed</li>
   *       <li>If bits 18 and 20 are not set, {@link ScriptState#ticker_04} will be executed</li>
   *       <li>If bits 19 and 20 are not set, {@link ScriptState#renderer_08} will be executed</li>
   *       <li>If bit 26 is set and bit 20 is not set, {@link ScriptState#tempTicker_10} will be executed</li>
   *       <li>If bits 27 and 20 are not set, {@link ScriptState#destructor_0c} will be executed</li>
   *     </ul>
   *
   *     <p>In combat this variable is used for a few different things:</p>
   *     <ul>
   *       <li>0x2 - dragoon</li>
   *       <li>0x4 - is enemy</li>
   *       <li>0x8 - it is this character's turn</li>
   *       <li>0x20 - ?</li>
   *       <li>0x40 - ?</li>
   *     </ul>
   *   </li>
   *   <li>
   *     8 - Battle Menu flag - controls which options you can choose from.
   *     <ul>
   *       <li>0x01 Attack</li>
   *       <li>0x02 Guard</li>
   *       <li>0x04 Items</li>
   *       <li>0x08 Escape</li>
   *       <li>0x10 Dragoon</li>
   *       <li>0x20 D-Attack</li>
   *       <li>0x40 Magic</li>
   *       <li>0x80 Special</li>
   *     <ul>
   *   </li>
   *   <li>9 - is dragoon</li>
   * </ul>
   */
  public final int[] storage_44 = new int[33];
  public int scriptIndex_c8;
  public int _cc;
  public int _d0;
  public int _d4;
  public int _d8;
  public int _dc;
  public int _e0;
  public int _e4;
  public int _e8;
  public int _ec;
  public int _f0;
  public int _f4;
  public String type_f8;
  public int ui_fc;

  public ScriptState(final int index, @Nullable final T innerStruct) {
    this.index = index;
    this.innerStruct_00 = innerStruct;
  }
}

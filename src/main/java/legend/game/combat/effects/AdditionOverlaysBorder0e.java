package legend.game.combat.effects;

public class AdditionOverlaysBorder0e {
  public final int index;

  /** byte */
  public boolean isVisible_00;

  /** short; 0x200 is unrotated square */
  public int angleModifier_02;
  /** ubytes */
  public int r_04;
  public int g_05;
  public int b_06;

  /** short */
  public int size_08;
  /** short; Counts from start of addition, not hit */
  public int framesUntilRender_0a;
  /** byte */
  public int countFramesVisible_0c;
  /** byte; -1 = no translucency, 0 = no translucency + shadow, 1+ = no side effect */
  public int sideEffects_0d;

  public AdditionOverlaysBorder0e(final int index) {
    this.index = index;
  }
}

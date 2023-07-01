package legend.game.combat.effects;

import legend.core.gte.TmdObjTable1c;
import legend.core.gte.VECTOR;
import legend.core.memory.types.QuadConsumer;
import legend.core.memory.types.TriConsumer;
import legend.game.scripting.ScriptState;

public class ParticleEffectData98 implements BttlScriptData6cSubBase1 {
  public ScriptState<EffectManagerData6c> myState_00;
  /** Can be -1 */
  public int parentScriptIndex_04;
  public final EffectData98Inner24 _08 = new EffectData98Inner24();

  public TmdObjTable1c tmd_30;
  public short halfW_34;
  public short halfH_36;

  /** ushort */
  public int countParticleInstance_50;
  /** ushort */
  public int countFramesRendered_52;
  /** ushort */
  public int countParticleSub_54;
  /** ushort */
  public int tpage_56;
  /** ushort */
  public int u_58;
  /** ushort */
  public int v_5a;
  /** ushort */
  public int clut_5c;
  /** ubyte */
  public int w_5e;
  /** ubyte */
  public int h_5f;
  /** Some kind of effect type flag or something */
  public byte _60;
  public byte callback90Type_61;

  /** Size in bytes of following array of structs */
  // public int size_64;
  public ParticleEffectInstance94[] particleArray_68;
  /** Binary flag to control whether to recalculate a vector (usually vec_70) */
  public byte _6c;

  public final VECTOR vec_70 = new VECTOR();
  /** Something that is used to modify vec_70 */
  public int _80;
  public TriConsumer<EffectManagerData6c, ParticleEffectData98, ParticleEffectInstance94> callback_84;
  public QuadConsumer<ScriptState<EffectManagerData6c>, EffectManagerData6c, ParticleEffectData98, ParticleEffectInstance94> callback_88;
  public QuadConsumer<EffectManagerData6c, ParticleEffectData98, ParticleEffectInstance94, EffectData98Inner24> callback_8c;
  public QuadConsumer<ScriptState<EffectManagerData6c>, EffectManagerData6c, ParticleEffectData98, ParticleEffectInstance94> callback_90;
  /** Child particle effect? */
  public ParticleEffectData98 _94;
}

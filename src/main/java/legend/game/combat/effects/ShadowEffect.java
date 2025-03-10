package legend.game.combat.effects;

import legend.core.MathHelper;
import legend.core.gte.MV;
import legend.core.memory.Method;
import legend.game.scripting.ScriptState;

import static legend.game.Scus94491BpeSegment.tmdGp0Tpage_1f8003ec;
import static legend.game.Scus94491BpeSegment.zOffset_1f8003e8;
import static legend.game.Scus94491BpeSegment_800b.shadowModel_800bda10;
import static legend.game.combat.SEffe.FUN_800e60e0;
import static legend.game.combat.SEffe.FUN_800e6170;
import static legend.game.combat.SEffe.calculateEffectTransforms;
import static legend.game.combat.SEffe.renderTmdSpriteEffect;

public class ShadowEffect implements Effect<EffectManagerParams.VoidType> {
  @Override
  public void tick(final ScriptState<EffectManagerData6c<EffectManagerParams.VoidType>> state) {

  }

  /** Uses ctmd render pipeline */
  @Override
  @Method(0x80118790L)
  public void render(final ScriptState<EffectManagerData6c<EffectManagerParams.VoidType>> state) {
    final EffectManagerData6c<EffectManagerParams.VoidType> manager = state.innerStruct_00;

    if(manager.params_10.flags_00 >= 0) { // No errors
      final float y = manager.params_10.trans_04.y;
      manager.params_10.trans_04.y = 0.0f;
      final MV transforms = new MV();
      calculateEffectTransforms(transforms, manager);
      transforms.transfer.y = y;
      manager.params_10.trans_04.y = y;

      final float rotY = MathHelper.atan2(-transforms.m02, transforms.m00);
      transforms.rotateY(-rotY);
      transforms.rotateZ(-MathHelper.atan2(transforms.m01, transforms.m00));
      transforms.rotateX(-MathHelper.atan2(-transforms.m21, transforms.m22));
      transforms.rotateY(rotY);
      transforms.m01 = 0.0f;
      transforms.m11 = 0.0f;
      transforms.m21 = 0.0f;
      transforms.transfer.y -= 0.05f; // Fix Z-fighting with ground
      tmdGp0Tpage_1f8003ec = manager.params_10.flags_00 >>> 23 & 0x60;
      zOffset_1f8003e8 = manager.params_10.z_22;
      FUN_800e60e0((manager.params_10.colour_1c.x << 5) / (float)0x1000, (manager.params_10.colour_1c.y << 5) / (float)0x1000, (manager.params_10.colour_1c.z << 5) / (float)0x1000);
      renderTmdSpriteEffect(shadowModel_800bda10.modelParts_00[0].tmd_08, shadowModel_800bda10.modelParts_00[0].obj, manager.params_10, transforms);
      FUN_800e6170();
    }

    //LAB_801188d8
  }

  @Override
  public void destroy(final ScriptState<EffectManagerData6c<EffectManagerParams.VoidType>> state) {

  }
}

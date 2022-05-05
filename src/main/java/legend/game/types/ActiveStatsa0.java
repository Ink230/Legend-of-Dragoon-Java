package legend.game.types;

import legend.core.memory.Value;
import legend.core.memory.types.ArrayRef;
import legend.core.memory.types.ByteRef;
import legend.core.memory.types.MemoryRef;
import legend.core.memory.types.ShortRef;
import legend.core.memory.types.UnsignedByteRef;
import legend.core.memory.types.UnsignedIntRef;
import legend.core.memory.types.UnsignedShortRef;

public class ActiveStatsa0 implements MemoryRef {
  private final Value ref;

  public final UnsignedIntRef xp_00;
  public final UnsignedShortRef hp_04;
  public final UnsignedShortRef mp_06;
  public final UnsignedShortRef sp_08;
  public final UnsignedShortRef _0a;
  public final UnsignedShortRef _0c;
  public final UnsignedByteRef level_0e;
  public final UnsignedByteRef dlevel_0f;

  public final ArrayRef<ByteRef> equipment_30;
  public final ByteRef selectedAddition_35;
  public final ArrayRef<UnsignedByteRef> additionLevels_36;
  public final ArrayRef<UnsignedByteRef> additionXp_3e;
  public final UnsignedShortRef _46;
  public final UnsignedShortRef _48;
  public final UnsignedShortRef _4a;
  public final UnsignedShortRef _4c;
  public final UnsignedShortRef _4e;
  public final UnsignedShortRef _50;
  public final UnsignedShortRef _52;
  public final UnsignedShortRef _54;
  public final UnsignedShortRef _56;
  public final UnsignedShortRef _58;
  public final UnsignedShortRef _5a;
  public final UnsignedShortRef _5c;
  public final UnsignedShortRef _5e;
  public final UnsignedShortRef _60;
  public final ShortRef _62;
  public final ShortRef _64;
  public final UnsignedShortRef maxHp_66;
  public final UnsignedByteRef _68;
  public final UnsignedByteRef bodySpeed_69;
  public final UnsignedByteRef bodyAttack_6a;
  public final UnsignedByteRef bodyMagicAttack_6b;
  public final UnsignedByteRef bodyDefence_6c;
  public final UnsignedByteRef bodyMagicDefence_6d;
  public final UnsignedShortRef maxMp_6e;
  public final UnsignedByteRef _70;
  public final UnsignedByteRef _71;
  public final UnsignedByteRef dragoonAttack_72;
  public final UnsignedByteRef dragoonMagicAttack_73;
  public final UnsignedByteRef dragoonDefence_74;
  public final UnsignedByteRef dragoonMagicDefence_75;
  public final UnsignedByteRef _76;
  public final UnsignedByteRef _77;
  public final UnsignedByteRef _78;
  public final UnsignedByteRef _79;
  public final UnsignedByteRef _7a;
  public final UnsignedByteRef _7b;
  public final UnsignedByteRef _7c;
  public final UnsignedByteRef _7d;
  public final UnsignedByteRef _7e;
  public final UnsignedByteRef _7f;
  public final UnsignedByteRef _80;
  public final UnsignedByteRef _81;
  public final UnsignedByteRef _82;
  public final UnsignedByteRef _83;
  public final UnsignedByteRef _84;

  public final ShortRef gearSpeed_86;
  public final ShortRef gearAttack_88;
  public final ShortRef gearMagicAttack_8a;
  public final ShortRef gearDefence_8c;
  public final ShortRef gearMagicDefence_8e;
  public final ShortRef attackHit_90;
  public final ShortRef magicHit_92;
  public final ShortRef attackAvoid_94;
  public final ShortRef magicAvoid_96;
  public final UnsignedByteRef _98;
  public final UnsignedByteRef _99;
  public final UnsignedByteRef _9a;
  public final UnsignedByteRef _9b;
  public final UnsignedShortRef _9c;
  public final UnsignedByteRef _9e;
  public final UnsignedByteRef _9f;

  public ActiveStatsa0(final Value ref) {
    this.ref = ref;

    this.xp_00 = ref.offset(4, 0x00L).cast(UnsignedIntRef::new);
    this.hp_04 = ref.offset(2, 0x04L).cast(UnsignedShortRef::new);
    this.mp_06 = ref.offset(2, 0x06L).cast(UnsignedShortRef::new);
    this.sp_08 = ref.offset(2, 0x08L).cast(UnsignedShortRef::new);
    this._0a = ref.offset(2, 0x0aL).cast(UnsignedShortRef::new);
    this._0c = ref.offset(2, 0x0cL).cast(UnsignedShortRef::new);
    this.level_0e = ref.offset(1, 0x0eL).cast(UnsignedByteRef::new);
    this.dlevel_0f = ref.offset(1, 0x0fL).cast(UnsignedByteRef::new);

    this.equipment_30 = ref.offset(1, 0x30L).cast(ArrayRef.of(ByteRef.class, 5, 1, ByteRef::new));
    this.selectedAddition_35 = ref.offset(1, 0x35L).cast(ByteRef::new);
    this.additionLevels_36 = ref.offset(1, 0x36L).cast(ArrayRef.of(UnsignedByteRef.class, 8, 1, UnsignedByteRef::new));
    this.additionXp_3e = ref.offset(1, 0x3eL).cast(ArrayRef.of(UnsignedByteRef.class, 8, 1, UnsignedByteRef::new));
    this._46 = ref.offset(2, 0x46L).cast(UnsignedShortRef::new);
    this._48 = ref.offset(2, 0x48L).cast(UnsignedShortRef::new);
    this._4a = ref.offset(2, 0x4aL).cast(UnsignedShortRef::new);
    this._4c = ref.offset(2, 0x4cL).cast(UnsignedShortRef::new);
    this._4e = ref.offset(2, 0x4eL).cast(UnsignedShortRef::new);
    this._50 = ref.offset(2, 0x50L).cast(UnsignedShortRef::new);
    this._52 = ref.offset(2, 0x52L).cast(UnsignedShortRef::new);
    this._54 = ref.offset(2, 0x54L).cast(UnsignedShortRef::new);
    this._56 = ref.offset(2, 0x56L).cast(UnsignedShortRef::new);
    this._58 = ref.offset(2, 0x58L).cast(UnsignedShortRef::new);
    this._5a = ref.offset(2, 0x5aL).cast(UnsignedShortRef::new);
    this._5c = ref.offset(2, 0x5cL).cast(UnsignedShortRef::new);
    this._5e = ref.offset(2, 0x5eL).cast(UnsignedShortRef::new);
    this._60 = ref.offset(2, 0x60L).cast(UnsignedShortRef::new);
    this._62 = ref.offset(2, 0x62L).cast(ShortRef::new);
    this._64 = ref.offset(2, 0x64L).cast(ShortRef::new);
    this.maxHp_66 = ref.offset(2, 0x66L).cast(UnsignedShortRef::new);
    this._68 = ref.offset(1, 0x68L).cast(UnsignedByteRef::new);
    this.bodySpeed_69 = ref.offset(1, 0x69L).cast(UnsignedByteRef::new);
    this.bodyAttack_6a = ref.offset(1, 0x6aL).cast(UnsignedByteRef::new);
    this.bodyMagicAttack_6b = ref.offset(1, 0x6bL).cast(UnsignedByteRef::new);
    this.bodyDefence_6c = ref.offset(1, 0x6cL).cast(UnsignedByteRef::new);
    this.bodyMagicDefence_6d = ref.offset(1, 0x6dL).cast(UnsignedByteRef::new);
    this.maxMp_6e = ref.offset(2, 0x6eL).cast(UnsignedShortRef::new);
    this._70 = ref.offset(1, 0x70L).cast(UnsignedByteRef::new);
    this._71 = ref.offset(1, 0x71L).cast(UnsignedByteRef::new);
    this.dragoonAttack_72 = ref.offset(1, 0x72L).cast(UnsignedByteRef::new);
    this.dragoonMagicAttack_73 = ref.offset(1, 0x73L).cast(UnsignedByteRef::new);
    this.dragoonDefence_74 = ref.offset(1, 0x74L).cast(UnsignedByteRef::new);
    this.dragoonMagicDefence_75 = ref.offset(1, 0x75L).cast(UnsignedByteRef::new);
    this._76 = ref.offset(1, 0x76L).cast(UnsignedByteRef::new);
    this._77 = ref.offset(1, 0x77L).cast(UnsignedByteRef::new);
    this._78 = ref.offset(1, 0x78L).cast(UnsignedByteRef::new);
    this._79 = ref.offset(1, 0x79L).cast(UnsignedByteRef::new);
    this._7a = ref.offset(1, 0x7aL).cast(UnsignedByteRef::new);
    this._7b = ref.offset(1, 0x7bL).cast(UnsignedByteRef::new);
    this._7c = ref.offset(1, 0x7cL).cast(UnsignedByteRef::new);
    this._7d = ref.offset(1, 0x7dL).cast(UnsignedByteRef::new);
    this._7e = ref.offset(1, 0x7eL).cast(UnsignedByteRef::new);
    this._7f = ref.offset(1, 0x7fL).cast(UnsignedByteRef::new);
    this._80 = ref.offset(1, 0x80L).cast(UnsignedByteRef::new);
    this._81 = ref.offset(1, 0x81L).cast(UnsignedByteRef::new);
    this._82 = ref.offset(1, 0x82L).cast(UnsignedByteRef::new);
    this._83 = ref.offset(1, 0x83L).cast(UnsignedByteRef::new);
    this._84 = ref.offset(1, 0x84L).cast(UnsignedByteRef::new);

    this.gearSpeed_86 = ref.offset(2, 0x86L).cast(ShortRef::new);
    this.gearAttack_88 = ref.offset(2, 0x88L).cast(ShortRef::new);
    this.gearMagicAttack_8a = ref.offset(2, 0x8aL).cast(ShortRef::new);
    this.gearDefence_8c = ref.offset(2, 0x8cL).cast(ShortRef::new);
    this.gearMagicDefence_8e = ref.offset(2, 0x8eL).cast(ShortRef::new);
    this.attackHit_90 = ref.offset(2, 0x90L).cast(ShortRef::new);
    this.magicHit_92 = ref.offset(2, 0x92L).cast(ShortRef::new);
    this.attackAvoid_94 = ref.offset(2, 0x94L).cast(ShortRef::new);
    this.magicAvoid_96 = ref.offset(2, 0x96L).cast(ShortRef::new);
    this._98 = ref.offset(1, 0x98L).cast(UnsignedByteRef::new);
    this._99 = ref.offset(1, 0x99L).cast(UnsignedByteRef::new);
    this._9a = ref.offset(1, 0x9aL).cast(UnsignedByteRef::new);
    this._9b = ref.offset(1, 0x9bL).cast(UnsignedByteRef::new);
    this._9c = ref.offset(2, 0x9cL).cast(UnsignedShortRef::new);
    this._9e = ref.offset(1, 0x9eL).cast(UnsignedByteRef::new);
    this._9f = ref.offset(1, 0x9fL).cast(UnsignedByteRef::new);
  }

  @Override
  public long getAddress() {
    return this.ref.getAddress();
  }
}

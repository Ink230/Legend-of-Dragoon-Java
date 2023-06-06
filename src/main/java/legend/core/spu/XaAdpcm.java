package legend.core.spu;

import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.bytes.ByteList;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import it.unimi.dsi.fastutil.shorts.ShortList;
import legend.core.MathHelper;
import legend.core.memory.Ref;

import java.util.List;

public final class XaAdpcm {
  private XaAdpcm() { }

  private static final int BYTES_PER_HEADER = 24;

  private static short oldL;
  private static short olderL;
  private static short oldR;
  private static short olderR;

  private static final int[] positiveXaAdpcmTable = { 0, 60, 115, 98, 122 };
  private static final int[] negativeXaAdpcmTable = { 0, 0, -52, -55, -60 };

  public static byte[] decode(final byte[] xaadpcm, final byte codingInfo) {
    final ByteList decoded = new ByteArrayList();

    final ShortList l = new ShortArrayList();
    final ShortList r = new ShortArrayList();

    final boolean isStereo = (codingInfo & 0x1) != 0;
    final boolean is18900hz = (codingInfo >>> 2 & 0x1) != 0;
    final boolean is8BitPerSample = (codingInfo >>> 4 & 0x1) != 0;

    int position = BYTES_PER_HEADER; //Skip sync, header and subheader
    for(int i = 0; i < 18; i++) { //Each sector consists of 12h 128-byte portions (=900h bytes) (the remaining 14h bytes of the sectors 914h-byte data region are 00h filled).
      for(int blk = 0; blk < 4; blk++) {
        final Ref<Short> oldLRef = new Ref<>(oldL);
        final Ref<Short> oldRRef = new Ref<>(oldR);
        final Ref<Short> olderLRef = new Ref<>(olderL);
        final Ref<Short> olderRRef = new Ref<>(olderR);

        l.addAll(decodeNibbles(xaadpcm, position, blk, 0, oldLRef, olderLRef));

        if(isStereo) {
          r.addAll(decodeNibbles(xaadpcm, position, blk, 1, oldRRef, olderRRef));
        } else {
          l.addAll(decodeNibbles(xaadpcm, position, blk, 1, oldLRef, olderLRef));
        }

        oldL = oldLRef.get();
        oldR = oldRRef.get();
        olderL = olderLRef.get();
        olderR = olderRRef.get();
      }

      position += 128;
    }

    if(isStereo) {
      for(int sample = 0; sample < l.size(); sample++) {
        decoded.add((byte)l.getShort(sample));
        decoded.add((byte)(l.getShort(sample) >> 8));
        decoded.add((byte)r.getShort(sample));
        decoded.add((byte)(r.getShort(sample) >> 8));
      }
    } else {
      for(int sample = 0; sample < l.size(); sample++) {
        //duplicating because out output expects Stereo
        decoded.add((byte)l.getShort(sample));
        decoded.add((byte)(l.getShort(sample) >> 8));
        decoded.add((byte)l.getShort(sample));
        decoded.add((byte)(l.getShort(sample) >> 8));
      }
    }

    return decoded.toByteArray();
  }

  public static ShortList decodeNibbles(final byte[] xaapdcm, final int position, final int blk, final int nibble, final Ref<Short> old, final Ref<Short> older) {
    final ShortList list = new ShortArrayList();

    final int shift = 12 - (xaapdcm[position + 4 + blk * 2 + nibble] & 0x0F);
    final int filter = (xaapdcm[position + 4 + blk * 2 + nibble] & 0x30) >> 4;

    final int f0 = positiveXaAdpcmTable[filter];
    final int f1 = negativeXaAdpcmTable[filter];

    for(int i = 0; i < 28; i++) {
      final int t = signed4bit((byte)(xaapdcm[position + 16 + blk + i * 4] >> nibble * 4 & 0x0F));
      final int s = (t << shift) + (old.get() * f0 + older.get() * f1 + 32) / 64;
      final short sample = (short)MathHelper.clamp(s, -0x8000, 0x7FFF);

      list.add(sample);
      older.set(old.get());
      old.set(sample);
    }

    return list;
  }

  public static int signed4bit(final byte value) {
    return value << 28 >> 28;
  }
}

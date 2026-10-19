package legend.game.submap;

import org.joml.Vector3f;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubmapCollisionContractsTest {
  @Test
  void customQueriesDoNotRequireRetailGeometryOrLimitPrimitiveIndices() {
    final CollisionGeometry geometry = new CollisionGeometry(null);
    final RecordingCollision collision = new RecordingCollision();
    geometry.setCustomCollision(collision);

    assertEquals(101, geometry.getCollisionPrimitiveAtPoint(2.0f, 3.0f, 4.0f, false, true));
    assertEquals(new Vector3f(2.0f, 3.0f, 4.0f), collision.position);
    assertFalse(collision.checkSteepness);
    assertTrue(collision.checkY);

    assertEquals(102, geometry.getClosestCollisionPrimitive(5.0f, 6.0f, 7.0f));
    assertEquals(new Vector3f(5.0f, 6.0f, 7.0f), collision.position);

    final Vector3f center = new Vector3f(-1.0f);
    geometry.getMiddleOfCollisionPrimitive(103, center);
    assertEquals(103, collision.centerIndex);
    assertEquals(new Vector3f(8.0f, 9.0f, 10.0f), center);
  }

  @Test
  void customFlagsUseExplicitIndicesAndLegacyPackedSetterStillDecodesItsIndex() {
    final CollisionGeometry geometry = new CollisionGeometry(null);
    geometry.setCollisionAndTransitionInfo(4, 0x20);
    final int retailFlags = geometry.getCollisionAndTransitionInfo(4);
    final RecordingCollision collision = new RecordingCollision();
    geometry.setCustomCollision(collision);

    geometry.setCollisionAndTransitionInfo(1000, 0x20);
    geometry.addBlocker(1000);
    assertEquals(0x28, geometry.getCollisionAndTransitionInfo(1000));
    geometry.removeBlocker(1000);
    geometry.addDoor(1000, 3, 2);
    assertEquals(0xc2_0030, geometry.getCollisionAndTransitionInfo(1000));
    geometry.removeDoor(1000);
    assertEquals(0x20, geometry.getCollisionAndTransitionInfo(1000));

    final int packed = 0x1234_3508;
    geometry.setCollisionAndTransitionInfo(packed);
    assertEquals(packed, collision.flags.get(13));
    assertEquals(0, geometry.getCollisionAndTransitionInfo(-1));

    geometry.clearCollisionAndTransitionInfo();
    assertTrue(collision.flags.isEmpty());
    geometry.setCustomCollision(null);
    assertEquals(retailFlags, geometry.getCollisionAndTransitionInfo(4));
  }

  @Test
  void retailFlagsKeepPackedAndIndexedSetterSemantics() {
    final CollisionGeometry geometry = new CollisionGeometry(null);
    final int packed = 0x1234_3508;
    geometry.setCollisionAndTransitionInfo(packed);
    assertEquals(packed, geometry.getCollisionAndTransitionInfo(13));

    geometry.setCollisionAndTransitionInfo(13, packed);
    assertEquals(0x1234_3408, geometry.getCollisionAndTransitionInfo(13));
    assertEquals(0, geometry.getCollisionAndTransitionInfo(-1));
    assertEquals(0, geometry.getCollisionAndTransitionInfo(64));
    geometry.clearCollisionAndTransitionInfo();
    assertEquals(0, geometry.getCollisionAndTransitionInfo(13));
  }

  @Test
  void replacementAndUnloadCloseEachOwnedBackendOnce() {
    final CollisionGeometry geometry = new CollisionGeometry(null);
    final RecordingCollision first = new RecordingCollision();
    first.onClose = () -> assertFalse(geometry.hasCustomCollision());
    final RecordingCollision second = new RecordingCollision();
    geometry.setCustomCollision(first);
    geometry.setCustomCollision(first);
    assertEquals(0, first.closeCount);

    geometry.setCustomCollision(second);
    assertEquals(1, first.closeCount);
    assertEquals(0, second.closeCount);
    assertTrue(geometry.hasCustomCollision());

    geometry.unloadCollision();
    geometry.unloadCollision();
    assertFalse(geometry.hasCustomCollision());
    assertEquals(1, first.closeCount);
    assertEquals(1, second.closeCount);
  }

  @Test
  void failedCloseDetachesBackendAndUnloadStillClearsRetailDebugData() {
    final CollisionGeometry geometry = new CollisionGeometry(null);
    final RecordingCollision collision = new RecordingCollision();
    final IllegalStateException failure = new IllegalStateException("backend close failed");
    collision.onClose = () -> { throw failure; };
    geometry.setCustomCollision(collision);
    geometry.debugVertices = new Vector3f[] {new Vector3f()};

    assertSame(failure, assertThrows(IllegalStateException.class, geometry::unloadCollision));
    assertFalse(geometry.hasCustomCollision());
    assertEquals(null, geometry.debugVertices);
    geometry.unloadCollision();
    assertEquals(1, collision.closeCount);
  }

  @Test
  void tickAndDebugHooksKeepRotationBookkeepingEvenWhenBackendTickFails() {
    final CollisionGeometry geometry = new CollisionGeometry(null);
    final RecordingCollision collision = new RecordingCollision();
    geometry.setCustomCollision(collision);
    geometry.playerRotationWasUpdated_800d1a8c = 2;

    geometry.tick();
    geometry.drawCustomCollisionDebug();
    assertEquals(1, geometry.playerRotationWasUpdated_800d1a8c);
    assertEquals(1, collision.tickCount);
    assertEquals(1, collision.drawCount);

    collision.onTick = () -> { throw new IllegalStateException("backend tick failed"); };
    assertThrows(IllegalStateException.class, geometry::tick);
    assertEquals(0, geometry.playerRotationWasUpdated_800d1a8c);
    geometry.setCustomCollision(null);
    geometry.drawCustomCollisionDebug();
    assertEquals(1, collision.drawCount);
  }

  private static final class RecordingCollision implements SubmapCollision {
    private final Vector3f position = new Vector3f();
    private final Map<Integer, Integer> flags = new HashMap<>();
    private boolean checkSteepness;
    private boolean checkY;
    private int centerIndex;
    private int tickCount;
    private int closeCount;
    private int drawCount;
    private Runnable onTick = () -> { };
    private Runnable onClose = () -> { };

    @Override
    public int move(final float x, final float y, final float z, final Vector3f movement) {
      throw new AssertionError("Movement requires an active SMap and is verified by the engine runtime checks");
    }

    @Override
    public int find(final float x, final float y, final float z, final boolean checkSteepness, final boolean checkY) {
      this.position.set(x, y, z);
      this.checkSteepness = checkSteepness;
      this.checkY = checkY;
      return 101;
    }

    @Override
    public int closest(final float x, final float y, final float z) {
      this.position.set(x, y, z);
      return 102;
    }

    @Override
    public void center(final int index, final Vector3f out) {
      this.centerIndex = index;
      out.set(8.0f, 9.0f, 10.0f);
    }

    @Override
    public int getFlags(final int index) {
      return this.flags.getOrDefault(index, 0);
    }

    @Override
    public void setFlags(final int index, final int flags) {
      this.flags.put(index, flags);
    }

    @Override
    public void clearFlags() {
      this.flags.clear();
    }

    @Override
    public void tick() {
      this.tickCount++;
      this.onTick.run();
    }

    @Override
    public void close() {
      this.closeCount++;
      this.onClose.run();
    }

    @Override
    public void drawDebug() {
      this.drawCount++;
    }
  }
}

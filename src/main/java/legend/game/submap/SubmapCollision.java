package legend.game.submap;

import org.joml.Vector3f;

/**
 * Collision backend owned by a submap. Indices are local to this backend and are not limited to the retail 64-entry flag table.
 * All positions use submap world coordinates.
 */
public interface SubmapCollision {
  /**
   * Resolves movement from {@code (x, y, z)}. The vector's X/Z components are movement deltas; on success they contain the
   * allowed deltas and Y contains the absolute destination floor height, not a delta.
   *
   * @return the destination primitive index, or {@code -1} when movement cannot be resolved
   */
  int move(float x, float y, float z, Vector3f movement);

  /** @return the primitive containing the point, or {@code -1} when none satisfies the height and steepness checks */
  int find(float x, float y, float z, boolean checkSteepness, boolean checkY);

  /** @return the nearest primitive index, or {@code -1} when the backend has no primitives */
  int closest(float x, float y, float z);

  /** Writes the primitive's center to {@code out}, or zero when the index is invalid. */
  void center(int index, Vector3f out);

  /** @return collision/transition flags using {@link CollisionGeometry#getCollisionAndTransitionInfo(int)}, or zero for an invalid index */
  int getFlags(int index);

  /** Sets collision/transition flags for the explicit index; implementations must not decode an index from the flags. */
  void setFlags(int index, int flags);

  void clearFlags();

  default void tick() { }

  /** Releases backend resources when replaced or unloaded. */
  default void close() { }

  default void drawDebug() { }
}

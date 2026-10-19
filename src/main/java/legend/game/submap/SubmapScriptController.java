package legend.game.submap;

import legend.game.scripting.ScriptState;
import legend.game.scripting.ScriptedObject;

/**
 * Optional behavior owner for a submap whose controller and actors do not run legacy script files.
 * Actor state still belongs to SC so models, movement callbacks, and engine commands remain usable.
 * All callbacks run on the engine thread and belong to the active submap lifetime.
 */
public interface SubmapScriptController extends AutoCloseable {
  /** Called once after actors, transition data, and the destination spawn are ready. */
  default void loaded() { }

  /** Runs before the script manager each rendered frame, including while scripts are paused. */
  default void beforeFrame() { }

  /** Whether simulation performed by submap rendering should remain frozen for this frame. */
  default boolean simulationPaused() { return false; }

  /** Advances at the script cadence while the script manager is running. */
  void tick(ScriptState<ScriptedObject> controller);

  /** Cancels pending behavior before actor destruction; also called on failed loading. */
  @Override
  default void close() { }
}

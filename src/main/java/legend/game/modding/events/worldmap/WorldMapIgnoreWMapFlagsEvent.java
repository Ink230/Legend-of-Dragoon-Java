package legend.game.modding.events.worldmap;

import legend.game.modding.events.engine.InGameEvent;
import legend.game.types.GameState52c;
import legend.game.wmap.WMap;

/**
 * Fired during world-map initialization to configure location availability checks.
 * Listeners may set {@link #ignoreWMapFlags} to bypass world-map location flags.
 * Other location availability checks still apply.
 */
public class WorldMapIgnoreWMapFlagsEvent extends InGameEvent<WMap> implements WorldMapEvent {
  public boolean ignoreWMapFlags;

  public WorldMapIgnoreWMapFlagsEvent(final WMap engineState, final GameState52c gameState, final boolean ignoreWMapFlags) {
    super(engineState, gameState);

    this.ignoreWMapFlags = ignoreWMapFlags;
  }
}

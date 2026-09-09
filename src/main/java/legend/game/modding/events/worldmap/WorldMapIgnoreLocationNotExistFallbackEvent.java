package legend.game.modding.events.worldmap;

import legend.game.modding.events.engine.InGameEvent;
import legend.game.types.GameState52c;
import legend.game.wmap.WMap;

/**
 * Fired during world-map initialization to configure the starting-location fallback.
 * Listeners may set {@link #ignoreLocationNotExistFallback} to keep a known location
 * whose world-map flag is unset. An unknown location still falls back to Hellena.
 */
public class WorldMapIgnoreLocationNotExistFallbackEvent extends InGameEvent<WMap> implements WorldMapEvent {
  public boolean ignoreLocationNotExistFallback;

  public WorldMapIgnoreLocationNotExistFallbackEvent(final WMap engineState, final GameState52c gameState, final boolean ignoreLocationNotExistFallback) {
    super(engineState, gameState);

    this.ignoreLocationNotExistFallback = ignoreLocationNotExistFallback;
  }
}

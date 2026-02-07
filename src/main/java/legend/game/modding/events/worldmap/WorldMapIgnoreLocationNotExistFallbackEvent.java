package legend.game.modding.events.worldmap;

import legend.game.modding.events.engine.InGameEvent;
import legend.game.types.GameState52c;
import legend.game.wmap.WMap;

public class WorldMapIgnoreLocationNotExistFallbackEvent extends InGameEvent<WMap> implements WorldMapEvent {
  public boolean ignoreLocationNotExistFallback;

  public WorldMapIgnoreLocationNotExistFallbackEvent(final WMap engineState, final GameState52c gameState, final boolean ignoreLocationNotExistFallback) {
    super(engineState, gameState);
    this.ignoreLocationNotExistFallback = ignoreLocationNotExistFallback;
  }
}

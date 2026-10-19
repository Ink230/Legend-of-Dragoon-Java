package legend.game.saves;

import legend.game.EngineState;
import legend.game.types.GameState52c;
import legend.lodmod.LodEngineStateTypes;
import org.legendofdragoon.modloader.registries.RegistryEntry;

public abstract class CampaignType extends RegistryEntry {
  /** The initial save must use the campaign's state rather than always assuming a retail submap. */
  public EngineState<?> initialEngineState(final GameState52c gameState) {
    return LodEngineStateTypes.SUBMAP.get().constructor_00.get();
  }

  public abstract void setUpNewCampaign(final GameState52c gameState);
  public abstract void transitionToNewCampaign(final GameState52c gameState);

  public abstract void setUpLoadedGame(final GameState52c gameState);
  public abstract void transitionToLoadedGame(final GameState52c gameState);
}

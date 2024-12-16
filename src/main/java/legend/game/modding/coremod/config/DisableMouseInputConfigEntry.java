package legend.game.modding.coremod.config;

import legend.game.input.Input;
import legend.game.saves.BoolConfigEntry;
import legend.game.saves.ConfigCategory;
import legend.game.saves.ConfigCollection;
import legend.game.saves.ConfigStorageLocation;

import static legend.core.GameEngine.RENDERER;

public class DisableMouseInputConfigEntry extends BoolConfigEntry {
  public DisableMouseInputConfigEntry() {
    super(false, ConfigStorageLocation.GLOBAL, ConfigCategory.CONTROLS);
  }

  @Override
  public void onChange(final ConfigCollection configCollection, final Boolean oldValue, final Boolean newValue) {
    if(newValue && !Input.getController().getGuid().isEmpty()) {
      RENDERER.window().hideCursor();
    } else {
      RENDERER.window().showCursor();
    }
  }
}
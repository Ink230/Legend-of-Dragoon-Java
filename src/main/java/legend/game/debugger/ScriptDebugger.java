package legend.game.debugger;

import legend.core.opengl.Camera;
import legend.core.opengl.Context;
import legend.core.opengl.Font;
import legend.core.opengl.GuiManager;
import legend.core.opengl.Window;

import java.io.IOException;

public class ScriptDebugger {
  private final Window window;
  private final Context ctx;
  private final GuiManager guiManager;

  private final ScriptDebuggerGui gui;

  public ScriptDebugger() throws IOException {
    this.window = new Window("Script Debugger", 728, 600);
    this.ctx = new Context(this.window, new Camera(0.0f, 0.0f));

    this.guiManager = new GuiManager(this.window);
    this.window.setEventPoller(this.guiManager::captureInput);

    final Font font = new Font("gfx/fonts/Consolas.ttf");
    this.guiManager.setFont(font);

    this.gui = new ScriptDebuggerGui();
    this.guiManager.pushGui(this.gui);

    this.ctx.onDraw(() -> this.guiManager.draw(this.ctx.getWidth(), this.ctx.getHeight(), this.ctx.getWidth() / this.window.getScale(), this.ctx.getHeight() / this.window.getScale()));

    this.window.show();
    this.window.run();

    this.guiManager.free();
    font.free();
  }
}

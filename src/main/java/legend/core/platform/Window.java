package legend.core.platform;

import legend.core.platform.input.InputClass;
import legend.core.renderer.RenderApi;

import java.nio.file.Path;

public abstract class Window {
  private final WindowEvents events = new WindowEvents(this);
  private int minimumWidth = 320;
  private int minimumHeight = 240;

  public abstract RenderApi getRenderApi();

  protected abstract void destroy();
  protected abstract boolean shouldClose();

  public WindowEvents events() {
    return this.events;
  }

  public abstract void show();
  public abstract void close();

  public abstract void updateMonitor();
  public abstract void makeFullscreen();
  public abstract void makeWindowed();
  public abstract void centerWindow();

  public abstract void setTitle(final String title);

  public abstract int getWidth();
  public abstract int getHeight();
  public int getMinimumWidth() { return this.minimumWidth; }
  public int getMinimumHeight() { return this.minimumHeight; }

  /** Sets the platform window's minimum client size when supported. */
  public void setMinimumSize(final int width, final int height) {
    if(width <= 0 || height <= 0) throw new IllegalArgumentException("Window minimum size must be positive");
    this.minimumWidth = width;
    this.minimumHeight = height;
  }

  public abstract boolean hasFocus();

  public abstract InputClass getInputClass();
  public abstract void startTextInput();
  public abstract void stopTextInput();

  public abstract void disableCursor();
  public abstract void showCursor();
  public abstract void hideCursor();
  public abstract void useNormalCursor();
  public abstract void usePointerCursor();
  public abstract void setWindowIcon(final Path path);

  public abstract void setFpsLimit(final int limit);
  public abstract int getFpsLimit();
}

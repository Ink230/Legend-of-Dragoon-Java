package legend.game.title;

import legend.core.gpu.Rect4i;
import legend.core.memory.Method;
import legend.game.EngineState;
import legend.game.EngineStateEnum;
import legend.game.Scus94491BpeSegment_8002;
import legend.game.input.Input;
import legend.game.input.InputAction;
import legend.game.types.McqHeader;
import legend.game.unpacker.FileData;
import legend.game.unpacker.Unpacker;

import static legend.core.GameEngine.GPU;
import static legend.core.GameEngine.MODS;
import static legend.core.GameEngine.bootMods;
import static legend.game.Scus94491BpeSegment.loadDrgnFile;
import static legend.game.Scus94491BpeSegment.renderMcq;
import static legend.game.Scus94491BpeSegment.resizeDisplay;
import static legend.game.Scus94491BpeSegment.startFadeEffect;
import static legend.game.Scus94491BpeSegment_8002.FUN_8002a9c0;
import static legend.game.Scus94491BpeSegment_8002.deallocateRenderables;
import static legend.game.Scus94491BpeSegment_8004.engineStateOnceLoaded_8004dd24;
import static legend.game.Scus94491BpeSegment_8007.vsyncMode_8007a3b8;
import static legend.game.Scus94491BpeSegment_800b.fullScreenEffect_800bb140;
import static legend.game.Scus94491BpeSegment_800b.gameOverMcq_800bdc3c;
import static legend.game.Scus94491BpeSegment_800b.uiFile_800bdc3c;

public class GameOver extends EngineState {
  private int loadingStage;

  @Method(0x800c7558L)
  private void gameOverLoaded(final FileData data) {
    final McqHeader mcq = new McqHeader(data);

    final Rect4i rect = new Rect4i(640, 0, mcq.vramWidth_08, mcq.vramHeight_0a);
    gameOverMcq_800bdc3c = mcq;
    GPU.uploadData(rect, mcq.imageData);
    this.loadingStage = 3;
  }

  @Method(0x800c75b4L)
  private void renderGameOver() {
    renderMcq(gameOverMcq_800bdc3c, 640, 0, -320, -108, 36, 128);
  }

  @Override
  @Method(0x800c75fcL)
  public void tick() {
    switch(this.loadingStage) {
      case 0 -> {
        if(Unpacker.getLoadingFileCount() == 0) {
          bootMods(MODS.getAllModIds());

          FUN_8002a9c0();
          resizeDisplay(640, 240);
          this.loadingStage = 1;
        }
      }

      case 1 -> {
        this.loadingStage = 2;
        loadDrgnFile(0, 6667, this::gameOverLoaded);
      }

      case 3 -> {
        deallocateRenderables(0xff);
        startFadeEffect(2, 10);
        this.loadingStage = 4;
      }

      // Game Over Screen
      case 4 -> {
        if(Input.pressedThisFrame(InputAction.BUTTON_CENTER_2) || Input.pressedThisFrame(InputAction.BUTTON_SOUTH)) {
          Scus94491BpeSegment_8002.playSound(2);
          this.loadingStage = 5;
          startFadeEffect(1, 10);
        }

        this.renderGameOver();
      }

      case 5 -> {
        if(fullScreenEffect_800bb140.currentColour_28 >= 0xff) {
          this.loadingStage = 6;
        }

        //LAB_800c7740
        this.renderGameOver();
      }

      case 6 -> {
        deallocateRenderables(0xff);

        if(uiFile_800bdc3c != null) {
          uiFile_800bdc3c.delete();
        }

        uiFile_800bdc3c = null;
        gameOverMcq_800bdc3c = null;
        engineStateOnceLoaded_8004dd24 = EngineStateEnum.TITLE_02;
        vsyncMode_8007a3b8 = 2;
      }
    }

    //LAB_800c7788
  }
}

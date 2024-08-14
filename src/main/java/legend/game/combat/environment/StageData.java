package legend.game.combat.environment;

import legend.game.modding.events.battle.BattleEncounterStageDataEvent;

import static legend.core.GameEngine.EVENTS;

public final class StageData {
  private StageData() { }

  public static final StageData2c[] stageData_80109a98 = {
    new StageData2c(255, 224, 90, 255, 1, 20, 8, 6, 21, 42, 0xffff),
    new StageData2c(255, 224, 90, 255, 2, 21, 8, 1, 21, 42, 0xffff),
    new StageData2c(255, 224, 90, 255, 3, 22, 8, 5, 22, 42, 0xffff),
    new StageData2c(255, 224, 90, 255, 14, 15, 21, 1, 43, 8, 0xffff),
    new StageData2c(255, 224, 90, 255, 14, 16, 22, 0, 47, 8, 0xffff),
    new StageData2c(255, 224, 90, 255, 15, 16, 22, 5, 39, 8, 0xffff),
    new StageData2c(255, 224, 90, 255, 15, 13, 22, 0, 42, 8, 0xffff),
    new StageData2c(255, 224, 90, 255, 16, 14, 44, 22, 0, 30, 0xffff),
    new StageData2c(255, 224, 90, 255, 14, 15, 13, 0, 46, 10, 0xffff),
    new StageData2c(255, 224, 90, 255, 13, 16, 8, 0, 11, 12, 0xffff),
    new StageData2c(255, 225, 80, 255, 1, 2, 16, 1, 21, 19, 0xffff),
    new StageData2c(255, 225, 80, 255, 1, 3, 1, 9, 42, 21, 0xffff),
    new StageData2c(255, 225, 80, 255, 1, 4, 6, 14, 43, 16, 0xffff),
    new StageData2c(255, 225, 80, 255, 2, 3, 6, 21, 24, 19, 0xffff),
    new StageData2c(255, 225, 80, 255, 2, 4, 3, 19, 22, 47, 0xffff),
    new StageData2c(255, 225, 80, 255, 3, 4, 1, 22, 19, 25, 0xffff),
    new StageData2c(255, 225, 80, 255, 3, 1, 19, 42, 16, 44, 0xffff),
    new StageData2c(255, 225, 80, 255, 4, 2, 3, 11, 18, 25, 0xffff),
    new StageData2c(255, 225, 80, 255, 2, 3, 1, 19, 42, 29, 0xffff),
    new StageData2c(255, 225, 80, 255, 1, 4, 7, 22, 17, 44, 0xffff),
    new StageData2c(255, 225, 70, 255, 2, 19, 8, 4, 22, 33, 0xffff),
    new StageData2c(255, 225, 70, 255, 3, 20, 21, 1, 14, 39, 0xffff),
    new StageData2c(255, 225, 70, 255, 4, 21, 14, 3, 25, 34, 0xffff),
    new StageData2c(255, 225, 70, 255, 14, 15, 15, 6, 17, 43, 0xffff),
    new StageData2c(255, 225, 70, 255, 14, 16, 6, 9, 21, 46, 0xffff),
    new StageData2c(255, 225, 70, 255, 15, 16, 7, 11, 31, 27, 0xffff),
    new StageData2c(255, 225, 70, 255, 15, 13, 8, 23, 33, 29, 0xffff),
    new StageData2c(255, 225, 70, 255, 16, 14, 10, 17, 26, 35, 0xffff),
    new StageData2c(255, 225, 70, 255, 14, 15, 0, 8, 31, 44, 0xffff),
    new StageData2c(255, 225, 70, 255, 13, 16, 4, 18, 23, 40, 0xffff),
    new StageData2c(255, 224, 60, 255, 19, 2, 1, 8, 16, 43, 0xffff),
    new StageData2c(255, 224, 60, 255, 19, 3, 6, 15, 25, 42, 0xffff),
    new StageData2c(255, 224, 60, 255, 19, 4, 1, 16, 24, 42, 0xffff),
    new StageData2c(255, 224, 60, 255, 20, 3, 0, 9, 15, 27, 0xffff),
    new StageData2c(255, 224, 60, 255, 20, 4, 5, 17, 25, 46, 0xffff),
    new StageData2c(255, 224, 60, 255, 21, 4, 5, 10, 26, 45, 0xffff),
    new StageData2c(255, 224, 60, 255, 21, 1, 5, 8, 18, 25, 0xffff),
    new StageData2c(255, 224, 60, 255, 22, 2, 5, 8, 17, 33, 0xffff),
    new StageData2c(255, 224, 60, 255, 20, 3, 0, 11, 23, 28, 0xffff),
    new StageData2c(255, 224, 60, 255, 19, 4, 0, 8, 17, 29, 0xffff),
    new StageData2c(255, 225, 50, 255, 25, 26, 15, 63, 34, 43, 0xffff),
    new StageData2c(255, 225, 50, 255, 25, 27, 10, 6, 22, 33, 0xffff),
    new StageData2c(255, 225, 50, 255, 25, 28, 22, 1, 12, 42, 0xffff),
    new StageData2c(255, 225, 50, 255, 26, 27, 18, 1, 13, 28, 0xffff),
    new StageData2c(255, 225, 50, 255, 26, 28, 2, 8, 25, 34, 0xffff),
    new StageData2c(255, 225, 50, 255, 27, 28, 8, 66, 17, 39, 0xffff),
    new StageData2c(255, 225, 50, 255, 25, 28, 11, 5, 19, 33, 0xffff),
    new StageData2c(255, 225, 50, 255, 26, 27, 1, 19, 34, 28, 0xffff),
    new StageData2c(255, 225, 50, 255, 27, 26, 11, 5, 18, 25, 0xffff),
    new StageData2c(255, 225, 50, 255, 28, 25, 0, 18, 23, 44, 0xffff),
    new StageData2c(255, 225, 50, 255, 1, 22, 78, 12, 42, 23, 0xffff),
    new StageData2c(255, 225, 50, 255, 1, 19, 21, 1, 14, 34, 0xffff),
    new StageData2c(255, 225, 50, 255, 1, 21, 14, 1, 21, 43, 0xffff),
    new StageData2c(255, 225, 50, 255, 2, 21, 10, 63, 15, 42, 0xffff),
    new StageData2c(255, 225, 50, 255, 2, 22, 8, 0, 33, 46, 0xffff),
    new StageData2c(255, 225, 50, 255, 3, 22, 4, 78, 8, 28, 0xffff),
    new StageData2c(255, 225, 50, 255, 3, 19, 3, 8, 28, 40, 0xffff),
    new StageData2c(255, 225, 50, 255, 4, 20, 2, 19, 44, 29, 0xffff),
    new StageData2c(255, 225, 50, 255, 2, 21, 5, 19, 23, 56, 0xffff),
    new StageData2c(255, 225, 50, 255, 1, 22, 60, 12, 23, 52, 0xffff),
    new StageData2c(255, 225, 40, 255, 1, 2, 84, 1, 19, 36, 0xffff),
    new StageData2c(255, 225, 40, 255, 1, 3, 36, 3, 19, 40, 0xffff),
    new StageData2c(255, 225, 40, 255, 1, 4, 22, 6, 16, 26, 0xffff),
    new StageData2c(255, 225, 40, 255, 2, 3, 6, 21, 19, 62, 0xffff),
    new StageData2c(255, 225, 40, 255, 2, 4, 5, 62, 22, 40, 0xffff),
    new StageData2c(255, 225, 40, 255, 3, 4, 19, 66, 22, 66, 0xffff),
    new StageData2c(255, 225, 40, 255, 3, 1, 7, 22, 19, 69, 0xffff),
    new StageData2c(255, 225, 40, 255, 4, 2, 5, 19, 40, 29, 0xffff),
    new StageData2c(255, 225, 40, 255, 2, 3, 40, 19, 60, 69, 0xffff),
    new StageData2c(255, 225, 40, 255, 1, 4, 5, 22, 19, 69, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 1, 21, 14, 34, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 1, 5, 16, 19, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 14, 1, 23, 47, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 6, 16, 19, 62, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 100, 255, 12, 24, 44, 22, 27, 45, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 8, 6, 15, 33, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 14, 1, 15, 34, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 52, 1, 21, 34, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 40, 255, 1, 8, 14, 4, 25, 43, 0xffff),
    new StageData2c(255, 225, 40, 255, 1, 9, 14, 1, 12, 42, 0xffff),
    new StageData2c(255, 225, 40, 255, 1, 10, 6, 21, 42, 14, 0xffff),
    new StageData2c(255, 225, 40, 255, 2, 9, 9, 6, 18, 43, 0xffff),
    new StageData2c(255, 225, 40, 255, 2, 10, 13, 14, 42, 18, 0xffff),
    new StageData2c(255, 225, 40, 255, 3, 10, 0, 8, 22, 25, 0xffff),
    new StageData2c(255, 225, 40, 255, 3, 7, 5, 8, 18, 25, 0xffff),
    new StageData2c(255, 225, 40, 255, 4, 8, 0, 12, 29, 10, 0xffff),
    new StageData2c(255, 225, 40, 255, 2, 9, 2, 10, 18, 44, 0xffff),
    new StageData2c(255, 225, 40, 255, 1, 10, 5, 8, 18, 28, 0xffff),
    new StageData2c(255, 225, 40, 255, 13, 26, 8, 6, 13, 43, 0xffff),
    new StageData2c(255, 225, 40, 255, 13, 27, 0, 13, 21, 25, 0xffff),
    new StageData2c(255, 225, 40, 255, 13, 28, 8, 6, 18, 39, 0xffff),
    new StageData2c(255, 225, 40, 255, 14, 27, 0, 8, 13, 24, 0xffff),
    new StageData2c(255, 225, 40, 255, 14, 28, 5, 8, 18, 47, 0xffff),
    new StageData2c(255, 225, 40, 255, 15, 28, 5, 18, 27, 19, 0xffff),
    new StageData2c(255, 225, 40, 255, 15, 28, 8, 27, 17, 13, 0xffff),
    new StageData2c(255, 225, 40, 255, 16, 27, 5, 8, 13, 29, 0xffff),
    new StageData2c(255, 225, 40, 255, 14, 26, 13, 5, 18, 41, 0xffff),
    new StageData2c(255, 225, 40, 255, 13, 25, 5, 11, 86, 25, 0xffff),
    new StageData2c(255, 224, 40, 255, 231, 232, 14, 6, 21, 47, 0xffff),
    new StageData2c(255, 224, 40, 255, 232, 4, 14, 1, 21, 46, 0xffff),
    new StageData2c(255, 224, 40, 255, 233, 231, 16, 6, 19, 34, 0xffff),
    new StageData2c(255, 224, 40, 255, 231, 233, 14, 6, 18, 34, 0xffff),
    new StageData2c(255, 224, 40, 255, 232, 233, 8, 42, 18, 66, 0xffff),
    new StageData2c(255, 224, 40, 255, 233, 4, 2, 8, 44, 10, 0xffff),
    new StageData2c(255, 224, 40, 255, 231, 232, 5, 18, 22, 44, 0xffff),
    new StageData2c(255, 224, 40, 255, 232, 231, 0, 8, 18, 25, 0xffff),
    new StageData2c(255, 224, 40, 255, 233, 232, 8, 0, 19, 25, 0xffff),
    new StageData2c(255, 224, 40, 255, 4, 231, 42, 8, 52, 29, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 225, 30, 255, 2, 22, 47, 1, 14, 22, 0xffff),
    new StageData2c(255, 225, 30, 255, 3, 19, 14, 28, 21, 67, 0xffff),
    new StageData2c(255, 225, 30, 255, 6, 6, 14, 1, 28, 40, 0xffff),
    new StageData2c(255, 225, 30, 255, 2, 15, 9, 34, 17, 67, 0xffff),
    new StageData2c(255, 225, 30, 255, 2, 16, 5, 12, 23, 26, 0xffff),
    new StageData2c(255, 225, 30, 255, 3, 16, 5, 10, 22, 25, 0xffff),
    new StageData2c(255, 225, 30, 255, 3, 13, 0, 8, 18, 28, 0xffff),
    new StageData2c(255, 225, 30, 255, 4, 14, 5, 8, 23, 26, 0xffff),
    new StageData2c(255, 225, 30, 255, 2, 15, 7, 10, 19, 39, 0xffff),
    new StageData2c(255, 225, 30, 255, 1, 16, 5, 8, 23, 25, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 14, 21, 1, 16, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 15, 8, 6, 21, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 16, 1, 8, 18, 24, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 15, 21, 6, 17, 43, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 16, 4, 10, 22, 40, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 16, 5, 8, 18, 25, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 13, 2, 18, 19, 29, 0xffff),
    new StageData2c(255, 224, 30, 255, 16, 14, 7, 8, 18, 44, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 15, 0, 30, 12, 25, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 16, 5, 18, 23, 33, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 2, 6, 15, 9, 34, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 3, 4, 8, 16, 46, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 4, 14, 1, 21, 42, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 3, 0, 21, 16, 46, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 4, 3, 18, 25, 43, 0xffff),
    new StageData2c(255, 226, 30, 255, 15, 4, 0, 10, 18, 25, 0xffff),
    new StageData2c(255, 226, 30, 255, 15, 1, 0, 12, 18, 47, 0xffff),
    new StageData2c(255, 226, 30, 255, 16, 2, 7, 8, 22, 33, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 3, 2, 10, 23, 25, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 28, 0, 19, 44, 29, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 14, 14, 1, 15, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 15, 6, 14, 23, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 16, 14, 1, 15, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 15, 4, 15, 10, 25, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 16, 13, 6, 23, 39, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 16, 0, 10, 19, 42, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 13, 7, 31, 19, 45, 0xffff),
    new StageData2c(255, 224, 30, 255, 16, 14, 5, 10, 24, 29, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 15, 0, 11, 23, 26, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 16, 1, 11, 13, 45, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 26, 31, 1, 15, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 27, 19, 6, 17, 41, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 28, 14, 1, 22, 42, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 27, 0, 8, 21, 46, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 16, 0, 10, 14, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 16, 5, 11, 25, 19, 0xffff),
    new StageData2c(255, 224, 30, 255, 16, 13, 3, 10, 23, 26, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 14, 7, 8, 23, 29, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 15, 5, 12, 26, 27, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 16, 2, 11, 26, 19, 0xffff),
    new StageData2c(255, 226, 30, 255, 19, 20, 9, 1, 15, 34, 0xffff),
    new StageData2c(255, 226, 30, 255, 19, 21, 8, 6, 22, 39, 0xffff),
    new StageData2c(255, 226, 30, 255, 19, 22, 9, 1, 15, 42, 0xffff),
    new StageData2c(255, 226, 30, 255, 20, 21, 5, 14, 18, 42, 0xffff),
    new StageData2c(255, 226, 30, 255, 20, 22, 1, 23, 28, 19, 0xffff),
    new StageData2c(255, 226, 30, 255, 21, 22, 9, 5, 13, 33, 0xffff),
    new StageData2c(255, 226, 30, 255, 21, 19, 5, 8, 11, 25, 0xffff),
    new StageData2c(255, 226, 30, 255, 22, 20, 0, 25, 19, 33, 0xffff),
    new StageData2c(255, 226, 30, 255, 20, 21, 2, 13, 18, 29, 0xffff),
    new StageData2c(255, 226, 30, 255, 19, 22, 5, 8, 18, 40, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 2, 8, 6, 15, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 3, 0, 16, 8, 44, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 4, 12, 1, 21, 42, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 3, 14, 6, 21, 42, 0xffff),
    new StageData2c(255, 224, 30, 255, 26, 28, 0, 11, 23, 44, 0xffff),
    new StageData2c(255, 224, 30, 255, 27, 28, 8, 4, 30, 46, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 25, 5, 8, 18, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 26, 26, 2, 19, 44, 74, 0xffff),
    new StageData2c(255, 224, 30, 255, 27, 27, 5, 10, 23, 44, 0xffff),
    new StageData2c(255, 224, 30, 255, 28, 28, 4, 19, 23, 24, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 26, 1, 8, 11, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 27, 14, 1, 31, 42, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 28, 6, 16, 13, 39, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 27, 8, 1, 14, 25, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 28, 5, 57, 10, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 28, 4, 8, 23, 40, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 28, 7, 10, 44, 36, 0xffff),
    new StageData2c(255, 224, 30, 255, 16, 27, 5, 11, 23, 25, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 26, 0, 8, 12, 28, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 25, 19, 23, 26, 10, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 2, 6, 9, 18, 25, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 3, 1, 9, 10, 27, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 4, 6, 9, 18, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 26, 3, 1, 9, 15, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 26, 4, 5, 13, 23, 28, 0xffff),
    new StageData2c(255, 224, 30, 255, 27, 4, 2, 10, 13, 26, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 1, 3, 12, 13, 45, 0xffff),
    new StageData2c(255, 224, 30, 255, 26, 2, 5, 11, 18, 27, 0xffff),
    new StageData2c(255, 224, 30, 255, 27, 3, 2, 11, 28, 19, 0xffff),
    new StageData2c(255, 224, 30, 255, 28, 4, 0, 10, 18, 29, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 0, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 26, 0, 8, 17, 24, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 27, 6, 8, 10, 28, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 28, 8, 6, 21, 42, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 27, 14, 1, 30, 27, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 28, 5, 8, 15, 25, 0xffff),
    new StageData2c(255, 226, 30, 255, 15, 28, 7, 8, 11, 46, 0xffff),
    new StageData2c(255, 226, 30, 255, 15, 28, 5, 11, 30, 39, 0xffff),
    new StageData2c(255, 226, 30, 255, 16, 27, 5, 8, 39, 52, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 26, 5, 12, 31, 39, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 25, 4, 12, 28, 29, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 14, 23, 6, 18, 41, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 15, 9, 75, 30, 41, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 16, 6, 11, 35, 98, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 15, 5, 8, 23, 24, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 16, 1, 13, 33, 89, 0xffff),
    new StageData2c(255, 226, 30, 255, 15, 16, 7, 31, 41, 85, 0xffff),
    new StageData2c(255, 226, 30, 255, 16, 13, 78, 11, 33, 19, 0xffff),
    new StageData2c(255, 226, 30, 255, 15, 14, 19, 1, 26, 33, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 15, 7, 13, 33, 52, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 16, 5, 19, 41, 29, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 26, 1, 21, 17, 24, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 27, 0, 14, 24, 42, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 28, 8, 24, 16, 80, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 27, 0, 21, 1, 42, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 28, 5, 10, 21, 27, 0xffff),
    new StageData2c(255, 226, 30, 255, 15, 28, 2, 8, 23, 44, 0xffff),
    new StageData2c(255, 226, 30, 255, 15, 28, 5, 8, 28, 86, 0xffff),
    new StageData2c(255, 226, 30, 255, 16, 27, 12, 4, 23, 46, 0xffff),
    new StageData2c(255, 226, 30, 255, 14, 26, 5, 27, 12, 81, 0xffff),
    new StageData2c(255, 226, 30, 255, 13, 25, 17, 45, 24, 63, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 26, 6, 8, 16, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 27, 5, 10, 15, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 28, 16, 6, 21, 43, 0xffff),
    new StageData2c(255, 224, 30, 255, 26, 27, 5, 10, 16, 24, 0xffff),
    new StageData2c(255, 224, 30, 255, 26, 28, 1, 8, 12, 42, 0xffff),
    new StageData2c(255, 224, 30, 255, 27, 28, 7, 10, 36, 25, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 28, 0, 8, 12, 27, 0xffff),
    new StageData2c(255, 224, 30, 255, 26, 27, 8, 29, 45, 78, 0xffff),
    new StageData2c(255, 224, 30, 255, 27, 26, 5, 8, 26, 85, 0xffff),
    new StageData2c(255, 224, 30, 255, 28, 25, 0, 8, 28, 78, 0xffff),
    new StageData2c(255, 224, 30, 255, 7, 8, 10, 6, 19, 39, 0xffff),
    new StageData2c(255, 224, 30, 255, 7, 9, 19, 1, 23, 41, 0xffff),
    new StageData2c(255, 224, 30, 255, 7, 10, 16, 6, 10, 39, 0xffff),
    new StageData2c(255, 224, 30, 255, 8, 9, 19, 5, 23, 28, 0xffff),
    new StageData2c(255, 224, 30, 255, 8, 10, 36, 2, 10, 40, 0xffff),
    new StageData2c(255, 224, 30, 255, 9, 10, 4, 10, 19, 44, 0xffff),
    new StageData2c(255, 224, 30, 255, 9, 7, 7, 10, 23, 29, 0xffff),
    new StageData2c(255, 224, 30, 255, 10, 8, 10, 5, 23, 44, 0xffff),
    new StageData2c(255, 224, 30, 255, 8, 9, 19, 0, 12, 40, 0xffff),
    new StageData2c(255, 224, 30, 255, 7, 10, 5, 19, 23, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 2, 10, 6, 19, 39, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 3, 12, 1, 23, 45, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 4, 16, 5, 36, 39, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 3, 19, 6, 28, 98, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 4, 2, 12, 40, 28, 0xffff),
    new StageData2c(255, 224, 30, 255, 3, 4, 4, 10, 25, 44, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 2, 7, 10, 29, 41, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 0, 12, 0, 19, 44, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 1, 7, 19, 28, 98, 0xffff),
    new StageData2c(255, 224, 30, 255, 3, 0, 19, 5, 23, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 26, 14, 1, 24, 42, 0xffff),
    new StageData2c(255, 224, 100, 255, 26, 27, 15, 6, 26, 44, 0xffff),
    new StageData2c(255, 224, 100, 255, 27, 25, 16, 3, 28, 47, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 14, 10, 6, 31, 36, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 16, 8, 66, 23, 40, 0xffff),
    new StageData2c(255, 224, 30, 255, 7, 14, 11, 1, 30, 39, 0xffff),
    new StageData2c(255, 224, 30, 255, 8, 15, 8, 63, 13, 23, 0xffff),
    new StageData2c(255, 224, 30, 255, 9, 16, 16, 6, 35, 33, 0xffff),
    new StageData2c(255, 224, 100, 255, 10, 13, 15, 5, 32, 43, 0xffff),
    new StageData2c(255, 224, 30, 255, 20, 21, 11, 0, 23, 39, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 26, 10, 1, 36, 39, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 27, 72, 19, 36, 28, 0xffff),
    new StageData2c(255, 224, 30, 255, 3, 28, 1, 16, 39, 47, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 25, 69, 71, 23, 28, 0xffff),
    new StageData2c(255, 224, 30, 255, 19, 21, 78, 40, 80, 28, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 2, 14, 6, 24, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 3, 2, 89, 57, 17, 0xffff),
    new StageData2c(255, 224, 30, 255, 3, 4, 30, 1, 11, 27, 0xffff),
    new StageData2c(255, 224, 30, 255, 7, 8, 8, 3, 36, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 9, 10, 19, 2, 26, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 13, 16, 6, 22, 43, 0xffff),
    new StageData2c(255, 224, 30, 255, 3, 14, 11, 1, 25, 39, 0xffff),
    new StageData2c(255, 224, 100, 255, 4, 15, 10, 6, 26, 45, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 16, 19, 4, 28, 47, 0xffff),
    new StageData2c(255, 224, 30, 255, 20, 22, 78, 7, 11, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 2, 9, 79, 87, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 3, 17, 67, 77, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 3, 4, 13, 68, 89, 27, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 3, 17, 66, 31, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 4, 88, 66, 27, 34, 0xffff),
    new StageData2c(255, 224, 30, 255, 1, 1, 0, 1, 2, 3, 0xffff),
    new StageData2c(255, 224, 30, 255, 2, 2, 4, 5, 6, 7, 0xffff),
    new StageData2c(255, 224, 30, 255, 3, 3, 59, 60, 63, 53, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 67, 68, 79, 84, 0xffff),
    new StageData2c(255, 224, 30, 255, 7, 7, 57, 74, 75, 78, 0xffff),
    new StageData2c(255, 224, 30, 255, 8, 8, 71, 66, 12, 19, 0xffff),
    new StageData2c(255, 224, 30, 255, 9, 9, 8, 10, 14, 69, 0xffff),
    new StageData2c(255, 224, 30, 255, 10, 10, 15, 16, 18, 62, 0xffff),
    new StageData2c(255, 224, 30, 255, 13, 13, 9, 11, 13, 17, 0xffff),
    new StageData2c(255, 224, 30, 255, 14, 14, 50, 51, 52, 53, 0xffff),
    new StageData2c(255, 224, 30, 255, 15, 15, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 16, 16, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 19, 19, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 20, 20, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 21, 21, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 22, 22, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 25, 25, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 26, 26, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 27, 27, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 28, 28, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 231, 231, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 232, 232, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 233, 233, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 234, 234, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 235, 235, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 240, 0, 255, 0, 0, 22, 58, 58, 58, 0xffff),
    new StageData2c(255, 240, 0, 0, 9, 9, 0, 76, 87, 76, 0x70),
    new StageData2c(255, 241, 0, 255, 5, 5, 2, 14, 60, 21, 0xffff),
    new StageData2c(255, 241, 0, 5, 5, 5, 5, 23, 51, 51, 0xc),
    new StageData2c(1, 241, 0, 255, 6, 6, 7, 16, 59, 87, 0xffff),
    new StageData2c(255, 241, 0, 255, 23, 23, 63, 19, 63, 34, 0xffff),
    new StageData2c(255, 242, 0, 7, 17, 17, 62, 63, 62, 89, 0xc8),
    new StageData2c(255, 241, 0, 255, 23, 23, 20, 58, 20, 58, 0xffff),
    new StageData2c(255, 242, 0, 4, 31, 31, 2, 19, 95, 91, 0x1be),
    new StageData2c(255, 241, 0, 0, 5, 5, 56, 68, 56, 68, 0x2cc),
    new StageData2c(255, 241, 0, 12, 30, 30, 54, 75, 74, 54, 0x1a8),
    new StageData2c(255, 240, 0, 255, 17, 17, 56, 5, 9, 46, 0xffff),
    new StageData2c(255, 240, 0, 0, 5, 5, 81, 63, 14, 99, 0xec),
    new StageData2c(255, 241, 0, 255, 227, 227, 72, 78, 72, 74, 0xffff),
    new StageData2c(255, 242, 0, 255, 17, 17, 0, 16, 23, 97, 0xffff),
    new StageData2c(255, 242, 0, 255, 17, 17, 0, 16, 88, 97, 0xffff),
    new StageData2c(255, 242, 0, 255, 17, 17, 75, 16, 23, 97, 0xffff),
    new StageData2c(255, 242, 0, 255, 17, 17, 0, 16, 23, 97, 0xffff),
    new StageData2c(255, 240, 0, 255, 5, 5, 0, 10, 25, 47, 0xffff),
    new StageData2c(255, 241, 0, 255, 5, 5, 5, 9, 62, 43, 0xffff),
    new StageData2c(1, 240, 0, 255, 18, 6, 27, 64, 64, 64, 0xffff),
    new StageData2c(1, 240, 0, 255, 18, 6, 27, 64, 64, 64, 0xffff),
    new StageData2c(1, 240, 0, 255, 18, 6, 27, 64, 64, 64, 0xffff),
    new StageData2c(1, 240, 0, 255, 18, 6, 27, 64, 64, 64, 0xffff),
    new StageData2c(255, 242, 0, 54, 5, 5, 83, 83, 74, 79, 0x77),
    new StageData2c(255, 242, 0, 0, 228, 228, 54, 90, 57, 71, 0x100),
    new StageData2c(255, 242, 0, 6, 29, 29, 35, 66, 54, 90, 0x190),
    new StageData2c(255, 242, 0, 255, 17, 17, 84, 54, 56, 57, 0xffff),
    new StageData2c(255, 240, 0, 255, 23, 5, 69, 69, 69, 57, 0xffff),
    new StageData2c(255, 240, 0, 255, 5, 5, 57, 18, 23, 39, 0xffff),
    new StageData2c(255, 240, 0, 255, 17, 12, 50, 52, 54, 66, 0xffff),
    new StageData2c(255, 240, 0, 255, 5, 5, 51, 85, 67, 98, 0xffff),
    new StageData2c(255, 240, 0, 255, 5, 5, 88, 78, 51, 13, 0xffff),
    new StageData2c(255, 242, 0, 255, 234, 234, 2, 12, 44, 56, 0xffff),
    new StageData2c(255, 240, 0, 255, 5, 5, 54, 63, 78, 84, 0xffff),
    new StageData2c(255, 240, 0, 255, 5, 5, 56, 60, 67, 74, 0xffff),
    new StageData2c(255, 242, 0, 255, 11, 11, 12, 13, 85, 69, 0xffff),
    new StageData2c(255, 242, 100, 255, 23, 23, 9, 1, 17, 99, 0xffff),
    new StageData2c(255, 240, 0, 255, 17, 17, 85, 54, 89, 1, 0xffff),
    new StageData2c(255, 240, 0, 255, 29, 29, 9, 14, 43, 67, 0xffff),
    new StageData2c(255, 240, 0, 255, 24, 24, 92, 92, 92, 92, 0xffff),
    new StageData2c(255, 240, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 240, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 240, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 240, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 240, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 241, 0, 255, 11, 11, 5, 88, 26, 77, 0xffff),
    new StageData2c(255, 241, 0, 4, 17, 17, 41, 12, 63, 80, 0x2d1),
    new StageData2c(255, 241, 0, 255, 17, 17, 52, 78, 68, 81, 0xffff),
    new StageData2c(255, 240, 0, 255, 29, 29, 6, 24, 56, 78, 0xffff),
    new StageData2c(255, 242, 0, 255, 23, 23, 44, 18, 61, 61, 0xffff),
    new StageData2c(255, 242, 0, 255, 23, 23, 9, 43, 13, 65, 0xffff),
    new StageData2c(255, 242, 0, 255, 23, 23, 6, 9, 64, 55, 0xffff),
    new StageData2c(255, 242, 0, 255, 5, 17, 50, 52, 66, 80, 0xffff),
    new StageData2c(255, 242, 0, 255, 17, 17, 60, 70, 70, 87, 0xffff),
    new StageData2c(255, 242, 0, 255, 17, 17, 73, 73, 89, 59, 0xffff),
    new StageData2c(255, 240, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 240, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 241, 0, 0, 31, 31, 13, 43, 89, 79, 0x2df),
    new StageData2c(255, 243, 0, 0, 31, 31, 53, 90, 71, 75, 0x2a0),
    new StageData2c(255, 243, 0, 255, 17, 17, 13, 71, 91, 53, 0xffff),
    new StageData2c(255, 243, 0, 255, 17, 17, 66, 74, 53, 57, 0xffff),
    new StageData2c(255, 243, 0, 255, 17, 17, 57, 66, 57, 88, 0xffff),
    new StageData2c(255, 242, 0, 255, 5, 5, 62, 68, 23, 42, 0xffff),
    new StageData2c(255, 242, 0, 255, 11, 11, 62, 68, 22, 39, 0xffff),
    new StageData2c(255, 242, 0, 255, 6, 6, 62, 68, 27, 41, 0xffff),
    new StageData2c(255, 224, 0, 255, 11, 11, 58, 32, 96, 14, 0xffff),
    new StageData2c(255, 224, 0, 255, 17, 17, 55, 96, 96, 96, 0xffff),
    new StageData2c(255, 224, 0, 255, 6, 6, 58, 32, 58, 9, 0xffff),
    new StageData2c(255, 224, 30, 255, 235, 234, 11, 12, 30, 40, 0xffff),
    new StageData2c(255, 224, 30, 255, 235, 234, 5, 8, 13, 30, 0xffff),
    new StageData2c(255, 226, 30, 255, 0, 6, 8, 6, 24, 40, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 18, 0, 8, 51, 24, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 18, 2, 8, 23, 19, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 18, 5, 23, 41, 8, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 18, 0, 19, 28, 33, 0xffff),
    new StageData2c(255, 224, 30, 255, 0, 18, 63, 8, 28, 19, 0xffff),
    new StageData2c(255, 224, 0, 255, 6, 231, 4, 8, 13, 75, 0xffff),
    new StageData2c(255, 224, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 0, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 0, 255, 24, 24, 0, 9, 14, 17, 0xffff),
    new StageData2c(255, 224, 0, 0, 0, 0, 6, 9, 27, 47, 0x71),
    new StageData2c(255, 224, 0, 255, 29, 0, 0, 8, 15, 25, 0xffff),
    new StageData2c(255, 224, 0, 255, 0, 18, 6, 9, 25, 19, 0xffff),
    new StageData2c(255, 224, 30, 255, 29, 29, 5, 8, 18, 46, 0xffff),
    new StageData2c(255, 224, 0, 255, 232, 233, 63, 9, 54, 44, 0xffff),
    new StageData2c(255, 224, 0, 255, 235, 235, 8, 13, 8, 25, 0xffff),
    new StageData2c(255, 224, 0, 255, 0, 0, 8, 8, 8, 20, 0xffff),
    new StageData2c(255, 224, 0, 255, 18, 0, 4, 8, 17, 25, 0xffff),
    new StageData2c(255, 224, 100, 255, 0, 0, 4, 8, 18, 42, 0xffff),
    new StageData2c(255, 224, 0, 255, 232, 0, 4, 8, 9, 34, 0xffff),
    new StageData2c(255, 224, 0, 255, 233, 18, 7, 8, 15, 39, 0xffff),
    new StageData2c(255, 224, 0, 255, 24, 24, 13, 8, 25, 30, 0xffff),
    new StageData2c(255, 224, 0, 255, 29, 5, 8, 10, 11, 15, 0xffff),
    new StageData2c(255, 224, 0, 255, 24, 29, 0, 22, 16, 46, 0xffff),
    new StageData2c(255, 224, 0, 255, 235, 234, 0, 9, 10, 11, 0xffff),
    new StageData2c(255, 224, 0, 255, 29, 5, 8, 17, 16, 25, 0xffff),
    new StageData2c(255, 224, 100, 255, 5, 5, 0, 18, 25, 46, 0xffff),
    new StageData2c(255, 224, 0, 255, 235, 231, 0, 8, 10, 18, 0xffff),
    new StageData2c(255, 224, 0, 255, 234, 234, 2, 8, 18, 44, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
    new StageData2c(255, 224, 10, 255, 4, 4, 8, 8, 8, 8, 0xffff),
  };

  public static StageData2c getEncounterStageData(final int encounterId) {
    final var encounterStageData = stageData_80109a98[encounterId];
    final var encounterStageDataEvent = EVENTS.postEvent(new BattleEncounterStageDataEvent(encounterStageData));

    return encounterStageDataEvent.stageData;
  }
}

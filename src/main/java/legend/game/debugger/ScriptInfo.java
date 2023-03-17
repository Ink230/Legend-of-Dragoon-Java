package legend.game.debugger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScriptInfo extends Application {
  private ScriptInfoController controller;

  private int preselectScript;

  public ScriptInfo preselectScript(final int preselectScript) {
    this.preselectScript = preselectScript;
    return this;
  }

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("script_info.fxml"));
    final Parent root = loader.load();
    final Scene scene = new Scene(root);
    scene.getStylesheets().add(this.getClass().getResource("script_info.css").toExternalForm());

    this.controller = loader.getController();
    this.controller.selectScript(this.preselectScript);

    stage.setTitle("Script Debugger");
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop() throws Exception {
    this.controller.uninitialize();
    super.stop();
  }
}

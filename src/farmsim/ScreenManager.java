package farmsim;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ScreenManager {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScreen(String pane) throws Exception {
        if (stage == null) {
            throw new IllegalStateException("No Stage Set");
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(pane));
        Parent next = loader.load();

        Scene nextScene = new Scene(next);

        stage.setScene(nextScene);
        stage.show();
    }
}
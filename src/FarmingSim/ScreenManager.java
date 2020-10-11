package FarmingSim;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ScreenManager {
    public Stage stage;

    public void setScreen(String screen, String pane) throws Exception {
        if (stage == null) {
            throw new IllegalStateException("No Stage Set");
        }

        Parent next = null;


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(pane));
        next = loader.load();

        Scene nextScene = new Scene(next);

        stage.setScene(nextScene);
        stage.show();
    }
}
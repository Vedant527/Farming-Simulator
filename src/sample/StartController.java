package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class StartController {
    public void move_on(ActionEvent e) throws Exception {
        ScreenManager.setScreen(
                "Player",
                FXMLLoader.load(getClass().getResource("player.fxml"))
        );
    }
}
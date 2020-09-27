package FarmingSim;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class PlayerController {
    public void move_back(ActionEvent e) throws Exception {
        ScreenManager.setScreen(
                "Start",
                FXMLLoader.load(getClass().getResource("start.fxml"))
        );
    }
}
package FarmingSim;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class StartController {
    public void move_on(ActionEvent e) throws Exception {
        ScreenManager.addScreen(
                "Player",
                FXMLLoader.load(getClass().getResource("CustomizationPage.fxml"))
        );
        ScreenManager.setScreen("Player");
    }
}
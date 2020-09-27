package FarmingSim.Controllers;

import FarmingSim.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class StartController {
    public void move_on(ActionEvent e) throws Exception {
        ScreenManager.setScreen(
                "CustomizationPage",
                FXMLLoader.load(getClass().getResource("../FXML/CustomizationPage.fxml"))
        );
    }
}
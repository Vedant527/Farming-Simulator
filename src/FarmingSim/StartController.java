package FarmingSim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class StartController {

    @FXML Button BeginButton;

    @FXML
    private void initialize() {
        Image img = new Image("FarmingSim/Resources/BeginButton.png");
        ImageView startButton = new ImageView(img);
        startButton.setFitWidth(120);
        startButton.setFitHeight(40);

        BeginButton.setGraphic(startButton);
    }

    public void move_on(ActionEvent e) throws Exception {
        ScreenManager.addScreen(
                "Player",
                FXMLLoader.load(getClass().getResource("CustomizationPage.fxml"))
        );
        ScreenManager.setScreen("Player");
    }
}
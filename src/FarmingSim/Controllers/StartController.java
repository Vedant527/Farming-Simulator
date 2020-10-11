package FarmingSim.Controllers;

import FarmingSim.GameState;
import FarmingSim.UIUpdateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class StartController extends UIUpdateable {

    @FXML
    Button BeginButton;

    public StartController() {
        super(0);
    }

    @FXML
    public void firstInit() {
        Image img = new Image("FarmingSim/Resources/BeginButton.png");
        ImageView startButton = new ImageView(img);
        startButton.setFitWidth(120);
        startButton.setFitHeight(40);

        BeginButton.setGraphic(startButton);
    }

    public void move_on(ActionEvent e) throws Exception {
        GameState.screenManager.setScreen(
                "Customization",
                "FXML/CustomizationPage.fxml"
        );
    }

    public void updateUI() {
        return;
    }
}
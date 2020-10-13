package farmsim.Controllers;

import farmsim.GameState;
import farmsim.UIUpdateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class StartController extends UIUpdateable {

    @FXML
    private Button beginButton;

    public StartController() {
        super(0);
    }

    @FXML
    public void firstInit() {
        Image img = new Image("farmsim/Resources/BeginButton.png");
        ImageView startButton = new ImageView(img);
        startButton.setFitWidth(120);
        startButton.setFitHeight(40);

        beginButton.setGraphic(startButton);
    }

    public void moveOn(ActionEvent e) throws Exception {
        GameState.getScreenManager().setScreen(
                "FXML/CustomizationPage.fxml"
        );
    }

    public void updateUI() {
        return;
    }
}
package farmsim.Controllers;

import farmsim.FarmHand;
import farmsim.GameState;
import farmsim.UIUpdateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class HandController extends UIUpdateable {
    @FXML
    private Text moneyDisplay;
    @FXML
    private Text dayDisplay;
    @FXML
    private Text farmName;
    @FXML
    private Text season;
    @FXML
    private Text noobCounter;
    @FXML
    private Text normCounter;
    @FXML
    private Text proCounter;

    @FXML
    private Button noobHire;
    @FXML
    private Button normHire;
    @FXML
    private Button proHire;
    @FXML
    private Button noobFire;
    @FXML
    private Button normFire;
    @FXML
    private Button proFire;

    public HandController() {
        super(4);
    }

    public void hireNoob() {
        GameState.getInventory().addHand(0);
        updateUI();
    }

    public void hireNorm() {
        GameState.getInventory().addHand(1);
        updateUI();
    }

    public void hirePro() {
        GameState.getInventory().addHand(2);
        updateUI();
    }

    public void fireNoob() {
        GameState.getInventory().removeHand(0);
        updateUI();
    }

    public void fireNorm() {
        GameState.getInventory().removeHand(1);
        updateUI();
    }

    public void firePro() {
        GameState.getInventory().removeHand(2);
        updateUI();
    }

    public void firstInit() {
        setText();
    }

    public void updateUI() {
        setText();
    }

    public void setText() {
        int[] counts = {0,0,0};
        for (FarmHand hands : GameState.getInventory().getFarmHands()) {
            counts[hands.getLevel()]++;
        }
        noobCounter.setText(Integer.toString(counts[0]));
        normCounter.setText(Integer.toString(counts[1]));
        proCounter.setText(Integer.toString(counts[2]));

//        season.setText("Season: " + GameState.getSeason());
        dayDisplay.setText("Day: " + GameState.getDay());
    }

    public void moveBack(ActionEvent e) throws Exception {
        GameState.incrementDay();
        GameState.getScreenManager().setScreen(
                "FXML/FarmUI.fxml"
        );
    }
}

package FarmingSim.Controllers;

import FarmingSim.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FarmUIController {

    @FXML
    private Text moneyDisplay;
    private Integer money = 0;

    @FXML
    private Text dayDisplay;
    private Integer dayNum = 0;

    @FXML

    private Text farmName;
    @FXML
    private Text season;

    @FXML
    public void initialize() {
        if (CustomizationPageController.difficulty == Settings.Difficulty.EASY) {
            money = 500;
            moneyDisplay.setText("Money: " + money.toString());
        } else if (CustomizationPageController.difficulty == Settings.Difficulty.MEDIUM) {
            money = 300;
            moneyDisplay.setText("Money: " + money.toString());
        } else if (CustomizationPageController.difficulty == Settings.Difficulty.HARD) {
            money = 10;
            moneyDisplay.setText("Money: " + money.toString());
        }
        farmName.setText(CustomizationPageController.name + "'s Farm");
        season.setText("Season: " + CustomizationPageController.season.toString());

    }

    @FXML
    public void incDay() {
        dayNum++;
        dayDisplay.setText("Day: " + dayNum.toString());
    }

    @FXML
    public void alterMoney(int amt) {
        money += amt;
        moneyDisplay.setText("Money: " + money.toString());
    }

    @FXML
    public void increaseMoney(ActionEvent actionEvent) {
        alterMoney(1);
        incDay();
    }
}
package FarmingSim.Controllers;

import FarmingSim.Inventory;
import FarmingSim.ScreenManager;
import FarmingSim.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

public class FarmUIController {

    @FXML
    private Text moneyDisplay;

    @FXML
    private Text dayDisplay;
    private Integer dayNum = 0;

    @FXML
    private Text farmName;
    @FXML
    private Text season;
    @FXML
    private Text cornSeedText;
    @FXML
    private Text wheatSeedText;
    @FXML
    private Text tobaccoSeedText;
    @FXML
    private Text hempSeedText;
    @FXML
    private Text maxSeedInventoryText;

    @FXML
    public void initialize() {
        moneyDisplay.setText("Money: " + Inventory.money);
        maxSeedInventoryText.setText("Max Inventory: " + Inventory.MAX_SEED_INVENTORY);
        cornSeedText.setText("Wheat: " + Inventory.seedNum[Settings.Seed.CORN.ordinal()]);
        wheatSeedText.setText("Wheat: " + Inventory.seedNum[Settings.Seed.WHEAT.ordinal()]);
        tobaccoSeedText.setText("Tobacco: " + Inventory.seedNum[Settings.Seed.TOBACCO.ordinal()]);
        hempSeedText.setText("Hemp: " + Inventory.seedNum[Settings.Seed.HEMP.ordinal()]);
        farmName.setText(CustomizationPageController.name + "'s Farm");
        season.setText("Season: " + CustomizationPageController.season);
    }

    @FXML
    public void incDay() {
        dayNum++;
        dayDisplay.setText("Day: " + dayNum.toString());
    }

//    @FXML
    /*public void alterMoney(int amt) {
        money += amt;
        moneyDisplay.setText("Money: " + money.toString());
    }

    @FXML
    public void increaseMoney(ActionEvent actionEvent) {
        alterMoney(1);
        incDay();
    }
     */

    public void move_on(ActionEvent e) throws Exception {
        ScreenManager.addScreen(
                "Player",
                FXMLLoader.load(getClass().getResource("../FXML/Market.fxml"))
        );
        ScreenManager.setScreen("Player");
    }
}

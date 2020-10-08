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
    private static Text dayDisplay;
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

    public static void updateDay(){
        dayDisplay.setText("Day: " + Inventory.day);
    }

    public void move_on(ActionEvent e) throws Exception {
        ScreenManager.setScreen(
                "Market",
                FXMLLoader.load(getClass().getResource("../FXML/Market.fxml"))
        );
    }
}

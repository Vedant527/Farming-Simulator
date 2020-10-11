package FarmingSim.Controllers;

import FarmingSim.Farm;
import FarmingSim.Inventory;
import FarmingSim.ScreenManager;
import FarmingSim.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
    private GridPane farmGrid;

    @FXML
    public void initialize() {
        moneyDisplay.setText("Money: " + Inventory.money);
        maxSeedInventoryText.setText("Max Inventory: " + Inventory.MAX_SEED_INVENTORY);
        cornSeedText.setText("Corn: " + Inventory.seedNum[Settings.CropType.CORN.ordinal()]);
        wheatSeedText.setText("Wheat: " + Inventory.seedNum[Settings.CropType.WHEAT.ordinal()]);
        tobaccoSeedText.setText("Tobacco: " + Inventory.seedNum[Settings.CropType.TOBACCO.ordinal()]);
        hempSeedText.setText("Hemp: " + Inventory.seedNum[Settings.CropType.HEMP.ordinal()]);
        farmName.setText(CustomizationPageController.name + "'s Farm");

        season.setText("Season: " + CustomizationPageController.season);

        Button[] gridChildren = farmGrid.getChildren().toArray(new Button[0]);
        Farm farm = new Farm();
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            n.setId("Button" + i / farmGrid.getColumnCount() + "_" + i % farmGrid.getColumnCount());
            n.setText(farm.plots[i].cropType.toString().toLowerCase() + "\n"
                + farm.plots[i].state.toString().toLowerCase());
            //this is unsafe we should make sure there is exactly as many plots as gridChildren
            //the idea is we make one onAction that somehow knows the buttonID of hte button that called it
            //then we use that index to get into farm and do stuff to the appropriate plot
            //i think we can make the button useless unless state is mature and there's enough space in inventory
            //at least that's how it works now
        }

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

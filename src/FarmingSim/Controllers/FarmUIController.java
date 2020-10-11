package FarmingSim.Controllers;

import FarmingSim.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class FarmUIController extends UIUpdateable {

    @FXML
    public Text moneyDisplay;

    @FXML
    private Text dayDisplay;
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
    private Text cornCropText;
    @FXML
    private Text wheatCropText;
    @FXML
    private Text tobaccoCropText;
    @FXML
    private Text hempCropText;
    @FXML
    private Text maxCropInventoryText;


    public FarmUIController() {
        super(2);
    }

    @FXML
    public void firstInit() {
        moneyDisplay.setText("Money: " + Inventory.money);
        maxSeedInventoryText.setText("Max Inventory: " + Inventory.MAX_SEED_INVENTORY);
        cornSeedText.setText("Corn: " + Inventory.seedNum[GameState.CropType.CORN.ordinal()]);
        wheatSeedText.setText("Wheat: " + Inventory.seedNum[GameState.CropType.WHEAT.ordinal()]);
        tobaccoSeedText.setText("Tobacco: " + Inventory.seedNum[GameState.CropType.TOBACCO.ordinal()]);
        hempSeedText.setText("Hemp: " + Inventory.seedNum[GameState.CropType.HEMP.ordinal()]);
        farmName.setText(CustomizationPageController.name + "'s Farm");

        maxCropInventoryText.setText("Max Inventory: " + Inventory.MAX_CROP_INVENTORY);
        cornCropText.setText("Corn: " + Inventory.cropNum[GameState.CropType.CORN.ordinal()]);
        wheatCropText.setText("Wheat: " + Inventory.cropNum[GameState.CropType.WHEAT.ordinal()]);
        tobaccoCropText.setText("Tobacco: " + Inventory.cropNum[GameState.CropType.TOBACCO.ordinal()]);
        hempCropText.setText("Hemp: " + Inventory.cropNum[GameState.CropType.HEMP.ordinal()]);

        season.setText("Season: " + CustomizationPageController.season);
        dayDisplay.setText("Day :" + Inventory.day);

        Button[] gridChildren = farmGrid.getChildren().toArray(new Button[0]);
        GameState.plots = new Plot[gridChildren.length];
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            n.setId(Integer.toString(i));
            GameState.plots[i] = new Plot();
            n.setText(GameState.plots[i].cropType.name().toLowerCase() + "\n"
                + GameState.plots[i].state.name().toLowerCase());
            n.setOnMouseClicked((MouseEvent e) -> harvest(e));
            //this is unsafe we should make sure there is exactly as many plots as gridChildren
            //the idea is we make one onAction that somehow knows the buttonID of hte button that called it
            //then we use that index to get into farm and do stuff to the appropriate plot
            //i think we can make the button useless unless state is mature and there's enough space in inventory
            //at least that's how it works now
        }
    }

    public void harvest(MouseEvent e) {
        Node ivm = (Node) e.getSource();
        int id = Integer.parseInt(ivm.getId());
        GameState.plots[id].harvest();
        updateUI();
    }

    @FXML
    public void updateUI() {
        if (moneyDisplay == null) {
            return;
        }
        moneyDisplay.setText("Money: " + Inventory.money);
        maxSeedInventoryText.setText("Max Inventory: " + Inventory.MAX_SEED_INVENTORY);
        cornSeedText.setText("Corn: " + Inventory.seedNum[GameState.CropType.CORN.ordinal()]);
        wheatSeedText.setText("Wheat: " + Inventory.seedNum[GameState.CropType.WHEAT.ordinal()]);
        tobaccoSeedText.setText("Tobacco: " + Inventory.seedNum[GameState.CropType.TOBACCO.ordinal()]);
        hempSeedText.setText("Hemp: " + Inventory.seedNum[GameState.CropType.HEMP.ordinal()]);
        farmName.setText(CustomizationPageController.name + "'s Farm");

        maxCropInventoryText.setText("Max Inventory: " + Inventory.MAX_CROP_INVENTORY);
        cornCropText.setText("Corn: " + Inventory.cropNum[GameState.CropType.CORN.ordinal()]);
        wheatCropText.setText("Wheat: " + Inventory.cropNum[GameState.CropType.WHEAT.ordinal()]);
        tobaccoCropText.setText("Tobacco: " + Inventory.cropNum[GameState.CropType.TOBACCO.ordinal()]);
        hempCropText.setText("Hemp: " + Inventory.cropNum[GameState.CropType.HEMP.ordinal()]);


        season.setText("Season: " + CustomizationPageController.season);
        dayDisplay.setText("Day :" + Inventory.day);

        Button[] gridChildren = farmGrid.getChildren().toArray(new Button[0]);
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            n.setId(Integer.toString(i));
            n.setText(GameState.plots[i].cropType.name().toLowerCase() + "\n"
                    + GameState.plots[i].state.name().toLowerCase());
            n.setOnMouseClicked((MouseEvent e) -> harvest(e));
        }
    }

    public void move_on(ActionEvent e) throws Exception {
        GameState.screenManager.setScreen(
                "Market",
                "FXML/Market.fxml"
        );
    }
}

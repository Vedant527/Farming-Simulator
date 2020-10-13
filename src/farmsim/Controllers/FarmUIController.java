package farmsim.Controllers;

import farmsim.GameState;
import farmsim.Plot;
import farmsim.UIUpdateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class FarmUIController extends UIUpdateable {

    @FXML
    private Text moneyDisplay;

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
        moneyDisplay.setText("Money: " + GameState.getInventory().getMoney());
        maxSeedInventoryText.setText("Max Inventory"
                + GameState.getInventory().getMaxSeedInventory());
        cornSeedText.setText(
                "Corn: " + GameState.getInventory()
                        .getSeedNum()[GameState.CropType.CORN.ordinal()]);
        wheatSeedText.setText(
                "Wheat: " + GameState.getInventory()
                        .getSeedNum()[GameState.CropType.WHEAT.ordinal()]);
        tobaccoSeedText.setText(
                "Tobacco: " + GameState.getInventory()
                        .getSeedNum()[GameState.CropType.TOBACCO.ordinal()]);
        hempSeedText.setText(
                "Hemp: " + GameState.getInventory()
                        .getSeedNum()[GameState.CropType.HEMP.ordinal()]);
        farmName.setText(GameState.getName() + "'s Farm");

        maxCropInventoryText.setText(
                "Max Inventory" + GameState.getInventory().getMaxCropInventory());
        cornCropText.setText(
                "Corn: " + GameState.getInventory()
                        .getCropNum()[GameState.CropType.CORN.ordinal()]);
        wheatCropText.setText(
                "Wheat: " + GameState.getInventory()
                        .getCropNum()[GameState.CropType.WHEAT.ordinal()]);
        tobaccoCropText.setText(
                "Tobacco: " + GameState.getInventory()
                        .getCropNum()[GameState.CropType.TOBACCO.ordinal()]);
        hempCropText.setText(
                "Hemp: " + GameState.getInventory()
                        .getCropNum()[GameState.CropType.HEMP.ordinal()]);

        season.setText("Season: " + GameState.getSeason());
        dayDisplay.setText("Day :" + GameState.getDay());

        Button[] gridChildren = farmGrid.getChildren().toArray(new Button[0]);
        GameState.setPlots(new Plot[gridChildren.length]);
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            n.setId(Integer.toString(i));
            GameState.setPlots(i, new Plot());
            n.setText(GameState.getPlots(i).getCropType().name().toLowerCase() + "\n"
                    + GameState.getPlots(i).getState().name().toLowerCase());
            n.setOnMouseClicked((MouseEvent e) -> harvest(e));
        }
    }

    public void harvest(MouseEvent e) {
        Node ivm = (Node) e.getSource();
        int id = Integer.parseInt(ivm.getId());
        GameState.getPlots(id).harvest();
        updateUI();
    }

    @FXML
    public void updateUI() {
        if (moneyDisplay == null) {
            return;
        }
        moneyDisplay.setText("Money: " + GameState.getInventory().getMoney());
        maxSeedInventoryText.setText(
                "Max GameState.getInventory(): " + GameState.getInventory().getMaxSeedInventory());
        cornSeedText.setText(
                "Corn: " + GameState.getInventory()
                        .getSeedNum()[GameState.CropType.CORN.ordinal()]);
        wheatSeedText.setText(
                "Wheat: " + GameState.getInventory()
                        .getSeedNum()[GameState.CropType.WHEAT.ordinal()]);
        tobaccoSeedText.setText(
                "Tobacco: " + GameState.getInventory()
                        .getSeedNum()[GameState.CropType.TOBACCO.ordinal()]);
        hempSeedText.setText(
                "Hemp: " + GameState.getInventory()
                        .getSeedNum()[GameState.CropType.HEMP.ordinal()]);
        farmName.setText(GameState.getName() + "'s Farm");

        maxCropInventoryText.setText(
                "Max GameState.getInventory(): " + GameState.getInventory().getMaxCropInventory());
        cornCropText.setText(
                "Corn: " + GameState.getInventory()
                        .getCropNum()[GameState.CropType.CORN.ordinal()]);
        wheatCropText.setText(
                "Wheat: " + GameState.getInventory()
                        .getCropNum()[GameState.CropType.WHEAT.ordinal()]);
        tobaccoCropText.setText(
                "Tobacco: " + GameState.getInventory()
                        .getCropNum()[GameState.CropType.TOBACCO.ordinal()]);
        hempCropText.setText(
                "Hemp: " + GameState.getInventory()
                        .getCropNum()[GameState.CropType.HEMP.ordinal()]);


        season.setText("Season: " + GameState.getSeason());
        dayDisplay.setText("Day :" + GameState.getDay());

        Button[] gridChildren = farmGrid.getChildren().toArray(new Button[0]);
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            n.setId(Integer.toString(i));
            n.setText(GameState.getPlots(i).getCropType().name().toLowerCase() + "\n"
                    + GameState.getPlots(i).getState().name().toLowerCase());
            n.setOnMouseClicked((MouseEvent e) -> harvest(e));
        }
    }

    public void moveOn(ActionEvent e) throws Exception {
        GameState.getScreenManager().setScreen(
                "FXML/Market.fxml"
        );
    }
}

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

    private int clicks = 0;
    private final int MAXCLICKS = 30;

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
        setTexts();
        Button[] gridChildren = farmGrid.getChildren().toArray(new Button[0]);
        GameState.setPlots(new Plot[gridChildren.length]);
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            n.setId(Integer.toString(i));
            GameState.setPlots(i, new Plot());
            n.setText(GameState.getPlots(i).getCropType().name().toLowerCase() + "\n"
                    + GameState.getPlots(i).getState().name().toLowerCase() + "\nWater:"
                    + GameState.getPlots(i).getWaterLevel());
            n.setOnMouseClicked((MouseEvent e) -> clickPlot(e));
        }
    }

    public void clickPlot(MouseEvent e) {
        //check if clicks big enough to increment day
        clicks++;
        if (clicks % MAXCLICKS == 0) {
            GameState.incrementDay();
        }
        Node ivm = (Node) e.getSource();
        int id = Integer.parseInt(ivm.getId());
        if (GameState.getPlots(id).getState() == Plot.CropState.MATURE ||
            GameState.getPlots(id).getState() == Plot.CropState.DEAD) {
            harvest(e, id);
        } else if (GameState.getPlots(id).getState() == Plot.CropState.EMPTY) {
            plant(e, id);
        } else {
            water(e, id);
        }
    }

    public void water(MouseEvent e, int id) {
        GameState.getPlots(id).increaseWater();
        updateUI();
    }

    public void plant(MouseEvent e, int id) {
        GameState.getPlots(id).plant(GameState.getCropType());
        updateUI();
    }

    public void harvest(MouseEvent e, int id) {
        GameState.getPlots(id).harvest();
        updateUI();
    }

    @FXML
    public void updateUI() {
        if (moneyDisplay == null) {
            return;
        }
        setTexts();
        Button[] gridChildren = farmGrid.getChildren().toArray(new Button[0]);
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            n.setId(Integer.toString(i));
            n.setText(GameState.getPlots(i).getCropType().name().toLowerCase() + "\n"
                    + GameState.getPlots(i).getState().name().toLowerCase() + "\nWater:"
                    + GameState.getPlots(i).getWaterLevel());
            n.setOnMouseClicked((MouseEvent e) -> clickPlot(e));
        }
    }

    public void setTexts() {
        moneyDisplay.setText("Money: " + GameState.getInventory().getMoney());
        maxSeedInventoryText.setText(
                "Max Inventory: " + GameState.getInventory().getMaxSeedInventory());
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
                "Max Inventory: " + GameState.getInventory().getMaxCropInventory());
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
        dayDisplay.setText("Day: " + GameState.getDay());
    }

    public void incrementDay(ActionEvent e) throws Exception {
        GameState.incrementDay();
        clicks = 0;
        updateUI();
    }

    public void moveOn(ActionEvent e) throws Exception {
        GameState.getScreenManager().setScreen(
                "FXML/Market.fxml"
        );
    }
}

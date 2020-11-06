package farmsim.Controllers;

import farmsim.Crop;
import farmsim.GameState;
import farmsim.Plot;
import farmsim.UIUpdateable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Random;

public class FarmUIController extends UIUpdateable {

    private int clicks = 0;
    private final int maxClicks = 30;

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
    private ChoiceBox seedBox;

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

    @FXML
    private Text pesticideText;
    @FXML
    private Text fertilizerText;

    @FXML
    private Text waterText;
    @FXML
    private Text fertilizerLevelText;
    @FXML
    private Text pesticideLevelText;
    @FXML
    private Text cropTypeText;
    @FXML
    private Text cropStateText;

    @FXML
    private Text organicCornText;
    @FXML
    private Text pesticideCornText;
    @FXML
    private Text organicWheatText;
    @FXML
    private Text pesticideWheatText;
    @FXML
    private Text organicTobaccoText;
    @FXML
    private Text pesticideTobaccoText;
    @FXML
    private Text organicHempText;
    @FXML
    private Text pesticideHempText;


    public FarmUIController() {
        super(2);
    }

    @FXML
    public void firstInit() {
        Button[] gridChildren = farmGrid.getChildren().toArray(new Button[0]);
        GameState.setPlots(new Plot[gridChildren.length]);
        Random random = new Random();
        seedBox.setItems(FXCollections
                .observableArrayList(GameState.CropType.values()));
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            n.setId(Integer.toString(i));
            GameState.setPlots(i, new Plot());

            Crop.State[] tmp = Crop.State.values();
            Crop.State rand = tmp[random.nextInt(tmp.length)];
            GameState.getPlots(i).setCropState(rand);
            n.setText(GameState.getPlots(i).getCrop().getType().name().toLowerCase() + "\n"
                    + GameState.getPlots(i).getCrop().getState().name().toLowerCase() + "\nWater:"
                    + GameState.getPlots(i).getWaterLevel());
            n.setOnMouseClicked((MouseEvent e) -> clickPlot(e));
            n.setStyle("-fx-background-color: #d0c3a2");
        }
        GameState.setCurrPlot(GameState.getPlots(0));
        setTexts();
    }

    public void clickPlot(MouseEvent e) {
        //either refreshes the display to the left or harvests
        //check if clicks big enough to increment day
        //clicks++;
        //if (clicks % maxClicks == 0) {
        //    GameState.incrementDay();
        //}
        Node ivm = (Node) e.getSource();
        int id = Integer.parseInt(ivm.getId());
        Plot curr = GameState.getPlots(id);
        GameState.setCurrPlot(curr);

        Button[] gridChildren = farmGrid.getChildren().toArray(new Button[0]);
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            if (i == id) {
                n.setStyle("-fx-background-color: #ffc900");
            } else {
                n.setStyle("-fx-background-color: #d0c3a2");
            }
        }

        if (curr.getCrop().getState() == Crop.State.MATURE
            || curr.getCrop().getState() == Crop.State.DEAD) {
            harvest(curr);
        } else if (curr.getCrop().getState() == Crop.State.EMPTY) {
            plant(curr);
        } else {
            updateUI();
        }
    }

    public void water(ActionEvent e) {
        //i think this is safe bc curr plot will always be selected for this button
        //to be available
        GameState.getCurrPlot().increaseWater();
        updateUI();
    }

    public void fertilize(ActionEvent e) {
        GameState.getCurrPlot().increaseFertilizerLevel();
        updateUI();
    }

    public void pesticide(ActionEvent e) {
        GameState.getCurrPlot().increasePesticideLevel();
        updateUI();
    }

    public void plant(Plot plt) {
        plt.plant(GameState.getCropType());
        updateUI();
    }

    public void harvest(Plot plt) {
        plt.harvest();
        updateUI();
    }

    public void switchSeed(ActionEvent e) {
        if (seedBox.getValue() != null) {
            GameState.setCropType((GameState.CropType) seedBox.getValue());
        }
        seedBox.setValue(GameState.getCropType());
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
            n.setText(GameState.getPlots(i).getCrop().getType().name().toLowerCase() + "\n"
                    + GameState.getPlots(i).getCrop().getState().name().toLowerCase() + "\nWater:"
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
        cornCropText.setText("Corn: ");
        wheatCropText.setText("Wheat: ");
        tobaccoCropText.setText("Tobacco: ");
        hempCropText.setText("Hemp: ");
        organicCornText.setText(String.valueOf(GameState.getInventory()
                .get(GameState.CropType.CORN, true)));
        organicWheatText.setText(String.valueOf(GameState.getInventory()
                .get(GameState.CropType.WHEAT, true)));
        organicTobaccoText.setText(String.valueOf(GameState.getInventory()
                .get(GameState.CropType.TOBACCO, true)));
        organicHempText.setText(String.valueOf(GameState.getInventory()
                .get(GameState.CropType.HEMP, true)));
        pesticideCornText.setText(String.valueOf(GameState.getInventory()
                .get(GameState.CropType.CORN, false)));
        pesticideWheatText.setText(String.valueOf(GameState.getInventory()
                .get(GameState.CropType.WHEAT, false)));
        pesticideTobaccoText.setText(String.valueOf(GameState.getInventory()
                .get(GameState.CropType.TOBACCO, false)));
        pesticideHempText.setText(String.valueOf(GameState.getInventory()
                .get(GameState.CropType.HEMP, false)));

        pesticideText.setText("Pesticides: " + GameState.getInventory().getPesticideNum());
        fertilizerText.setText("Fertilizer: " + GameState.getInventory().getFertilizerNum());


        season.setText("Season: " + GameState.getSeason());
        dayDisplay.setText("Day: " + GameState.getDay());

        seedBox.setItems(FXCollections.observableArrayList(GameState.CropType.values()));
        seedBox.setValue(GameState.getCropType());

        //all the curr plot texts
        waterText.setText("Water: " + GameState.getCurrPlot().getWaterLevel());
        fertilizerLevelText.setText("Fertilizer: " + GameState.getCurrPlot().getFertilizerLevel());
        pesticideLevelText.setText("Pesticide: " + GameState.getCurrPlot().getPesticideLevel());
        cropTypeText.setText("Crop: " + GameState.getCurrPlot().getCrop().getType());
        cropStateText.setText("Age: " + GameState.getCurrPlot().getCrop().getState());
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

package farmsim.Controllers;

import farmsim.Crop;
import farmsim.GameState;
import farmsim.Plot;
import farmsim.UIUpdateable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
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
                .observableArrayList(Crop.Type.values()));
        for (int i = 0; i < gridChildren.length; i++) {
            Button n = gridChildren[i];
            n.setId(Integer.toString(i));
            GameState.setPlots(i, new Plot());

            Crop.State[] tmp = Crop.State.values();
            Crop.State rand = tmp[random.nextInt(tmp.length)];
            GameState.getPlots(i).setCropState(rand);
            n.setText(GameState.getPlots(i).getCrop().toString() +"\n"
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
        clicks++;
        if (clicks % maxClicks == 0) {
            GameState.incrementDay();
        }
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
            GameState.setCropType((Crop.Type) seedBox.getValue());
        }
        seedBox.setValue(GameState.getCropType());
    }

    public void disaster() {
        int causeRain = 1; // if random == causeX, X event will occur
        int causeDrought = 2;
        int causeLocusts = 3;

        int random = 0;

        if (GameState.getDifficulty() == GameState.Difficulty.EASY) {
            random = ((int) (Math.random() * 50) + 1);
        } else if (GameState.getDifficulty() == GameState.Difficulty.MEDIUM) {
            random = ((int) (Math.random() * 35) + 1);
        } else if (GameState.getDifficulty() == GameState.Difficulty.HARD) {
            random = ((int) (Math.random() * 4) + 1);
        }

        if (random == causeRain) {
            rain();
        }

        if (random == causeDrought) {
            drought();
        }

        if (random == causeLocusts) {
            unleashLocusts();
        }
    }

    public void rain() {
        int increment = ((int) (Math.random() * 2) + 1);
        for (int plot = 0; plot < GameState.getPlots().length; plot++) {

            if (increment + GameState.getPlots(plot).getWaterLevel() - 1 >= 6) {
                GameState.getPlots(plot).getCrop().kill();
                GameState.getPlots(plot).setWaterLevel(0);
            } else {
                GameState.getPlots(plot).setWaterLevel(GameState.getPlots(plot).getWaterLevel() + increment);
            }
        }

        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.WARNING);
        a.setContentText("It stormed like a *****! Your plants' water levels increase by " + increment);
        a.show();
    }

    public void drought() {
        int decrement = ((int) (Math.random() * 2) + 1);
        for (int plot = 0; plot < GameState.getPlots().length; plot++) {

            for (int i = 0; i < decrement; i++) {
                GameState.getPlots(plot).decreaseWater();
            }

        }

        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.WARNING);
        a.setContentText("Uh oh, a drought has occurred! Your plants' water levels decreased by " + (decrement - 1));
        a.show();
    }

    public void unleashLocusts() {
        ArrayList<Integer>  affectedPlants = new ArrayList<>();
        int deathtoll = 0;
        for (int plot = 0; plot < GameState.getPlots().length; plot++) {
            int chanceOfDeath = ((int) (Math.random() * 3) + plot);

            if (GameState.getPlots(plot).getCrop().getState() != Crop.State.DEAD &&
                    plot == chanceOfDeath && !(GameState.getPlots(plot).getFertilizerLevel() > 0)) {
                GameState.getPlots(plot).getCrop().setState(Crop.State.DEAD);
                deathtoll++;
                affectedPlants.add(plot + 1);
            }
        }

        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.WARNING);
        a.setContentText("The enemy locusts have attacked ヽ(ಠ_ಠ)ノ, we lost " + deathtoll + " plants, soldier! " +
                        "Crops affected: " + affectedPlants);
        a.show();
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
            n.setText(GameState.getPlots(i).getCrop().toString() +"\n"
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
                "Corn: " + GameState.getInventory().getSeedNum(Crop.Type.CORN));
        wheatSeedText.setText(
                "Wheat: " + GameState.getInventory().getSeedNum(Crop.Type.WHEAT));
        tobaccoSeedText.setText(
                "Tobacco: " + GameState.getInventory().getSeedNum(Crop.Type.TOBACCO));
        hempSeedText.setText(
                "Hemp: " + GameState.getInventory().getSeedNum(Crop.Type.HEMP));
        farmName.setText(GameState.getName() + "'s Farm");

        maxCropInventoryText.setText(
                "Max Inventory: " + GameState.getInventory().getMaxCropInventory());
        cornCropText.setText("Corn: ");
        wheatCropText.setText("Wheat: ");
        tobaccoCropText.setText("Tobacco: ");
        hempCropText.setText("Hemp: ");
        organicCornText.setText(String.valueOf(GameState.getInventory()
                .getCropNum(Crop.Type.CORN, true)));
        organicWheatText.setText(String.valueOf(GameState.getInventory()
                .getCropNum(Crop.Type.WHEAT, true)));
        organicTobaccoText.setText(String.valueOf(GameState.getInventory()
                .getCropNum(Crop.Type.TOBACCO, true)));
        organicHempText.setText(String.valueOf(GameState.getInventory()
                .getCropNum(Crop.Type.HEMP, true)));
        pesticideCornText.setText(String.valueOf(GameState.getInventory()
                .getCropNum(Crop.Type.CORN, false)));
        pesticideWheatText.setText(String.valueOf(GameState.getInventory()
                .getCropNum(Crop.Type.WHEAT, false)));
        pesticideTobaccoText.setText(String.valueOf(GameState.getInventory()
                .getCropNum(Crop.Type.TOBACCO, false)));
        pesticideHempText.setText(String.valueOf(GameState.getInventory()
                .getCropNum(Crop.Type.HEMP, false)));

        pesticideText.setText("Pesticides: " + GameState.getInventory().getPesticideNum());
        fertilizerText.setText("Fertilizer: " + GameState.getInventory().getFertilizerNum());


        season.setText("Season: " + GameState.getSeason());
        dayDisplay.setText("Day: " + GameState.getDay());

        seedBox.setItems(FXCollections.observableArrayList(Crop.Type.values()));
        seedBox.setValue(GameState.getCropType());

        //all the curr plot texts
        waterText.setText("Water: " + GameState.getCurrPlot().getWaterLevel());
        fertilizerLevelText.setText("Fertilizer: " + GameState.getCurrPlot().getFertilizerLevel());
        pesticideLevelText.setText("Pesticide: " + GameState.getCurrPlot().getPesticideLevel());
        cropTypeText.setText("Crop: " + GameState.getCurrPlot().getCrop().getType());
        cropStateText.setText("Age: " + GameState.getCurrPlot().getCrop().getState());
    }

    public void incrementDay(ActionEvent e) throws Exception {
        disaster();
        GameState.incrementDay();
        clicks = 0;
        updateUI();
    }

    public void moveOn(ActionEvent e) throws Exception {
        disaster();
        GameState.getScreenManager().setScreen(
                "FXML/Market.fxml"
        );
    }

    public void moveHands(ActionEvent e) throws Exception {
        GameState.getScreenManager().setScreen(
                "FXML/Hands.fxml"
        );
    }
}

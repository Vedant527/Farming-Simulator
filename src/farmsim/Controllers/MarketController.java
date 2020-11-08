package farmsim.Controllers;

import farmsim.Crop;
import farmsim.GameState;
import farmsim.UIUpdateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MarketController extends UIUpdateable {
    //will access GameState.getInventory() when you buy and sell
    //rn, you can only buy 1 seed at a time

    @FXML
    private Button buyCornButton;
    @FXML
    private Button buyWheatButton;
    @FXML
    private Button buyTobaccoButton;
    @FXML
    private Button buyHempButton;
    @FXML
    private Button buyPesticidesButton;
    @FXML
    private Button buyFertilizerButton;
    @FXML
    private Button sellOrganicCornButton;
    @FXML
    private Button sellOrganicWheatButton;
    @FXML
    private Button sellOrganicTobaccoButton;
    @FXML
    private Button sellOrganicHempButton;
    @FXML
    private Button sellPesticideCornButton;
    @FXML
    private Button sellPesticideWheatButton;
    @FXML
    private Button sellPesticideTobaccoButton;
    @FXML
    private Button sellPesticideHempButton;

    @FXML
    private Text dayMarketDisplay;
    @FXML
    private Text moneyDisplay;
    @FXML
    private Text seasonMarketDisplay;

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
    private Text pesticideText;
    @FXML
    private Text fertilizerText;

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

    public MarketController() {
        super(3);
    }

    public void firstInit() {
        setTexts();
    }


    public void buyCorn() {
        GameState.getInventory().buyImpl(Crop.Type.CORN);
        updateUI();
    }

    public void buyWheat() {
        GameState.getInventory().buyImpl(Crop.Type.WHEAT);
        updateUI();
    }

    public void buyTobacco() {
        GameState.getInventory().buyImpl(Crop.Type.TOBACCO);
        updateUI();
    }

    public void buyHemp() {
        GameState.getInventory().buyImpl(Crop.Type.HEMP);
        updateUI();
    }

    public void buyFertilizer() {
        GameState.getInventory().buyFert();
        updateUI();
    }

    public void buyPesticides() {
        GameState.getInventory().buyPest();
        updateUI();
    }

    public void sellOrganicCorn() {
        GameState.getInventory().sellImpl(Crop.Type.CORN, true);
        updateUI();
    }

    public void sellOrganicWheat() {
        GameState.getInventory().sellImpl(Crop.Type.WHEAT, true);
        updateUI();
    }

    public void sellOrganicTobacco() {
        GameState.getInventory().sellImpl(Crop.Type.TOBACCO, true);
        updateUI();
    }

    public void sellOrganicHemp() {
        GameState.getInventory().sellImpl(Crop.Type.HEMP, true);
        updateUI();
    }

    public void sellPesticideCorn() {
        GameState.getInventory().sellImpl(Crop.Type.CORN, false);
        updateUI();
    }

    public void sellPesticideWheat() {
        GameState.getInventory().sellImpl(Crop.Type.WHEAT, false);
        updateUI();
    }

    public void sellPesticideTobacco() {
        GameState.getInventory().sellImpl(Crop.Type.TOBACCO, false);
        updateUI();
    }

    public void sellPesticideHemp() {
        GameState.getInventory().sellImpl(Crop.Type.HEMP, false);
        updateUI();
    }


    public void moveBack(ActionEvent e) throws Exception {
        GameState.incrementDay();
        GameState.getScreenManager().setScreen(
                "FXML/FarmUI.fxml"
        );
    }

    public void updateUI() {
        setTexts();
    }

    public void setTexts() {
        buyCornButton.setText(
                "$" + GameState.getInventory().getSeedPrices()[Crop.Type.CORN.ordinal()]);
        buyWheatButton.setText(
                "$" + GameState.getInventory().getSeedPrices()[Crop.Type.WHEAT.ordinal()]);
        buyTobaccoButton.setText(
                "$" + GameState.getInventory()
                        .getSeedPrices()[Crop.Type.TOBACCO.ordinal()]);
        buyHempButton.setText(
                "$" + GameState.getInventory().getSeedPrices()[Crop.Type.HEMP.ordinal()]);
        buyPesticidesButton.setText("$" + GameState.getInventory().getPesticidePrice());
        buyFertilizerButton.setText("$" + GameState.getInventory().getFertilizerPrice());
        moneyDisplay.setText(
                "Money: $" + GameState.getInventory().getMoney());
        dayMarketDisplay.setText("Day: " + GameState.getDay());
        seasonMarketDisplay.setText("Season: " + GameState.getSeason().toString());


        sellOrganicCornButton.setText(
                "$" + GameState.getInventory().getCropPrices()[Crop.Type.CORN.ordinal()]);
        sellPesticideCornButton.setText(
                "$" + String.valueOf(GameState.getInventory().getCropPrices()
                        [Crop.Type.CORN.ordinal()] - 5));
        sellOrganicWheatButton.setText(
                "$" + GameState.getInventory().getCropPrices()[Crop.Type.WHEAT.ordinal()]);
        sellPesticideWheatButton.setText(
                "$" + String.valueOf(GameState.getInventory().getCropPrices()
                        [Crop.Type.WHEAT.ordinal()] - 5));
        sellOrganicTobaccoButton.setText(
                "$" + GameState.getInventory()
                        .getCropPrices()[Crop.Type.TOBACCO.ordinal()]);
        sellPesticideTobaccoButton.setText(
                "$" + String.valueOf(GameState.getInventory().getCropPrices()
                        [Crop.Type.TOBACCO.ordinal()] - 5));
        sellOrganicHempButton.setText(
                "$" + GameState.getInventory().getCropPrices()[Crop.Type.HEMP.ordinal()]);
        sellPesticideHempButton.setText(
                "$" + String.valueOf(GameState.getInventory().getCropPrices()
                        [Crop.Type.HEMP.ordinal()] - 5));

        //inventory texts
        maxSeedInventoryText.setText(
                "Max Inventory: " + GameState.getInventory().getMaxSeedInventory());
        cornSeedText.setText(
                "Corn: " + GameState.getInventory()
                        .getSeedNum(Crop.Type.CORN));
        wheatSeedText.setText(
                "Wheat: " + GameState.getInventory()
                        .getSeedNum(Crop.Type.WHEAT));
        tobaccoSeedText.setText(
                "Tobacco: " + GameState.getInventory()
                        .getSeedNum(Crop.Type.TOBACCO));
        hempSeedText.setText(
                "Hemp: " + GameState.getInventory()
                        .getSeedNum(Crop.Type.HEMP));

        maxCropInventoryText.setText(
                "Max Inventory: " + GameState.getInventory().getMaxCropInventory());
        cornCropText.setText("Corn: ");
        wheatCropText.setText("Wheat: ");
        tobaccoCropText.setText("Tobacco: ");
        hempCropText.setText("Hemp: ");
        organicCornText.setText(String.valueOf(GameState.getInventory()
                .get(Crop.Type.CORN, true)));
        organicWheatText.setText(String.valueOf(GameState.getInventory()
                .get(Crop.Type.WHEAT, true)));
        organicTobaccoText.setText(String.valueOf(GameState.getInventory()
                .get(Crop.Type.TOBACCO, true)));
        organicHempText.setText(String.valueOf(GameState.getInventory()
                .get(Crop.Type.HEMP, true)));
        pesticideCornText.setText(String.valueOf(GameState.getInventory()
                .get(Crop.Type.CORN, false)));
        pesticideWheatText.setText(String.valueOf(GameState.getInventory()
                .get(Crop.Type.WHEAT, false)));
        pesticideTobaccoText.setText(String.valueOf(GameState.getInventory()
                .get(Crop.Type.TOBACCO, false)));
        pesticideHempText.setText(String.valueOf(GameState.getInventory()
                .get(Crop.Type.HEMP, false)));

        pesticideText.setText("Pesticides: " + GameState.getInventory().getPesticideNum());
        fertilizerText.setText("Fertilizer: " + GameState.getInventory().getFertilizerNum());
    }
}

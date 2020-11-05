package farmsim.Controllers;

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
        for (int i = 0; i < GameState.getInventory().getSeedPrices().length; i++) {
            GameState.getInventory().setSeedPrices(
                    i, GameState.getInventory().calculatePriceFromDifficulty(
                            GameState.getInventory().getSeedPrices()[i]));
            GameState.getInventory().setCropPrices(
                    i, GameState.getInventory().calculatePriceFromDifficulty(
                            GameState.getInventory().getCropPrices()[i]));
        }
        GameState.getInventory().setFertilizerPrice(
                GameState.getInventory().calculatePriceFromDifficulty(
                        GameState.getInventory().getFertilizerPrice()));
        GameState.getInventory().setPesticidePrice(
                GameState.getInventory().calculatePriceFromDifficulty(
                        GameState.getInventory().getPesticidePrice()));
        setTexts();
    }


    public void buyCorn() {
        GameState.getInventory().buyImpl(GameState.CropType.CORN);
        updateUI();
    }

    public void buyWheat() {
        GameState.getInventory().buyImpl(GameState.CropType.WHEAT);
        updateUI();
    }

    public void buyTobacco() {
        GameState.getInventory().buyImpl(GameState.CropType.TOBACCO);
        updateUI();
    }

    public void buyHemp() {
        GameState.getInventory().buyImpl(GameState.CropType.HEMP);
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
        GameState.getInventory().sellImpl(GameState.CropType.CORN, true);
        updateUI();
    }

    public void sellOrganicWheat() {
        GameState.getInventory().sellImpl(GameState.CropType.WHEAT, true);
        updateUI();
    }

    public void sellOrganicTobacco() {
        GameState.getInventory().sellImpl(GameState.CropType.TOBACCO, true);
        updateUI();
    }

    public void sellOrganicHemp() {
        GameState.getInventory().sellImpl(GameState.CropType.HEMP, true);
        updateUI();
    }

    public void sellPesticideCorn() {
        GameState.getInventory().sellImpl(GameState.CropType.CORN, false);
        updateUI();
    }

    public void sellPesticideWheat() {
        GameState.getInventory().sellImpl(GameState.CropType.WHEAT, false);
        updateUI();
    }

    public void sellPesticideTobacco() {
        GameState.getInventory().sellImpl(GameState.CropType.TOBACCO, false);
        updateUI();
    }

    public void sellPesticideHemp() {
        GameState.getInventory().sellImpl(GameState.CropType.HEMP, false);
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
                "$" + GameState.getInventory().getSeedPrices()[GameState.CropType.CORN.ordinal()]);
        buyWheatButton.setText(
                "$" + GameState.getInventory().getSeedPrices()[GameState.CropType.WHEAT.ordinal()]);
        buyTobaccoButton.setText(
                "$" + GameState.getInventory()
                        .getSeedPrices()[GameState.CropType.TOBACCO.ordinal()]);
        buyHempButton.setText(
                "$" + GameState.getInventory().getSeedPrices()[GameState.CropType.HEMP.ordinal()]);
        buyPesticidesButton.setText("$" + GameState.getInventory().getPesticidePrice());
        buyFertilizerButton.setText("$" + GameState.getInventory().getFertilizerPrice());
        moneyDisplay.setText(
                "Money: $" + GameState.getInventory().getMoney());
        dayMarketDisplay.setText("Day: " + GameState.getDay());
        seasonMarketDisplay.setText("Season: " + GameState.getSeason().toString());


        sellOrganicCornButton.setText(
                "$" + GameState.getInventory().getCropPrices()[GameState.CropType.CORN.ordinal()]);
        sellPesticideCornButton.setText(
                "$" + String.valueOf(GameState.getInventory().getCropPrices()
                        [GameState.CropType.CORN.ordinal()] - 5));
        sellOrganicWheatButton.setText(
                "$" + GameState.getInventory().getCropPrices()[GameState.CropType.WHEAT.ordinal()]);
        sellPesticideWheatButton.setText(
                "$" + String.valueOf(GameState.getInventory().getCropPrices()
                        [GameState.CropType.WHEAT.ordinal()] - 5));
        sellOrganicTobaccoButton.setText(
                "$" + GameState.getInventory()
                        .getCropPrices()[GameState.CropType.TOBACCO.ordinal()]);
        sellPesticideTobaccoButton.setText(
                "$" + String.valueOf(GameState.getInventory().getCropPrices()
                        [GameState.CropType.TOBACCO.ordinal()] - 5));
        sellOrganicHempButton.setText(
                "$" + GameState.getInventory().getCropPrices()[GameState.CropType.HEMP.ordinal()]);
        sellPesticideHempButton.setText(
                "$" + String.valueOf(GameState.getInventory().getCropPrices()
                        [GameState.CropType.HEMP.ordinal()] - 5));

        //inventory texts
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

        maxCropInventoryText.setText(
                "Max Inventory: " + GameState.getInventory().getMaxCropInventory());
        cornCropText.setText("Corn: ");
        wheatCropText.setText("Wheat: ");
        tobaccoCropText.setText("Tobacco: ");
        hempCropText.setText("Hemp: ");
        organicCornText.setText(String.valueOf(GameState.getInventory().
                getOrganicCropNum()[GameState.CropType.CORN.ordinal()]));
        organicWheatText.setText(String.valueOf(GameState.getInventory()
                .getOrganicCropNum()[GameState.CropType.WHEAT.ordinal()]));
        organicTobaccoText.setText(String.valueOf(GameState.getInventory()
                .getOrganicCropNum()[GameState.CropType.TOBACCO.ordinal()]));
        organicHempText.setText(String.valueOf(GameState.getInventory()
                .getOrganicCropNum()[GameState.CropType.HEMP.ordinal()]));
        pesticideCornText.setText(String.valueOf(GameState.getInventory().
                getPesticideCropNum()[GameState.CropType.CORN.ordinal()]));
        pesticideWheatText.setText(String.valueOf(GameState.getInventory().
                getPesticideCropNum()[GameState.CropType.WHEAT.ordinal()]));
        pesticideTobaccoText.setText(String.valueOf(GameState.getInventory().
                getPesticideCropNum()[GameState.CropType.TOBACCO.ordinal()]));
        pesticideHempText.setText(String.valueOf(GameState.getInventory().
                getPesticideCropNum()[GameState.CropType.HEMP.ordinal()]));

        pesticideText.setText("Pesticides: " + GameState.getInventory().getPesticideNum());
        fertilizerText.setText("Fertilizer: " + GameState.getInventory().getFertilizerNum());
    }
}

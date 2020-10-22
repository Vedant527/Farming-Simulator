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
    private Button sellCornButton;
    @FXML
    private Button sellWheatButton;
    @FXML
    private Button sellTobaccoButton;
    @FXML
    private Button sellHempButton;

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
    private Text cornCropText;
    @FXML
    private Text wheatCropText;
    @FXML
    private Text tobaccoCropText;
    @FXML
    private Text hempCropText;
    @FXML
    private Text maxCropInventoryText;

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

    public void sellCorn() {
        GameState.getInventory().sellImpl(GameState.CropType.CORN);
        updateUI();
    }

    public void sellWheat() {
        GameState.getInventory().sellImpl(GameState.CropType.WHEAT);
        updateUI();
    }

    public void sellTobacco() {
        GameState.getInventory().sellImpl(GameState.CropType.TOBACCO);
        updateUI();
    }

    public void sellHemp() {
        GameState.getInventory().sellImpl(GameState.CropType.HEMP);
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
                "$" + GameState.getInventory().getSeedPrices()[GameState.CropType.CORN.ordinal()]
        );
        sellCornButton.setText(
                "$" + GameState.getInventory().getCropPrices()[GameState.CropType.CORN.ordinal()]
        );
        buyWheatButton.setText(
                "$" + GameState.getInventory().getSeedPrices()[GameState.CropType.WHEAT.ordinal()]);
        sellWheatButton.setText(
                "$" + GameState.getInventory().getCropPrices()[GameState.CropType.WHEAT.ordinal()]);
        buyTobaccoButton.setText(
                "$" + GameState.getInventory()
                        .getSeedPrices()[GameState.CropType.TOBACCO.ordinal()]);
        sellTobaccoButton.setText(
                "$" + GameState.getInventory()
                        .getCropPrices()[GameState.CropType.TOBACCO.ordinal()]);
        buyHempButton.setText(
                "$" + GameState.getInventory().getSeedPrices()[GameState.CropType.HEMP.ordinal()]);
        sellHempButton.setText(
                "$" + GameState.getInventory().getCropPrices()[GameState.CropType.HEMP.ordinal()]);
        moneyDisplay.setText(
                "Money: $" + GameState.getInventory().getMoney());
        dayMarketDisplay.setText("Day: 0");
        seasonMarketDisplay.setText("Season: " + GameState.getSeason().toString());

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
                "Hemp: " + GameState.getInventory().getSeedNum()[GameState.CropType
                        .HEMP.ordinal()]);

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
    }
}

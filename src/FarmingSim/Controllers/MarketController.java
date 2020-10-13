package FarmingSim.Controllers;

import FarmingSim.Inventory;
import FarmingSim.GameState;
import FarmingSim.UIUpdateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MarketController extends UIUpdateable {
//will access Inventory when you buy and sell
    //rn, you can only buy 1 seed at a time

@FXML Button buyCornButton;
@FXML Button buyWheatButton;
@FXML Button buyTobaccoButton;
@FXML Button buyHempButton;
@FXML Button sellCornButton;
@FXML Button sellWheatButton;
@FXML Button sellTobaccoButton;
@FXML Button sellHempButton;

@FXML private Text dayMarketDisplay;
@FXML private Text moneyDisplay;
@FXML private Text seasonMarketDisplay;

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
        for (int i = 0; i < Inventory.seedPrices.length; i++) {
            Inventory.seedPrices[i] = Inventory.calculatePriceFromDifficulty(Inventory.seedPrices[i]);
            Inventory.cropPrices[i] = Inventory.calculatePriceFromDifficulty(Inventory.cropPrices[i]);
        }

        buyCornButton.setText("$" + Inventory.seedPrices[GameState.CropType.CORN.ordinal()]);
        sellCornButton.setText("$" + Inventory.cropPrices[GameState.CropType.CORN.ordinal()]);
        buyWheatButton.setText("$" + Inventory.seedPrices[GameState.CropType.WHEAT.ordinal()]);
        sellWheatButton.setText("$" + Inventory.cropPrices[GameState.CropType.WHEAT.ordinal()]);
        buyTobaccoButton.setText("$" + Inventory.seedPrices[GameState.CropType.TOBACCO.ordinal()]);
        sellTobaccoButton.setText("$" + Inventory.cropPrices[GameState.CropType.TOBACCO.ordinal()]);
        buyHempButton.setText("$" + Inventory.seedPrices[GameState.CropType.HEMP.ordinal()]);
        sellHempButton.setText("$" + Inventory.cropPrices[GameState.CropType.HEMP.ordinal()]);
        moneyDisplay.setText("Money: $" + Inventory.money);
        dayMarketDisplay.setText("Day: 0");
        seasonMarketDisplay.setText("Season: " + CustomizationPageController.season.toString());

        maxSeedInventoryText.setText("Max Inventory: " + Inventory.MAX_SEED_INVENTORY);
        cornSeedText.setText("Corn: " + Inventory.seedNum[GameState.CropType.CORN.ordinal()]);
        wheatSeedText.setText("Wheat: " + Inventory.seedNum[GameState.CropType.WHEAT.ordinal()]);
        tobaccoSeedText.setText("Tobacco: " + Inventory.seedNum[GameState.CropType.TOBACCO.ordinal()]);
        hempSeedText.setText("Hemp: " + Inventory.seedNum[GameState.CropType.HEMP.ordinal()]);

        maxCropInventoryText.setText("Max Inventory: " + Inventory.MAX_CROP_INVENTORY);
        cornCropText.setText("Corn: " + Inventory.cropNum[GameState.CropType.CORN.ordinal()]);
        wheatCropText.setText("Wheat: " + Inventory.cropNum[GameState.CropType.WHEAT.ordinal()]);
        tobaccoCropText.setText("Tobacco: " + Inventory.cropNum[GameState.CropType.TOBACCO.ordinal()]);
        hempCropText.setText("Hemp: " + Inventory.cropNum[GameState.CropType.HEMP.ordinal()]);
    }



    public void buyCorn() {
        Inventory.buyImpl(GameState.CropType.CORN);
        updateUI();
    }
    public void buyWheat() {
        Inventory.buyImpl(GameState.CropType.WHEAT);
        updateUI();
    }
    public void buyTobacco() {
        Inventory.buyImpl(GameState.CropType.TOBACCO);
        updateUI();
    }
    public void buyHemp() {
        Inventory.buyImpl(GameState.CropType.HEMP);
        updateUI();
    }
    public void sellCorn() {
        Inventory.sellImpl(GameState.CropType.CORN);
        updateUI();
    }
    public void sellWheat() {
        Inventory.sellImpl(GameState.CropType.WHEAT);
        updateUI();
    }
    public void sellTobacco() {
        Inventory.sellImpl(GameState.CropType.TOBACCO);
        updateUI();
    }
    public void sellHemp() {
        Inventory.sellImpl(GameState.CropType.HEMP);
        updateUI();
    }



    public void move_back(ActionEvent e) throws Exception {
        Inventory.day++;
        GameState.screenManager.setScreen(
                "FXML/FarmUI.fxml"
        );
    }

    public void updateUI() {
        buyCornButton.setText("$" + Inventory.seedPrices[GameState.CropType.CORN.ordinal()]);
        sellCornButton.setText("$" + Inventory.cropPrices[GameState.CropType.CORN.ordinal()]);
        buyWheatButton.setText("$" + Inventory.seedPrices[GameState.CropType.WHEAT.ordinal()]);
        sellWheatButton.setText("$" + Inventory.cropPrices[GameState.CropType.WHEAT.ordinal()]);
        buyTobaccoButton.setText("$" + Inventory.seedPrices[GameState.CropType.TOBACCO.ordinal()]);
        sellTobaccoButton.setText("$" + Inventory.cropPrices[GameState.CropType.TOBACCO.ordinal()]);
        buyHempButton.setText("$" + Inventory.seedPrices[GameState.CropType.HEMP.ordinal()]);
        sellHempButton.setText("$" + Inventory.cropPrices[GameState.CropType.HEMP.ordinal()]);
        moneyDisplay.setText("Money: $" + Inventory.money);
        dayMarketDisplay.setText("Day: 0");
        seasonMarketDisplay.setText("Season: " + CustomizationPageController.season.toString());

        maxSeedInventoryText.setText("Max Inventory: " + Inventory.MAX_SEED_INVENTORY);
        cornSeedText.setText("Corn: " + Inventory.seedNum[GameState.CropType.CORN.ordinal()]);
        wheatSeedText.setText("Wheat: " + Inventory.seedNum[GameState.CropType.WHEAT.ordinal()]);
        tobaccoSeedText.setText("Tobacco: " + Inventory.seedNum[GameState.CropType.TOBACCO.ordinal()]);
        hempSeedText.setText("Hemp: " + Inventory.seedNum[GameState.CropType.HEMP.ordinal()]);

        maxCropInventoryText.setText("Max Inventory: " + Inventory.MAX_CROP_INVENTORY);
        cornCropText.setText("Corn: " + Inventory.cropNum[GameState.CropType.CORN.ordinal()]);
        wheatCropText.setText("Wheat: " + Inventory.cropNum[GameState.CropType.WHEAT.ordinal()]);
        tobaccoCropText.setText("Tobacco: " + Inventory.cropNum[GameState.CropType.TOBACCO.ordinal()]);
        hempCropText.setText("Hemp: " + Inventory.cropNum[GameState.CropType.HEMP.ordinal()]);
    }
}

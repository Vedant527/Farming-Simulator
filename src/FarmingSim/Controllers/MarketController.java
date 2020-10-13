package FarmingSim.Controllers;

import FarmingSim.Inventory;
import FarmingSim.GameState;
import FarmingSim.UIUpdateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public MarketController() {
        super(3);
    }

    public void firstInit() {
        for (int i = 0; i < Inventory.seedPrices.length; i++) {
            Inventory.seedPrices[i] = Inventory.calculatePriceFromDifficulty(Inventory.seedPrices[i]);
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
    }



    public void buyCorn() {
        Inventory.buyImpl(GameState.CropType.CORN);
        updateMoney();
    }
    public void buyWheat() {
        Inventory.buyImpl(GameState.CropType.WHEAT);
        updateMoney();
    }
    public void buyTobacco() {
        Inventory.buyImpl(GameState.CropType.TOBACCO);
        updateMoney();
    }
    public void buyHemp() {
        Inventory.buyImpl(GameState.CropType.HEMP);
        updateMoney();
    }
    public void sellCorn() {
        Inventory.sellImpl(GameState.CropType.CORN);
        updateMoney();
    }
    public void sellWheat() {
        Inventory.sellImpl(GameState.CropType.WHEAT);
        updateMoney();
    }
    public void sellTobacco() {
        Inventory.sellImpl(GameState.CropType.TOBACCO);
        updateMoney();
    }
    public void sellHemp() {
        Inventory.sellImpl(GameState.CropType.HEMP);
        updateMoney();
    }

    public void updateMoney() { moneyDisplay.setText("Money: $" + Inventory.money); }


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
    }
}

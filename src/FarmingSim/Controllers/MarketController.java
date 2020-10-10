package FarmingSim.Controllers;

import FarmingSim.Inventory;
import FarmingSim.ScreenManager;
import FarmingSim.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MarketController {
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
@FXML private Text moneyMarketDisplay;
@FXML private Text seasonMarketDisplay;



    public void initialize() {
        for (int i = 0; i < Inventory.seedPrices.length; i++) {
            Inventory.seedPrices[i] = Inventory.calculatePriceFromDifficulty(Inventory.seedPrices[i]);
        }
        buyCornButton.setText("$" + Inventory.seedPrices[Settings.CropType.CORN.ordinal()]);
        sellCornButton.setText("$" + Inventory.cropPrices[Settings.CropType.CORN.ordinal()]);
        buyWheatButton.setText("$" + Inventory.seedPrices[Settings.CropType.WHEAT.ordinal()]);
        sellWheatButton.setText("$" + Inventory.cropPrices[Settings.CropType.WHEAT.ordinal()]);
        buyTobaccoButton.setText("$" + Inventory.seedPrices[Settings.CropType.TOBACCO.ordinal()]);
        sellTobaccoButton.setText("$" + Inventory.cropPrices[Settings.CropType.TOBACCO.ordinal()]);
        buyHempButton.setText("$" + Inventory.seedPrices[Settings.CropType.HEMP.ordinal()]);
        sellHempButton.setText("$" + Inventory.cropPrices[Settings.CropType.HEMP.ordinal()]);
        moneyMarketDisplay.setText("Money: $" + Inventory.money);
        dayMarketDisplay.setText("Day: 0");
        seasonMarketDisplay.setText("Season: " + CustomizationPageController.season.toString());
    }



    public void buyCorn() {
        Inventory.buyImpl(Settings.CropType.CORN);
        updateMoney();
    }
    public void buyWheat() {
        Inventory.buyImpl(Settings.CropType.WHEAT);
        updateMoney();
    }
    public void buyTobacco() {
        Inventory.buyImpl(Settings.CropType.TOBACCO);
        updateMoney();
    }
    public void buyHemp() {
        Inventory.buyImpl(Settings.CropType.HEMP);
        updateMoney();
    }
    public void sellCorn() {
        Inventory.sellImpl(Settings.CropType.CORN);
        updateMoney();
    }
    public void sellWheat() {
        Inventory.sellImpl(Settings.CropType.WHEAT);
        updateMoney();
    }
    public void sellTobacco() {
        Inventory.sellImpl(Settings.CropType.TOBACCO);
        updateMoney();
    }
    public void sellHemp() {
        Inventory.sellImpl(Settings.CropType.HEMP);
        updateMoney();
    }

    public void updateMoney() { moneyMarketDisplay.setText("Money: $" + Inventory.money); }


    public void move_back(ActionEvent e) throws Exception {
        Inventory.day++;
        /*
        ScreenManager.setScreen(
                "FarmUI",
                FXMLLoader.load(getClass().getResource("../FXML/FarmUI.fxml"))
        );
        */
        ScreenManager.setScreen("FarmUI");
    }
}

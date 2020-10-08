package FarmingSim.Controllers;

import FarmingSim.Inventory;
import FarmingSim.ScreenManager;
import FarmingSim.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MarketController {
//will access Inventory when you buy and sell
    //rn, you can only buy 1 seed at a time
Alert a = new Alert(Alert.AlertType.NONE);

@FXML Button buyCornButton;
@FXML Button buyWheatButton;
@FXML Button buyTobaccoButton;
@FXML Button buyHempButton;

@FXML private Text moneyMarketDisplay;
@FXML private Text dayMarketDisplay;
@FXML private Text seasonMarketDisplay;

public int cornSeedPrice;
public int wheatSeedPrice;
public int tobaccoSeedPrice;
public int hempSeedPrice;
public int[] prices = new int[]{2,3,10,100}; // Base prices will be overwritten at init


    public void initialize() {
        for (int i = 0; i < prices.length; i++) {
            prices[i] = calculatePriceFromDifficulty(prices[i]);
        }
        buyCornButton.setText("$" + prices[Settings.Seed.CORN.ordinal()]);
        buyWheatButton.setText("S" + prices[Settings.Seed.WHEAT.ordinal()]);
        buyTobaccoButton.setText("$" + prices[Settings.Seed.TOBACCO.ordinal()]);
        buyHempButton.setText("$" + prices[Settings.Seed.HEMP.ordinal()]);
        moneyMarketDisplay.setText("Money: $" + Inventory.money);
        dayMarketDisplay.setText("Day: 0");
        seasonMarketDisplay.setText("Season: " + CustomizationPageController.season.toString());
    }

    public int calculatePriceFromDifficulty(int basePrice) {
        //we get a difficulty multiplier and then if it's spring, we add on an extra $5 bc high demand
        basePrice *= (new int[]{1, 2, 5})[CustomizationPageController.difficulty.ordinal()];
        basePrice += (CustomizationPageController.season.ordinal() == 0) ? 5 : 0;
        return basePrice;
    }

    private void buyImpl(Settings.Seed seed) {
        if (atMaxInventory() || zeroMoney(seed)) {
            return;
        }
        Inventory.seedNum[seed.ordinal()]++;
        Inventory.money -= prices[seed.ordinal()];
        moneyMarketDisplay.setText("Money: $" + Inventory.money);
    }

    public void buyCorn() {
        buyImpl(Settings.Seed.CORN);
    }
    public void buyWheat() {
        buyImpl(Settings.Seed.WHEAT);
    }
    public void buyTobacco() {
        buyImpl(Settings.Seed.WHEAT);
    }
    public void buyHemp() {
        buyImpl(Settings.Seed.HEMP);
    }


    public boolean atMaxInventory() {
        int seed_sum = 0;
        int crop_sum = 0;
        for (int i = 0; i < Settings.Seed.size(); i++) {
            seed_sum += Inventory.seedNum[i];
            crop_sum += Inventory.cropNum[i];
        }
        if (seed_sum >= Inventory.MAX_SEED_INVENTORY) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Max Seed Inventory Reached!");
            a.show();
            return true;
        }
        if (crop_sum >= Inventory.MAX_CROP_INVENTORY) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Max Crop Inventory Reached!");
            a.show();
            return true;
        }
        return false;
    }

    public boolean zeroMoney(Settings.Seed seed) {
        if (Inventory.money - prices[seed.ordinal()] <= 0) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You cannot afford this " + seed.name().toLowerCase() + "!");
            a.show();
            return true;
        }
        return false;
    }

    public void move_back(ActionEvent e) throws Exception {
        Inventory.day++;
        ScreenManager.addScreen(
                "Player",
                FXMLLoader.load(getClass().getResource("../FXML/FarmUI.fxml"))
        );
        ScreenManager.setScreen("Player");
    }
}

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




    public void initialize() {
        cornSeedPrice = calculatePriceFromDifficulty(2);
        wheatSeedPrice = calculatePriceFromDifficulty(3);
        tobaccoSeedPrice = calculatePriceFromDifficulty(10);
        hempSeedPrice = calculatePriceFromDifficulty(100);
        buyCornButton.setText("$" + cornSeedPrice);
        buyWheatButton.setText("S" + wheatSeedPrice);
        buyTobaccoButton.setText("$" + tobaccoSeedPrice);
        buyHempButton.setText("$" + hempSeedPrice);
        moneyMarketDisplay.setText("Money: $" + Inventory.money);
        dayMarketDisplay.setText("Day: 0");
        seasonMarketDisplay.setText("Season: " + CustomizationPageController.season.toString());
    }

    public Integer calculatePriceFromDifficulty(int basePrice) {
        //we get a difficulty multiplier and then if it's spring, we add on an extra $5 bc high demand
        switch (CustomizationPageController.difficulty) {
            case EASY:
                if (CustomizationPageController.season == Settings.Season.SPRING) {
                    return basePrice + 5;
                }
                return basePrice;
            case MEDIUM:
                if (CustomizationPageController.season == Settings.Season.SPRING) {
                    return basePrice * 2 + 5;
                }
                return basePrice * 2;
            case HARD:
                if (CustomizationPageController.season == Settings.Season.SPRING) {
                return basePrice * 5 + 5;
            }
                return basePrice * 5;
        }
        return basePrice;
    }

    public void buyCorn() {
        if (atMaxInventory() || zeroMoney(Settings.Seed.CORN)) {
            return;
        }
        Inventory.cornSeedNum++;
        Inventory.money -= cornSeedPrice;
        moneyMarketDisplay.setText("Money: $" + Inventory.money);
    }
    public void buyWheat() {
        if (atMaxInventory() || zeroMoney(Settings.Seed.WHEAT)) {
            return;
        }
        Inventory.wheatSeedNum++;
        Inventory.money -= wheatSeedPrice;
        moneyMarketDisplay.setText("Money: $" + Inventory.money);
    }
    public void buyTobacco() {
        if (atMaxInventory() || zeroMoney(Settings.Seed.TOBACCO)) {
            return;
        }
        Inventory.tobaccoSeedNum++;
        Inventory.money -= tobaccoSeedPrice;
        moneyMarketDisplay.setText("Money: $" + Inventory.money);
    }
    public void buyHemp() {
        if(atMaxInventory() || zeroMoney(Settings.Seed.HEMP)) {
            return;
        }
        Inventory.hempSeedNum++;
        Inventory.money -= hempSeedPrice;
        moneyMarketDisplay.setText("Money: $" + Inventory.money);
    }


    public boolean atMaxInventory() {
        if (Inventory.cornSeedNum + Inventory.wheatSeedNum + Inventory.tobaccoSeedNum + Inventory.hempSeedNum
                >= Inventory.MAX_SEED_INVENTORY) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Max Inventory Reached!");
            a.show();
            return true;
        }
        return false;
    }

    public boolean zeroMoney(Settings.Seed seed) {
        switch (seed) {
            case CORN:
                if (Inventory.money - cornSeedPrice <= 0) {
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("You cannot afford this corn!");
                    a.show();
                    return true;
                }
            break;
            case WHEAT:
                if (Inventory.money - wheatSeedPrice <= 0) {
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("You cannot afford this wheat!");
                    a.show();
                    return true;
                }
            break;
            case TOBACCO:
                if (Inventory.money - tobaccoSeedPrice <= 0) {
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("You cannot afford this tobacco!");
                    a.show();
                    return true;
                }
            break;
            case HEMP:
                if (Inventory.money - hempSeedPrice <= 0) {
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("You cannot afford this hemp!");
                    a.show();
                    return true;
                }
                break;
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

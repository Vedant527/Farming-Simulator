package FarmingSim;

import FarmingSim.Controllers.CustomizationPageController;
import javafx.scene.control.Alert;


public class Inventory {
    /* ORDER of indices
    0 = Corn
    1 = Hemp
    2 = Wheat
    3 = Tobacco
    */
    public static int[] seedNum = new int[Settings.CropType.size()];
    public static int[] cropNum = new int[Settings.CropType.size()];
    public static int[] seedPrices = new int[]{2,3,10,100}; // Base prices will be overwritten at init
    public static int[] cropPrices = new int[]{10,20,50,420};

    public static int day;

    public static int money;

    static Alert a = new Alert(Alert.AlertType.NONE);

    //at a later date we can make these changeable with adding inventory space or something
    public static int MAX_SEED_INVENTORY = 100;
    public static int MAX_CROP_INVENTORY = 100;

    public static void setDefault() {
        for (int i = 0; i < Settings.CropType.size(); i++) {
            seedNum[i] = 0;
            cropNum[i] = 0;
        }
        seedNum[CustomizationPageController.cropType.ordinal()] = 10;
        money = (new int[]{500, 300, 100})[CustomizationPageController.cropType.ordinal()];
    }
    public static boolean isFull() {
        int seed_sum = 0;
        int crop_sum = 0;
        for (int i = 0; i < Settings.CropType.size(); i++) {
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

    public static boolean hasCrop(Settings.CropType cropType) {
        if (cropNum[cropType.ordinal()] == 0) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You don't have any " + cropType.name().toLowerCase() + "left!");
            a.show();
            return false;
        }
        return true;
    }

    public static boolean canBuy(Settings.CropType cropType) {
        if (Inventory.money - Inventory.seedPrices[cropType.ordinal()] <= 0) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You cannot afford this " + cropType.name().toLowerCase() + "!");
            a.show();
            return false;
        }
        return true;
    }
    public static int calculatePriceFromDifficulty(int basePrice) {
        //we get a difficulty multiplier and then if it's spring, we add on an extra $5 bc high demand
        basePrice *= (new int[]{1, 2, 5})[CustomizationPageController.difficulty.ordinal()];
        basePrice += (CustomizationPageController.season.ordinal() == 0) ? 5 : 0;
        return basePrice;
    }

    public static void buyImpl(Settings.CropType cropType) {
        if (Inventory.isFull() || !Inventory.canBuy(cropType)) {
            return;
        }
        Inventory.seedNum[cropType.ordinal()]++;
        Inventory.money -= Inventory.seedPrices[cropType.ordinal()];
    }

    public static void sellImpl(Settings.CropType cropType) {
        if (!hasCrop(cropType)) {
            return;
        }
        Inventory.cropNum[cropType.ordinal()]--;
        Inventory.money += Inventory.cropPrices[cropType.ordinal()];
    }
}


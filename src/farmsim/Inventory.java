package farmsim;

import javafx.scene.control.Alert;


public class Inventory {
    /* ORDER of indices
    0 = Corn
    1 = Hemp
    2 = Wheat
    3 = Tobacco
    */
    private int[] seedNum = new int[GameState.CropType.size()];
    private int[] cropNum = new int[GameState.CropType.size()];
    private int[] seedPrices = new int[]{2, 3, 10, 100}; // Base prices will be overwritten at init
    private int[] cropPrices = new int[]{10, 20, 50, 420};


    private int money;


    private Alert a = new Alert(Alert.AlertType.NONE);

    //at a later date we can make these changeable with adding inventory space or something
    private int maxSeedInventory = 100;
    private int maxCropInventory = 100;

    public void setSeedNum(int indx, int seedNum) {
        this.seedNum[indx] = seedNum;
    }

    public int[] getSeedNum() {
        return seedNum;
    }

    public void setCropNum(int indx, int cropNum) {
        this.cropNum[indx] = cropNum;
    }

    public int[] getCropNum() {
        return cropNum;
    }

    public void setCropPrices(int indx, int cropPrices) {
        this.cropPrices[indx] = cropPrices;
    }

    public int[] getCropPrices() {
        return cropPrices;
    }

    public void setSeedPrices(int indx, int seedPrices) {
        this.seedPrices[indx] = seedPrices;
    }

    public int[] getSeedPrices() {
        return seedPrices;
    }

    public int getMaxCropInventory() {
        return maxCropInventory;
    }

    public void setMaxCropInventory(int maxCropInventory) {
        this.maxCropInventory = maxCropInventory;
    }

    public int getMaxSeedInventory() {
        return maxSeedInventory;
    }

    public void setMaxSeedInventory(int maxSeedInventory) {
        this.maxSeedInventory = maxSeedInventory;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setDefault(GameState.CropType type) {
        for (int i = 0; i < GameState.CropType.size(); i++) {
            seedNum[i] = 0;
            cropNum[i] = 0;
        }
        seedNum[type.ordinal()] = 10;
        money = (new int[]{500, 300, 100})[type.ordinal()];
    }
    public boolean isFull() {
        int seedSum = 0;
        int cropSum = 0;
        for (int i = 0; i < GameState.CropType.size(); i++) {
            seedSum += this.seedNum[i];
            cropSum += this.cropNum[i];
        }
        if (seedSum >= this.maxSeedInventory) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Max Seed GameState.getInventory() Reached!");
            a.show();
            return true;
        }
        if (cropSum >= this.maxCropInventory) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Max Crop GameState.getInventory() Reached!");
            a.show();
            return true;
        }
        return false;
    }

    public boolean hasCrop(GameState.CropType cropType) {
        if (cropNum[cropType.ordinal()] == 0) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You don't have any " + cropType.name().toLowerCase() + "left!");
            a.show();
            return false;
        }
        return true;
    }

    public boolean canBuy(GameState.CropType cropType) {
        if (this.money - this.seedPrices[cropType.ordinal()] <= 0) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You cannot afford this " + cropType.name().toLowerCase() + "!");
            a.show();
            return false;
        }
        return true;
    }
    public int calculatePriceFromDifficulty(int basePrice) {
        //we get a difficulty multiplier and if it's spring, we add on an extra $5 bc high demand
        basePrice *= (new int[]{1, 2, 5})[GameState.getDifficulty().ordinal()];
        basePrice += (GameState.getSeason().ordinal() == 0) ? 5 : 0;
        return basePrice;
    }

    public void buyImpl(GameState.CropType cropType) {
        if (this.isFull() || !this.canBuy(cropType)) {
            return;
        }
        this.seedNum[cropType.ordinal()]++;
        this.money -= this.seedPrices[cropType.ordinal()];
    }

    public void sellImpl(GameState.CropType cropType) {
        if (!hasCrop(cropType)) {
            return;
        }
        this.cropNum[cropType.ordinal()]--;
        this.money += this.cropPrices[cropType.ordinal()];
    }
}

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
    private int[] organicCropNum = new int[GameState.CropType.size()];
    private int[] pesticideCropNum = new int[GameState.CropType.size()];
    private int pesticideNum = 0;
    private int pesticidePrice = 1;
    private int fertilizerNum = 0;
    private int fertilizerPrice = 1;
    private int[] seedPrices = new int[]{2, 3, 10, 100}; // Base prices will be overwritten at init
    private int[] cropPrices = new int[]{10, 20, 50, 420};
    private int[] startMoney = new int[]{500, 300, 100};


    private int money;


    private Alert a = new Alert(Alert.AlertType.NONE);

    //at a later date we can make these changeable with adding inventory space or something
    private int maxSeedInventory = 100;
    private int maxCropInventory = 100;


    public int getPesticideNum() {
        return pesticideNum;
    }

    public void setPesticideNum(int pesticideNum) {
        this.pesticideNum = pesticideNum;
    }

    public void decreasePesticideNum() {
        if (this.pesticideNum > 0) {
            this.pesticideNum--;
        }
    }
    public void decreaseFertilizerNum() {
        if (this.fertilizerNum > 0) {
            this.fertilizerNum--;
        }
    }

    public int getFertilizerNum() {
        return fertilizerNum;
    }

    public void setFertilizerNum(int fertilizerNum) {
        this.fertilizerNum = fertilizerNum;
    }

    public int getPesticidePrice() {
        return pesticidePrice;
    }

    public void setPesticidePrice(int pesticidePrice) {
        this.pesticidePrice = pesticidePrice;
    }

    public int getFertilizerPrice() {
        return fertilizerPrice;
    }

    public void setFertilizerPrice(int fertilizerPrice) {
        this.fertilizerPrice = fertilizerPrice;
    }
    public void setSeedNum(int indx, int seedNum) {
        this.seedNum[indx] = seedNum;
    }

    public void decreaseSeedNum(int idx) {
        this.seedNum[idx]--;
    }

    public int[] getSeedNum() {
        return seedNum;
    }

    public void setOrganicCropNum(int indx, int cropNum) {
        this.organicCropNum[indx] = cropNum;
    }

    public int[] getOrganicCropNum() {
        return organicCropNum;
    }

    public void setPesticideCropNum(int idx, int cropNum) { this.pesticideCropNum[idx] = cropNum; }

    public int[] getPesticideCropNum() { return pesticideCropNum; }

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
            organicCropNum[i] = 0;
        }
        seedNum[type.ordinal()] = 10;
    }

    public void setStartMoney(GameState.Difficulty diff) {
        money = startMoney[diff.ordinal()];
    }

    public boolean isFull() {
        int seedSum = 0;
        int cropSum = 0;
        for (int i = 0; i < GameState.CropType.size(); i++) {
            seedSum += this.seedNum[i];
            cropSum += this.organicCropNum[i];
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

    public boolean hasCrop(GameState.CropType cropType, boolean isOrganic) {
        if (isOrganic) {
            if (organicCropNum[cropType.ordinal()] == 0) {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("You don't have any organic " + cropType.name().toLowerCase()
                        + " left!");
                a.show();
                return false;
            }
        } else {
            if (pesticideCropNum[cropType.ordinal()] == 0) {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("You don't have any pesticide " + cropType.name().toLowerCase()
                        + " left!");
                a.show();
                return false;
            }
        }
        return true;
    }

    public boolean hasSeed(GameState.CropType cropType) {
        if (seedNum[cropType.ordinal()] == 0) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You don't have any " + cropType.name().toLowerCase() + "seeds left!");
            a.show();
            return false;
        }
        return true;
    }

    public boolean canBuy(GameState.CropType cropType) {
        if (this.money - this.seedPrices[cropType.ordinal()] < 0) {
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

    public void sellImpl(GameState.CropType cropType, boolean isOrganic) {
        if (!hasCrop(cropType, isOrganic)) {
            return;
        }
        if (isOrganic) {
            this.organicCropNum[cropType.ordinal()]--;
            this.money += this.cropPrices[cropType.ordinal()];
        } else {
            this.pesticideCropNum[cropType.ordinal()]--;
            this.money += this.cropPrices[cropType.ordinal()] - 5; //$5 is the pesticide penalty
        }
    }

    public void buyPest() {
        if (this.money >= pesticidePrice) {
            this.money -= pesticidePrice;
            pesticideNum++;
        }
    }
    public void buyFert() {
        if (this.money >= fertilizerPrice) {
            this.money -= fertilizerPrice;
            fertilizerNum++;
        }
    }
}

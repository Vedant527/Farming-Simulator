package farmsim;

import javafx.scene.control.Alert;

import java.util.HashMap;


public class Inventory {
    /* ORDER of indices
    0 = Corn
    1 = Hemp
    2 = Wheat
    3 = Tobacco
    */
    private int[] seedNum = new int[GameState.CropType.size()];
    //private int[] organicCropNum = new int[GameState.CropType.size()];
    //private int[] pesticideCropNum = new int[GameState.CropType.size()];
    private int pesticideNum = 0;
    private int pesticidePrice = 1;
    private int fertilizerNum = 0;
    private int fertilizerPrice = 1;
    private int[] seedPrices = new int[]{2, 3, 10, 100}; // Base prices will be overwritten at init
    private int[] cropPrices = new int[]{10, 20, 50, 420};
    private final int[] startMoney = new int[]{500, 300, 100};

    private HashMap<Crop, Integer> crops = new HashMap<Crop, Integer>();
    private int money;

    private Alert a = new Alert(Alert.AlertType.NONE);

    //at a later date we can make these changeable with adding inventory space or something
    private int maxSeedInventory = 100;
    private int maxCropInventory = 100;
    private int pestReduction = 5;

    public Inventory() {
        for (int i = 0; i < (Crop.State.values().length - 1); i++) {
            GameState.CropType typ = GameState.CropType.values()[i];
            this.crops.put(new Crop(typ, Crop.State.EMPTY, true), 0);
            this.crops.put(new Crop(typ, Crop.State.EMPTY, false), 0);
        }
    }

    public void calcPrices() {
        for (int i = 0; i < (this.seedPrices.length - 1); i++) {
            this.seedPrices[i] = calculatePriceFromDifficulty(this.seedPrices[i]);
            this.cropPrices[i] = calculatePriceFromDifficulty(this.cropPrices[i]);
        }
        this.fertilizerPrice = calculatePriceFromDifficulty(this.fertilizerPrice);
        this.pesticideNum = calculatePriceFromDifficulty(this.pesticidePrice);
    }

    public void setDefault(GameState.CropType type) {
        for (int i = 0; i < GameState.CropType.size(); i++) {
            seedNum[i] = 0;
        }
        seedNum[type.ordinal()] = 10;
    }

    public void setStartMoney(GameState.Difficulty diff) {
        money = startMoney[diff.ordinal()];
    }

    public boolean isFull() {
        int seedSum = 0;
        for (int i = 0; i < GameState.CropType.size(); i++) {
            seedSum += this.seedNum[i];
        }
        if (seedSum >= this.maxSeedInventory) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Max Seed GameState.getInventory() Reached!");
            a.show();
            return true;
        }
        if (this.size() >= this.maxCropInventory) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Max Crop GameState.getInventory() Reached!");
            a.show();
            return true;
        }
        return false;
    }

    public boolean hasCrop(GameState.CropType cropType, boolean isOrganic) {
        Crop tmp = new Crop(cropType, isOrganic);
        return this.hasCrop(tmp);
    }

    public boolean hasCrop(Crop c) {
        if (this.crops.get(c) == 0) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You don't have any " + c + " left!");
            a.show();
            return false;
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

    public void sellImpl(GameState.CropType t, boolean org) {
        Crop tmp = new Crop(t, org);
        this.sellImpl(tmp);
    }

    public void sellImpl(Crop c) {
        if (getCropNum(c) != 0) {
            return;
        }

        this.add(c, -1);
        this.money += c.price(this.cropPrices);
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

    public void move(Crop c) {
        this.add(c, -1);
        c.setOrganic(false);
        this.add(c, 1);
    }

    public void add(Crop c, int val) {
        this.crops.replace(c, this.crops.get(c) + val);
    }

    public int size() {
        return this.crops.values().stream().reduce(0, (Integer x, Integer y) -> {
            return y + x;
        });
    }

    public int get(GameState.CropType t, boolean o) {
        Crop tmp = new Crop(t, o);
        return this.get(tmp);
    }

    public int get(Crop c) {
        return this.crops.get(c);
    }

/*
----------------------------------------------------------------------------------------------------
VARIABLE GETTERS AND SETTERS
----------------------------------------------------------------------------------------------------
*/

    public int getCropNum(Crop c) {
        return this.crops.get(c);
    }

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

    public void setSeedPrices(int indx, int seedPrices) {
        this.seedPrices[indx] = seedPrices;
    }

    public int[] getSeedPrices() {
        return seedPrices;
    }

    public int[] getCropPrices() {
        return cropPrices;
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
}

package farmsim;

import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.LinkedList;


public class Inventory {
    /* ORDER of indices
    0 = Corn
    1 = Hemp
    2 = Wheat
    3 = Tobacco
    */
    private int pesticideNum = 0;
    private int pesticidePrice = 1;
    private int fertilizerNum = 0;
    private int fertilizerPrice = 1;
    private int[] seedPrices = new int[]{2, 3, 10, 100}; // Base prices will be overwritten at init
    private int[] cropPrices = new int[]{10, 20, 50, 420};
    private final int[] startMoney = new int[]{500, 300, 100};

    private HashMap<Crop, Integer> crops = new HashMap<Crop, Integer>();
    private LinkedList<FarmHand> hands = new LinkedList<>();
    private int money;

    private Alert a = new Alert(Alert.AlertType.NONE);

    //at a later date we can make these changeable with adding inventory space or something
    private int maxSeedInventory = 100;
    private int maxCropInventory = 100;
    private int pestReduction = 5;

    public Inventory() {
        for (int i = 0; i < (Crop.State.values().length - 1); i++) {
            Crop.Type typ = Crop.Type.values()[i];
            this.crops.put(new Crop(typ, Crop.State.MATURE, true), 0);
            this.crops.put(new Crop(typ, Crop.State.MATURE, false), 0);
            this.crops.put(new Crop(typ, Crop.State.SEED, false), 0);
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

    public void setDefault(Crop.Type type) {
        this.setSeedNum(type, 10);
    }

    public void setStartMoney(GameState.Difficulty diff) {
        money = startMoney[diff.ordinal()];
    }

    public boolean isFull() {
        if (this.seedNum() >= this.maxSeedInventory) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Max Seed Inventory Reached!");
            a.show();
            return true;
        }
        if (this.cropNum() >= this.maxCropInventory) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Max Crop Inventory Reached!");
            a.show();
            return true;
        }
        return false;
    }

    public boolean hasCrop(Crop.Type cropType, boolean isOrganic) {
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

    public boolean isEmpty(Crop.Type t, boolean isOrg) {
        Crop tmp = new Crop(t, isOrg);
        return this.isEmpty(tmp);
    }

    public boolean isEmpty(Crop c) {
        return this.crops.get(c) == 0;
    }

    public boolean hasSeed(Crop.Type cropType) {
        Crop tmp = new Crop(cropType, Crop.State.SEED);
        if (get(tmp) == 0) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You don't have any " + cropType.name().toLowerCase() + "seeds left!");
            a.show();
            return false;
        }
        return true;
    }

    public boolean canBuy(Crop c) {
        if (this.money - c.price(this.seedPrices) < 0) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("You cannot afford this " + c + "!");
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

    public void buyImpl(Crop.Type cropType) {
        Crop tmp = new Crop(cropType);
        if (this.isFull() || !this.canBuy(tmp)) {
            return;
        }
        this.add(tmp, 1);
        this.money -= tmp.price(this.seedPrices);
    }

    public void sellImpl(Crop.Type t, boolean org) {
        Crop tmp = new Crop(t, org);
        this.sellImpl(tmp);
    }

    public void sellImpl(Crop c) {
        if (getCropNum(c) <= 0) {
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

    public void add(Crop.Type type, Crop.State state, boolean org, int val) {
        Crop tmp = new Crop(type, state, org);
        this.add(tmp, val);
    }

    public void add(Crop c, int val) {
        this.crops.replace(c, this.crops.get(c) + val);
    }

    public int seedNum() {
        int sum = 0;
        for (Crop.Type t : Crop.Type.values()) {
            Crop tmp = new Crop(t);
            sum += this.get(tmp);
        }
        return sum;
    }

    public int cropNum() {
        int sum = 0;
        for (Crop.Type t : Crop.Type.values()) {
            Crop tmp = new Crop(t, true);
            sum += this.get(tmp); // Adds organic
            tmp.setOrganic(false);
            sum += this.get(tmp); // Adds inorganic
        }
        return sum;
    }

/*
----------------------------------------------------------------------------------------------------
VARIABLE GETTERS AND SETTERS
----------------------------------------------------------------------------------------------------
*/
    public void removeHand(int level) {
        FarmHand tmp = new FarmHand(level);
        this.removeHand(tmp);
    }

    public void removeHand(FarmHand f) {
        this.hands.remove(f);
    }

    public void addHand(int level) {
        FarmHand tmp = new FarmHand(level);
        this.addHand(tmp);
    }

    public void addHand(FarmHand f) {
        this.hands.add(f);
    }

    public LinkedList<FarmHand> getFarmHands() {
        return this.hands;
    }

    public int get(Crop.Type t, boolean o) {
        Crop tmp = new Crop(t, o);
        return this.get(tmp);
    }

    public int get(Crop c) {
        return this.crops.get(c);
    }

    public void set(Crop.Type t, boolean o, int num) {
        Crop tmp = new Crop(t, o);
        this.set(tmp, num);
    }

    public void set(Crop c, int num) {
        this.crops.replace(c, num);
    }

    public int getCropNum(Crop.Type t, boolean org) {
        Crop tmp = new Crop(t, org);
        return this.getCropNum(tmp);
    }

    public int getCropNum(Crop c) {
        return this.crops.get(c);
    }

    public void setSeedNum(Crop.Type type, int val) {
        this.set(new Crop(type), val);
    }

    public void decreaseSeedNum(Crop.Type type) {
        this.add(new Crop(type), -1);
    }

    public int getSeedNum(Crop.Type type) {
        return this.get(new Crop(type));
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

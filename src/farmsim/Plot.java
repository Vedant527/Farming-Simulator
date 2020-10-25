package farmsim;

import java.util.Random;

public class Plot {
    //current implementation: to clear a dead crop you must "harvest" it. it just wont increase
    //your inventory

    private CropState state;
    private GameState.CropType cropType;
    private int waterLevel;
    private final int maxWater = 4;
    private final int minWater = 1;
    private final double growChance = 0.5;

    public int getWaterLevel() {
        return this.waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public CropState getState() {
        return this.state;
    }

    public void setState(CropState state) {
        this.state = state;
    }

    public GameState.CropType getCropType() {
        return this.cropType;
    }

    public void setCropType(GameState.CropType cropType) {
        this.cropType = cropType;
    }

    public enum CropState {
        EMPTY, //no crop currently planted, this means available to plant
        SEED,
        IMMATURE,
        MATURE,
        DEAD;
    }

    public Plot() {
        //Random rand = new Random(); //instructions say have random maturity for this milestone
        //int var = rand.nextInt(4);
        //this.state = CropState.values()[var];
        this.state = CropState.EMPTY;
        this.cropType = GameState.getCropType();
        this.waterLevel = 0;
    }

    public void plant(GameState.CropType cropType) {
        if (this.state == CropState.EMPTY && GameState.getInventory().hasSeed(cropType)) {
            this.state = CropState.SEED;
            //we have to use gamestate's croptype so we can plant different types of plant
            this.cropType = GameState.getCropType();
            GameState.getInventory().decreaseSeedNum(cropType.ordinal());
            this.waterLevel = 0;
        }
    }

    public void grow() {
        Random rand = new Random();
        if (rand.nextFloat() < (1 - growChance)) {
            return;
        }
        if (this.state != CropState.EMPTY && this.state != CropState.DEAD) {
            //if bad water, kill it
            if (!waterLevelSufficient()) {
                state = CropState.DEAD;
                return;
            }
            state = CropState.values()[(state.ordinal() + 1) % CropState.values().length];
        }
    }

    public boolean waterLevelSufficient() {
        return this.waterLevel <= maxWater && this.waterLevel >= minWater;
    }

    public void increaseWater() {
        if (this.waterLevel + 1 > maxWater) {
            this.state = CropState.DEAD;
        }
        this.waterLevel++;
    }

    public void decreaseWater() {
        if (this.state != CropState.EMPTY) {
            if (this.waterLevel - 1 < minWater) {
                this.state = CropState.DEAD;
            } else {
                this.waterLevel--;
            }
        }
    }

    public void harvest() {
        if (this.state == CropState.MATURE && !GameState.getInventory().isFull()) {
            this.state = CropState.EMPTY;
            this.waterLevel = 0;
            GameState.getInventory().getCropNum()[cropType.ordinal()]++;
        } else if (this.state == CropState.DEAD) {
            this.state = CropState.EMPTY;
            this.waterLevel = 0;
        }
    }

}

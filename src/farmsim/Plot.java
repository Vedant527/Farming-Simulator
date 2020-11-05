package farmsim;

import java.util.Random;

public class Plot {
    //current implementation: to clear a dead crop you must "harvest" it. it just wont increase
    //your inventory
    //Hardcoded some values into harvest

    private CropState state;
    private GameState.CropType cropType;
    private int waterLevel;
    private int pesticideLevel;
    private int fertilizerLevel;
    private double growChance = 0.5;
    private double fertilizerBump = .15; //maybe make this depend on diff

    private final int maxWater = 4;
    private final int minWater = 1;
    private final int maxPesticide = 4;
    private final int maxFertilizer = 4;

    public enum CropState {
        EMPTY, //no crop currently planted, this means available to plant
        SEED,
        IMMATURE,
        MATURE,
        DEAD;
    }
    public int getPesticideLevel() {
        return pesticideLevel;
    }

    public void setPesticideLevel(int pesticideLevel) {
        this.pesticideLevel = pesticideLevel;
    }

    public int getFertilizerLevel() {
        return fertilizerLevel;
    }

    public void setFertilizerLevel(int fertilizerLevel) {
        this.fertilizerLevel = fertilizerLevel;
    }

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

    public CropState getCropState() {
        return this.state;
    }

    public void setCropState(CropState s) {
        this.state = s;
    }
    public void setFertilizerBump(double fertilizerBump) {
        this.fertilizerBump = fertilizerBump;
    }
    public Plot() {
        //Random rand = new Random(); //instructions say have random maturity for this milestone
        //int var = rand.nextInt(4);
        //this.state = CropState.values()[var];
        this.state = CropState.EMPTY;
        this.cropType = GameState.getCropType();
        this.waterLevel = 0;
        this.pesticideLevel = 0;
        this.fertilizerLevel = 0;
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
        if (this.waterLevel >= maxWater) {
            this.state = CropState.DEAD;
        }
        this.waterLevel++;
    }

    public void decreaseWater() {
        if (this.state != CropState.EMPTY) {
            if (this.waterLevel <= minWater) {
                this.state = CropState.DEAD;
            } else {
                this.waterLevel--;
            }
        }
    }

    //for now you can pesticide and fertilize plots at any stage, but overdoing it kills
    public void increasePesticideLevel() {
        if (GameState.getInventory().getPesticideNum() > 0) {
            if (this.pesticideLevel >= maxPesticide) {
                this.state = CropState.DEAD;
            } else {
                this.pesticideLevel++;
            }
            GameState.getInventory().decreasePesticideNum();
        }
    }

    public void decreasePesticideLevel() {
        if (this.pesticideLevel > 0) {
            this.pesticideLevel--;
        }
    }

    //each fertilizer up to death increases grow chance by 15%
    public void increaseFertilizerLevel() {
        if (this.fertilizerLevel < maxFertilizer && GameState.getInventory().getFertilizerNum() > 0) {
            this.fertilizerLevel++;
            this.growChance += fertilizerBump;
            GameState.getInventory().decreaseFertilizerNum();
        } //else do nothing
    }

    public void decreaseFertilizerLevel() {
        if (this.fertilizerLevel > 0) {
            this.fertilizerLevel--;
            this.growChance -= fertilizerBump;
        }
    }

    public void harvest() {
        if (this.state == CropState.MATURE && !GameState.getInventory().isFull()) {
            this.state = CropState.EMPTY;
            this.waterLevel = 0;
            Random rand = new Random();//m5 says fert has to have chance of increasing yield
            if (this.pesticideLevel > 0) {
                GameState.getInventory().getPesticideCropNum()[cropType.ordinal()]++;
                if (this.fertilizerLevel > 2 && (rand).nextDouble() < 0.2) {
                    GameState.getInventory().getPesticideCropNum()[cropType.ordinal()]++;
                }
            } else {
                GameState.getInventory().getOrganicCropNum()[cropType.ordinal()]++;
                if (this.fertilizerLevel > 2 && (rand).nextDouble() < 0.2) {
                    GameState.getInventory().getOrganicCropNum()[cropType.ordinal()]++;
                }
            }
            this.pesticideLevel = 0;
            this.fertilizerLevel = 0;
        } else if (this.state == CropState.DEAD) {
            this.state = CropState.EMPTY;
            this.waterLevel = 0;
            this.pesticideLevel = 0;
            this.fertilizerLevel = 0;
        }
    }

}

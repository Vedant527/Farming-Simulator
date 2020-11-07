package farmsim;

import java.util.Random;

public class Plot {
    //current implementation: to clear a dead crop you must "harvest" it. it just wont increase
    private Crop crop;
    private int waterLevel;
    private int pesticideLevel;
    private int fertilizerLevel;

    //Hardcoded some values into harvest
    private double growChance = 0.5;
    private double fertilizerBump = .15; //maybe make this depend on diff
    private double fertYieldChance = 0.2;
    private double fertGrowChance = 0.15;

    private final int maxWater = 4;
    private final int minWater = 0;
    private final int maxPesticide = 4;
    private final int maxFertilizer = 4;

    public Plot() {
        //Random rand = new Random(); //instructions say have random maturity for this milestone
        //int var = rand.nextInt(4);
        //this.state = CropState.values()[var];
        this.crop = new Crop(GameState.getCropType(), Crop.State.EMPTY, true);
        this.waterLevel = 0;
        this.pesticideLevel = 0;
        this.fertilizerLevel = 0;
    }


    public void plant(GameState.CropType cropType) {
        if (this.crop.getState() == Crop.State.EMPTY
                && GameState.getInventory().hasSeed(cropType)) {
            this.crop = new Crop(cropType, Crop.State.SEED, true);
            //we have to use gamestate's croptype so we can plant different types of plant
            GameState.getInventory().decreaseSeedNum(cropType);
            this.waterLevel = 0;
        }
    }

    public void grow() {
        // kills if not enough water
        if (!waterLevelSufficient()) {
            this.crop.kill();
            return;
        }
        // Increases the likelihood that the crop grows when fertilized.
        double chance = growChance;
        for (int i = 0; i < this.fertilizerLevel; i++) {
            chance += fertGrowChance;
        }
        // If not lucky, dont grow
        Random rand = new Random();
        if (rand.nextFloat() > (1 - chance)) {
            return;
        }
        this.crop.grow();
    }

    public boolean waterLevelSufficient() {
        return this.waterLevel <= maxWater && this.waterLevel >= minWater;
    }

    public void increaseWater() {
        if (this.waterLevel >= maxWater) {
            this.crop.kill();
        }
        this.waterLevel++;
    }

    public void decreaseWater() {
        // not important if no crop
        if (this.crop.getState() == Crop.State.EMPTY) {
            return;
        }
        if (this.waterLevel <= minWater) {
            this.crop.kill();
            return;
        }
        this.waterLevel--;
    }

    //for now you can pesticide and fertilize plots at any stage, but overdoing it kills
    public void increasePesticideLevel() {
        if (GameState.getInventory().getPesticideNum() <= 0) {
            return;
        }
        if (this.pesticideLevel >= maxPesticide) {
            this.crop.kill();
        } else {
            this.pesticideLevel++;
            this.crop.setOrganic(false);
        }
    }

    public void decreasePesticideLevel() {
        if (this.pesticideLevel > 0) {
            this.pesticideLevel--;
        }
    }

    //each fertilizer up to death increases grow chance by 15%
    public void increaseFertilizerLevel() {
        if (GameState.getInventory().getFertilizerNum() <= 0) {
            return;
        }
        if (this.fertilizerLevel < maxFertilizer) {
            this.fertilizerLevel++;
            GameState.getInventory().decreaseFertilizerNum();
        } //else do nothing
    }

    public void decreaseFertilizerLevel() {
        if (this.fertilizerLevel > 0) {
            this.fertilizerLevel--;
        }
    }

    public void harvest() {
        if (this.crop.getState() == Crop.State.MATURE && !GameState.getInventory().isFull()) {
            Random rand = new Random();//m5 says fert has to have chance of increasing yield
            int adder = 1;
            if (this.fertilizerLevel > 2 && (rand).nextDouble() < fertYieldChance) {
                adder = 2;
            }
            GameState.getInventory().add(this.crop, adder);
            this.empty();
        } else if (this.crop.getState() == Crop.State.DEAD) {
            this.empty();
        }
    }

    public void empty() {
        this.crop.setState(Crop.State.EMPTY);
        this.waterLevel = 0;
        this.pesticideLevel = 0;
        this.fertilizerLevel = 0;
    }

/*
----------------------------------------------------------------------------------------------------
VARIABLE GETTERS AND SETTERS
----------------------------------------------------------------------------------------------------
*/

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

    public Crop getCrop() {
        return this.crop;
    }

    public void setCrop(Crop c) {
        this.crop = c;
    }

    public void setCropState(Crop.State s) {
        this.crop.setState(s);
    }
}

package farmsim;

import java.util.Random;

public class Plot {

    private CropState state;
    private GameState.CropType cropType;

    public CropState getState() {
        return state;
    }

    public void setState(CropState state) {
        this.state = state;
    }

    public GameState.CropType getCropType() {
        return cropType;
    }

    public void setCropType(GameState.CropType cropType) {
        this.cropType = cropType;
    }

    public enum CropState {
        EMPTY, //no crop currently planted, this means available to plant
        SEED,
        IMMATURE,
        MATURE;
    }

    public Plot() {
        Random rand = new Random(); //instructions say have random maturity for this milestone
        int var = rand.nextInt(4);
        this.state = CropState.values()[var];
        this.cropType = GameState.getCropType();
    }

    public void plant(GameState.CropType cropType) {
        state = CropState.SEED;
        this.cropType = cropType;
    }

    public void harvest() {
        if (state == CropState.MATURE && !GameState.getInventory().isFull()) {
            state = CropState.EMPTY;
            GameState.getInventory().getCropNum()[cropType.ordinal()]++;
        }
    }

}

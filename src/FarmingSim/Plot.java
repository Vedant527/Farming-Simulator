package FarmingSim;

import FarmingSim.Controllers.CustomizationPageController;

import java.util.Random;

public class Plot {

    public CropState state;
    public GameState.CropType cropType;

    public enum CropState {
        EMPTY,//no crop currently planted, thsi means available to plant
        SEED,
        IMMATURE,
        MATURE;
    }

    public Plot() {
        Random rand = new Random();//instructions say we have to have random assortment of maturity for this milestone
        int var = rand.nextInt(4);
        this.state = CropState.values()[var];
        this.cropType = CustomizationPageController.cropType;
    }

    public void plant(GameState.CropType cropType) {
        state = CropState.SEED;
        this.cropType = cropType;
    }

    public void harvest() {
        if (state == CropState.MATURE && !Inventory.isFull()) {
            state = CropState.EMPTY;
            Inventory.cropNum[cropType.ordinal()]++;
        }
    }

}

package FarmingSim;

import FarmingSim.Controllers.CustomizationPageController;

import java.util.Random;

public class Plot {

    public CropState state;
    public Settings.CropType cropType;

    public enum CropState {
        EMPTY,//no crop currently planted, thsi means available to plant
        SEED,
        IMMATURE,
        MATURE;
    }

    public void Plot() {
        Random rand = new Random();//instructions say we have to have random assortment of maturity for this milestone
        state = CropState.values()[rand.nextInt(3)];
        this.cropType = CustomizationPageController.cropType;
    }

    public void plant(Settings.CropType cropType) {
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

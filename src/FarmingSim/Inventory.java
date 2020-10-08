package FarmingSim;

import FarmingSim.Controllers.CustomizationPageController;
import FarmingSim.Settings;


public class Inventory {
    /* ORDER of indexs
    0 = Corn
    1 = Hemp
    2 = Wheat
    3 = Tobacco
    */
    public static int[] seedNum = new int[Settings.Seed.size()];
    public static int[] cropNum = new int[Settings.Seed.size()];

    public static int day;

    public static int money;

    //at a later date we can make these changeable with adding inventory space or something
    public static int MAX_SEED_INVENTORY = 100;
    public static int MAX_CROP_INVENTORY = 100;

    public static void setDefault() {
        /*
        switch (CustomizationPageController.seed) {
            //I am hoping that this meets their request of "just the starter seeds selected on original config page"
            //but we should definitely ask someone about that
            case CORN:
                cornSeedNum = 10;
                hempSeedNum = 0;
                wheatSeedNum = 0;
                tobaccoSeedNum = 0;
            case WHEAT:
                cornSeedNum = 0;
                hempSeedNum = 0;
                wheatSeedNum = 10;
                tobaccoSeedNum = 0;
                break;
            case TOBACCO:
                cornSeedNum = 0;
                hempSeedNum = 0;
                wheatSeedNum = 0;
                tobaccoSeedNum = 10;
                break;
            case HEMP:
                cornSeedNum = 0;
                hempSeedNum = 10;
                wheatSeedNum = 0;
                tobaccoSeedNum = 0;
                break;
        }
        cornCropNum = 0;
        wheatCropNum = 0;
        hempCropNum = 0;
        tobaccoCropNum = 0;
        day = 0;
        switch (CustomizationPageController.difficulty) {
            case EASY:
                money = 500;
                break;
            case MEDIUM:
                money = 300;
                break;
            case HARD:
                money = 100;
                break;
        }
        */
        for (int i = 0; i < Settings.Seed.size(); i++) {
            seedNum[i] = 0;
            cropNum[i] = 0;
        }
        seedNum[CustomizationPageController.seed.ordinal()] = 10;
        money = (new int[]{500, 300, 100})[CustomizationPageController.seed.ordinal()];
    }
}


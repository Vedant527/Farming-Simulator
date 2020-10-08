package FarmingSim;

import FarmingSim.Controllers.CustomizationPageController;
import FarmingSim.Settings;


public class Inventory {
    public static Integer cornSeedNum;
    public static Integer hempSeedNum;
    public static Integer wheatSeedNum;
    public static Integer tobaccoSeedNum;

    public static Integer cornCropNum;
    public static Integer hempCropNum;
    public static Integer wheatCropNum;
    public static Integer tobaccoCropNum;

    public static Integer day;

    public static Integer money;

    public static Integer dayNum;

    //at a later date we can make these changeable with adding inventory space or something
    public static final Integer MAX_SEED_INVENTORY = 100;
    public static final Integer MAX_CROP_INVENTORY = 100;

    public static void setDefault() {
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
    }
}


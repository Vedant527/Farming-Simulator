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
        for (int i = 0; i < Settings.Seed.size(); i++) {
            seedNum[i] = 0;
            cropNum[i] = 0;
        }
        seedNum[CustomizationPageController.seed.ordinal()] = 10;
        money = (new int[]{500, 300, 100})[CustomizationPageController.seed.ordinal()];
    }
}


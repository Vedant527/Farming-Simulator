package FarmingSim;

import FarmingSim.Controllers.CustomizationPageController;
import FarmingSim.Controllers.FarmUIController;
import FarmingSim.Controllers.MarketController;
import FarmingSim.Controllers.StartController;

public class GameState {
    public static UIUpdateable startController = new StartController();
    public static UIUpdateable customizationPageController = new CustomizationPageController();
    public static UIUpdateable farmUIController = new FarmUIController();
    public static UIUpdateable marketController = new MarketController();
    public static ScreenManager screenManager = new ScreenManager();

    public static boolean[] hasInited = {false, false, false, false};

    public static Plot[] plots;

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD;
    }

    public enum Season {
        SPRING,
        SUMMER,
        FALL,
        WINTER;
    }

    public enum CropType {
        CORN,
        WHEAT,
        TOBACCO,
        HEMP;

        public static int size() {
            return CropType.values().length;
        }
    }
}

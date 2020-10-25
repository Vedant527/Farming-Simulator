package farmsim;

import farmsim.Controllers.CustomizationPageController;
import farmsim.Controllers.FarmUIController;
import farmsim.Controllers.MarketController;
import farmsim.Controllers.StartController;

public class GameState {
    private static UIUpdateable startController = new StartController();
    private static UIUpdateable customizationPageController = new CustomizationPageController();
    private static UIUpdateable farmUIController = new FarmUIController();
    private static UIUpdateable marketController = new MarketController();
    private static ScreenManager screenManager = new ScreenManager();
    private static Inventory inventory = new Inventory();
    private static boolean[] hasInited = {false, false, false, false};
    private static Plot[] plots;
    private static int day;
    private static String name;
    private static Difficulty difficulty = GameState.Difficulty.EASY;
    private static Season season = GameState.Season.SPRING;
    private static CropType cropType = GameState.CropType.CORN;

    private static final int SEASON_CHANGE = 90;

    public static UIUpdateable getStartController() {
        return startController;
    }

    public static UIUpdateable getCustomizationPageController() {
        return customizationPageController;
    }

    public static UIUpdateable getFarmUIController() {
        return farmUIController;
    }

    public static UIUpdateable getMarketController() {
        return marketController;
    }

    public static ScreenManager getScreenManager() {
        return screenManager;
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public static boolean[] getHasInited() {
        return hasInited;
    }

    public static boolean getHasInited(int index) {
        return hasInited[index];
    }

    public static void setHasInited(boolean[] hasInited) {
        GameState.hasInited = hasInited;
    }

    public static void setHasInited(int index, boolean hasInited) {
        GameState.hasInited[index] = hasInited;
    }

    public static Plot[] getPlots() {
        return plots;
    }

    public static Plot getPlots(int index) {
        return plots[index];
    }

    public static void setPlots(Plot[] plots) {
        GameState.plots = plots;
    }

    public static void setPlots(int index, Plot plot) {
        GameState.plots[index] = plot;
    }


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        GameState.name = name;
    }


    public static int getDay() {
        return day;
    }

    public static void setDay(int day) {
        GameState.day = day;
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Difficulty difficulty) {
        GameState.difficulty = difficulty;
    }


    public static Season getSeason() {
        return season;
    }

    public static void setSeason(Season season) {
        GameState.season = season;
    }


    public static CropType getCropType() {
        return cropType;
    }

    public static void setCropType(CropType cropType) {
        GameState.cropType = cropType;
    }




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

    public static void incrementDay() {
        day++;
        if (day % SEASON_CHANGE == 0) {
            increaseSeason();
        }
        for (Plot plot : plots) {
            plot.grow();
            plot.decreaseWater();
        }
    }

    public static void increaseSeason() {
        season = Season.values()[(season.ordinal() + 1) % Season.values().length];
    }
}

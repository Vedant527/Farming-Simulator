package FarmingSim;

public class Settings {
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

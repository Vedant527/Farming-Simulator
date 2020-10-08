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

    public enum Seed {
        CORN,
        WHEAT,
        TOBACCO,
        HEMP;

        public static int size() {
            return Seed.values().length;
        }
    }
}

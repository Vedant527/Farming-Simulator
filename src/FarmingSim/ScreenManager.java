package FarmingSim;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class ScreenManager {
    private static HashMap<String, Pane> screens = new HashMap<>();
    private static Stage stage;
    private static String curr;

    public static void setStage(Stage base) {
        stage = base;
    }

    public static void addScreen(String str, Pane pane) {
        if (stage == null) {
            throw new IllegalStateException("No Stage Set");
        }
        if (screens.containsKey(str)) {
            throw new IllegalArgumentException("Screen already added");
        }
        screens.put(str, pane);
    }

    public static void removeScreen(String screen) {
        if (stage == null) {
            throw new IllegalStateException("No Stage Set");
        }
        screens.remove(screen);
    }

    public static void setScreen(String screen, Pane pane) {
        if (!screens.containsKey(screen)) {
            addScreen(screen, pane);
        }
        setScreen(screen);
    }

    public static void setScreen(String screen) {
        if (stage == null) {
            throw new IllegalStateException("No Stage Set");
        }
        
        stage.getScene().setRoot(screens.get(screen));
        stage.show();
        curr = screen;
    }

    public static String getCurrentScreen() {
        return curr;
    }

    public static Pane getCurrentScene() {
        return screens.get(curr);
    }
}
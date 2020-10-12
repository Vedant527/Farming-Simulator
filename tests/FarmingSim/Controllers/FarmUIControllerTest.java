package FarmingSim.Controllers;

import FarmingSim.ScreenManager;
import FarmingSim.Settings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;

import static FarmingSim.Controllers.FarmUIController.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.testfx.matcher.control.TextMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;

public class FarmUIControllerTest extends ApplicationTest{

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testGridDisplays() {
        FxAssert.verifyThat("#farmGrid", (GridPane f) -> f.isVisible());
        FxAssert.verifyThat("farmGrid", (GridPane f) -> {
            boolean valid = true;
            for (int i = 0; i < f.getChildren().size(); i++) {
                valid = valid && f.getChildren().get(i).isVisible();
            }
            return valid;
        });
    }

    @Test
    public void testHarvest() {
        FxAssert.verifyThat("farmGrid", (GridPane f) -> {
            boolean valid = true;
            for (int i = 0; i < f.getChildren().size(); i++) {
                if (((javafx.scene.control.Button) f.getChildren().get(i)).getText() ==
                        ("button_" + Integer.toString(i)))
                valid = valid && f.getChildren().get(i).isVisible();
            }
            return valid;
        });
    }

    @Test
    public void testMarketButton() {
        FxAssert.verifyThat("#marketButton", (Button b) -> b.isVisible());
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FarmUI.fxml"));
        ScreenManager.setStage(primaryStage);
        primaryStage.setTitle("FarmingSim");

        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }
}
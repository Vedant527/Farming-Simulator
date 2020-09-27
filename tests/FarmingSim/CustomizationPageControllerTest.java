package FarmingSim;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.ListViewMatchers;

public class CustomizationPageControllerTest extends ApplicationTest{

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
    public void testDiff() {
        clickOn("#DIF");
        FxAssert.verifyThat("#DIF", (ChoiceBox l) -> l.getItems().size() == 3);
    }

    @Test
    public void testSeason() {
        clickOn("#SEASON");
        FxAssert.verifyThat("#SEASON", (ChoiceBox l) -> l.getItems().size() == 4);
    }

    @Test
    public void testSeedType() {
        clickOn("#SEEDTYPE");
        FxAssert.verifyThat("#SEEDTYPE", (ChoiceBox l) -> l.getItems().size() == 4);
    }

    @Test
    public void move_on() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CustomizationPage.fxml"));
        ScreenManager.setStage(primaryStage);
        primaryStage.setTitle("FarmingSim");

        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

    }
}
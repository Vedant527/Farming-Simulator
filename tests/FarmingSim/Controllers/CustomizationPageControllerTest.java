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
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;

import static FarmingSim.Controllers.CustomizationPageController.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.testfx.matcher.control.TextMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;

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
        FxAssert.verifyThat("#DIF", (ChoiceBox l) -> l.getItems().size() == 3 && l.isVisible());
    }

    @Test
    public void testSeason() {
        clickOn("#SEASON");
        FxAssert.verifyThat("#SEASON", (ChoiceBox l) -> l.getItems().size() == 4 && l.isVisible());
    }

    @Test
    public void testSeedType() {
        clickOn("#SEEDTYPE");
        FxAssert.verifyThat("#SEEDTYPE", (ChoiceBox l) -> l.getItems().size() == 4 && l.isVisible());
    }

    @Test
    public void testStoresCorrectDiff() {
        clickOn("#DIF");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        FxAssert.verifyThat("#DIF", node -> difficulty.equals(((ChoiceBox) node).getValue()));
        assertEquals(Settings.Difficulty.MEDIUM,difficulty);
    }
    @Test
    public void testStoresCorrectSeed() {
        clickOn("#SEEDTYPE");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        FxAssert.verifyThat("#SEEDTYPE", node -> seed.equals(((ChoiceBox) node).getValue()));
        assertEquals(Settings.Seed.HEMP,seed);
    }
    @Test
    public void testStoresCorrectSeason() {
        clickOn("#SEASON");
        type(KeyCode.ENTER);
        FxAssert.verifyThat("#SEASON", node -> season.equals(((ChoiceBox) node).getValue()));
        assertEquals(Settings.Season.SPRING, season);
    }
    @Test
    public void testName() {
        clickOn("#NAME");
        write("testname");
        type(KeyCode.ENTER);
        sleep(500);
        FxAssert.verifyThat("#NAME", node -> name.equals(((TextField) node).getText()));
    }
    @Test
    public void testStartButton() {
        FxAssert.verifyThat("#START", (Button b) -> b.isVisible());
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
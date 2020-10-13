package FarmingSim.Controllers;

import FarmingSim.ScreenManager;
import FarmingSim.GameState;
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
        GameState.hasInited = new boolean[]{false, false, false, false};
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
        type(KeyCode.ENTER);
        FxAssert.verifyThat("#DIF", node -> difficulty.equals(((ChoiceBox) node).getValue()));
        assertEquals(GameState.Difficulty.EASY,difficulty);
    }
    @Test
    public void testStoresCorrectSeed() {
        clickOn("#SEEDTYPE");
        type(KeyCode.ENTER);
        FxAssert.verifyThat("#SEEDTYPE", node -> cropType.equals(((ChoiceBox) node).getValue()));
        assertEquals(GameState.CropType.CORN, cropType);
    }
    @Test
    public void testStoresCorrectSeason() {
        clickOn("#SEASON");
        type(KeyCode.ENTER);
        FxAssert.verifyThat("#SEASON", node -> season.equals(((ChoiceBox) node).getValue()));
        assertEquals(GameState.Season.SPRING, season);
    }
    @Test
    public void testName() {
        clickOn("#NAME");
        String tester = "testname";
        write(tester);
        type(KeyCode.ENTER);
        sleep(500);
        FxAssert.verifyThat("#NAME", (TextField node) -> node.getText().equals(tester));
    }
    @Test
    public void testStartButton() {
        FxAssert.verifyThat("#START", (Button b) -> b.isVisible());
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FarmingSim");
        GameState.screenManager.stage = primaryStage;
        GameState.screenManager.setScreen("FXML/CustomizationPage.fxml");
    }
}
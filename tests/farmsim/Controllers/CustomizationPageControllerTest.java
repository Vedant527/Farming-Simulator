package farmsim.Controllers;

import farmsim.GameState;
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
import org.testfx.framework.junit.ApplicationTest;

import static farmsim.Controllers.CustomizationPageController.*;
import static org.junit.Assert.assertEquals;

public class CustomizationPageControllerTest extends ApplicationTest {

    @Before
    public void setUp() throws Exception {
        GameState.setHasInited(new boolean[]{false, false, false, false});
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testDiff() {
        clickOn("#diffBox");
        FxAssert.verifyThat("#diffBox", (ChoiceBox l) -> {
            return l.getItems().size() == 3 && l.isVisible();
        });
    }

    @Test
    public void testSeason() {
        clickOn("#seasonBox");
        FxAssert.verifyThat("#seasonBox", (ChoiceBox l) -> {
            return l.getItems().size() == 4 && l.isVisible();
        });
    }

    @Test
    public void testSeedType() {
        clickOn("#seedBox");
        FxAssert.verifyThat("#seedBox", (ChoiceBox l) -> {
            return l.getItems().size() == 4 && l.isVisible();
        });
    }

    @Test
    public void testStoresCorrectDiff() {
        clickOn("#diffBox");
        type(KeyCode.ENTER);
        FxAssert.verifyThat("#diffBox", (ChoiceBox node) -> {
            return GameState.getDifficulty().equals(node.getValue());
        });
        assertEquals(GameState.Difficulty.EASY, GameState.getDifficulty());
    }

    @Test
    public void testStoresCorrectSeed() {
        clickOn("#seedBox");
        type(KeyCode.ENTER);
        FxAssert.verifyThat("#seedBox", (ChoiceBox node) -> {
            return GameState.getCropType().equals(node.getValue());
        });
        assertEquals(GameState.CropType.CORN, GameState.getCropType());
    }

    @Test
    public void testStoresCorrectSeason() {
        clickOn("#seasonBox");
        type(KeyCode.ENTER);
        FxAssert.verifyThat("#seasonBox", node -> {
            return GameState.getSeason().equals(((ChoiceBox) node).getValue());
        });
        assertEquals(GameState.Season.SPRING, GameState.getSeason());
    }

    @Test
    public void testName() {
        clickOn("#nameField");
        String tester = "testname";
        write(tester);
        type(KeyCode.ENTER);
        sleep(500);
        FxAssert.verifyThat("#nameField", (TextField node) -> {
            return node.getText().equals(tester);
        });
    }

    @Test
    public void testStartButton() {
        FxAssert.verifyThat("#startButton", (Button b) -> b.isVisible());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FarmingSim");
        GameState.getScreenManager().setStage(primaryStage);
        GameState.getScreenManager().setScreen("FXML/CustomizationPage.fxml");
    }
}
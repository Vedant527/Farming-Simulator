
package farmsim.Controllers;

import farmsim.Crop;
import farmsim.GameState;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class FarmUIControllerTest extends ApplicationTest {
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
        FxAssert.verifyThat("#farmGrid", (GridPane f) -> {
            boolean valid = true;
            for (int i = 0; i < f.getChildren().size(); i++) {
                valid = valid && f.getChildren().get(i).isVisible();
            }
            return valid;
        });
    }

    @Test
    public void testHarvest() {
        int i = 0;
        GameState.getPlots(i).setCropState(Crop.State.MATURE);
        clickOn("#" + i);
        FxAssert.verifyThat("#cornCropText", (Text t) -> {
            return (t.getText().equals("Corn: 1"));
        });
    }

    @Test
    public void testMarketButton() {
        FxAssert.verifyThat("#marketButton", (Button b) -> b.isVisible());
    }

    @Test
    public void testSelectsType() {
        clickOn("#seedBox");
        FxAssert.verifyThat("#seedBox", (ChoiceBox l) -> {
            return l.getItems().size() == 4 && l.isVisible();
        });
    }

    @Test
    public void testDay() {
        clickOn("#nextDayButton");
        type(KeyCode.ENTER);
        assertEquals(1, GameState.getDay());
    }

    @Test
    public void testChangeSeason() {
        for (int i = 0; i < 100; i++) {
            clickOn("#nextDayButton");
            type(KeyCode.ENTER);
        }
        assertEquals(GameState.Season.SUMMER, GameState.getSeason());
    }

    @Test
    public void testIncWater() {
        int plot = 0;
        GameState.setCropType(GameState.CropType.CORN);
        GameState.getInventory().setSeedNum(GameState.CropType.CORN, 10);
        GameState.getPlots(plot).setCropState(Crop.State.EMPTY);
        for (int i = 0; i < 3; i++) {
            clickOn("#" + plot);
            type(KeyCode.ENTER);
        }
        assertEquals(2, GameState.getPlots(0).getWaterLevel());
    }

    @Test
    public void testDead() {
        int plot = 0;
        GameState.setCropType(GameState.CropType.CORN);
        GameState.getInventory().setSeedNum(GameState.CropType.CORN, 10);
        GameState.getPlots(plot).setCropState(Crop.State.EMPTY);
        for (int i = 0; i < 6; i++) {
            clickOn("#" + plot);
            type(KeyCode.ENTER);
        }
        assertEquals(Crop.State.DEAD, GameState.getPlots(plot).getCrop().getState());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FarmingSim");
        GameState.getScreenManager().setStage(primaryStage);
        GameState.getScreenManager().setScreen("FXML/FarmUI.fxml");
    }
}
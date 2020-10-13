
package farmsim.Controllers;

import farmsim.GameState;
import farmsim.Plot;
import javafx.scene.control.Button;
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
        for (; i < GameState.getPlots().length; i++) {
            if (GameState.getPlots(i).getState() == Plot.CropState.MATURE) {
                break;
            }
        }
        clickOn("#" + i);
        FxAssert.verifyThat("#cornCropText", (Text t) -> {
            return (t.getText().equals("Corn: 1"));
        });
    }

    @Test
    public void testMarketButton() {
        FxAssert.verifyThat("#marketButton", (Button b) -> b.isVisible());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FarmingSim");
        GameState.getScreenManager().setStage(primaryStage);
        GameState.getScreenManager().setScreen("FXML/FarmUI.fxml");
    }
}
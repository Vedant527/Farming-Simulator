package farmsim.Controllers;

import farmsim.GameState;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;


public class StartControllerTest extends ApplicationTest {

    @Before
    public void setUp() throws Exception {
    }
    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FarmingSim");
        GameState.getScreenManager().setStage(primaryStage);
        GameState.getScreenManager().setScreen("FXML/Start.fxml");
    }

    @Test
    public void testWelcomeLabel() {
        FxAssert.verifyThat("#welcome", (Node l) -> l.isVisible());
    }
    @Test
    public void testSubmitButton() {
        FxAssert.verifyThat("#move_on_button", (Node l) -> l.isVisible());
    }

}
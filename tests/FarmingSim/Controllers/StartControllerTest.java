package FarmingSim.Controllers;

import FarmingSim.GameState;
import FarmingSim.ScreenManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxAssert;


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
        GameState.screenManager.stage = primaryStage;
        GameState.screenManager.setScreen("FXML/Start.fxml");
        }

    @Test
    public void testWelcomeLabel(){
        FxAssert.verifyThat("#welcome", (Node l)-> l.isVisible());
    }
    @Test
    public void testSubmitButton() {
        FxAssert.verifyThat("#move_on_button", (Node l)-> l.isVisible());
    }

}
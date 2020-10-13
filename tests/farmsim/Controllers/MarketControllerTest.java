package farmsim.Controllers;

import farmsim.GameState;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class MarketControllerTest extends ApplicationTest {
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
    public void testSell() {
        GameState.getInventory().setMoney(0);
        GameState.getInventory().setCropNum(0, 20);
        clickOn("#sellCornButton");
        FxAssert.verifyThat("#moneyDisplay", (Text t) -> {
            return t.getText().equals("Money: $15")
                    && GameState.getInventory().getCropNum()[0] == 19
                    && GameState.getInventory().getMoney() == 15;
        });
    }

    @Test
    public void testSellNoCrops() {
        GameState.getInventory().setMoney(0);
        GameState.getInventory().setCropNum(0, 0);
        clickOn("#sellCornButton");
        FxAssert.verifyThat("#moneyDisplay", (Text t) -> {
            return t.getText().equals("Money: $0") && GameState.getInventory().getCropNum()[0] == 0
                    && GameState.getInventory().getMoney() == 0;
        });
    }

    @Test
    public void testBuy() {
        GameState.getInventory().setMoney(500);
        clickOn("#buyCornButton");
        assertEquals(1, GameState.getInventory().getSeedNum()[0]);
    }

    @Test
    public void testBuyNoMoney() {
        GameState.getInventory().setMoney(0);
        GameState.getInventory().setSeedNum(0, 0);
        clickOn("#sellCornButton");
        assertEquals(0, GameState.getInventory().getSeedNum()[0]);
    }

    @Test
    public void testFarmButton() {
        FxAssert.verifyThat("#farmButton", (Button b) -> b.isVisible());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FarmingSim");
        GameState.getScreenManager().setStage(primaryStage);
        GameState.getScreenManager().setScreen("FXML/Market.fxml");
    }
}
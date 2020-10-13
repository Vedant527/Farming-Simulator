package FarmingSim.Controllers;

import FarmingSim.Inventory;
import FarmingSim.Plot;
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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
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

public class MarketControllerTest extends ApplicationTest{
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
        Inventory.money = 0;
        Inventory.cropNum[0] = 20;
        clickOn("#sellCornButton");
        FxAssert.verifyThat("#moneyDisplay", (Text t) -> {
            return t.getText().equals("Money: $10") && Inventory.cropNum[0] == 19
                    && Inventory.money == 10;
        });
    }

    @Test
    public void testSellNoCrops() {
        Inventory.money = 0;
        Inventory.cropNum[0] = 0;
        clickOn("#sellCornButton");
        FxAssert.verifyThat("#moneyDisplay", (Text t) -> {
            return t.getText().equals("Money: $0") && Inventory.cropNum[0] == 0
                    && Inventory.money == 0;
        });
    }

    @Test
    public void testBuy() {
        Inventory.money = 500;
        clickOn("#buyCornButton");
        assertEquals(1, Inventory.seedNum[0]);
    }

    @Test
    public void testBuyNoMoney() {
        Inventory.money = 0;
        Inventory.seedNum[0] = 0;
        clickOn("#sellCornButton");
        assertEquals(0, Inventory.seedNum[0]);
    }

    @Test
    public void testFarmButton() {
        FxAssert.verifyThat("#farmButton", (Button b) -> b.isVisible());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FarmingSim");
        GameState.screenManager.stage = primaryStage;
        GameState.screenManager.setScreen("FXML/Market.fxml");
    }
}
package FarmingSim.Controllers;

import FarmingSim.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class CustomizationPageController extends UIUpdateable {

    ObservableList<GameState.Difficulty> difficulties = FXCollections
            .observableArrayList(GameState.Difficulty.values());
    ObservableList<GameState.Season> seasons = FXCollections
            .observableArrayList(GameState.Season.values());
    ObservableList<GameState.CropType> cropTypes = FXCollections
            .observableArrayList(GameState.CropType.values());

    @FXML
    private ChoiceBox DIF;
    @FXML
    private ChoiceBox SEASON;
    @FXML
    private ChoiceBox SEEDTYPE;
    @FXML
    private TextField NAME;
    @FXML
    private Button START;

    public CustomizationPageController() {
        super(1);
    }

    public static String name;
    public static GameState.Difficulty difficulty = GameState.Difficulty.EASY;
    public static GameState.Season season = GameState.Season.SPRING;
    public static GameState.CropType cropType = GameState.CropType.CORN;

    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    @Override
    public void firstInit() {
        JButton start = new JButton();
        start.setIcon(new ImageIcon("FarmingSim/Resources/StartButton.png"));
        // Initializes dropdowns
        SEASON.setItems(seasons);
        DIF.setItems(difficulties);
        SEEDTYPE.setItems(cropTypes);
        // Default values for dropdowns
        SEASON.setValue(GameState.Season.values()[0]);
        DIF.setValue(difficulty.values()[0]);
        SEEDTYPE.setValue(cropType.values()[0]);

        Image img = new Image("FarmingSim/Resources/StartButton.png");
        ImageView startButton = new ImageView(img);
        startButton.setFitWidth(120);
        startButton.setFitHeight(40);

        START.setGraphic(startButton);


    }

    private void setFields() {
        name = NAME.getText();
        season = (GameState.Season) SEASON.getValue();
        difficulty = (GameState.Difficulty) DIF.getValue();
        cropType = (GameState.CropType) SEEDTYPE.getValue();

    }

    public void updatesName(ActionEvent e) {
        name = NAME.getText();
    }

    public void updatesDifficulty(ActionEvent e) {
        difficulty = (GameState.Difficulty) DIF.getValue();
    }

    public void updatesSeed(ActionEvent e) {
        cropType = (GameState.CropType)L SEEDTYPE.getValue();
    }

    public void updatesSeason(ActionEvent e) {
        season = (GameState.Season) SEASON.getValue();
    }


    public void move_on(ActionEvent e) throws Exception {
        setFields();
        //if name is bad don't let them pass
        if (name.trim().equals("")) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("No name has been entered");
            a.show();
            return;
        }
        Inventory.setDefault(CustomizationPageController.cropType);
        GameState.screenManager.setScreen(
                "FXML/FarmUI.fxml"
        );
        //ScreenManager.getCurrentScene();
        //ScreenManager.setScreen("Player");
    }

    @Override
    public void updateUI(){
        return;
    }

}

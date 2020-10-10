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

public class CustomizationPageController {

    ObservableList<Settings.Difficulty> difficulties = FXCollections
            .observableArrayList(Settings.Difficulty.values());
    ObservableList<Settings.Season> seasons = FXCollections
            .observableArrayList(Settings.Season.values());
    ObservableList<Settings.CropType> cropTypes = FXCollections
            .observableArrayList(Settings.CropType.values());

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


    //i made these guys public so that inventory can see them, they are in a sub-package (?) so can't access thru default visibility
    public static String name;
    public static Settings.Difficulty difficulty;
    public static Settings.Season season;
    public static Settings.CropType cropType;

    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    private void initialize() {
        JButton start = new JButton();
        start.setIcon(new ImageIcon("FarmingSim/Resources/StartButton.png"));
        // Initializes dropdowns
        SEASON.setItems(seasons);
        DIF.setItems(difficulties);
        SEEDTYPE.setItems(cropTypes);
        // Default values for dropdowns
        SEASON.setValue(Settings.Season.values()[0]);
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
        season = (Settings.Season) SEASON.getValue();
        difficulty = (Settings.Difficulty) DIF.getValue();
        cropType = (Settings.CropType) SEEDTYPE.getValue();

    }

    public void updatesName(ActionEvent e) {
        name = NAME.getText();
    }

    public void updatesDifficulty(ActionEvent e) {
        difficulty = (Settings.Difficulty) DIF.getValue();
    }

    public void updatesSeed(ActionEvent e) {
        cropType = (Settings.CropType) SEEDTYPE.getValue();
    }

    public void updatesSeason(ActionEvent e) {
        season = (Settings.Season) SEASON.getValue();
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
        Inventory.setDefault();
        ScreenManager.setScreen(
                "FarmUI",
                FXMLLoader.load(getClass().getResource("../FXML/FarmUI.fxml"))
        );
        //ScreenManager.getCurrentScene();
        //ScreenManager.setScreen("Player");
    }


}

package farmsim.Controllers;

import farmsim.GameState;
import farmsim.UIUpdateable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class CustomizationPageController extends UIUpdateable {

    private ObservableList<GameState.Difficulty> difficulties = FXCollections
            .observableArrayList(GameState.Difficulty.values());
    private ObservableList<GameState.Season> seasons = FXCollections
            .observableArrayList(GameState.Season.values());
    private ObservableList<GameState.CropType> cropTypes = FXCollections
            .observableArrayList(GameState.CropType.values());

    @FXML
    private ChoiceBox diffBox;
    @FXML
    private ChoiceBox seasonBox;
    @FXML
    private ChoiceBox seedBox;
    @FXML
    private TextField nameField;
    @FXML
    private Button startButton;

    public CustomizationPageController() {
        super(1);
    }

    private Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    @Override
    public void firstInit() {
        JButton start = new JButton();
        start.setIcon(new ImageIcon("FarmingSim/Resources/StartButton.png"));
        // Initializes dropdowns
        seasonBox.setItems(seasons);
        diffBox.setItems(difficulties);
        seedBox.setItems(cropTypes);
        // Default values for dropdowns
        seasonBox.setValue(GameState.Season.values()[0]);
        diffBox.setValue(GameState.Difficulty.values()[0]);
        seedBox.setValue(GameState.CropType.values()[0]);

        Image img = new Image("farmsim/Resources/StartButton.png");
        ImageView startButton = new ImageView(img);
        startButton.setFitWidth(120);
        startButton.setFitHeight(40);

        this.startButton.setGraphic(startButton);


    }

    private void setFields() {
        GameState.setName(nameField.getText());
        GameState.setSeason((GameState.Season) seasonBox.getValue());
        GameState.setDifficulty((GameState.Difficulty) diffBox.getValue());
        GameState.setCropType((GameState.CropType) seedBox.getValue());

    }

    public void updatesName(ActionEvent e) {
        GameState.setName(nameField.getText());
    }

    public void updatesDifficulty(ActionEvent e) {
        GameState.setDifficulty((GameState.Difficulty) diffBox.getValue());
    }

    public void updatesSeed(ActionEvent e) {
        GameState.setCropType((GameState.CropType) seedBox.getValue());
    }

    public void updatesSeason(ActionEvent e) {
        GameState.setSeason((GameState.Season) seasonBox.getValue());
    }

    public void moveOn(ActionEvent e) throws Exception {
        setFields();
        if (GameState.getName().trim().equals("")) {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("No name has been entered");
            a.show();
            return;
        }
        GameState.getInventory().setDefault(GameState.getCropType());
        GameState.getScreenManager().setScreen(
                "FXML/FarmUI.fxml"
        );
    }

    @Override
    public void updateUI() {
        return;
    }

}

package FarmingSim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class CustomizationPageController {
    ObservableList<String> difficulties = FXCollections
            .observableArrayList("Easy", "Medium", "Hard");
    ObservableList<String> seasons = FXCollections
            .observableArrayList("Spring", "Summer", "Fall", "Winter");
    ObservableList<String> seeds = FXCollections
            .observableArrayList("Corn", "Wheat", "Tobacco", "Hemp");

    @FXML private ChoiceBox DIF;
    @FXML private ChoiceBox SEASON;
    @FXML private ChoiceBox SEEDTYPE;
    @FXML private TextField NAME;
    @FXML private Button START;

    String name, difficulty, season;

    @FXML
    private void initialize(){
        JButton start = new JButton();
        start.setIcon(new ImageIcon("FarmingSim/Resources/StartButton.png"));
        SEASON.setItems(seasons);
        DIF.setItems(difficulties);
        SEEDTYPE.setItems(seeds);

        Image img = new Image("FarmingSim/Resources/StartButton.png");
        ImageView startButton = new ImageView(img);
        startButton.setFitWidth(120);
        startButton.setFitHeight(40);

        START.setGraphic(startButton);


    }

    private void setFields(){
        name = NAME.getText();

    }






}

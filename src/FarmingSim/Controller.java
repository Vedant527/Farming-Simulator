package FarmingSim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller {
    ObservableList<String> difficulties = FXCollections
            .observableArrayList("Easy", "Medium", "Hard");
    ObservableList<String> seasons = FXCollections
            .observableArrayList("Spring", "Summer", "Fall", "Winter");

    @FXML private ChoiceBox DIF;
    @FXML private ChoiceBox SEASON;
    @FXML private TextField NAME;

    String name = NAME.getText();





}

package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML
    TextField NAME;
    String name = NAME.getText();

    @FXML ComboBox<String> DIFFICULTY;
    @FXML ComboBox<String> SEASON;

    ObservableList<String> difficulties = FXCollections
            .observableArrayList("Easy", "Medium", "Hard");
    ObservableList<String> seasons = FXCollections
            .observableArrayList("Spring", "Summer", "Fall", "Winter");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Farm Simulator");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

        DIFFICULTY.setItems(difficulties);
        SEASON.setItems(seasons);


    }


    public static void main(String[] args) {
        launch(args);
    }
}

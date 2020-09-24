package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

//    @FXML
//    TextField NAME;
//    String name = NAME.getText();
//
//    @FXML ComboBox<String> DIFFICULTY;
//    @FXML ComboBox<String> SEASON;
//    ObservableList<String> difficulties = FXCollections
//            .observableArrayList("Easy", "Medium", "Hard");
//    ObservableList<String> seasons = FXCollections
//            .observableArrayList("Spring", "Summer", "Fall", "Winter");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CustomizePage.fxml"));
        primaryStage.setTitle("Farm Simulator");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

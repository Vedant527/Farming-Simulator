package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage base;

    public enum State {
        START,
        PLAYER
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.base = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        this.base.setTitle("Hello World");
        this.base.setScene(new Scene(root, 300, 275));
        this.base.show();
    }

    @FXML
    public void move_on() throws Exception{
        setState(State.PLAYER);
    }
    @FXML
    public void move_back() throws Exception {
        setState(State.START);
    }

    private void setState(State val) throws Exception {
        switch (val){
            case START: load("start.fxml");
            case PLAYER: load("player.fxml");
        }
    }

    @FXML
    private void load(String name) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(name));
        this.base.getScene().setRoot(root);
        //this.base.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

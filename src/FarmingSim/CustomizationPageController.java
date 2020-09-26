package FarmingSim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class CustomizationPageController {
    ObservableList<Difficulty> difficulties = FXCollections
            .observableArrayList(Difficulty.values());
    ObservableList<Season> seasons = FXCollections
            .observableArrayList(Season.values());
    ObservableList<Seed> seeds = FXCollections
            .observableArrayList(Seed.values());

    @FXML private ChoiceBox DIF;
    @FXML private ChoiceBox SEASON;
    @FXML private ChoiceBox SEEDTYPE;
    @FXML private TextField NAME;
    @FXML private Button START;

    static String name;
    static Difficulty difficulty;
    static Season season;
    static Seed seed;

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
        season = (Season) SEASON.getValue();
        difficulty = (Difficulty) DIF.getValue();
        seed = (Seed) SEEDTYPE.getValue();

    }



    public void move_on(ActionEvent e) throws Exception {
        setFields();
        //if name is bad don't let them pass
        if (name == null) {
            return;
        }
        ScreenManager.addScreen(
                "Player",
                FXMLLoader.load(getClass().getResource("FarmUI.fxml"))
        );
        ScreenManager.setScreen("Player");
    }


}

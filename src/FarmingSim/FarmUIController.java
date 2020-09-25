package FarmingSim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.HashMap;

public class FarmUIController {

    @FXML
    private Text moneyDisplay;
    private Integer money = 0;

    @FXML
    private Text dayDisplay;
    private Integer dayNum = 0;

    @FXML
    public void incDay(){
        dayNum++;
        dayDisplay.setText("Day: " + dayNum.toString());
    }

    @FXML
    public void alterMoney(int amt){
        money += amt;
        moneyDisplay.setText("Money: " + money.toString());
    }

    @FXML
    public void increaseMoney(ActionEvent actionEvent) {
        alterMoney(1);
        incDay();
    }
}

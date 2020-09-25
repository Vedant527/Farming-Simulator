import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Map;
import java.util.HashMap;

/*controller class for the UI, uses a map for the plots' titled panes
also uses a map of plots, maybe this is bad
The reason: i want to keep the UI stuff and the actual stuff accessible w
 */
public class InitialFarmUIController {
    @FXML
    private Accordion plotList;
    @FXML
    private Label farmerName;
    @FXML
    private Label money;

    private Map<String,TitledPane> tpMap;
    private Map<String,Plot> plotMap;

    @FXML
    public void initialize() {
        farmerName.setText("placeholder farmer's name");
        money.setText("amoutn of money");
        tpMap = new HashMap<>();
        plotMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Plot plt = new Plot();
            String key = "Plot" + i;
            TitledPane tp = new TitledPane(key, this.plotGridPane(plt));
            tpMap.put(key, tp);
            plotMap.put(key, plt);
            plotList.getPanes().addAll(tp);
        }
    }

    private GridPane plotGridPane(Plot plot) {
        GridPane gridPane = new GridPane();
        gridPane.add(new Text("Crop Type: "), 0, 0);
        gridPane.add(new Text(plot.getCropType().name()), 1, 0);
        gridPane.add(new Text("Amount: "), 0, 1);
        gridPane.add(new Text(Integer.toString(plot.getBushels())), 1, 1);
        gridPane.add(new Text("Ready to harvest: "), 0, 2);
        gridPane.add(new Text(Boolean.toString(plot.isReady())), 1, 2);
        gridPane.add(new Button("Harvest"), 0, 3);
        return gridPane;
    }
}

package viewControllers;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Algorithm;
import models.Field;
import models.Lane;

/**
 *
 */
public class HistoryController extends ViewController {
    @FXML
    private VBox playgrounds;
    private Algorithm algorithm;
    private Lane lane;


    //like constructor
    @Override
    public void setup(NavigationController navigationController) {
        this.algorithm = navigationController.getAlgorithm();
        playgrounds.getChildren().clear();

        for (int i = 0; i < 20; i++) {
            HBox hBox = new HBox();

            for (Field f : algorithm.getLane().getFields()) {
                hBox.getChildren().add(f.getLabel());
            }
            playgrounds.getChildren().add(hBox);
        }
    }

    @Override
    public void destroy() {

    }
}

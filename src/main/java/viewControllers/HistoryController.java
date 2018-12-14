package viewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Algorithm;

/**
 *
 */
public class HistoryController extends ViewController {
    @FXML
    private VBox playgrounds;
    private Algorithm algorithm;



    //like constructor
    @Override
    public void setup(NavigationController navigationController) {
        this.algorithm = navigationController.getAlgorithm();
        playgrounds.getChildren().clear();

        for (int i = 0; i < 20; i++) {
            HBox hBox = new HBox();

            for(int j = 0; j < algorithm.getLane().getFields().size(); j++) {
                Label label = new Label();
                label.setId("white");
                label.setPrefSize(playgrounds.getWidth()/algorithm.getLane().getFields().size(), playgrounds.getHeight()/20);
                hBox.getChildren().add(label);
            }
            playgrounds.getChildren().add(hBox);
        }
    }

    @Override
    public void destroy() {

    }
}

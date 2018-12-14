package viewControllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.Algorithm;
import models.Field;
import models.State;

/**
 *
 */
public class HistoryController extends ViewController {
    @FXML
    private VBox playgrounds;
    private Algorithm algorithm;
    private Timeline updater;

    @Override
    public void setup(NavigationController navigationController) {

        this.algorithm = navigationController.getAlgorithm();
        playgrounds.getChildren().clear();

        this.updater = new Timeline(new KeyFrame(Duration.millis(1000 / algorithm.getTicksPerSecond()), e -> {
            playgrounds.getChildren().clear();
            for(State state : algorithm.getLane().getPreviousStates()) {
                HBox hBox = new HBox();
                for(Field field : state.getFields()) {
                    hBox.getChildren().add(field.getLabel());
                }
                playgrounds.getChildren().add(hBox);
            }
        }));
        this.updater.setCycleCount(Timeline.INDEFINITE);
        this.updater.play();
    }

    @Override
    public void destroy() {
        this.updater.stop();
    }
}

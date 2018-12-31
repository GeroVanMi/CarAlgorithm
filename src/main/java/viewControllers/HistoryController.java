package viewControllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.Algorithm;
import models.Field;

public class HistoryController extends ViewController {
    @FXML
    private VBox playgrounds;
    private NavigationController navigationController;
    private Algorithm algorithm;

    @Override
    public void setup(NavigationController navigationController) {
        this.navigationController = navigationController;
        this.algorithm = navigationController.getAlgorithm();
        playgrounds.getChildren().clear();

        for (int i = 0; i < 20; i++) {
            HBox hBox = new HBox();

            for (int j = 0; j < algorithm.getLane().getFields().size(); j++) {
                Label label = new Label();
                label.setId("white");
                label.setPrefSize(playgrounds.getWidth() / algorithm.getLane().getFields().size(), playgrounds.getHeight() / 20);
                hBox.getChildren().add(label);
            }
            playgrounds.getChildren().add(hBox);
        }
        Timeline updater = new Timeline(new KeyFrame(Duration.millis(1000 / algorithm.getTicksPerSecond()), e -> {
            this.tick();
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
    }

    public void tick() {
        if(algorithm.getLoop().getStatus() == Animation.Status.RUNNING) {
            if (playgrounds.getChildren().size() >= 20) {
                playgrounds.getChildren().remove(0);
            }
            HBox hBox = new HBox();
            for (Field field : algorithm.getLane().getFields()) {
                Label label = new Label();
                label.setId(field.getColor());
                label.setPrefSize(playgrounds.getWidth() / algorithm.getLane().getFields().size(), playgrounds.getHeight() / 20);
                hBox.getChildren().add(label);
            }
            playgrounds.getChildren().add(hBox);
        }
    }


    @Override
    public void destroy() {
        // TODO: implement destroy
    }

    @Override
    public void reset() {
        playgrounds.getChildren().clear();
        this.setup(navigationController);
    }
}
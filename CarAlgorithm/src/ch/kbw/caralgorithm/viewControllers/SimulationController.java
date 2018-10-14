package ch.kbw.caralgorithm.viewControllers;

import ch.kbw.caralgorithm.models.Algorithm;
import ch.kbw.caralgorithm.models.Field;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SimulationController {

    private Algorithm algorithm;
    @FXML
    private VBox window;
    @FXML
    private Button starterButton;
    @FXML
    private HBox playground;
    @FXML
    private Slider tickSlider, fleaSlider, spawnSlider, amountOfFields;

    private Timeline loop;

    @FXML
    public void handleButtonStart(ActionEvent e) {
        algorithm = new Algorithm(1, (int)amountOfFields.getValue(), spawnSlider.getValue(), fleaSlider.getValue(), window.getWidth());
        this.loadPlayground();
        this.createLoop();
        loop.play();
    }

    public void createLoop() {
        loop = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            algorithm.tick();
            loadPlayground();
            System.out.println("Tick");
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
    }

    public void loadPlayground() {
        playground.getChildren().clear();
        for(Field f : algorithm.getLanes().get(0).getFields()) {
            playground.getChildren().add(f.getLabel());
        }
    }
}
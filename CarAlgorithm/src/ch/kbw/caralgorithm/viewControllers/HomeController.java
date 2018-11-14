package ch.kbw.caralgorithm.viewControllers;

import ch.kbw.caralgorithm.models.Algorithm;
import ch.kbw.caralgorithm.models.Car;
import ch.kbw.caralgorithm.models.Field;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 */
public class HomeController extends ViewController {

    private Algorithm algorithm;
    @FXML
    private VBox window, vBox;
    @FXML
    private Button starterButton;
    @FXML
    private HBox playground;
    @FXML
    private Slider tickSlider, fleaSlider, spawnSlider, amountOfFields;

    private Timeline updater;
    private NavigationController navigationController;

    /**
     * @param e
     */
    @FXML
    public void handleButtonStart(ActionEvent e) {
        if (algorithm == null) {
            algorithm = new Algorithm(1, (int) amountOfFields.getValue(), spawnSlider.getValue(), fleaSlider.getValue(), window.getWidth(), tickSlider.getValue());
            navigationController.setAlgorithm(algorithm);
        }
        algorithm.playLoop();
        this.loadPlayground();
        updater.play();
    }

    @FXML
    public void handleButtonReset(ActionEvent e) {
        algorithm = new Algorithm(1, (int) amountOfFields.getValue(), spawnSlider.getValue(), fleaSlider.getValue(), window.getWidth(), tickSlider.getValue());
        navigationController.setAlgorithm(algorithm);
        algorithm.playLoop();
        this.loadPlayground();
        updater.play();
    }

    /**
     *
     */
    public void loadPlayground() {
        playground.getChildren().clear();
        for (Field f : algorithm.getLanes().get(0).getFields()) {
            playground.getChildren().add(f.getLabel());
        }
        updater = new Timeline(new KeyFrame(Duration.millis(1000 / this.tickSlider.getValue()), event ->
        {
            try {
                for (Car c : algorithm.getLanes().get(0).getCarsInLane()) {
                    playground.getChildren().get(c.getPosition()).setId(c.getColor());
                }
            } catch (IndexOutOfBoundsException ex) {
                // TODO: Handle car left lane
            }
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void setup(NavigationController navigationController) {
        try {
            this.navigationController = navigationController;
            this.algorithm = navigationController.getAlgorithm();
            this.loadPlayground();
        } catch (NullPointerException ex) {
            // TODO: Handle first call
        }
    }
}
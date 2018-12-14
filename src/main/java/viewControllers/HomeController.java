package viewControllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.Algorithm;
import models.Car;
import models.Field;

/**
 *
 */
public class HomeController extends ViewController {

    private Algorithm algorithm;
    @FXML
    private VBox window;
    @FXML
    private Button starterButton;
    @FXML
    private HBox playground;
    @FXML
    private Slider tickSlider, fleaSlider, spawnSlider, amountOfFields;

    private Timeline updater;
    private NavigationController navigationController;

    /**
     *
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
        starterButton.setText("Pause");
        starterButton.setId("pauseButton");
        starterButton.setOnAction(this::handleButtonPause);
    }

    @FXML
    public void handleButtonPause(ActionEvent e) {
        algorithm.pauseLoop();
        updater.pause();
        starterButton.setText("Resume");
        starterButton.setId("startButton");
        starterButton.setOnAction(this::handleButtonStart);
    }

    /**
     * @param e
     */
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
        for (Field f : algorithm.getLane().getFields()) {
            playground.getChildren().add(f.getLabel());
        }
        updater = new Timeline(new KeyFrame(Duration.millis(1000 / this.tickSlider.getValue()), event ->
        {
            try {
                for (Car c : algorithm.getLane().getCarsInLane()) {
                    playground.getChildren().get(c.getPosition()).setId(c.getColor());
                }
            } catch (IndexOutOfBoundsException ex) {
                // TODO: Handle car left lane
            }
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * @param navigationController
     */
    @Override
    public void setup(NavigationController navigationController) {
        try {
            this.navigationController = navigationController;
            this.algorithm = navigationController.getAlgorithm();
            this.fleaSlider.setValue(algorithm.getLane().getGlobalFleaChance());
            this.spawnSlider.setValue(algorithm.getLane().getGlobalSpawnChance());
            this.tickSlider.setValue(algorithm.getTicksPerSecond());
            this.loadPlayground();

            if(algorithm.getStatus() == Animation.Status.RUNNING) {
                
            }
        } catch (NullPointerException ex) {
            // TODO: Handle first call
        }
    }

    @Override
    public void destroy() {
        if(updater != null) {
            updater.stop();
        }
    }
}
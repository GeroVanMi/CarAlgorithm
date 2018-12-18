package viewControllers;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Algorithm;
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

    private NavigationController navigationController;

    /**
     *
     */
    @FXML
    public void handleButtonStart(ActionEvent e) {
        if (algorithm == null) {
            algorithm = new Algorithm((int) amountOfFields.getValue(), spawnSlider.getValue(), fleaSlider.getValue(), window.getWidth(), tickSlider.getValue());
            navigationController.setAlgorithm(algorithm);
        }
        algorithm.playLoop();
        this.loadPlayground();
        starterButton.setText("Pause");
        starterButton.setId("pauseButton");
        starterButton.setOnAction(this::handleButtonPause);
    }

    @FXML
    public void handleButtonPause(ActionEvent e) {
        algorithm.pauseLoop();
        starterButton.setText("Resume");
        starterButton.setId("startButton");
        starterButton.setOnAction(this::handleButtonStart);
    }

    /**
     * @param e
     */
    @FXML
    public void handleButtonReset(ActionEvent e) {
        algorithm = new Algorithm((int) amountOfFields.getValue(), spawnSlider.getValue(), fleaSlider.getValue(), window.getWidth(), tickSlider.getValue());
        navigationController.setAlgorithm(algorithm);
        algorithm.playLoop();
        this.loadPlayground();
    }

    /**
     *
     */
    public void loadPlayground() {
        playground.getChildren().clear();
        for (Field f : algorithm.getLane().getFields()) {
            playground.getChildren().add(f.getLabel());
        }
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
        } catch (NullPointerException ex) {
            // TODO: Handle first call
        }
    }

    @Override
    public void destroy() {
    }
}
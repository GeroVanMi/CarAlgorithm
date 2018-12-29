package viewControllers;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        // TODO: Delete buttons
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
        // TODO: Delete buttons
    }


    public void createPlayground() {
        for (Field field : algorithm.getLane().getFields()) {
            Label label = new Label();
            label.setId(field.getColor());
            label.setMinWidth(window.getPrefWidth() / algorithm.getLane().getFields().size());
            label.setMinHeight(50);
            label.setPrefSize(window.getWidth() / algorithm.getLane().getFields().size(), 50);

            field.addLabel(label);
            playground.getChildren().add(label);
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
            this.amountOfFields.setValue(algorithm.getLane().getFields().size());
            this.tickSlider.setValue(algorithm.getTicksPerSecond());
            this.createPlayground();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        // TODO: How to implement destroy()
    }
}
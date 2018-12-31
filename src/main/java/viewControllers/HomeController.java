package viewControllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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
    private HBox playground;
    @FXML
    private Slider tickSlider, fleaSlider, spawnSlider, amountOfFields;
    //@FXML
    //private CheckBox borderCheckBox;
    @FXML
    private Label amountOfCars, avgSpeed, ticksPassed;

    private NavigationController navigationController;

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
     * Die Methode setup sorgt dafür, dass alle Werte vom Algorithmus auf die Slider gelegt werden und das Spielfeld
     * erstellt wird. Ausserdem werden die Statistiken festgelegt.
     * @param navigationController Der Navigationscontroller, der alle Daten bereithält.
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

            // Statistics
            this.algorithm = navigationController.getAlgorithm();
            Timeline updater = new Timeline(new KeyFrame(Duration.millis(1000 / algorithm.getTicksPerSecond()), e -> {
                amountOfCars.setText("" + algorithm.getLane().getCarsInLane().size() + " cars");
                avgSpeed.setText("" + Math.round(algorithm.getLane().calculateAvgSpeed() * 100) / 100 + " fields per tick");// TODO: Better representation
                ticksPassed.setText("" + algorithm.getTimeInTicks() + " ticks");
            }));
            updater.setCycleCount(Timeline.INDEFINITE);
            updater.play();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    /*@FXML
    public void setBorders() {
        for(Node node : playground.getChildren()) {
            node.getStyleClass().clear();
            if(borderCheckBox.isSelected()) {
                node.getStyleClass().add("border");
            } else {
                node.getStyleClass().add("borderless");
            }
        }
    }*/

    @Override
    public void destroy() {
        // TODO: implement destroy()
    }

    @FXML
    public void handleChangeTicks() {
        algorithm.setTicksPerSecond(tickSlider.getValue());
    }

    @FXML
    public void handleChangeFlea() {
        algorithm.getLane().setGlobalFleaChance(fleaSlider.getValue());
    }

    @FXML
    public void handleChangeSpawnChance() {
        algorithm.getLane().setGlobalSpawnChance(spawnSlider.getValue());
    }

    @FXML
    public void handleChangeFields() {
        algorithm.getLane().updateFields((int)amountOfFields.getValue(), playground.getPrefWidth());
        this.playground.getChildren().clear();
        this.setup(navigationController);
    }

    @Override
    public void reset() {
        this.playground.getChildren().clear();
        this.setup(navigationController);
    }
}
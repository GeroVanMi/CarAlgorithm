package viewControllers;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import models.Algorithm;

/**
 * Controllerclass for Statisticspage
 */
public class StatisticsController extends ViewController {

    private Algorithm algorithm;
    private Timeline updater;

    @FXML
    private Label amount, avgSpeed, spawnChance, fleaChance, ticks;

    @Override
    public void setup(NavigationController navigationController) {
        this.algorithm = navigationController.getAlgorithm();
        updater = new Timeline(new KeyFrame(Duration.millis(1000 / algorithm.getTicksPerSecond()), e -> {
            amount.setText("" + algorithm.getLane().getCarsInLane().size());
            spawnChance.setText("" + algorithm.getLane().getGlobalSpawnChance());
            spawnChance.setText("" + algorithm.getLane().getGlobalFleaChance());
            ticks.setText("" + algorithm.getTimeInTicks());
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
    }

    @Override
    public void destroy() {

    }
}
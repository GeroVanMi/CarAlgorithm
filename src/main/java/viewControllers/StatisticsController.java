package viewControllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Algorithm;

/**
 * Controllerclass for Statisticspage
 */
public class StatisticsController extends ViewController {

    private Algorithm algorithm;

    @FXML
    private Label amount, avgSpeed, spawnChance, fleaChance, ticks;

    @Override
    public void setup(NavigationController navigationController) {
        this.algorithm = navigationController.getAlgorithm();
    }

    @Override
    public void destory() {

    }
}
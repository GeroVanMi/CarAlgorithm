package ch.kbw.caralgorithm.viewControllers;

import ch.kbw.caralgorithm.models.Algorithm;
import ch.kbw.caralgorithm.models.Field;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 */
public class HomeController extends ViewController
{

    private Algorithm algorithm;
    @FXML
    private VBox window, vBox;
    @FXML
    private Button starterButton;
    @FXML
    private HBox playground;
    @FXML
    private Slider tickSlider, fleaSlider, spawnSlider, amountOfFields;
    @FXML
    private MenuBar menuBar;

    private Timeline updater;

    /**
     * @param e
     */
    @FXML
    public void handleButtonStart(ActionEvent e)
    {
        algorithm = new Algorithm(1, (int) amountOfFields.getValue(), spawnSlider.getValue(), fleaSlider.getValue(), window.getWidth(), 2);
        algorithm.playLoop();
        this.loadPlayground();
        updater.play();
    }

    /**
     *
     */
    public void loadPlayground()
    {
        updater = new Timeline(new KeyFrame(Duration.millis(1000 / this.tickSlider.getValue()), event ->
        {
            playground.getChildren().clear();
            for (Field f : algorithm.getLanes().get(0).getFields())
            {
                playground.getChildren().add(f.getLabel());
            }
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * hier kann man mit Hilfe der Menubars zwischen verschiedenen Versionen wechseln.
     */
    public void changeField()
    {
        menuBar = new MenuBar();
        vBox = new VBox(menuBar);
        ActionEvent e = new ActionEvent();
        Menu menu = new Menu();

        MenuItem start = new Menu();
        MenuItem history = new Menu();
        MenuItem statistic = new Menu();
        menu.getItems().addAll(start, history, statistic);
    }

    @Override
    public void setup( NavigationController navigationController) {
        this.algorithm = algorithm;
        this.loadPlayground();
    }
}
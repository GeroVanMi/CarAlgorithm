package ch.kbw.caralgorithm.viewControllers;

import ch.kbw.caralgorithm.models.Algorithm;
import ch.kbw.caralgorithm.models.Field;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 *
 */
public class SimulationController
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

    private Timeline loop;

    /**
     * @param e
     */
    @FXML
    public void handleButtonStart(ActionEvent e)
    {
        algorithm = new Algorithm(1, (int) amountOfFields.getValue(), spawnSlider.getValue(), fleaSlider.getValue(), window.getWidth());
        this.loadPlayground();
        this.createLoop();
        loop.play();
    }

    /**
     *
     */
    public void createLoop()
    {
        if (loop != null)
        {
            loop.stop();
        }
        loop = new Timeline(new KeyFrame(Duration.millis(1000 / tickSlider.getValue()), event ->
        {
            algorithm.tick();
            loadPlayground();
            changeField();
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     *
     */
    public void loadPlayground()
    {
        playground.getChildren().clear();
        for (Field f : algorithm.getLanes().get(0).getFields())
        {
            playground.getChildren().add(f.getLabel());
        }
    }

    /**
     * hier kann man  mit Hilfe des Menubars zwischen verschiedenen Versionen wechseln.
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

    @FXML
    public void handleMenuNormal(ActionEvent e)
    {
        System.out.println("start funktioniert");
        loadScreen("../views/view.fxml");
    }

    @FXML
    public void handleMenuHistory(ActionEvent e)
    {
        System.out.println("History funktioniert");
        loadScreen("../views/history.fxml");
    }

    @FXML
    public void handleMenuStatistiken(ActionEvent e)
    {
        System.out.println("Statistiken funktioniert");
        loadScreen("../views/statistics.fxml");
    }

    public void loadScreen(String screen) {
        Parent root;
        try {
            Stage stage = (Stage) window.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screen));
            root = loader.load();
            Scene scene = new Scene(root, 1300, 950);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
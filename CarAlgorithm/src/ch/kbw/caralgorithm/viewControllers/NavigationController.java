package ch.kbw.caralgorithm.viewControllers;

import ch.kbw.caralgorithm.models.Algorithm;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class NavigationController {

    private Algorithm algorithm;
    private Timeline loop;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Label title;

    public void initialize(NavigationController nav) {
        loadContent("../../resources/fxml/home.fxml");
        title.setText("Nagel-Schreckenberg-Modell Home");

    }

    @FXML
    public void handleButtonHistory(ActionEvent ae) {
         loadContent("../../resources/fxml/history.fxml");
         title.setText("Nagel-Schreckenberg-Modell History");
    }

    @FXML
    public void handleButtonHome(ActionEvent e) {
        loadContent("../../resources/fxml/home.fxml");
        title.setText("Nagel-Schreckenberg-Modell Home");
    }

    @FXML
    public void handleButtonStatistics(ActionEvent e) {
        loadContent("../../resources/fxml/statistics.fxml");
        title.setText("Nagel-Schreckenberg-Modell Statistics");
    }

    public void createTimeline(double tps)
    {
        if (loop != null) {
            loop.stop();
        }
        loop = new Timeline(new KeyFrame(Duration.millis(1000 / tps), event -> {
            algorithm.tick();
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
    }

    public void loadContent(String path) {
        try {
            contentPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane pane = loader.load();
            contentPane.getChildren().add(pane);
            ViewController viewController = loader.getController();
            viewController.initialize(this);
        } catch (Exception ex) {
            // TODO: NULL
            ex.printStackTrace();
        }
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Timeline getLoop() {
        return loop;
    }

    public void setLoop(Timeline loop) {
        this.loop = loop;
    }
}
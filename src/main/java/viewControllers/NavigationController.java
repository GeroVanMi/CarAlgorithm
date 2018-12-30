package viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.Algorithm;
import models.Lane;

/**
 * Kontrolliert die Navigation und sorgt dafür, dass der gewünschte Inhalt angezeigt wird.
 */
public class NavigationController {

    private Algorithm algorithm;
    private ViewController currentContentController;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Label title;

    public void setup() {
        algorithm = new Algorithm(100, 50, 50, contentPane.getWidth(), 2);
        algorithm.playLoop();
        loadContent("/views/home.fxml");
        title.setText("Nagel-Schreckenberg-Modell Home");
    }

    @FXML
    public void handleButtonHistory(ActionEvent ae) {
        loadContent("/views/notHistory.fxml");
        title.setText("Nagel-Schreckenberg-Modell History");
    }

    @FXML
    public void handleButtonHome(ActionEvent ae) {
        loadContent("/views/home.fxml");
        title.setText("Nagel-Schreckenberg-Modell Home");
    }

    @FXML
    public void handleButtonStatistics(ActionEvent ae) {
        loadContent("/views/statistics.fxml");
        title.setText("Nagel-Schreckenberg-Modell Statistics");
    }

    @FXML
    public void handleButtonStop(ActionEvent ae) {
        algorithm.stopLoop();
        Lane currentLane = algorithm.getLane();
        algorithm = new Algorithm(currentLane.getFields().size(),
                        currentLane.getGlobalSpawnChance(),
                        currentLane.getGlobalFleaChance(),
                        contentPane.getWidth(),
                        algorithm.getTicksPerSecond());
        algorithm.playLoop();
        currentContentController.reset();
    }

    @FXML
    public void handleButtonPlay(ActionEvent ae) {
        Button button = (Button) ae.getSource();
        button.setOnAction(this::handleButtonPause);
        button.getStyleClass().clear();
        button.getStyleClass().add("pauseButton");
        algorithm.playLoop();
    }

    @FXML
    public void handleButtonPause(ActionEvent ae) {
        Button button = (Button) ae.getSource();
        button.setOnAction(this::handleButtonPlay);
        button.getStyleClass().clear();
        button.getStyleClass().add("playButton");
        algorithm.pauseLoop();
    }

    @FXML
    public void handleButtonForward(ActionEvent ae) {
        algorithm.tick();
    }

    public void loadContent(String path) {
        try {
            if (currentContentController != null) {
                currentContentController.destroy();
            }
            contentPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane pane = loader.load();
            contentPane.getChildren().add(pane);
            ViewController viewController = loader.getController();
            if (viewController != null) {
                viewController.setup(this);
                currentContentController = viewController;
            }
        } catch (Exception ex) {
            // TODO: NULL
            ex.printStackTrace();
        }
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
package viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import models.Algorithm;

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
    }

    @FXML
    public void handleButtonPlay(ActionEvent ae) {
        Button button = (Button)ae.getSource();
        button.setOnAction(this::handleButtonPause);
        algorithm.playLoop();
    }

    public void handleButtonPause(ActionEvent ae) {
        Button button = (Button)ae.getSource();
        button.setOnAction(this::handleButtonPlay);
        algorithm.pauseLoop();
    }

    @FXML
    public void handleButtonForward(ActionEvent ae) {
        algorithm.tick();
    }

    public void loadContent(String path) {
        try {
            if(currentContentController != null) {
                currentContentController.destroy();
            }
            contentPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane pane = loader.load();
            contentPane.getChildren().add(pane);
            ViewController viewController = loader.getController();
            if(viewController != null) {
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
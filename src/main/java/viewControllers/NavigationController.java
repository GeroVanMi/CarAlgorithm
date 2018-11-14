package viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Algorithm;

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
         loadContent("/views/history.fxml");
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

    public void loadContent(String path) {
        try {
            contentPane.getChildren().clear();
            currentContentController.destory();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane pane = loader.load();
            contentPane.getChildren().add(pane);
            ViewController viewController = loader.getController();
            viewController.setup(this);
            currentContentController = viewController;
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
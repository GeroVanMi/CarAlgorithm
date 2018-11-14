package ch.kbw.caralgorithm.viewControllers;

import ch.kbw.caralgorithm.models.Algorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class NavigationController {

    private Algorithm algorithm;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Label title;

    public void setup() {
        loadContent("../../resources/fxml/home.fxml");
        title.setText("Nagel-Schreckenberg-Modell Home");
    }

    @FXML
    public void handleButtonHistory(ActionEvent ae) {
         loadContent("../../resources/fxml/history.fxml");
         title.setText("Nagel-Schreckenberg-Modell History");
    }

    @FXML
    public void handleButtonHome(ActionEvent ae) {
        loadContent("../../resources/fxml/home.fxml");
        title.setText("Nagel-Schreckenberg-Modell Home");
    }

    @FXML
    public void handleButtonStatistics(ActionEvent ae) {
        loadContent("../../resources/fxml/statistics.fxml");
        title.setText("Nagel-Schreckenberg-Modell Statistics");
    }

    public void loadContent(String path) {
        try {
            contentPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Pane pane = loader.load();
            contentPane.getChildren().add(pane);
            ViewController viewController = loader.getController();
            viewController.setup(this);
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
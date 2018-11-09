package ch.kbw.caralgorithm.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class NavigationController {

    @FXML
    private AnchorPane contentPane;
    @FXML
    private Label title;

    public void setUp() {
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

    public void loadContent(String path) {
        try {
            for(int i = 0; i < contentPane.getChildren().size(); i++) {
                contentPane.getChildren().remove(0);
            }
            Pane pane = FXMLLoader.load(getClass().getResource(path));
            contentPane.getChildren().add(pane);
        } catch (Exception ex) {
            // TODO: NULL
            ex.printStackTrace();
        }
    }
}

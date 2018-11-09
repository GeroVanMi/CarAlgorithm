package ch.kbw.caralgorithm.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class NavigationController {

    @FXML
    private AnchorPane contentPane;

    @FXML
    public void handleButtonHistory(ActionEvent ae) {
         try {
             Pane pane = FXMLLoader.load(getClass().getResource("../../resources/fxml/history.fxml"));
             contentPane.getChildren().add(pane);
             System.out.println("It worked?");
         } catch (Exception ex) {
            // TODO: NULL
             ex.printStackTrace();
         }
    }

    @FXML
    public void handleButtonHome(ActionEvent e) {

    }

    @FXML
    public void handleButtonStatistics(ActionEvent e) {

    }
}

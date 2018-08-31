package caralgorithm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label fieldOne;

    @FXML
    public void handleButtonStart(ActionEvent event) {
        fieldOne.setId("red");
    }
}

package ch.kbw.caralgorithm.viewControllers;

import ch.kbw.caralgorithm.models.Lanes;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class HistoryController {
    @FXML
    private Slider tickSlider, fleaSlider, spawnSlider, amountOfFields;
  //  private Thread thread;

    private Lanes lanes;

    public HistoryController(){
        HomeController hc = new HomeController();
        lanes = new Lanes((int) amountOfFields.getValue(), 5.0, 328.0);

    }
}

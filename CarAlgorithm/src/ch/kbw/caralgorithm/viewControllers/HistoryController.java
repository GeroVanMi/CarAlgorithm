package ch.kbw.caralgorithm.viewControllers;

import ch.kbw.caralgorithm.models.Algorithm;
import ch.kbw.caralgorithm.models.Field;
import ch.kbw.caralgorithm.models.Lane;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HistoryController
{
    @FXML
    private VBox playground;
    /*@FXML
    private Slider tickSlider, fleaSlider, spawnSlider, amountOfFields;*/
    @FXML
    private VBox window, vBox;
    private Timeline loop;
    private SimulationController sc;

    public void handleButtonStart(ActionEvent e)
    {
        sc = new SimulationController();
        Algorithm algorithm = new Algorithm(20, sc.get /*(int) amountOfFields.getValue()*/, spawnSlider.getValue(), fleaSlider.getValue(), window.getWidth(), 25);


       /* for(int i = 0; i<20; i++){
            Lane lane = new Lane(20, playground.getWidth(),7.0);
        }*/
        sc.loadPlayground();
        sc.createLoop();
        loop.play();
    }
}

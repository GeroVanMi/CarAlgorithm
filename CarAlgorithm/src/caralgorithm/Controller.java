package caralgorithm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    @FXML
    private HBox playground;

    @FXML
    private Slider tickSlider, carSlider, fleaSlider;

    @FXML
    private VBox window;

    @FXML
    public void handleButtonStart(ActionEvent event) {
        int length = 40;
        Slot[] slots = new Slot[length];
        int lastPlayground = playground.getChildren().size();
        for(int i = 0; i < lastPlayground; i++) {
            playground.getChildren().remove(0);
        }

        for(int i = 0; i < length; i++) {
            Slot slot = new Slot(false, window.getWidth(), length, i);
            slots[i] = slot;
            playground.getChildren().add(slot.getLabel());
        }

        ArrayList<Car> carsInMap = new ArrayList<>();
        ArrayList<Car> carsOutOfMap = new ArrayList<>();

        Timeline gameloop = new Timeline(new KeyFrame(

                Duration.millis(1000 / tickSlider.getValue()),
                ae -> {
                    int leftCars = carsOutOfMap.size();
                    for (int i = 0; i < leftCars; i++) {
                        carsInMap.remove(carsOutOfMap.get(0));
                        carsOutOfMap.remove(0);
                    }

                    Random random = new Random();
                    if(!slots[0].hasCar()) {
                        if(random.nextInt(100) < carSlider.getValue()) {
                            Car c = new Car(3, 3, fleaSlider.getValue(), 0);
                            carsInMap.add(c);
                            slots[0].setHasCar(true);
                        }
                    }

                    for (int i = 0; i < slots.length; i++) {
                        if(slots[i].hasCar()) {

                            for(Car c:carsInMap) {
                                if(c.getPos() == i) {
                                    String id = "";
                                    switch (c.getVelocity()) {
                                        case 0: id = "red";
                                        break;
                                        case 1: id = "orange";
                                        break;
                                        case 2: id = "yellow";
                                        break;
                                        case 3: id = "green";
                                        break;
                                    }
                                    slots[i].getLabel().setId(id);
                                }
                            }


                        }
                        else if(!slots[i].hasCar()) {
                            slots[i].getLabel().setId("white");
                        }
                    }

                    for(Car c:carsInMap) {
                        if(c.getVelocity() < c.getMaxVelocity()) {
                            c.incVelocity();
                        }
                        if(random.nextInt(100) < c.getFlea()) {
                            c.decVelocity();
                        }

                        try {
                            for (int i = 0; i < c.getVelocity(); i++) {
                                if (slots[c.getPos() + i + 1].hasCar()) {
                                    c.setVelocity(i);
                                    break;
                                }
                            }

                        } catch (ArrayIndexOutOfBoundsException e) {
                            // TODO: Implement visual representation for leaving car?
                            // System.out.println("Car is about to leave.");
                        }
                    }

                    for(Car c:carsInMap) {
                        try {
                            slots[c.getPos()].setHasCar(false);
                            c.setPos(c.getPos() + c.getVelocity());
                            slots[c.getPos()].setHasCar(true);
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            carsOutOfMap.add(c);
                        }
                    }
                }));
        gameloop.setCycleCount(Timeline.INDEFINITE);
        gameloop.play();
    }
}

package caralgorithm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class Controller {

    @FXML
    private HBox playground;

    @FXML
    public void handleButtonStart(ActionEvent event) {
        int length = 20;
        Slot[] slots = new Slot[length];
        int lastPlayground = playground.getChildren().size();
        for(int i = 0; i < lastPlayground; i++) {
            playground.getChildren().remove(0);
        }

        for(int i = 0; i < length; i++) {
            Slot slot = new Slot(false, length, i);
            slots[i] = slot;
            playground.getChildren().add(slot.getLabel());
        }

        ArrayList<Car> carsInMap = new ArrayList<>();
        ArrayList<Car> carsOutOfMap = new ArrayList<>();
        Car car = new Car(3, 3, 0, 0);
        Car car1 = new Car(3, 3, 0, 2);
        carsInMap.add(car);
        carsInMap.add(car1);
        slots[0].setHasCar(true);

        Timeline gameloop = new Timeline(new KeyFrame(

                Duration.millis(1000),
                ae -> {
                    carsInMap.removeAll(carsOutOfMap);
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
                        try {
                            for (int i = 0; i < c.getVelocity(); i++) {
                                if (slots[c.getPos() + i + 1].hasCar()) {
                                    c.setVelocity(i);
                                    break;
                                }
                            }
                            slots[c.getPos()].setHasCar(false);
                            c.setPos(c.getPos() + c.getVelocity());
                            slots[c.getPos()].setHasCar(true);
                        } catch (ArrayIndexOutOfBoundsException aioobe) {
                            carsOutOfMap.add(c);
                            slots[c.getPos()].setHasCar(false);
                        }
                    }
                }));
        gameloop.setCycleCount(Timeline.INDEFINITE);
        gameloop.play();
    }

    public void render(Slot[] slots) {

    }
}

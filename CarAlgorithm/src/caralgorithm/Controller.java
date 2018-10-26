package caralgorithm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Controller
{

    @FXML
    private HBox playground, playground2, playground3;

    @FXML
    private Slider tickSlider, carSlider, fleaSlider, fieldsAmount;

    @FXML
    private VBox window;

    @FXML
    private Button starterButton;

    private Timeline gameLoop;

    @FXML
    public void handleButtonStart(ActionEvent event)
    {
        starterButton.setOnAction(this::handleButtonStop);
        starterButton.setText("Stop");
        HBox[] playgrounds = {playground, playground2, playground3};

        int length = (int) fieldsAmount.getValue();
        int height = 3;
        Slot[] slots = new Slot[length];
        int lastPlayground = playground.getChildren().size();
        for (int j = 0; j < height; j++)
        {


            /*for (int i = 0; i < lastPlayground; i++)
            {
                playground.getChildren().remove(0);
                playground2.getChildren().remove(0);
                playground3.getChildren().remove(0);


            }*/

        }
        for (int j = 0; j < height; j++)
        {
            for (int i = 0; i < length; i++)
            {
                Slot slot = new Slot(false, window.getWidth(), length, i);
                slots[i] = slot;
                playgrounds[j].getChildren().add(slot.getLabel());
            }
        }

        ArrayList<Car> carsInMap = new ArrayList<>();
        ArrayList<Car> carsOutOfMap = new ArrayList<>();

        gameLoop = new Timeline(new KeyFrame(

                Duration.millis(1000 / tickSlider.getValue()),
                ae ->
                {
                    int leftCars = carsOutOfMap.size();
                    for (int i = 0; i < leftCars; i++)
                    {
                        carsInMap.remove(carsOutOfMap.get(0));
                        carsOutOfMap.remove(0);
                    }

                    Random random = new Random();
                    if (!slots[0].hasCar())
                    {
                        if (random.nextInt(100) < carSlider.getValue())
                        {
                            Car c = new Car(5, 6, fleaSlider.getValue(), 0);
                            carsInMap.add(c);
                            slots[0].setHasCar(true);
                        }
                    }

                    for (int j = 0; j < height; j++)
                    {
                        for (int i = 0; i < slots.length; i++)
                        {

                            if (slots[i].hasCar())
                            {

                                for (Car c : carsInMap)
                                {
                                    if (c.getPos() == i)
                                    {
                                        String id = "";
                                        switch (c.getVelocity())
                                        {
                                            case 0:
                                                id = "red";
                                                break;
                                            case 1:
                                                id = "darkOrange";
                                                break;
                                            case 2:
                                                id = "orange";
                                                break;
                                            case 3:
                                                id = "darkYellow";
                                                break;
                                            case 4:
                                                id = "yellow";
                                                break;
                                            case 5:
                                                id = "green";
                                                break;
                                            case 6:
                                                id = "darkGreen";
                                                break;

                                        }
                                        slots[i].getLabel().setId(id);
                                    }
                                }


                            } else if (!slots[i].hasCar())
                            {
                                slots[i].getLabel().setId("white");
                            }
                        }
                    }

                    for (Car c : carsInMap)
                    {
                        if (c.getVelocity() < c.getMaxVelocity())
                        {
                            c.incVelocity();
                        }
                        if (random.nextInt(100) < c.getFlea())
                        {
                            c.decVelocity();
                        }

                        try
                        {
                            for (int k = 0; k < height; k++)
                            {
                                for (int i = 0; i < c.getVelocity(); i++)
                                {

                                    if (slots[c.getPos() + i + 1].hasCar())
                                    {
                                        c.setVelocity(i);
                                        break;
                                    }
                                }

                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {

                        }
                    }

                    for (Car c : carsInMap)
                    {
                        try
                        {
                            slots[c.getPos()].setHasCar(false);
                            c.setPos(c.getPos() + c.getVelocity());
                            slots[c.getPos()].setHasCar(true);
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            carsOutOfMap.add(c);
                        }
                    }


                }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    @FXML
    public void handleButtonStop(ActionEvent e)
    {
        try
        {
            gameLoop.pause();
            starterButton.setOnAction(this::handleButtonRestart);
            starterButton.setText("Restart");
        } catch (NullPointerException npe)
        {

        }
    }

    @FXML
    public void handleButtonRestart(ActionEvent e)
    {
        try
        {
            gameLoop.stop();
            gameLoop.play();
            starterButton.setOnAction(this::handleButtonStop);
            starterButton.setText("Stop");
        } catch (NullPointerException npe)
        {

        }
    }

    @FXML
    public void changeTicksPS(MouseEvent me)
    {
        try
        {
            gameLoop.setDelay(Duration.millis(1000 / tickSlider.getValue()));
            gameLoop.stop();
            gameLoop.play();
        } catch (NullPointerException npe)
        {

        }
    }
}

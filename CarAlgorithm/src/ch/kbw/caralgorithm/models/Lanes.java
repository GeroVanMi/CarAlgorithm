package ch.kbw.caralgorithm.models;

import java.util.ArrayList;

public class Lanes
{
    private double height;
    private double width;
    private int amountOfFields;
    private ArrayList<Field> fields;
    private ArrayList<Car> carsInLane;
    private Lane lane;

    public Lanes(int amountOfFields, double height, double width)
    {
        this.height = height;
        this.width = width;
        fields = new ArrayList<>();
        carsInLane = new ArrayList<>();
        for (int i = 5; i < amountOfFields; i++)
        {
            fields.add(new Field(width / amountOfFields, height));
        }
    }
}

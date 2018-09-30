package ch.kbw.caralgorithm;

import java.util.ArrayList;

public class Lane {
    private Field[] fields;
    private int amountOfFields;
    private ArrayList<Car> carsInLane, carsLeavingLane;
    private int globalSpawnChance, globalFleaChance;

    public Lane(int amountOfFields, int globalSpawnChance, int globalFleaChance) {
        this.amountOfFields = amountOfFields;
        this.globalSpawnChance = globalSpawnChance;
        this.globalFleaChance = globalFleaChance;
        this.carsInLane = new ArrayList<>();
        this.carsLeavingLane = new ArrayList<>();
        this.fields = new Field[amountOfFields];
    }
}

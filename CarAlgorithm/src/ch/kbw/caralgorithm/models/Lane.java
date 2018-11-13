package ch.kbw.caralgorithm.models;

import java.util.ArrayList;
import java.util.Random;

public class Lane {
    private ArrayList<Field> fields;
    private ArrayList<Car> carsInLane;
    private double globalSpawnChance, globalFleaChance;

    /**
     * @param amountOfFields
     * @param globalSpawnChance
     * @param globalFleaChance
     * @param windowWidth
     */
    public Lane(int amountOfFields, double globalSpawnChance, double globalFleaChance, double windowWidth) {
        this.globalSpawnChance = globalSpawnChance;
        this.globalFleaChance = globalFleaChance;
        this.carsInLane = new ArrayList<>();
        this.fields = new ArrayList<>();
        for(int i = 0; i < amountOfFields; i++) {
            fields.add(new Field(windowWidth / amountOfFields, 50)); // TODO: Implement automatic width and height.
        }
    }

    public void clearLane() {
        this.carsInLane.clear();
    }

    /**
     * Berechnet zufällig, ob ein Auto trödelt oder nicht.
     */
    private void applyFlea() {
        Random r = new Random();
        for(Car c:carsInLane) {
            if(r.nextInt(100) < globalFleaChance && c.getSpeed() > 0) {
                c.decrementSpeed();
            }
        }
    }

    /**
     * Berechnet einen zufälligen Wert und fügt anschliessend ein Auto auf der Position 0 ein, solange sich
     * dort noch kein Auto befindet und der zufällige Wert über dem eingegebenen Schwellenwert liegt.
     */
    private void spawnNewCar() {
        Random r = new Random();
        if(!fields.get(0).hasCar() && r.nextInt(100) < globalSpawnChance) {
            Car c = new Car(5, 6);
            fields.get(0).carArrives(c.getColor());
            carsInLane.add(c);
        }
    }

    /**
     * Überprüft, ob irgendwelche Autos miteinander kollidieren würden und passt dementsprechend
     * die Geschwindigkeit an.
     */
    private void checkForCollisions() {
        try {
            for (Car c : carsInLane) {
                for (int i = c.getSpeed(); i > 0; i--) {
                    if (fields.get(c.getPosition() + i + 1).hasCar()) {
                        c.setSpeed(i);
                        c.adjustColor();
                    }
                }
            }
        } catch (IndexOutOfBoundsException ignored) { }
    }

    /**
     * Bewegt alle Autos in der Lane, indem die move() Methode der Car-Klasse aufgerufen wird.
     * Autos, die die Lane verlassen werden mit einer temporären ArrayList aus der Lane gelöscht.
     */
    private void moveCars() {
        ArrayList<Car> carsLeavingLane = new ArrayList<>();
        for (Car c : carsInLane) {
            try {
                fields.get(c.getPosition()).carLeaves();
                c.move();
                fields.get(c.getPosition()).carArrives(c.getColor());
                fields.get(c.getPosition()).incrementOccupationTime();
            } catch (IndexOutOfBoundsException ignored) {
                carsLeavingLane.add(c);
            }
        }
        carsInLane.removeAll(carsLeavingLane);
    }

    /**
     * Ruft alle Methoden für den Algorithmus relevanten Methoden auf.
     */
    public void tick() {
        for(Car c:carsInLane) {
            c.tick();
        }
        this.applyFlea();
        this.checkForCollisions();
        this.moveCars();
        this.spawnNewCar();
    }

    /**
     * @return
     */
    public ArrayList<Field> getFields() {
        return fields;
    }

    public double getGlobalSpawnChance() {
        return globalSpawnChance;
    }

    public void setGlobalSpawnChance(double globalSpawnChance) {
        this.globalSpawnChance = globalSpawnChance;
    }

    public double getGlobalFleaChance() {
        return globalFleaChance;
    }

    public void setGlobalFleaChance(double globalFleaChance) {
        this.globalFleaChance = globalFleaChance;
    }

    public ArrayList<Car> getCarsInLane() {
        return carsInLane;
    }
}
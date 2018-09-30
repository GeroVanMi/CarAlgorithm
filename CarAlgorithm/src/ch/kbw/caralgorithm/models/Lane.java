package ch.kbw.caralgorithm.models;

import java.util.ArrayList;
import java.util.Random;

public class Lane {
    private Field[] fields;
    private ArrayList<Car> carsInLane;
    private int globalSpawnChance, globalFleaChance;

    public Lane(int amountOfFields, int globalSpawnChance, int globalFleaChance) {
        this.globalSpawnChance = globalSpawnChance;
        this.globalFleaChance = globalFleaChance;
        this.carsInLane = new ArrayList<>();
        this.fields = new Field[amountOfFields];
        for(int i = 0; i < fields.length; i++) {
            fields[i] = new Field(25, 25); // TODO: Implement automatic width and height.
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
        if(!fields[0].hasCar() && r.nextInt(100) < globalSpawnChance) {
            fields[0].carArrives();
            carsInLane.add(new Car(5, 6));
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
                    if (fields[c.getPosition() + i + 1].hasCar()) {
                        c.setSpeed(i);
                        c.adjustColor();
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    /**
     * Bewegt alle Autos in der Lane, indem die move() Methode der Car-Klasse aufgerufen wird.
     * Autos, die die Lane verlassen werden mit einer temporären ArrayList aus der Lane gelöscht.
     */
    private void moveCars() {
        ArrayList<Car> carsLeavingLane = new ArrayList<>();
        for (Car c : carsInLane) {
            try {
                fields[c.getPosition()].carLeaves();
                c.move();
                fields[c.getPosition()].carArrives();
                fields[c.getPosition()].incrementOccupationTime();
            } catch (ArrayIndexOutOfBoundsException ex) {
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
}
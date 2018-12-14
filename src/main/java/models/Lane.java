package models;

import java.util.ArrayList;
import java.util.Random;

public class Lane {
    private ArrayList<Field> currentState;
    private ArrayList<State> previousStates;
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
        this.currentState = new ArrayList<>();
        this.carsInLane = new ArrayList<>();
        this.previousStates = new ArrayList<>();
        for(int i = 0; i < amountOfFields; i++) {
            currentState.add(new Field(windowWidth / amountOfFields, 50));
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
        if(!currentState.get(0).hasCar() && r.nextInt(100) < globalSpawnChance) {
            Car c = new Car(5, 6);
            currentState.get(0).carArrives(c.getColor());
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
                    if (currentState.get(c.getPosition() + i + 1).hasCar()) {
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
                currentState.get(c.getPosition()).carLeaves();
                c.move();
                currentState.get(c.getPosition()).carArrives(c.getColor());
                currentState.get(c.getPosition()).incrementOccupationTime();
            } catch (IndexOutOfBoundsException ignored) {
                carsLeavingLane.add(c);
            }
        }
        carsInLane.removeAll(carsLeavingLane);
    }

    /**
     * Berechnet die aktuelle Durchschnittsgeschwindigkeit.
     */
    public float calculateAvgSpeed() {
        float avgSpeed = 0;
        for(Car c:carsInLane) {
            avgSpeed += c.getSpeed();
        }
        return avgSpeed / carsInLane.size();
    }

    /**
     * Ruft alle Methoden für den Algorithmus relevanten Methoden auf.
     */
    public void tick() {
        // Speichert den aktuellen Stand ab
        this.previousStates.add(new State(this.getCurrentState()));
        // Tatsächliches Tick-Handeling
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
    public ArrayList<Field> getCurrentState() {
        return currentState;
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
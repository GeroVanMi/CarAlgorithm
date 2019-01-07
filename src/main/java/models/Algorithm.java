package models;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Eine Modelklasse, die alle wichtigen Daten an einem Punkt zusammenbringt.
 */
public class Algorithm {
    private Lane lane;
    private int totalTimeInTicks;
    private double ticksPerSecond;
    private Timeline loop;

    /**
     * @param amountOfFields Die Anzahl Felder, die existieren.
     * @param spawnChance Die Prozentchance mit der ein neues Auto auf dem 0. Feld erstellt wird.
     * @param fleaChance Die Prozentchance mit der ein Auto trödelt.
     * @param windowWidth Länge des Fensters, in dem die Lane dargestellt wird. TODO: Aus dem Model entfernen.
     */
    public Algorithm(int amountOfFields, double spawnChance, double fleaChance, double windowWidth, double ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
        this.totalTimeInTicks = 0;
        this.lane = new Lane(amountOfFields, spawnChance, fleaChance, windowWidth);
        this.createLoop();
    }

    public void resetTotalTime() {
        this.totalTimeInTicks = 0;
    }

    /**
     * Erstellt die Timeline, die dafür sorgt, dass der Algorithmus regelmässig ausgeführt wird, solange diese noch
     * nicht existiert.
     */
    public void createLoop() {
        if(loop != null)
        {
            loop.stop();
        }
        loop = new Timeline(new KeyFrame(Duration.millis(1000 / ticksPerSecond), event ->
        {
            this.tick();
            totalTimeInTicks++;
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
    }

    public void stopLoop() {
        loop.stop();
    }

    public void pauseLoop() {
        loop.pause();
    }

    public void playLoop() {
        loop.play();
    }

    /**
     *  Ruft einmal die Tickmethode der Lane auf.
     */
    public void tick() {
        lane.tick();
        totalTimeInTicks++;
    }

    public void setTicksPerSecond(double ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
        loop.stop();
        loop.getKeyFrames().remove(0);
        loop.getKeyFrames().add(new KeyFrame(Duration.millis(1000 / ticksPerSecond), e-> {
            this.tick();
            totalTimeInTicks++;
        }));
        loop.play();
    }

    public Lane getLane() {
        return lane;
    }

    public double getTicksPerSecond() {
        return ticksPerSecond;
    }

    public int getTimeInTicks() {
        return totalTimeInTicks;
    }

    public Timeline getLoop() {
        return loop;
    }
}

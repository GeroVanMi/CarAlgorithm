package models;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 */
public class Algorithm {
    private Lane lane;
    private int totalTimeInTicks;
    private double ticksPerSecond;
    private Timeline loop;

    /**
     * @param amountOfFields
     * @param spawnChance
     * @param fleaChance
     * @param windowWidth
     */
    public Algorithm(int amountOfFields, double spawnChance, double fleaChance, double windowWidth, double ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
        this.totalTimeInTicks = 0;
        this.lane = new Lane(amountOfFields, spawnChance, fleaChance, windowWidth);
        this.createLoop();
    }

    /**
     *
     */
    public void resetTotalTime() {
        this.totalTimeInTicks = 0;
    }

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
     *
     */
    public void tick() {
        lane.tick();
        totalTimeInTicks++;
    }

    /**
     * @return
     */
    public Lane getLane() {
        return lane;
    }

    public double getTicksPerSecond() {
        return ticksPerSecond;
    }

    public void setTicksPerSecond(double ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
    }

    public int getTimeInTicks() {
        return totalTimeInTicks;
    }

    public Timeline getLoop() {
        return loop;
    }
}

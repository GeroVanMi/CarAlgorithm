package ch.kbw.caralgorithm.models;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 *
 */
public class Algorithm {
    private ArrayList<Lane> lanes;
    private int totalTimeInTicks;
    private Timeline loop;

    /**
     * @param amountOfLanes
     * @param amountOfFields
     * @param spawnChance
     * @param fleaChance
     * @param windowWidth
     */
    public Algorithm(int amountOfLanes ,int amountOfFields, double spawnChance, double fleaChance, double windowWidth, double tps) {
        this.lanes = new ArrayList<>();
        this.totalTimeInTicks = 0;
        for(int i = 0; i < amountOfLanes; i++) {
            this.lanes.add(new Lane(amountOfFields, spawnChance, fleaChance, windowWidth));
        }
        this.createLoop(tps);
    }

    /**
     *
     */
    public void resetTotalTime() {
        this.totalTimeInTicks = 0;
    }

    public void createLoop(double ticksPerSecond) {
        if(loop != null)
        {
            loop.stop();
        }
        loop = new Timeline(new KeyFrame(Duration.millis(1000 / ticksPerSecond), event ->
        {
            this.tick();
            System.out.println("Ticking...");
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
        for(Lane l:lanes) {
            l.tick();
            totalTimeInTicks++;
        }
    }

    /**
     * @return
     */
    public ArrayList<Lane> getLanes() {
        return lanes;
    }
}

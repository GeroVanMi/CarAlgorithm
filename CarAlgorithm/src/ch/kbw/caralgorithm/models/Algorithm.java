package ch.kbw.caralgorithm.models;

import java.util.ArrayList;

/**
 *
 */
public class Algorithm {
    private ArrayList<Lane> lanes;
    private int totalTimeInTicks;

    /**
     * @param amountOfLanes
     * @param amountOfFields
     * @param spawnChance
     * @param fleaChance
     * @param windowWidth
     */
    public Algorithm(int amountOfLanes ,int amountOfFields, double spawnChance, double fleaChance, double windowWidth, double height) {
        this.lanes = new ArrayList<>();
        this.totalTimeInTicks = 0;
        for(int i = 0; i < amountOfLanes; i++) {
            this.lanes.add(new Lane(amountOfFields, spawnChance, fleaChance, windowWidth, 50));
        }
    }

    /**
     *
     */
    public void resetTotalTime() {
        this.totalTimeInTicks = 0;
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
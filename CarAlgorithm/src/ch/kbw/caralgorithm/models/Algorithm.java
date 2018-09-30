package ch.kbw.caralgorithm.models;

import java.util.ArrayList;

public class Algorithm {
    private ArrayList<Lane> lanes;
    private int totalTimeInTicks;

    public Algorithm(int amountOfLanes) {
        this.lanes = new ArrayList<>();
        this.totalTimeInTicks = 0;
    }

    public void resetTotalTime() {
        this.totalTimeInTicks = 0;
    }

    public void createLane(int amountOfFields, int spawnChance, int fleaChance) {
        this.lanes.add(new Lane(amountOfFields, spawnChance, fleaChance));
    }

    public void tick() {
        for(Lane l:lanes) {
            l.tick();
            totalTimeInTicks++;
        }
    }
}

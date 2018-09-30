package ch.kbw.caralgorithm;

public class Algorithm {
    private Lane[] lanes;
    private int totalTimeInTicks;

    public Algorithm(int amountOfLanes) {
        this.lanes = new Lane[amountOfLanes];
        this.totalTimeInTicks = 0;
    }

    public void resetTotalTime() {
        this.totalTimeInTicks = 0;
    }

    public void tick() {

    }
}

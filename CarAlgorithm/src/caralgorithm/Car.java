package caralgorithm;

public class Car {
    private int velocity, maxVelocity, pos;
    private double flea;

    public Car(int velocity, int maxVelocity, double flea, int pos) {
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        this.flea = flea;
        this.pos = pos;
    }

    public int getVelocity() {
        return velocity;
    }

    public double getFlea() {
        return flea;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public int getPos() {
        return pos;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setFlea(double flea) {
        this.flea = flea;
    }

    public void incVelocity() {
        this.velocity++;
    }

    public void decVelocity() {
        this.velocity--;
    }
}

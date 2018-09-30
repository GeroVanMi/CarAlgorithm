package ch.kbw.caralgorithm;

public class Field {
    private boolean hasCar;
    //private Label label; // missing import
    private int occupationTime;

    public Field() {
        this.hasCar = false;
        //label = new Label();
        // TODO: Set size and height of label.
        occupationTime = 0;
    }

    /**
     * Die Methode setzt die Variable "hasCar" auf true.
     */
    public void carArrives() {
        this.hasCar = true;
    }

    /**
     * Die Methode setzt die Variable "hasCar" auf false.
     */
    public void carLeaves() {
        this.hasCar = false;
    }

    public void incrementOccupationTime() {
        this.occupationTime++;
    }

    public void resetOccupationTime() {
        this.occupationTime = 0;
    }

    /*public void setLabelColor() {
        TODO: Set the label color.
    }
    */
}

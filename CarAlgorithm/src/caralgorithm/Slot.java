package caralgorithm;

import javafx.scene.control.Label;

public class Slot {
    private Label label;
    private boolean hasCar;

    public Slot(boolean hasCar, double windowSize, int length, int pos) {
        label = new Label();
        label.setPrefSize(windowSize / length, 80);
        label.setLayoutX(pos * label.getWidth());
        label.setId("white");
        this.hasCar = hasCar;
    }

    public Label getLabel() {
        return label;
    }

    public boolean hasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }
}

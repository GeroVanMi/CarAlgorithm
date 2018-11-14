package models;

import javafx.scene.control.Label;

/**
 * Die Modelklasse Field haelt alle Informationen eines einzelnen Feldes innerhalb einer Bahn.
 */
public class Field {

    /**
     * Ein Wahr/Falsch Wert, der festhaelt, ob sich ein Auto auf dem Feld befindet.
     */
    private boolean hasCar;
    /**
     * Das Label, das effektiv auf dem UI angezeigt wird.
     */
    private Label label;
    /**
     * Haelt fest, fuer wieviele Ticks / Runden sich ein Auto auf dem Feld befunden hat.
     */
    private int occupationTime;

    /**
     * Konstrukter fuer die Klasse Field.
     * Erstellt ein Feld mitsamt Label, auf dem kein Auto ist und setzt die Zeit, in der das Feld bisher
     * belegt war, auf 0.
     * @param width Laenge des Feldes
     * @param height Hoehe des Feldes
     */
    public Field(double width, double height) {
        this.hasCar = false;
        label = new Label();
        label.setPrefSize(width, height);
        label.setId("white");
        occupationTime = 0;
    }

    /**
     * Die Methode setzt die Variable "hasCar" auf true.
     */
    public void carArrives(String color) {
        this.hasCar = true;
        label.setId(color);
    }

    /**
     * Die Methode setzt die Variable "hasCar" auf false.
     */
    public void carLeaves() {
        this.hasCar = false;
        label.setId("white");
    }

    /**
     * Erhoeht die Zeit, in der das Feld belegt ist, um 1.
     */
    public void incrementOccupationTime() {
        this.occupationTime++;
    }

    /**
     * Setzt die Zeit, in der das Feld belegt war, wieder auf 0.
     */
    public void resetOccupationTime() {
        this.occupationTime = 0;
    }

    /**
     * @return Gibt zurueck, ob sich ein Auto auf dem Feld befindet.
     */
    public boolean hasCar() {
        return hasCar;
    }

    /**
     * @return
     */
    public Label getLabel() {
        return label;
    }
}

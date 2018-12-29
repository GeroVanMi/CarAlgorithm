package models;

import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Die Modelklasse Field haelt alle Informationen eines einzelnen Feldes innerhalb einer Bahn.
 */
public class Field {

    /**
     * Ein Wahr/Falsch Wert, der festhaelt, ob sich ein Auto auf dem Feld befindet.
     */
    private boolean hasCar;

    /**
     * Haelt fest, fuer wieviele Ticks / Runden sich ein Auto auf dem Feld befunden hat.
     */
    private int occupationTime;

    /**
     * Die Farbe, die das Feld aktuell hat. Sie basiert auf der Geschwindigkeit des Autos, das auf dem Feld ist oder ist weiss.
     */
    private String color;

    /**
     * Eine Liste, die alle Labels hält, die das Feld darstellen.
     */
    private ArrayList<Label> labels;

    /**
     * Konstrukter fuer die Klasse Field.
     * Erstellt ein Feld mitsamt Label, auf dem kein Auto ist und setzt die Zeit, in der das Feld bisher
     * belegt war, auf 0.
     * @param width Laenge des Feldes
     * @param height Hoehe des Feldes
     */
    public Field(double width, double height) {
        this.labels = new ArrayList<>();
        this.hasCar = false;
        this.occupationTime = 0;
        this.color = "white";
    }

    /**
     * Die Methode setzt die Variable "hasCar" auf true.
     */
    public void carArrives(String color) {
        this.hasCar = true;
        this.color = color;
        this.updateLabels(color);
    }

    /**
     * Die Methode setzt die Variable "hasCar" auf false.
     */
    public void carLeaves() {
        this.hasCar = false;
        this.color = "white";
        this.updateLabels("white");
    }

    /**
     * Sendet die neue Farbe an alle Labels, die das Feld darstellen.
     * @param color
     */
    public void updateLabels(String color) {
        for(Label label : labels) {
            label.setId(color);
        }
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
     * @return Gibt die Farbe des Feldes zurück.
     */
    public String getColor() {
        return color;
    }

    public void tick() {
        if(hasCar) {
            this.occupationTime++;
        }
    }

    public void addLabel(Label label) {
        labels.add(label);
    }
}

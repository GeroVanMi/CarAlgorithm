package ch.kbw.caralgorithm;

/**
 * Car ist eine Modelklasse, die alle Informationen zu einem einzelnen Auto hält.
 * Es legt die eigene Farbe anhand der Geschwindigkeit bei jedem Tick neu fest.
 */
public class Car {
    private int speed, maxSpeed, position;
    private String color;

    /**
     * Der Konstruktor initialisiert ein neues Fahrzeug bei den gegebenen Werten.
     * @param speed Legt die Anzahl Felder fest, die ein Auto pro Tick fährt.
     * @param maxSpeed Legt die maximale Geschwindigkeit eines Autos fest.
     * @param position Die aktuelle Position eines Autos in einer Lane.
     * @param color Die Farbe eines Autos, basierend auf seiner Geschwindigkeit.
     */
    public Car(int speed, int maxSpeed, int position, String color) {
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.position = position;
        this.color = color;
    }

    /**
     * Erhöht die Geschwindigkeit um 1.
     */
    public void incrementSpeed() {
        this.speed++;
    }

    /**
     * Verringert die Geschwindigkeit um 1.
     */
    public void decrementSpeed() {
        this.speed--;
    }

    /**
     * Setzt die Geschwindigkeit auf den gegebenen Parameter speed.
     * @param speed Die gewünschte neue Geschwindigkeit.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Legt die Farbe anhand der Geschwindigkeit des Fahrzeuges fest.
     */
    public void adjustColor() {
        switch (speed) {
            case 0: color = "red";
                break;
            case 1: color = "darkOrange";
                break;
            case 2: color = "orange";
                break;
            case 3: color = "darkYellow";
                break;
            case 4: color = "yellow";
                break;
            case 5: color = "green";
                break;
            case 6: color = "darkGreen";
                break;

        }
    }

    /**
     * Wird einmal pro festgegebene Tickzeit aufgerufen und erhöht die Geschwindigkeit um 1,
     * solange das Auto nicht die maximale Geschwindigkeit erreicht hat.
     */
    public void tick() {
        if(speed < maxSpeed) {
            this.speed++;
        }
    }
}

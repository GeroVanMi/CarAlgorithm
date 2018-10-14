package ch.kbw.caralgorithm.models;

/**
 * Car ist eine Modelklasse, die alle Informationen zu einem einzelnen Auto haelt.
 * Es legt die eigene Farbe anhand der Geschwindigkeit bei jedem Tick neu fest.
 */
public class Car {
    /**
     * Legt die Anzahl Felder fest, die ein Auto pro Tick faehrt.
     */
    private int speed;
    /**
     * Bestimmt wie schnell sich ein Auto maximal fahren kann.
     */
    private int maxSpeed;
    /**
     * Die aktuelle Position eines Autos.
     */
    private int position;
    /**
     * Die Farbe des Auto wird als CSS ID dem aktuellen Feld übergeben und dann dargestellt.
     */
    private String color;

    /**
     * Der Konstruktor initialisiert ein neues Fahrzeug bei den gegebenen Werten und setzt
     * die Farbe basierend auf der Anfangsgeschwindigkeit.
     * @param speed Legt die Anfangsgeschwindigkeit fest.
     * @param maxSpeed Legt die Maximalgeschwindigkeit des Autos fest.
     */
    public Car(int speed, int maxSpeed) {
        this.speed = speed;
        this.maxSpeed = maxSpeed;
        this.position = 0;
        adjustColor();
    }

    /**
     * Erhoeht die Geschwindigkeit um 1.
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
     * @param speed Die gewuenschte neue Geschwindigkeit.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move() {
        position += speed;
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
     * Wird einmal pro festgegebene Tickzeit aufgerufen und erhoeht die Geschwindigkeit um 1,
     * solange das Auto nicht die maximale Geschwindigkeit erreicht hat.
     */
    public void tick() {
        if(speed < maxSpeed) {
            this.speed++;
        }
    }

    /**
     * @return Gibt die Geschwindigkeit eines Autos zurueck.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return Gibt die aktuelle Position eines Autos zurueck.
     */
    public int getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }
}

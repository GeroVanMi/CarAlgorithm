package viewControllers;

public interface ViewController {
    /**
     * Wird beim Erstellen eines Controllers aufgerufen und agiert als eine Art Konstruktor.
     * @param navigationController Der "NavigationsController" muss bei jedem Viewcontroller gesetzt werden und agiert als Eltern-Node.
     */
    void setup(NavigationController navigationController);
    void destroy();
    void reset();
}

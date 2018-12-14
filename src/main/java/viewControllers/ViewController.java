package viewControllers;

public abstract class ViewController {
    /**
     * Wird beim Erstellen eines Controllers aufgerufen und agiert als eine Art Konstruktor.
     * @param navigationController Der "NavigationsController" muss bei jedem Viewcontroller gesetzt werden und agiert als Eltern-Node.
     */
    public abstract void setup(NavigationController navigationController);
    public abstract void destroy();
}

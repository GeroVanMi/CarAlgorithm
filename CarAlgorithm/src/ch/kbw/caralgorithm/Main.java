package ch.kbw.caralgorithm;

import ch.kbw.caralgorithm.viewControllers.HomeController;
import ch.kbw.caralgorithm.viewControllers.NavigationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/navigation.fxml"));
        primaryStage.setResizable(true);
        primaryStage.setTitle("Phantomstau");
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/navigation.fxml"));
        root = loader.load();
        NavigationController navigationController = loader.getController();
        navigationController.setUp();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

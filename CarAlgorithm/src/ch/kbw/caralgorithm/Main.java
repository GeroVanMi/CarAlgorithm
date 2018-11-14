package ch.kbw.caralgorithm;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/fxml/navigation.fxml"));
        Parent root = loader.load();
        NavigationController navigationController = loader.getController();
        navigationController.setup();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Phantomstau");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

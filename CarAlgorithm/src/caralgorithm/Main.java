package caralgorithm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gameField.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Phantomstau");
        primaryStage.setScene(new Scene(root, 1280, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

// In case of push error 403: add https://GeroVanMi@github.com/GeroVanMi/CarAlgorithm.git
package com.example.selectivefilesaver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// TODO: Incorporate WatchService API to automatically refresh directories.
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("File Handler 1.0");
        stage.setScene(scene);
        stage.show();

        MainController controller = (MainController)fxmlLoader.getController();
        controller.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
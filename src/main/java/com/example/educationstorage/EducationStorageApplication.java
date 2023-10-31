package com.example.educationstorage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EducationStorageApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EducationStorageApplication.class.getResource("EducationStorage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 950);
        stage.setTitle("Education Storage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
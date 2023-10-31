package com.example.educationstorage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;

import javafx.scene.control.MenuBar;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EducationStorageController implements Initializable {

    // define FXML attributes

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu gpaCalculatorMenu;
    @FXML
    private Menu yearFourMenu;
    @FXML
    private Menu yearOneMenu;
    @FXML
    private Menu yearThreeMenu;
    @FXML
    private Menu yearTwoMenu;

    private Connection conn;

    // initialize function
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        yearOneMenu.setStyle("-fx-padding: 12 10 12 10;");
        yearTwoMenu.setStyle("-fx-padding: 12 10 12 10;");
        yearThreeMenu.setStyle("-fx-padding: 12 10 12 10;");
        yearFourMenu.setStyle("-fx-padding: 12 10 12 10;");
        gpaCalculatorMenu.setStyle("-fx-padding: 12 10 12 10;");

        // connect to the Apache DB
        try {
            String dbURL = "jdbc:derby:EducationStorageDB;create=true";
            conn = DriverManager.getConnection(dbURL);
            System.out.println("Connected to the database.");
            // Perform database operations here
            new YearOneAdapter(conn, false);
            new YearTwoAdapter(conn, false);
            new YearThreeAdapter(conn, false);
            new YearFourAdapter(conn, false);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // method to open year 1 window
    public void openYearOne() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(EducationStorageController.class.getResource("YearOne-view.fxml"));
        // create the root node
        Parent YearOne = fxmlLoader.load();

        // set up adapters and controller
        YearOneController yearOneController = (YearOneController) fxmlLoader.getController();
        yearOneController.setEducationStorageController(this);
        yearOneController.setAdapters(new YearOneAdapter(conn, false));

        // create new stage
        Stage stage = new Stage();
        // add the year one UI elements to the stage
        stage.setScene(new Scene(YearOne));
        // set title of stage
        stage.setTitle("Year One");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    // method to open year 2 window
    public void openYearTwo() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(EducationStorageController.class.getResource("YearTwo-view.fxml"));
        // create the root node
        Parent YearTwo = fxmlLoader.load();

        // set up adapters and controller
        YearTwoController yearTwoController = (YearTwoController) fxmlLoader.getController();
        yearTwoController.setEducationStorageController(this);
        yearTwoController.setAdapters(new YearTwoAdapter(conn, false));

        // create new stage
        Stage stage = new Stage();
        // add the year two UI elements to the stage
        stage.setScene(new Scene(YearTwo));
        // set title of stage
        stage.setTitle("Year Two");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    // method to open year 3 window
    public void openYearThree() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(EducationStorageController.class.getResource("YearThree-view.fxml"));
        // create the root node
        Parent YearThree = fxmlLoader.load();

        // set up adapters and controller
        YearThreeController yearThreeController = (YearThreeController) fxmlLoader.getController();
        yearThreeController.setEducationStorageController(this);
        yearThreeController.setAdapters(new YearThreeAdapter(conn, false));

        // create new stage
        Stage stage = new Stage();
        // add the year three UI elements to the stage
        stage.setScene(new Scene(YearThree));
        // set title of stage
        stage.setTitle("Year Three");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // method to open year 4 window
    public void openYearFour() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(EducationStorageController.class.getResource("YearFour-view.fxml"));
        // create the root node
        Parent YearFour = fxmlLoader.load();

        // set up adapters and controller
        YearFourController yearFourController = (YearFourController) fxmlLoader.getController();
        yearFourController.setEducationStorageController(this);
        yearFourController.setAdapters(new YearFourAdapter(conn, false));

        // create new stage
        Stage stage = new Stage();
        // add the year four UI elements to the stage
        stage.setScene(new Scene(YearFour));
        // set title of stage
        stage.setTitle("Year Four");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // method to open gpa calculator window
    public void openGpaCalculator() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(EducationStorageController.class.getResource("GpaCalculator-view.fxml"));
        // create the root node
        Parent GpaCalculator = fxmlLoader.load();

        // set up adapters and controller
        GpaCalculatorController gpaCalculatorController = (GpaCalculatorController) fxmlLoader.getController();
        gpaCalculatorController.setEducationStorageController(this);
        gpaCalculatorController.setAdapters(new YearOneAdapter(conn, false), new YearTwoAdapter(conn, false),
                new YearThreeAdapter(conn, false), new YearFourAdapter(conn, false));

        // create new stage
        Stage stage = new Stage();
        // add the gpa calculator UI elements to the stage
        stage.setScene(new Scene(GpaCalculator));
        // set title of stage
        stage.setTitle("GPA Calculator");
        stage.show();
    }

}
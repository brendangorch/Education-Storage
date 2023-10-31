package com.example.educationstorage;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class YearThreeController implements Initializable {

    // define FXML attributes
    @FXML
    private Label errorMessageLabel;
    @FXML
    private TreeView<String> coursesTreeView;
    @FXML
    private TextField gradeTextField;
    @FXML
    private ProgressBar progressBarItem;
    @FXML
    private Button saveBtn;
    @FXML
    private Label gradeLabel;
    @FXML
    private MenuItem deleteItem;
    @FXML
    private MenuItem editInfoItem;
    @FXML
    private MenuItem addCourseItem;
    @FXML
    private Button clearBtn;
    @FXML
    private TextField courseTextField;
    @FXML
    private ComboBox<Double> weightComboBox;

    private YearThreeAdapter yearThreeAdapter;
    private EducationStorageController educationStorageController;

    // define button CSS styles
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #4aa9c9; -fx-background-radius: 15;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #39859e; -fx-background-radius: 12.5; " +
            "-fx-text-fill: white; -fx-cursor: hand; -fx-border-color: #4aa9c9; -fx-border-radius: 12.5;";
    private static final String IDLE_FIELD_STYLE = "-fx-background-color: #4aa9c9; -fx-background-radius: 15; -fx-prompt-text-fill: white;";
    private static final String HOVERED_FIELD_STYLE = "-fx-background-color: #39859e; -fx-background-radius: 12.5; " +
            "-fx-text-fill: black; -fx-cursor: text; -fx-border-color: #4aa9c9; -fx-border-radius: 12.5; -fx-prompt-text-fill: white;";

    private boolean editFlag = false;

    public void setAdapters(YearThreeAdapter yearThreeAdapt) throws SQLException {
        yearThreeAdapter = yearThreeAdapt;
        populateTreeView();
    }

    public void setEducationStorageController(EducationStorageController educationStorageControl) {
        educationStorageController = educationStorageControl;
    }

    // method for button animations
    public void buildHoverAnimations() {
        // set idle styles
        saveBtn.setStyle(IDLE_BUTTON_STYLE);
        clearBtn.setStyle(IDLE_BUTTON_STYLE);
        weightComboBox.setStyle(IDLE_BUTTON_STYLE);
        gradeTextField.setStyle(IDLE_FIELD_STYLE);
        courseTextField.setStyle(IDLE_FIELD_STYLE);

        // set on mouse entered styles
        saveBtn.setOnMouseEntered(e -> saveBtn.setStyle(HOVERED_BUTTON_STYLE));
        clearBtn.setOnMouseEntered(e -> clearBtn.setStyle(HOVERED_BUTTON_STYLE));
        weightComboBox.setOnMouseEntered(e -> weightComboBox.setStyle(HOVERED_BUTTON_STYLE));
        gradeTextField.setOnMouseEntered(e -> gradeTextField.setStyle(HOVERED_FIELD_STYLE));
        courseTextField.setOnMouseEntered(e -> courseTextField.setStyle(HOVERED_FIELD_STYLE));


        // set on mouse exited styles back to idle styles
        saveBtn.setOnMouseExited(e -> saveBtn.setStyle(IDLE_BUTTON_STYLE));
        clearBtn.setOnMouseExited(e -> clearBtn.setStyle(IDLE_BUTTON_STYLE));
        weightComboBox.setOnMouseExited(e -> weightComboBox.setStyle(IDLE_BUTTON_STYLE));
        gradeTextField.setOnMouseExited(e -> gradeTextField.setStyle(IDLE_FIELD_STYLE));
        courseTextField.setOnMouseExited(e -> courseTextField.setStyle(IDLE_FIELD_STYLE));
    }

    // initialize method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initially, set progress bar to invisible
        progressBarItem.setVisible(false);

        // disable buttons and text fields
        saveBtn.setDisable(true);
        clearBtn.setDisable(true);
        gradeTextField.setDisable(true);
        courseTextField.setDisable(true);
        weightComboBox.setDisable(true);

        populateComboBox();

        // set progress bar styles
        String progressBarStyle = "-fx-control-background-color: grey; -fx-control-inner-background: grey;" +
                " -fx-focus-color: grey; -fx-faint-focus-color: grey; -fx-border-color: grey; -fx-border-width: 0;";
        progressBarItem.setStyle(progressBarStyle);

        buildHoverAnimations();
    }

    // method to populate combo box
    public void populateComboBox() {
        weightComboBox.getItems().addAll(0.5, 1.0);
    }

    // method to populate tree view with existing courses in the DB
    public void populateTreeView() throws SQLException{

        // create root for the tree view
        TreeItem<String> root = new TreeItem<>("");
        // create term items for the tree view
        TreeItem<String> firstTerm = new TreeItem<>("First Term: Fall");
        TreeItem<String> secondTerm = new TreeItem<>("Second Term: Winter");
        TreeItem<String> thirdTerm = new TreeItem<>("Third Term: Summer");

        // Set a custom cell factory using lambda expression to change fonts and sizes in tree view
        coursesTreeView.setCellFactory(tv -> new TextFieldTreeCell<>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (!empty) {
                    // change size of the term nodes to slightly larger
                    if (item.equals("First Term: Fall") || item.equals("Second Term: Winter") || item.equals("Third Term: Summer")) {
                        setFont(new Font("Segoe UI", 20));
                    } else {
                        setFont(new Font("Segoe UI", 17));
                    }

                }
            }
        });

        // set the root of the tree and set show to false
        coursesTreeView.setRoot(root);
        coursesTreeView.setShowRoot(false);
        // add the 4 categories to the root
        root.getChildren().addAll(firstTerm, secondTerm, thirdTerm);


        // populate tree view with the existing courses in the database
        ObservableList<String> courseNamesList = yearThreeAdapter.returnListOfCourseNames();

        // loop over all course names in the DB
        for (String courseName: courseNamesList) {
            // get the term, ID, and percent grade of the course from the database
            int term = yearThreeAdapter.getTermNumber(courseName);
            double percentGrade = yearThreeAdapter.getPercentGrade(courseName);
            // create a new tree item with the name of the course and grade
            TreeItem<String> newCourse = new TreeItem<>(courseName + "- " + percentGrade + "%");

            // add the course to the correct term in the tree
            switch (term) {
                case 1 -> firstTerm.getChildren().add(newCourse);
                case 2 -> secondTerm.getChildren().add(newCourse);
                case 3 -> thirdTerm.getChildren().add(newCourse);
            }
        }

    }

    // method for when save button is clicked
    public void onSave() throws SQLException {

        // create a converter and colour object for grades and progress bar
        ConverterAndColour gradeObject = new ConverterAndColour();

        // if edit was selected, edit the course, rather than making a new course
        if (editFlag) {

            try {
                // get the item that was clicked on
                TreeItem<String> item = coursesTreeView.getSelectionModel().getSelectedItem();
                // isolate the course name
                String currentCourseName = deleteUntilCharacter(item.getValue(), '-');

                // get the new course name, grade, and weight
                String newCourseName = courseTextField.getText();
                double newCourseGrade = Double.parseDouble(gradeTextField.getText());
                double newWeight = weightComboBox.getValue();

                if (newCourseGrade >= 0 && newCourseGrade <= 100 && !courseTextField.getText().isBlank() &&
                        weightComboBox.getValue() != null) {

                    // update the tree view
                    item.setValue(newCourseName + "- " + newCourseGrade + "%");

                    // update the course in the database
                    yearThreeAdapter.changePercentGrade(currentCourseName, newCourseGrade);
                    yearThreeAdapter.changeCourseName(currentCourseName, newCourseName);
                    yearThreeAdapter.changeWeight(currentCourseName, newWeight);

                    // make progress bar and grade label visible
                    progressBarItem.setVisible(true);
                    gradeLabel.setVisible(true);

                    // set the progress bar and label to the grade
                    progressBarItem.setProgress((double) newCourseGrade / 100);
                    gradeLabel.setText(String.format("%.1f", newCourseGrade) + "%");
                    progressBarItem.setStyle(gradeObject.determineColour(newCourseGrade));

                    // reset error message
                    errorMessageLabel.setText("");

                    // disable and clear buttons and text fields
                    saveBtn.setDisable(true);
                    clearBtn.setDisable(true);
                    courseTextField.setDisable(true);
                    gradeTextField.setDisable(true);
                    weightComboBox.setDisable(true);
                    weightComboBox.setValue(null);
                    courseTextField.clear();
                    gradeTextField.clear();
                }


            } catch (Exception e) {
                System.out.println(e);
                errorMessageLabel.setText("Enter a valid course name and grade.");
            }

            // set the edit flag to false
            editFlag = false;

        } else { // otherwise, create a new course

            // try-catch block to check logic
            // checks if entered course name already exists then ensures valid course and grade are entered
            try {
                // get the course name entered
                String courseNameEntered = courseTextField.getText();
                // get the grade entered
                String gradeText = gradeTextField.getText().replaceAll(" ", "");
                double gradePercent = Double.parseDouble(gradeText);
                // get the weight
                double weight = weightComboBox.getValue();


                // check if valid grade and course name are entered
                if (gradePercent >= 0 && gradePercent <= 100 && !courseTextField.getText().isBlank()&&
                        weightComboBox.getValue() != null) {

                    // check that entered course name does not already exist in the database
                    if (!yearThreeAdapter.courseNameTaken(courseNameEntered)) {
                        // get the tree item clicked on (term)
                        TreeItem<String> termSelected = coursesTreeView.getSelectionModel().getSelectedItem();

                        // add the course to the tree view
                        TreeItem<String> newItem = new TreeItem<>(courseTextField.getText() + "- " + gradePercent + "%");
                        termSelected.getChildren().add(newItem);

                        // add the new course to the database depending on term selected
                        if (termSelected.getValue().equals("First Term: Fall")) {
                            yearThreeAdapter.insertCourse(yearThreeAdapter.getMaxId() + 1, courseNameEntered, 1, gradePercent, weight);
                        }
                        if (termSelected.getValue().equals("Second Term: Winter")) {
                            yearThreeAdapter.insertCourse(yearThreeAdapter.getMaxId() + 1, courseNameEntered, 2, gradePercent, weight);
                        }
                        if (termSelected.getValue().equals("Third Term: Summer")) {
                            yearThreeAdapter.insertCourse(yearThreeAdapter.getMaxId() + 1, courseNameEntered, 3, gradePercent, weight);
                        }

                        // make progress bar and grade label visible
                        progressBarItem.setVisible(true);
                        gradeLabel.setVisible(true);

                        // set the progress bar and label to the grade
                        progressBarItem.setProgress((double) gradePercent / 100);
                        gradeLabel.setText(gradeText + "%");
                        progressBarItem.setStyle(gradeObject.determineColour(gradePercent));

                        // reset error message
                        errorMessageLabel.setText("");

                        // disable and clear buttons and text fields
                        saveBtn.setDisable(true);
                        clearBtn.setDisable(true);
                        courseTextField.setDisable(true);
                        gradeTextField.setDisable(true);
                        weightComboBox.setDisable(true);
                        weightComboBox.setValue(null);
                        courseTextField.clear();
                        gradeTextField.clear();
                    } else {
                        errorMessageLabel.setText("This course already exists.");
                    }

                } else {
                    errorMessageLabel.setText("Enter a valid course name, grade, and weight.");
                }


            } catch (Exception e) {
                System.out.println(e);
                errorMessageLabel.setText("Enter a valid course name, grade, and weight.");

            }
        }

    }

    // method for when a tree item is clicked on (determines applicable context menu items)
    public void clickTreeItem(MouseEvent event) {
        // get the item that was clicked on
        TreeItem<String> item = coursesTreeView.getSelectionModel().getSelectedItem();

        // if the group is a term (root), disable edit and delete, enable add course
        try {
            if (item.getValue().equals("First Term: Fall") || item.getValue().equals("Second Term: Winter")
                    || item.getValue().equals("Third Term: Summer")) {
                addCourseItem.setDisable(false);
                editInfoItem.setDisable(true);
                deleteItem.setDisable(true);
                // if the group is not one of the terms, disable add course item, enable edit and delete
            } else {
                addCourseItem.setDisable(true);
                editInfoItem.setDisable(false);
                deleteItem.setDisable(false);
            }
        } catch (Exception e) {
        }
    }

    // method when add course is clicked
    public void addCourseClicked() throws SQLException {
        // enable text fields and buttons
        courseTextField.setDisable(false);
        gradeTextField.setDisable(false);
        saveBtn.setDisable(false);
        clearBtn.setDisable(false);
        courseTextField.requestFocus();
        weightComboBox.setDisable(false);

        progressBarItem.setVisible(false);

    }

    // clear button method
    public void clear() {
        // clear text fields
        courseTextField.clear();
        gradeTextField.clear();
        weightComboBox.setValue(null);
        // hide progress bar and text
        progressBarItem.setVisible(false);
        gradeLabel.setVisible(false);
        errorMessageLabel.setText("");

    }

    // helper method for delete method
    public String deleteUntilCharacter(String input, char targetChar) {

        // deletes characters from the end of a string until specified char is reached
        int index = input.length() - 1;
        while (index >= 0 && input.charAt(index) != targetChar) {
            index--;
        }
        if (index >= 0) {
            return input.substring(0, index);
        } else {
            return input;
        }
    }

    // method to delete a course
    public void deleteCourse() throws SQLException {
        // get the item that was clicked on
        TreeItem<String> item = coursesTreeView.getSelectionModel().getSelectedItem();

        // delete the course from the database
        yearThreeAdapter.deleteCourse(deleteUntilCharacter(item.getValue(), '-'));

        // delete the course from the tree view
        item.getParent().getChildren().remove(item);

        progressBarItem.setVisible(false);
    }

    // method to edit a course
    public void editCourse() throws SQLException {
        // get the item that was clicked on
        TreeItem<String> item = coursesTreeView.getSelectionModel().getSelectedItem();

        // enable fields and buttons
        courseTextField.setDisable(false);
        gradeTextField.setDisable(false);
        saveBtn.setDisable(false);
        clearBtn.setDisable(false);
        weightComboBox.setDisable(false);

        // set fields to selected course's data
        courseTextField.setText(deleteUntilCharacter(item.getValue(), '-'));
        gradeTextField.setText(String.format("%.1f", yearThreeAdapter.getPercentGrade(deleteUntilCharacter(item.getValue(), '-'))));
        weightComboBox.setValue(yearThreeAdapter.getWeight(deleteUntilCharacter(item.getValue(), '-')));

        editFlag = true;

    }
}

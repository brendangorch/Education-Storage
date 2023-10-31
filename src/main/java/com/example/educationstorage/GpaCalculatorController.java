package com.example.educationstorage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

public class GpaCalculatorController implements Initializable {
    // define attributes

    // first year objects
    @FXML
    private ProgressBar progressBarOne;
    @FXML
    private Button yearOneChangeBtn;
    @FXML
    private Label exclSummerLblOne;
    @FXML
    private Label firstTermLblOne;
    @FXML
    private Label highestLblOne;
    @FXML
    private Label lowestLblOne;
    @FXML
    private Label secondTermLblOne;
    @FXML
    private Label summerTermLblOne;
    @FXML
    private Label wGpaLblOne;
    @FXML
    private Label wGpaLetterOne;
    @FXML
    private Label wGpaScaleOne;
    @FXML
    private RadioButton yearOneRadioBtn;
    @FXML
    private Label totalCredLblOne;

    // second year objects
    @FXML
    private ProgressBar progressBarTwo;
    @FXML
    private Button yearTwoChangeBtn;
    @FXML
    private Label exclSummerLblTwo;
    @FXML
    private Label firstTermLblTwo;
    @FXML
    private Label highestLblTwo;
    @FXML
    private Label lowestLblTwo;
    @FXML
    private Label secondTermLblTwo;
    @FXML
    private Label summerTermLblTwo;
    @FXML
    private Label wGpaLblTwo;
    @FXML
    private Label wGpaLetterTwo;
    @FXML
    private Label wGpaScaleTwo;
    @FXML
    private RadioButton yearTwoRadioBtn;
    @FXML
    private Label totalCredLblTwo;

    // third year objects
    @FXML
    private ProgressBar progressBarThree;
    @FXML
    private Button yearThreeChangeBtn;
    @FXML
    private Label exclSummerLblThree;
    @FXML
    private Label firstTermLblThree;
    @FXML
    private Label highestLblThree;
    @FXML
    private Label lowestLblThree;
    @FXML
    private Label secondTermLblThree;
    @FXML
    private Label summerTermLblThree;
    @FXML
    private Label wGpaLblThree;
    @FXML
    private Label wGpaLetterThree;
    @FXML
    private Label wGpaScaleThree;
    @FXML
    private RadioButton yearThreeRadioBtn;
    @FXML
    private Label totalCredLblThree;

    // fourth year objects
    @FXML
    private ProgressBar progressBarFour;
    @FXML
    private Button yearFourChangeBtn;
    @FXML
    private Label exclSummerLblFour;
    @FXML
    private Label firstTermLblFour;
    @FXML
    private Label highestLblFour;
    @FXML
    private Label lowestLblFour;
    @FXML
    private Label secondTermLblFour;
    @FXML
    private Label summerTermLblFour;
    @FXML
    private Label wGpaLblFour;
    @FXML
    private Label wGpaLetterFour;
    @FXML
    private Label wGpaScaleFour;
    @FXML
    private RadioButton yearFourRadioBtn;
    @FXML
    private Label totalCredLblFour;



    @FXML
    private Label noDataOneLbl;
    @FXML
    private Label noDataTwoLbl;
    @FXML
    private Label noDataThreeLbl;
    @FXML
    private Label noDataFourLbl;
    @FXML
    private Label calculatedGpaLbl;
    @FXML
    private Label calculatedLetterLbl;
    @FXML
    private Label calculatedScaleLbl;
    @FXML
    private Button calculateBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private CheckBox selectAllBtn;
    private YearOneAdapter yearOneAdapter;
    private YearTwoAdapter yearTwoAdapter;
    private YearThreeAdapter yearThreeAdapter;
    private YearFourAdapter yearFourAdapter;
    private EducationStorageController educationStorageController;

    // define button CSS styles
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #4aa9c9; -fx-background-radius: 15;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #39859e; -fx-background-radius: 12.5; " +
            "-fx-text-fill: white; -fx-cursor: hand; -fx-border-color: #4aa9c9; -fx-border-radius: 12.5;";
    private static final String IDLE_RADIO_STYLE = "-fx-background-radius: 15;";
    private static final String HOVERED_RADIO_STYLE = "-fx-background-radius: 12.5; " +
            "-fx-text-fill: white; -fx-cursor: hand; -fx-border-color: #4aa9c9; -fx-border-radius: 12.5;";

    // converter and colour object
    ConverterAndColour converterAndColour = new ConverterAndColour();


    // set adapters for all years
    public void setAdapters(YearOneAdapter yearOneAdapt, YearTwoAdapter yearTwoAdapt, YearThreeAdapter yearThreeAdapt,
                            YearFourAdapter yearFourAdapt) throws SQLException {
        yearOneAdapter = yearOneAdapt;
        yearTwoAdapter = yearTwoAdapt;
        yearThreeAdapter = yearThreeAdapt;
        yearFourAdapter = yearFourAdapt;

        setYearData(1);
        setYearData(2);
        setYearData(3);
        setYearData(4);


    }

    public void setEducationStorageController(EducationStorageController educationStorageControl) {
        educationStorageController = educationStorageControl;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set progress bar styles
        String progressBarStyle = "-fx-control-background-color: grey; -fx-control-inner-background: grey;" +
                " -fx-focus-color: grey; -fx-faint-focus-color: grey; -fx-border-color: grey; -fx-border-width: 0;";
        progressBarOne.setStyle(progressBarStyle);
        progressBarTwo.setStyle(progressBarStyle);
        progressBarThree.setStyle(progressBarStyle);
        progressBarFour.setStyle(progressBarStyle);

        // initially set buttons to percent
        yearOneChangeBtn.setText("Percent");
        yearTwoChangeBtn.setText("Percent");
        yearThreeChangeBtn.setText("Percent");
        yearFourChangeBtn.setText("Percent");

        buildHoverAnimations();

        // hide no data labels
        noDataOneLbl.setVisible(false);
        noDataTwoLbl.setVisible(false);
        noDataThreeLbl.setVisible(false);
        noDataFourLbl.setVisible(false);

        // disable calculate button
        calculateBtn.setDisable(true);


    }

    // method to change state of a button
    public void changeBtnState(Button btn) {
        // check the current text/state of the button
        if (btn.getText().equals("Percent")) {
            btn.setText("Letter");
        } else if (btn.getText().equals("Letter")) {
            btn.setText("4.0 Scale");
        } else if (btn.getText().equals("4.0 Scale")) {
            btn.setText("Percent");
        }
    }

    // helper method to change labels of a given year
    public void changeYearLabels(int year) throws SQLException {

        if (year == 1) {
            if (yearOneChangeBtn.getText().equals("Percent")) {
                firstTermLblOne.setText(String.format("First Term GPA: %.2f%%", yearOneAdapter.getTermGpa(1)).replace("NaN%", "TBD"));
                secondTermLblOne.setText(String.format("Second Term GPA: %.2f%%", yearOneAdapter.getTermGpa(2)).replace("NaN%", "TBD"));
                summerTermLblOne.setText(String.format("Summer Term GPA: %.2f%%", yearOneAdapter.getTermGpa(3)).replace("NaN%", "TBD"));
                exclSummerLblOne.setText(String.format("GPA excl. Summer Term: %.2f%%", yearOneAdapter.getOneAndTwoTermsGPa()).replace("NaN%", "TBD"));
                highestLblOne.setText(String.format("Highest Grade: %.2f%%", Collections.max(yearOneAdapter.returnListOfGrades())).replace("NaN%", "TBD"));
                lowestLblOne.setText(String.format("Lowest Grade: %.2f%%", Collections.min(yearOneAdapter.returnListOfGrades())).replace("NaN%", "TBD"));
            } else if (yearOneChangeBtn.getText().equals("Letter")) {
                firstTermLblOne.setText(String.format("First Term GPA: %s", converterAndColour.determineLetterGrade(yearOneAdapter.getTermGpa(1))));
                secondTermLblOne.setText(String.format("Second Term GPA: %s", converterAndColour.determineLetterGrade(yearOneAdapter.getTermGpa(2))));
                summerTermLblOne.setText(String.format("Summer Term GPA: %s", converterAndColour.determineLetterGrade(yearOneAdapter.getTermGpa(3))));
                exclSummerLblOne.setText(String.format("GPA excl. Summer Term: %s", converterAndColour.determineLetterGrade(yearOneAdapter.getOneAndTwoTermsGPa())));
                highestLblOne.setText(String.format("Highest Grade: %s", converterAndColour.determineLetterGrade(Collections.max(yearOneAdapter.returnListOfGrades()))));
                lowestLblOne.setText(String.format("Lowest Grade: %s", converterAndColour.determineLetterGrade(Collections.min(yearOneAdapter.returnListOfGrades()))));
            } else if (yearOneChangeBtn.getText().equals("4.0 Scale")) {
                firstTermLblOne.setText(String.format("First Term GPA: %.1f", converterAndColour.determineGPAGrade(yearOneAdapter.getTermGpa(1))).replace("5.0", "TBD"));
                secondTermLblOne.setText(String.format("Second Term GPA: %.1f", converterAndColour.determineGPAGrade(yearOneAdapter.getTermGpa(2))).replace("5.0", "TBD"));
                summerTermLblOne.setText(String.format("Summer Term GPA: %.1f", converterAndColour.determineGPAGrade(yearOneAdapter.getTermGpa(3))).replace("5.0", "TBD"));
                exclSummerLblOne.setText(String.format("GPA excl. Summer Term: %.1f", converterAndColour.determineGPAGrade(yearOneAdapter.getOneAndTwoTermsGPa())).replace("5.0", "TBD"));
                highestLblOne.setText(String.format("Highest Grade: %.1f", converterAndColour.determineGPAGrade(Collections.max(yearOneAdapter.returnListOfGrades()))).replace("5.0", "TBD"));
                lowestLblOne.setText(String.format("Lowest Grade: %.1f", converterAndColour.determineGPAGrade(Collections.min(yearOneAdapter.returnListOfGrades()))).replace("5.0", "TBD"));
            }

        } else if (year == 2) {
            if (yearTwoChangeBtn.getText().equals("Percent")) {
                firstTermLblTwo.setText(String.format("First Term GPA: %.2f%%", yearTwoAdapter.getTermGpa(1)).replace("NaN%", "TBD"));
                secondTermLblTwo.setText(String.format("Second Term GPA: %.2f%%", yearTwoAdapter.getTermGpa(2)).replace("NaN%", "TBD"));
                summerTermLblTwo.setText(String.format("Summer Term GPA: %.2f%%", yearTwoAdapter.getTermGpa(3)).replace("NaN%", "TBD"));
                exclSummerLblTwo.setText(String.format("GPA excl. Summer Term: %.2f%%", yearTwoAdapter.getOneAndTwoTermsGPa()).replace("NaN%", "TBD"));
                highestLblTwo.setText(String.format("Highest Grade: %.2f%%", Collections.max(yearTwoAdapter.returnListOfGrades())).replace("NaN%", "TBD"));
                lowestLblTwo.setText(String.format("Lowest Grade: %.2f%%", Collections.min(yearTwoAdapter.returnListOfGrades())).replace("NaN%", "TBD"));
            } else if (yearTwoChangeBtn.getText().equals("Letter")) {
                firstTermLblTwo.setText(String.format("First Term GPA: %s", converterAndColour.determineLetterGrade(yearTwoAdapter.getTermGpa(1))));
                secondTermLblTwo.setText(String.format("Second Term GPA: %s", converterAndColour.determineLetterGrade(yearTwoAdapter.getTermGpa(2))));
                summerTermLblTwo.setText(String.format("Summer Term GPA: %s", converterAndColour.determineLetterGrade(yearTwoAdapter.getTermGpa(3))));
                exclSummerLblTwo.setText(String.format("GPA excl. Summer Term: %s", converterAndColour.determineLetterGrade(yearTwoAdapter.getOneAndTwoTermsGPa())));
                highestLblTwo.setText(String.format("Highest Grade: %s", converterAndColour.determineLetterGrade(Collections.max(yearTwoAdapter.returnListOfGrades()))));
                lowestLblTwo.setText(String.format("Lowest Grade: %s", converterAndColour.determineLetterGrade(Collections.min(yearTwoAdapter.returnListOfGrades()))));
            } else if (yearTwoChangeBtn.getText().equals("4.0 Scale")) {
                firstTermLblTwo.setText(String.format("First Term GPA: %.1f", converterAndColour.determineGPAGrade(yearTwoAdapter.getTermGpa(1))).replace("5.0", "TBD"));
                secondTermLblTwo.setText(String.format("Second Term GPA: %.1f", converterAndColour.determineGPAGrade(yearTwoAdapter.getTermGpa(2))).replace("5.0", "TBD"));
                summerTermLblTwo.setText(String.format("Summer Term GPA: %.1f", converterAndColour.determineGPAGrade(yearTwoAdapter.getTermGpa(3))).replace("5.0", "TBD"));
                exclSummerLblTwo.setText(String.format("GPA excl. Summer Term: %.1f", converterAndColour.determineGPAGrade(yearTwoAdapter.getOneAndTwoTermsGPa())).replace("5.0", "TBD"));
                highestLblTwo.setText(String.format("Highest Grade: %.1f", converterAndColour.determineGPAGrade(Collections.max(yearTwoAdapter.returnListOfGrades()))).replace("5.0", "TBD"));
                lowestLblTwo.setText(String.format("Lowest Grade: %.1f", converterAndColour.determineGPAGrade(Collections.min(yearTwoAdapter.returnListOfGrades()))).replace("5.0", "TBD"));
            }

        } else if (year == 3) {
            if (yearThreeChangeBtn.getText().equals("Percent")) {
                firstTermLblThree.setText(String.format("First Term GPA: %.2f%%", yearThreeAdapter.getTermGpa(1)).replace("NaN%", "TBD"));
                secondTermLblThree.setText(String.format("Second Term GPA: %.2f%%", yearThreeAdapter.getTermGpa(2)).replace("NaN%", "TBD"));
                summerTermLblThree.setText(String.format("Summer Term GPA: %.2f%%", yearThreeAdapter.getTermGpa(3)).replace("NaN%", "TBD"));
                exclSummerLblThree.setText(String.format("GPA excl. Summer Term: %.2f%%", yearThreeAdapter.getOneAndTwoTermsGPa()).replace("NaN%", "TBD"));
                highestLblThree.setText(String.format("Highest Grade: %.2f%%", Collections.max(yearThreeAdapter.returnListOfGrades())).replace("NaN%", "TBD"));
                lowestLblThree.setText(String.format("Lowest Grade: %.2f%%", Collections.min(yearThreeAdapter.returnListOfGrades())).replace("NaN%", "TBD"));
            } else if (yearThreeChangeBtn.getText().equals("Letter")) {
                firstTermLblThree.setText(String.format("First Term GPA: %s", converterAndColour.determineLetterGrade(yearThreeAdapter.getTermGpa(1))));
                secondTermLblThree.setText(String.format("Second Term GPA: %s", converterAndColour.determineLetterGrade(yearThreeAdapter.getTermGpa(2))));
                summerTermLblThree.setText(String.format("Summer Term GPA: %s", converterAndColour.determineLetterGrade(yearThreeAdapter.getTermGpa(3))));
                exclSummerLblThree.setText(String.format("GPA excl. Summer Term: %s", converterAndColour.determineLetterGrade(yearThreeAdapter.getOneAndTwoTermsGPa())));
                highestLblThree.setText(String.format("Highest Grade: %s", converterAndColour.determineLetterGrade(Collections.max(yearThreeAdapter.returnListOfGrades()))));
                lowestLblThree.setText(String.format("Lowest Grade: %s", converterAndColour.determineLetterGrade(Collections.min(yearThreeAdapter.returnListOfGrades()))));
            } else if (yearThreeChangeBtn.getText().equals("4.0 Scale")) {
                firstTermLblThree.setText(String.format("First Term GPA: %.1f", converterAndColour.determineGPAGrade(yearThreeAdapter.getTermGpa(1))).replace("5.0", "TBD"));
                secondTermLblThree.setText(String.format("Second Term GPA: %.1f", converterAndColour.determineGPAGrade(yearThreeAdapter.getTermGpa(2))).replace("5.0", "TBD"));
                summerTermLblThree.setText(String.format("Summer Term GPA: %.1f", converterAndColour.determineGPAGrade(yearThreeAdapter.getTermGpa(3))).replace("5.0", "TBD"));
                exclSummerLblThree.setText(String.format("GPA excl. Summer Term: %.1f", converterAndColour.determineGPAGrade(yearThreeAdapter.getOneAndTwoTermsGPa())).replace("5.0", "TBD"));
                highestLblThree.setText(String.format("Highest Grade: %.1f", converterAndColour.determineGPAGrade(Collections.max(yearThreeAdapter.returnListOfGrades()))).replace("5.0", "TBD"));
                lowestLblThree.setText(String.format("Lowest Grade: %.1f", converterAndColour.determineGPAGrade(Collections.min(yearThreeAdapter.returnListOfGrades()))).replace("5.0", "TBD"));
            }

        } else if (year == 4) {
            if (yearFourChangeBtn.getText().equals("Percent")) {
                firstTermLblFour.setText(String.format("First Term GPA: %.2f%%", yearFourAdapter.getTermGpa(1)).replace("NaN%", "TBD"));
                secondTermLblFour.setText(String.format("Second Term GPA: %.2f%%", yearFourAdapter.getTermGpa(2)).replace("NaN%", "TBD"));
                summerTermLblFour.setText(String.format("Summer Term GPA: %.2f%%", yearFourAdapter.getTermGpa(3)).replace("NaN%", "TBD"));
                exclSummerLblFour.setText(String.format("GPA excl. Summer Term: %.2f%%", yearFourAdapter.getOneAndTwoTermsGPa()).replace("NaN%", "TBD"));
                highestLblFour.setText(String.format("Highest Grade: %.2f%%", Collections.max(yearFourAdapter.returnListOfGrades())).replace("NaN%", "TBD"));
                lowestLblFour.setText(String.format("Lowest Grade: %.2f%%", Collections.min(yearFourAdapter.returnListOfGrades())).replace("NaN%", "TBD"));
            } else if (yearFourChangeBtn.getText().equals("Letter")) {
                firstTermLblFour.setText(String.format("First Term GPA: %s", converterAndColour.determineLetterGrade(yearFourAdapter.getTermGpa(1))));
                secondTermLblFour.setText(String.format("Second Term GPA: %s", converterAndColour.determineLetterGrade(yearFourAdapter.getTermGpa(2))));
                summerTermLblFour.setText(String.format("Summer Term GPA: %s", converterAndColour.determineLetterGrade(yearFourAdapter.getTermGpa(3))));
                exclSummerLblFour.setText(String.format("GPA excl. Summer Term: %s", converterAndColour.determineLetterGrade(yearFourAdapter.getOneAndTwoTermsGPa())));
                highestLblFour.setText(String.format("Highest Grade: %s", converterAndColour.determineLetterGrade(Collections.max(yearFourAdapter.returnListOfGrades()))));
                lowestLblFour.setText(String.format("Lowest Grade: %s", converterAndColour.determineLetterGrade(Collections.min(yearFourAdapter.returnListOfGrades()))));
            } else if (yearFourChangeBtn.getText().equals("4.0 Scale")) {
                firstTermLblFour.setText(String.format("First Term GPA: %.1f", converterAndColour.determineGPAGrade(yearFourAdapter.getTermGpa(1))).replace("5.0", "TBD"));
                secondTermLblFour.setText(String.format("Second Term GPA: %.1f", converterAndColour.determineGPAGrade(yearFourAdapter.getTermGpa(2))).replace("5.0", "TBD"));
                summerTermLblFour.setText(String.format("Summer Term GPA: %.1f", converterAndColour.determineGPAGrade(yearFourAdapter.getTermGpa(3))).replace("5.0", "TBD"));
                exclSummerLblFour.setText(String.format("GPA excl. Summer Term: %.1f", converterAndColour.determineGPAGrade(yearFourAdapter.getOneAndTwoTermsGPa())).replace("5.0", "TBD"));
                highestLblFour.setText(String.format("Highest Grade: %.1f", converterAndColour.determineGPAGrade(Collections.max(yearFourAdapter.returnListOfGrades()))).replace("5.0", "TBD"));
                lowestLblFour.setText(String.format("Lowest Grade: %.1f", converterAndColour.determineGPAGrade(Collections.min(yearFourAdapter.returnListOfGrades()))).replace("5.0", "TBD"));
            }

        }

    }

    // implementations of onYearBtnClick method
    public void onYearOneBtnClick() throws SQLException {
        changeBtnState(yearOneChangeBtn);
        changeYearLabels(1);
    }

    public void onYearTwoBtnClick() throws SQLException {
        changeBtnState(yearTwoChangeBtn);
        changeYearLabels(2);
    }

    public void onYearThreeBtnClick() throws SQLException {
        changeBtnState(yearThreeChangeBtn);
        changeYearLabels(3);
    }

    public void onYearFourBtnClick() throws SQLException {
        changeBtnState(yearFourChangeBtn);
        changeYearLabels(4);
    }


    // method to set year data (labels)
    public void setYearData(int year) throws SQLException {
        if (year == 1) {
            if (!Double.isNaN(yearOneAdapter.getWGPa())) {
                yearOneChangeBtn.setDisable(false);
                yearOneRadioBtn.setDisable(false);
                // set wGpa labels
                wGpaLblOne.setText(String.format("%.2f%%", yearOneAdapter.getWGPa()));
                wGpaLetterOne.setText(converterAndColour.determineLetterGrade(yearOneAdapter.getWGPa()));
                wGpaScaleOne.setText(String.format("%.1f", converterAndColour.determineGPAGrade(yearOneAdapter.getWGPa())));
                // set progress bar
                progressBarOne.setStyle(converterAndColour.determineColour(yearOneAdapter.getWGPa()));
                progressBarOne.setProgress((double) yearOneAdapter.getWGPa() / 100);
            } else {
                wGpaLblOne.setText("TBD");
                yearOneChangeBtn.setDisable(true);
                yearOneRadioBtn.setDisable(true);
                noDataOneLbl.setVisible(true);
            }
            if (!Double.isNaN(yearOneAdapter.getTermGpa(1))) {
                firstTermLblOne.setText(String.format("First Term GPA: %.2f%%", yearOneAdapter.getTermGpa(1)));
            } else {
                firstTermLblOne.setText("First Term GPA: TBD");
            }
            if (!Double.isNaN(yearOneAdapter.getTermGpa(2))) {
                secondTermLblOne.setText(String.format("Second Term GPA: %.2f%%", yearOneAdapter.getTermGpa(2)));
            } else {
                secondTermLblOne.setText("Second Term GPA: TBD");
            }
            if (!Double.isNaN(yearOneAdapter.getTermGpa(3))) {
                summerTermLblOne.setText(String.format("Summer Term GPA: %.2f%%", yearOneAdapter.getTermGpa(3)));
            } else {
                summerTermLblOne.setText("Summer Term GPA: TBD");
            }
            if (!Double.isNaN(yearOneAdapter.getOneAndTwoTermsGPa())) {
                exclSummerLblOne.setText(String.format("GPA excl. Summer Term: %.2f%%", yearOneAdapter.getOneAndTwoTermsGPa()));
            } else {
                exclSummerLblOne.setText("GPA excl. Summer Term: TBD");
            }
            try {
                highestLblOne.setText(String.format("Highest Grade: %.2f%%", Collections.max(yearOneAdapter.returnListOfGrades())));
            } catch (Exception e) {
                highestLblOne.setText("Highest Grade: TBD");
            }
            try {
                lowestLblOne.setText(String.format("Lowest Grade: %.2f%%", Collections.min(yearOneAdapter.returnListOfGrades())));
            } catch (Exception e) {
                lowestLblOne.setText("Lowest Grade: TBD");
            }
            if (!Double.isNaN(yearOneAdapter.getTotalCredits())) {
                totalCredLblOne.setText(String.format("Total Credits: %.1f", yearOneAdapter.getTotalCredits()));
            } else {
                totalCredLblOne.setText("Total Credits: TBD");
            }

        } else if (year == 2) {
            if (!Double.isNaN(yearTwoAdapter.getWGPa())) {
                yearTwoChangeBtn.setDisable(false);
                yearTwoRadioBtn.setDisable(false);
                // set wGpa labels
                wGpaLblTwo.setText(String.format("%.2f%%", yearTwoAdapter.getWGPa()));
                wGpaLetterTwo.setText(converterAndColour.determineLetterGrade(yearTwoAdapter.getWGPa()));
                wGpaScaleTwo.setText(String.format("%.1f", converterAndColour.determineGPAGrade(yearTwoAdapter.getWGPa())));
                // set progress bar
                progressBarTwo.setStyle(converterAndColour.determineColour(yearTwoAdapter.getWGPa()));
                progressBarTwo.setProgress((double) yearTwoAdapter.getWGPa() / 100);
            } else {
                wGpaLblTwo.setText("TBD");
                yearTwoChangeBtn.setDisable(true);
                yearTwoRadioBtn.setDisable(true);
                noDataTwoLbl.setVisible(true);
            }
            if (!Double.isNaN(yearTwoAdapter.getTermGpa(1))) {
                firstTermLblTwo.setText(String.format("First Term GPA: %.2f%%", yearTwoAdapter.getTermGpa(1)));
            } else {
                firstTermLblTwo.setText("First Term GPA: TBD");
            }
            if (!Double.isNaN(yearTwoAdapter.getTermGpa(2))) {
                secondTermLblTwo.setText(String.format("Second Term GPA: %.2f%%", yearTwoAdapter.getTermGpa(2)));
            } else {
                secondTermLblTwo.setText("Second Term GPA: TBD");
            }
            if (!Double.isNaN(yearTwoAdapter.getTermGpa(3))) {
                summerTermLblTwo.setText(String.format("Summer Term GPA: %.2f%%", yearTwoAdapter.getTermGpa(3)));
            } else {
                summerTermLblTwo.setText("Summer Term GPA: TBD");
            }
            if (!Double.isNaN(yearTwoAdapter.getOneAndTwoTermsGPa())) {
                exclSummerLblTwo.setText(String.format("GPA excl. Summer Term: %.2f%%", yearTwoAdapter.getOneAndTwoTermsGPa()));
            } else {
                exclSummerLblTwo.setText("GPA excl. Summer Term: TBD");
            }
            try {
                highestLblTwo.setText(String.format("Highest Grade: %.2f%%", Collections.max(yearTwoAdapter.returnListOfGrades())));
            } catch (Exception e) {
                highestLblTwo.setText("Highest Grade: TBD");
            }
            try {
                lowestLblTwo.setText(String.format("Lowest Grade: %.2f%%", Collections.min(yearTwoAdapter.returnListOfGrades())));
            } catch (Exception e) {
                lowestLblTwo.setText("Lowest Grade: TBD");
            }
            if (!Double.isNaN(yearTwoAdapter.getTotalCredits())) {
                totalCredLblTwo.setText(String.format("Total Credits: %.1f", yearTwoAdapter.getTotalCredits()));
            } else {
                totalCredLblTwo.setText("Total Credits: TBD");
            }

        } else if (year == 3) {
            if (!Double.isNaN(yearThreeAdapter.getWGPa())) {
                yearThreeChangeBtn.setDisable(false);
                yearThreeRadioBtn.setDisable(false);
                // set wGpa labels
                wGpaLblThree.setText(String.format("%.2f%%", yearThreeAdapter.getWGPa()));
                wGpaLetterThree.setText(converterAndColour.determineLetterGrade(yearThreeAdapter.getWGPa()));
                wGpaScaleThree.setText(String.format("%.1f", converterAndColour.determineGPAGrade(yearThreeAdapter.getWGPa())));
                // set progress bar
                progressBarThree.setStyle(converterAndColour.determineColour(yearThreeAdapter.getWGPa()));
                progressBarThree.setProgress((double) yearThreeAdapter.getWGPa() / 100);
            } else {
                wGpaLblThree.setText("TBD");
                yearThreeChangeBtn.setDisable(true);
                yearThreeRadioBtn.setDisable(true);
                noDataThreeLbl.setVisible(true);
            }
            if (!Double.isNaN(yearThreeAdapter.getTermGpa(1))) {
                firstTermLblThree.setText(String.format("First Term GPA: %.2f%%", yearThreeAdapter.getTermGpa(1)));
            } else {
                firstTermLblThree.setText("First Term GPA: TBD");
            }
            if (!Double.isNaN(yearThreeAdapter.getTermGpa(2))) {
                secondTermLblThree.setText(String.format("Second Term GPA: %.2f%%", yearThreeAdapter.getTermGpa(2)));
            } else {
                secondTermLblThree.setText("Second Term GPA: TBD");
            }
            if (!Double.isNaN(yearThreeAdapter.getTermGpa(3))) {
                summerTermLblThree.setText(String.format("Summer Term GPA: %.2f%%", yearThreeAdapter.getTermGpa(3)));
            } else {
                summerTermLblThree.setText("Summer Term GPA: TBD");
            }
            if (!Double.isNaN(yearThreeAdapter.getOneAndTwoTermsGPa())) {
                exclSummerLblThree.setText(String.format("GPA excl. Summer Term: %.2f%%", yearThreeAdapter.getOneAndTwoTermsGPa()));
            } else {
                exclSummerLblThree.setText("GPA excl. Summer Term: TBD");
            }
            try {
                highestLblThree.setText(String.format("Highest Grade: %.2f%%", Collections.max(yearThreeAdapter.returnListOfGrades())));
            } catch (Exception e) {
                highestLblThree.setText("Highest Grade: TBD");
            }
            try {
                lowestLblThree.setText(String.format("Lowest Grade: %.2f%%", Collections.min(yearThreeAdapter.returnListOfGrades())));
            } catch (Exception e) {
                lowestLblThree.setText("Lowest Grade: TBD");
            }
            if (!Double.isNaN(yearThreeAdapter.getTotalCredits())) {
                totalCredLblThree.setText(String.format("Total Credits: %.1f", yearThreeAdapter.getTotalCredits()));
            } else {
                totalCredLblThree.setText("Total Credits: TBD");
            }

        } else if (year == 4) {
            if (!Double.isNaN(yearFourAdapter.getWGPa())) {
                yearFourChangeBtn.setDisable(false);
                yearFourRadioBtn.setDisable(false);
                // set wGpa labels
                wGpaLblFour.setText(String.format("%.2f%%", yearFourAdapter.getWGPa()));
                wGpaLetterFour.setText(converterAndColour.determineLetterGrade(yearFourAdapter.getWGPa()));
                wGpaScaleFour.setText(String.format("%.1f", converterAndColour.determineGPAGrade(yearFourAdapter.getWGPa())));
                // set progress bar
                progressBarFour.setStyle(converterAndColour.determineColour(yearFourAdapter.getWGPa()));
                progressBarFour.setProgress((double) yearFourAdapter.getWGPa() / 100);
            } else {
                wGpaLblFour.setText("TBD");
                yearFourChangeBtn.setDisable(true);
                yearFourRadioBtn.setDisable(true);
                noDataFourLbl.setVisible(true);
            }
            if (!Double.isNaN(yearFourAdapter.getTermGpa(1))) {
                firstTermLblFour.setText(String.format("First Term GPA: %.2f%%", yearFourAdapter.getTermGpa(1)));
            } else {
                firstTermLblFour.setText("First Term GPA: TBD");
            }
            if (!Double.isNaN(yearFourAdapter.getTermGpa(2))) {
                secondTermLblFour.setText(String.format("Second Term GPA: %.2f%%", yearFourAdapter.getTermGpa(2)));
            } else {
                secondTermLblFour.setText("Second Term GPA: TBD");
            }
            if (!Double.isNaN(yearFourAdapter.getTermGpa(3))) {
                summerTermLblFour.setText(String.format("Summer Term GPA: %.2f%%", yearFourAdapter.getTermGpa(3)));
            } else {
                summerTermLblFour.setText("Summer Term GPA: TBD");
            }
            if (!Double.isNaN(yearFourAdapter.getOneAndTwoTermsGPa())) {
                exclSummerLblFour.setText(String.format("GPA excl. Summer Term: %.2f%%", yearFourAdapter.getOneAndTwoTermsGPa()));
            } else {
                exclSummerLblFour.setText("GPA excl. Summer Term: TBD");
            }
            try {
                highestLblFour.setText(String.format("Highest Grade: %.2f%%", Collections.max(yearFourAdapter.returnListOfGrades())));
            } catch (Exception e) {
                highestLblFour.setText("Highest Grade: TBD");
            }
            try {
                lowestLblFour.setText(String.format("Lowest Grade: %.2f%%", Collections.min(yearFourAdapter.returnListOfGrades())));
            } catch (Exception e) {
                lowestLblFour.setText("Lowest Grade: TBD");
            }
            if (!Double.isNaN(yearFourAdapter.getTotalCredits())) {
                totalCredLblFour.setText(String.format("Total Credits: %.1f", yearFourAdapter.getTotalCredits()));
            } else {
                totalCredLblFour.setText("Total Credits: TBD");
            }

        }

    }

    // method for button animations
    public void buildHoverAnimations() {
        // set idle styles
        yearOneChangeBtn.setStyle(IDLE_BUTTON_STYLE);
        yearTwoChangeBtn.setStyle(IDLE_BUTTON_STYLE);
        yearThreeChangeBtn.setStyle(IDLE_BUTTON_STYLE);
        yearFourChangeBtn.setStyle(IDLE_BUTTON_STYLE);
        calculateBtn.setStyle(IDLE_BUTTON_STYLE);
        resetBtn.setStyle(IDLE_BUTTON_STYLE);
        yearOneRadioBtn.setStyle(IDLE_RADIO_STYLE);
        yearTwoRadioBtn.setStyle(IDLE_RADIO_STYLE);
        yearThreeRadioBtn.setStyle(IDLE_RADIO_STYLE);
        yearFourRadioBtn.setStyle(IDLE_RADIO_STYLE);

        // set on mouse entered styles
        yearOneChangeBtn.setOnMouseEntered(e -> yearOneChangeBtn.setStyle(HOVERED_BUTTON_STYLE));
        yearTwoChangeBtn.setOnMouseEntered(e -> yearTwoChangeBtn.setStyle(HOVERED_BUTTON_STYLE));
        yearThreeChangeBtn.setOnMouseEntered(e -> yearThreeChangeBtn.setStyle(HOVERED_BUTTON_STYLE));
        yearFourChangeBtn.setOnMouseEntered(e -> yearFourChangeBtn.setStyle(HOVERED_BUTTON_STYLE));
        calculateBtn.setOnMouseEntered(e -> calculateBtn.setStyle(HOVERED_BUTTON_STYLE));
        resetBtn.setOnMouseEntered(e -> resetBtn.setStyle(HOVERED_BUTTON_STYLE));
        yearOneRadioBtn.setOnMouseEntered(e -> yearOneRadioBtn.setStyle(HOVERED_RADIO_STYLE));
        yearTwoRadioBtn.setOnMouseEntered(e -> yearTwoRadioBtn.setStyle(HOVERED_RADIO_STYLE));
        yearThreeRadioBtn.setOnMouseEntered(e -> yearThreeRadioBtn.setStyle(HOVERED_RADIO_STYLE));
        yearFourRadioBtn.setOnMouseEntered(e -> yearFourRadioBtn.setStyle(HOVERED_RADIO_STYLE));

        // set on mouse exited styles back to idle styles
        yearOneChangeBtn.setOnMouseExited(e -> yearOneChangeBtn.setStyle(IDLE_BUTTON_STYLE));
        yearTwoChangeBtn.setOnMouseExited(e -> yearTwoChangeBtn.setStyle(IDLE_BUTTON_STYLE));
        yearThreeChangeBtn.setOnMouseExited(e -> yearThreeChangeBtn.setStyle(IDLE_BUTTON_STYLE));
        yearFourChangeBtn.setOnMouseExited(e -> yearFourChangeBtn.setStyle(IDLE_BUTTON_STYLE));
        calculateBtn.setOnMouseExited(e -> calculateBtn.setStyle(IDLE_BUTTON_STYLE));
        resetBtn.setOnMouseExited(e -> resetBtn.setStyle(IDLE_BUTTON_STYLE));
        yearOneRadioBtn.setOnMouseExited(e -> yearOneRadioBtn.setStyle(IDLE_RADIO_STYLE));
        yearTwoRadioBtn.setOnMouseExited(e -> yearTwoRadioBtn.setStyle(IDLE_RADIO_STYLE));
        yearThreeRadioBtn.setOnMouseExited(e -> yearThreeRadioBtn.setStyle(IDLE_RADIO_STYLE));
        yearFourRadioBtn.setOnMouseExited(e -> yearFourRadioBtn.setStyle(IDLE_RADIO_STYLE));
    }

    // method for select all
    public void selectAll() {

        if (selectAllBtn.isSelected()) {

            // select radio buttons (only if there is data for that year)
            if (!noDataOneLbl.isVisible()) {
                yearOneRadioBtn.setSelected(true);
                calculateBtn.setDisable(false);
            }
            if (!noDataTwoLbl.isVisible()) {
                yearTwoRadioBtn.setSelected(true);
                calculateBtn.setDisable(false);
            }
            if (!noDataThreeLbl.isVisible()) {
                yearThreeRadioBtn.setSelected(true);
                calculateBtn.setDisable(false);
            }
            if (!noDataFourLbl.isVisible()) {
                yearFourRadioBtn.setSelected(true);
                calculateBtn.setDisable(false);
            }


        } else {

            // deselect every button
            yearOneRadioBtn.setSelected(false);
            yearTwoRadioBtn.setSelected(false);
            yearThreeRadioBtn.setSelected(false);
            yearFourRadioBtn.setSelected(false);

            // disable calculate button
            calculateBtn.setDisable(true);

        }

    }

    // reset method
    public void reset() {
        // deselect every button
        yearOneRadioBtn.setSelected(false);
        yearTwoRadioBtn.setSelected(false);
        yearThreeRadioBtn.setSelected(false);
        yearFourRadioBtn.setSelected(false);

        selectAllBtn.setSelected(false);

        // reset labels
        calculatedGpaLbl.setText("N/A");
        calculatedLetterLbl.setText("");
        calculatedScaleLbl.setText("");

        // disable button
        calculateBtn.setDisable(true);
    }

    // method to enable calculate button when a radio button is selected
    public void enableCalculate() {
        if (yearOneRadioBtn.isSelected() || yearTwoRadioBtn.isSelected() || yearThreeRadioBtn.isSelected() ||
                yearFourRadioBtn.isSelected()) {
            calculateBtn.setDisable(false);
        } else {
            calculateBtn.setDisable(true);
        }
    }


    // method for when calculate is clicked
    public void onCalculateClick() throws SQLException {

        // get lists of all class names
        ObservableList<String> yearOneCourseList = yearOneAdapter.returnListOfCourseNames();
        ObservableList<String> yearTwoCourseList = yearTwoAdapter.returnListOfCourseNames();
        ObservableList<String> yearThreeCourseList = yearThreeAdapter.returnListOfCourseNames();
        ObservableList<String> yearFourCourseList = yearFourAdapter.returnListOfCourseNames();

        double totalCredits = 0;
        double totalAccumulation = 0;

        // loop over first year and add grades * corresponding weights if first year is selected
        if (yearOneRadioBtn.isSelected()) {
            for (String course : yearOneCourseList) {
                totalAccumulation += (yearOneAdapter.getPercentGrade(course) * yearOneAdapter.getWeight(course));
            }
            // add to total credits
            totalCredits += yearOneAdapter.getTotalCredits();
        }
        // loop over second year and add grades * corresponding weights if second year is selected
        if (yearTwoRadioBtn.isSelected()) {
            // loop over second year and add grades * corresponding weights
            for (String course : yearTwoCourseList) {
                totalAccumulation += (yearTwoAdapter.getPercentGrade(course) * yearTwoAdapter.getWeight(course));
            }
            // add to total credits
            totalCredits += yearTwoAdapter.getTotalCredits();
        }
        // loop over third year and add grades * corresponding weights if third year is selected
        if (yearThreeRadioBtn.isSelected()) {
            // loop over third year and add grades * corresponding weights
            for (String course : yearThreeCourseList) {
                totalAccumulation += (yearThreeAdapter.getPercentGrade(course) * yearThreeAdapter.getWeight(course));
            }
            // add to total credits
            totalCredits += yearThreeAdapter.getTotalCredits();
        }
        // loop over fourth year and add grades * corresponding weights if fourth year is selected
        if (yearFourRadioBtn.isSelected()) {
            // loop over fourth year and add grades * corresponding weights
            for (String course : yearFourCourseList) {
                totalAccumulation += (yearFourAdapter.getPercentGrade(course) * yearFourAdapter.getWeight(course));
            }
            // add to total credits
            totalCredits += yearFourAdapter.getTotalCredits();
        }

        // set labels
        calculatedGpaLbl.setText(String.format("%.2f", totalAccumulation / totalCredits));
        calculatedLetterLbl.setText(converterAndColour.determineLetterGrade(totalAccumulation / totalCredits));
        calculatedScaleLbl.setText(String.format("%.1f", converterAndColour.determineGPAGrade(totalAccumulation / totalCredits)));
    }

}

package com.example.educationstorage;

public class ConverterAndColour {

    // method to return the colour of the progress bar based on the grade
    public String determineColour(double gradePercent) {

        if (gradePercent >= 90) {
            return "-fx-accent: green";
        } else if (gradePercent < 90 && gradePercent >= 80) {
            return "-fx-accent: lightgreen";
        } else if (gradePercent < 80 && gradePercent >= 70) {
            return "-fx-accent: yellow";
        } else if (gradePercent < 70 && gradePercent >= 60) {
            return "-fx-accent: orange";
        } else {
            return "-fx-accent: red";
        }

    }

    // method to determine letter grade
    public String determineLetterGrade(double gradePercent) {
        if (gradePercent >= 90) {
            return "A+";
        } else if (gradePercent < 90 && gradePercent >= 80) {
            return "A";
        } else if (gradePercent < 80 && gradePercent >= 70) {
            return "B";
        } else if (gradePercent < 70 && gradePercent >= 60) {
            return "C";
        } else if (gradePercent < 60 && gradePercent >= 50) {
            return "D";
        } else if (gradePercent < 50 && gradePercent >= 40) {
            return "E";
        } else if (Double.isNaN(gradePercent)){
            return "TBD";
        } else {
            return "F";
        }
    }

    // method to determine 4.0 grade
    public double determineGPAGrade(double gradePercent) {
        if (gradePercent >= 90) {
            return 4.0;
        } else if (gradePercent < 90 && gradePercent >= 85) {
            return 3.9;
        } else if (gradePercent < 85 && gradePercent >= 80) {
            return 3.7;
        } else if (gradePercent < 80 && gradePercent >= 77) {
            return 3.3;
        } else if (gradePercent < 77 && gradePercent >= 73) {
            return 3;
        } else if (gradePercent < 73 && gradePercent >= 70) {
            return 2.7;
        } else if (gradePercent < 70 && gradePercent >= 67) {
            return 2.3;
        } else if (gradePercent < 67 && gradePercent >= 63) {
            return 2.0;
        } else if (gradePercent < 63 && gradePercent >= 60) {
            return 1.7;
        } else if (gradePercent < 60 && gradePercent >= 57) {
            return 1.3;
        } else if (gradePercent < 57 && gradePercent >= 53) {
            return 1.0;
        } else if (gradePercent < 53 && gradePercent >= 50) {
            return 0.7;
        } else if (Double.isNaN(gradePercent)) {
            return 5.0;
        } else {
            return 0;
        }
    }
}


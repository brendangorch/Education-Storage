package com.example.educationstorage;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class YearOneAdapter {

    // create a connection
    private Connection connection;

    public YearOneAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;

        Statement stmt = connection.createStatement();
        if (reset) {
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE YearOne");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        }

        try {
            // Create the table
            stmt.execute("CREATE TABLE YearOne ("
                    + "ID INT NOT NULL PRIMARY KEY,"
                    + "CourseName VARCHAR(50) NOT NULL,"
                    + "Term INT NOT NULL,"
                    + "PercentGrade DOUBLE NOT NULL,"
                    + "Weight DOUBLE NOT NULL"
                    + ")");

        } catch (SQLException ex) {
            // No need to report an error.
            // The table exists and may have some data.
        }
    }

    // method to insert a course into the database
    public void insertCourse(int id, String courseName, int term, double percentGrade, double weight) throws SQLException {
        Statement stmt = connection.createStatement();

        // insert the values into the table
        stmt.executeUpdate("INSERT INTO YearOne (ID, CourseName, Term, PercentGrade, Weight) VALUES (" + id +
                ", '" + courseName + "' , " + term + " , " + percentGrade + " , " + weight + ")");
    }

    // method to delete a course from the database
    public void deleteCourse(String courseName) throws SQLException {
        // create statement
        Statement stmt = connection.createStatement();
        // create sqlStatement string formatted to delete the course from the table
        String sqlStatement = "DELETE FROM YearOne WHERE CourseName = " + "'" + courseName + "'";
        // execute the update
        stmt.execute(sqlStatement);
    }

    // method to return the highest id number in the table
    public int getMaxId() throws SQLException {
        int num = 0;

        // create statement
        Statement stmt = connection.createStatement();
        // create sqlStatement for max id in the table
        String sqlStatement =  "SELECT MAX(ID) FROM YearOne";
        // execute query with sqlStatement
        ResultSet result = stmt.executeQuery(sqlStatement);
        if (result.next()) {
            // increment the number
            num = result.getInt(1);
        }
        return num;
    }

    // method to return a list of all course names in the database
    public ObservableList<String> returnListOfCourseNames() throws SQLException {
        ObservableList<String> courseNamesList = FXCollections.observableArrayList();
        ResultSet result;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM YearOne";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update list
        while (result.next()) {
            courseNamesList.add(result.getString("CourseName"));
        }
        return courseNamesList;
    }

    // method to check if a course name already exists
    public boolean courseNameTaken(String courseName) throws SQLException {
        // Create a Statement object
        Statement stmt = connection.createStatement();
        ResultSet result;
        boolean exists = false;

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT CourseName FROM YearOne";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // iterate over course names
        while (result.next()) {
            // if the course name exists, set boolean to true
            if(result.getString("CourseName").equals(courseName)){
                exists = true;
            }
        }
        return exists;
    }

    // method to get percent grade from course name
    public double getPercentGrade(String courseName) throws SQLException {
        // Create a Statement object
        Statement stmt = connection.createStatement();
        ResultSet result;
        double percentGrade = 0;

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT PercentGrade FROM YearOne WHERE CourseName = " + "'" + courseName + "'";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // get the grade from the result list
        if (result.next()) {
            percentGrade = result.getDouble("PercentGrade");
        }
        return percentGrade;
    }

    // method to get term number from course name
    public int getTermNumber(String courseName) throws SQLException {
        // Create a Statement object
        Statement stmt = connection.createStatement();
        ResultSet result;
        int termNumber = 0;

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT Term FROM YearOne WHERE CourseName = " + "'" + courseName + "'";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // get the term from the result list
        if (result.next()) {
            termNumber = result.getInt("Term");
        }
        return termNumber;
    }

    // method to get ID from course name
    public int getId(String courseName) throws SQLException {
        // Create a Statement object
        Statement stmt = connection.createStatement();
        ResultSet result;
        int id = 0;

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT ID FROM YearOne WHERE CourseName = " + "'" + courseName + "'";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // get the term from the result list
        if (result.next()) {
            id = result.getInt(1);
        }
        return id;
    }

    // method to get weight from course name
    public double getWeight(String courseName) throws SQLException {
        // Create a Statement object
        Statement stmt = connection.createStatement();
        ResultSet result;
        double weight = 0;

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT Weight FROM YearOne WHERE CourseName = " + "'" + courseName + "'";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // get the term from the result list
        if (result.next()) {
            weight = result.getDouble("Weight");
        }
        return weight;
    }

    // method to change course weight from its name
    public void changeWeight(String courseName, double newWeight) throws SQLException {
        // create statement
        Statement stmt = connection.createStatement();
        // create sqlStatement string formatted to update the course weight
        String sqlStatement = "UPDATE YearOne SET Weight =" + newWeight + "WHERE CourseName =" +
                "'" + courseName + "'";
        // execute update
        stmt.executeUpdate(sqlStatement);
    }

    // method to change the name of a course given its current name
    public void changeCourseName(String currentName, String newName) throws SQLException {
        // create statement
        Statement stmt = connection.createStatement();
        // create sqlStatement string formatted to update the course name
        String sqlStatement = "UPDATE YearOne SET CourseName  =" + "'" + newName + "'" + "WHERE CourseName =" +
                "'" + currentName + "'";
        // execute update
        stmt.executeUpdate(sqlStatement);
    }

    // method to change the grade of a course given its name
    public void changePercentGrade(String courseName, double newGrade) throws SQLException {
        // create statement
        Statement stmt = connection.createStatement();
        // create sqlStatement string formatted to update the course grade
        String sqlStatement = "UPDATE YearOne SET PercentGrade =" + newGrade + "WHERE CourseName =" +
                "'" + courseName + "'";
        // execute update
        stmt.executeUpdate(sqlStatement);
    }

    // method to get weight from ID
    public double getWeightFromId(int Id) throws SQLException {
        // Create a Statement object
        Statement stmt = connection.createStatement();
        ResultSet result;
        double weight = 0;

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT Weight FROM YearOne WHERE ID = " + Id;

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // get the term from the result list
        if (result.next()) {
            weight = result.getDouble("Weight");
        }
        return weight;
    }

    public double getWGPa() throws SQLException {

        ResultSet result;

        double accumulation = 0;
        double WGpa;
        int currentId = 1;
        double totalClasses = 0;


        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM YearOne";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update accumulation and total classes
        while (result.next()) {
            double currentWeight = getWeightFromId(currentId);
            double currentGrade = result.getDouble("PercentGrade") * currentWeight;
            accumulation += currentGrade;
            totalClasses += currentWeight;
            currentId++;
        }
        WGpa = accumulation / totalClasses;
        return WGpa;
    }

    // method to get a specific term's gpa
    public double getTermGpa(int term) throws SQLException {
        ResultSet result;

        double accumulation = 0;
        double TermGpa;
        int currentId = 1;
        double totalClasses = 0;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM YearOne";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update accumulation and total classes
        while (result.next()) {
            if (result.getInt("Term") == term) {
                double currentWeight = getWeightFromId(currentId);
                double currentGrade = result.getDouble("PercentGrade") * currentWeight;
                accumulation += currentGrade;
                totalClasses += currentWeight;
            }
            currentId++;
        }
        TermGpa = accumulation / totalClasses;
        return TermGpa;
    }

    // method to get gpa from first two terms
    public double getOneAndTwoTermsGPa() throws SQLException {
        ResultSet result;

        double accumulation = 0;
        double TermGpa;
        int currentId = 1;
        double totalClasses = 0;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM YearOne";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update accumulation and total classes
        while (result.next()) {
            if (result.getInt("Term") == 1 || result.getInt("Term") == 2) {
                double currentWeight = getWeightFromId(currentId);
                double currentGrade = result.getDouble("PercentGrade") * currentWeight;
                accumulation += currentGrade;
                totalClasses += currentWeight;
            }
            currentId++;
        }
        TermGpa = accumulation / totalClasses;
        return TermGpa;
    }

    // method to get all grades
    public ObservableList<Double> returnListOfGrades() throws SQLException {
        ObservableList<Double> gradesList = FXCollections.observableArrayList();
        ResultSet result;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM YearOne";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        // loop for the all rs rows and update list
        while (result.next()) {
            gradesList.add(result.getDouble("PercentGrade"));
        }
        return gradesList;
    }

    // method to get total credits in the year
    public double getTotalCredits() throws SQLException {
        ResultSet result;
        double totalCredits = 0;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM YearOne";

        // Execute the statement and return the result
        result = stmt.executeQuery(sqlStatement);

        while (result.next()) {
            totalCredits += result.getDouble("Weight");
        }
        return totalCredits;
    }
}

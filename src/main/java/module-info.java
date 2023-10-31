module com.example.educationstorage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.educationstorage to javafx.fxml;
    exports com.example.educationstorage;
}
module com.example.collisons {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.collisons to javafx.fxml;
    exports com.example.collisons;
}
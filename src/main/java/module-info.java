module com.example.eventhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.security.jgss;


    opens com.example.eventhub to javafx.fxml;
    exports com.example.eventhub;
}
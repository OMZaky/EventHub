module com.example.eventhub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eventhub to javafx.fxml;
    exports com.example.eventhub;
}
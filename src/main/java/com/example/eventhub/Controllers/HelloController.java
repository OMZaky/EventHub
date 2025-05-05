package com.example.eventhub;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;

public class HelloController extends Region {
    public Button button1;
    @FXML
    private Label welcomeText;
    @FXML private StackPane imageContainer; // Parent container
    @FXML private ImageView logoImage;      // ImageView
    @FXML private HBox RightHBox;
    @FXML private Line MyLine;
    @FXML private StackPane MyPane;

    public void initialize() {


        imageContainer.setMinSize(300,300);

        MyLine.startYProperty().bind(MyPane.heightProperty().multiply(0.2));
        MyLine.endYProperty().bind(MyPane.heightProperty().multiply(0.8));

        logoImage.fitWidthProperty().bind(
                imageContainer.widthProperty().multiply(0.8)
        );
        logoImage.fitHeightProperty().bind(
                imageContainer.heightProperty().multiply(0.8)
        );

        // Ensure the image preserves its aspect ratio
        logoImage.setPreserveRatio(true);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


}
package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class HelloController extends Region {
    public Button button1;
    @FXML
    private Label welcomeText;
    @FXML private StackPane imageContainer; // Parent container
    @FXML private ImageView logoImage;      // ImageView
    @FXML private HBox RightHBox;
    @FXML private Line MyLine;
    @FXML private StackPane MyPane;
    @FXML private TextField Username;
    @FXML private PasswordField Password;
    @FXML private VBox Username_vbox;
    @FXML private VBox Password_vbox;
    @FXML private Text welcometext;
    @FXML private JFXButton login_button;
    private String user;


    public void initialize() {

        login_button.disableProperty().bind(Username.textProperty().isEmpty().or(Password.textProperty().isEmpty()));
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

        Password.setOnKeyPressed(this::handleEnterKeyPress);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private void handleEnterKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            user = Person.LogIn(Username.getText(),Password.getText(), Username_vbox, Password_vbox);
        }
    }



    @FXML
    protected void loginbutton(){
        user = Person.LogIn(Username.getText(),Password.getText(), Username_vbox, Password_vbox);
        System.out.println(user);


    }


}
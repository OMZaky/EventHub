package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import static javafx.application.Platform.exit;


public class LoginController implements SceneController{
    @FXML
    private Label welcomeText;
    @FXML
    private StackPane imageContainer; // Parent container
    @FXML
    private ImageView logoImage;      // ImageView
    @FXML
    private HBox RightHBox;
    @FXML
    private Line MyLine;
    @FXML
    private StackPane MyPane;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private VBox Username_vbox;
    @FXML
    private VBox Password_vbox;
    @FXML
    private Text welcometext;
    @FXML
    private JFXButton login_button;
    @FXML
    private HBox rootPane;
    private Person user = null;
    private SceneManager sceneManager;


    public void initialize() {


        rootPane.setFocusTraversable(true);

        rootPane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                // Add event filter to handle clicks
                newScene.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    Node target = (Node) event.getTarget();
                    Node focusOwner = newScene.getFocusOwner();

                    // Check if focus is on a text input and click is outside
                    if (focusOwner instanceof TextInputControl && !isClickWithinInput(target)) {
                        rootPane.requestFocus(); // Remove focus
                    }
                });
            }
        });

        login_button.disableProperty().bind(Username.textProperty().isEmpty().or(Password.textProperty().isEmpty()));
        imageContainer.setMinSize(300, 300);

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
        Username.setOnKeyPressed(this::handleEnterKeyPressUser);
        Password.setOnKeyPressed(this::handleEnterKeyPressPass);
    }

    private boolean isClickWithinInput(Node target) {
        Node node = target;
        while (node != null) {
            if (node == Username || node == Password) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }


    private void handleEnterKeyPressPass(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginbutton();
        }
    }
    private void handleEnterKeyPressUser(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Password.requestFocus();
        }
    }


    @FXML
    protected void loginbutton() throws NullPointerException {
        user = Person.LogIn(Username.getText(), Password.getText(), Username_vbox, Password_vbox);

        if (user == null) {
            return;
        }
        switch (user) {
            case Attendee w -> sceneManager.switchToAttendeeHscreen(w);
            //case Organizer w -> sceneManager.switchToOrganizerHscreen(w);
            //case Admin w -> sceneManager.switchToAdminHscreen(w);
            default -> {
                showError("Error");
                exit(); // Only exit if absolutely necessary
            }
        }

        Username.setText("");
        Password.setText("");
        imageContainer.requestFocus();



    }

    @FXML
    public void RegisterButton(){
        sceneManager.switchToRegister();
    }


    // This is cool, might be useful
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;


    }
}
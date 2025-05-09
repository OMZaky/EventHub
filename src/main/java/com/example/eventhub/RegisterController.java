package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisterController implements SceneController {

    // FXML Components
    @FXML private Line MyLine;
    @FXML private StackPane MyPane;
    @FXML private JFXButton Register_Button;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField balance;
    @FXML private TextField address;
    @FXML private TextField trait1;
    @FXML private TextField trait2;
    @FXML private TextField trait3;
    @FXML private DatePicker DOB;
    @FXML private JFXRadioButton malebutton;
    @FXML private JFXRadioButton femalebutton;
    @FXML private VBox usernamevbox;

    // State Properties
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final BooleanProperty usernameValid = new SimpleBooleanProperty(false);
    private final PauseTransition validationPause = new PauseTransition(Duration.millis(500));

    private SceneManager sceneManager;

    public void initialize() {
        setupDatePicker();
        setupUsernameValidation();
        setupRadioButtons();
        setupBindings();
        setupBalanceValidation();
        setupLineBindings();
    }

    private void setupDatePicker() {
        DOB.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isAfter(LocalDate.now()));
            }
        });

        DOB.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && newValue.isAfter(LocalDate.now())) {
                DOB.setValue(oldValue);
            }
        });
    }

    private void setupUsernameValidation() {
        username.textProperty().addListener((obs, oldVal, newVal) -> {
            clearUsernameMessages();

            if (newVal.isEmpty()) {
                usernameValid.set(false);
                return;
            }

            validationPause.setOnFinished(e -> validateUsername(newVal));
            validationPause.stop();
            validationPause.playFromStart();
        });
    }

    private void validateUsername(String username) {
        boolean isValidFormat = username.matches("^[a-zA-Z0-9]{3,20}$");
        boolean isAvailable = Person.CheckUsername(username);

        Platform.runLater(() -> {
            Text validationText = new Text();
            validationText.setFont(Font.font("Arial", FontWeight.NORMAL, 12));

            if (!isValidFormat) {
                validationText.setText("(3-20) Characters");
                validationText.setFill(Color.ORANGE);
                usernameValid.set(false);
            } else if (!isAvailable) {
                validationText.setText("Username taken");
                validationText.setFill(Color.RED);
                usernameValid.set(false);
            } else {
                validationText.setText("Username available");
                validationText.setFill(Color.GREEN);
                usernameValid.set(true);
            }

            usernamevbox.getChildren().add(validationText);
        });
    }

    private void clearUsernameMessages() {
        usernamevbox.getChildren().removeIf(node -> node instanceof Text);
        usernameValid.set(false);
    }

    private void setupRadioButtons() {
        malebutton.setToggleGroup(toggleGroup);
        femalebutton.setToggleGroup(toggleGroup);
    }

    private void setupBindings() {
        BooleanBinding requiredFieldsEmpty = username.textProperty().isEmpty()
                .or(password.textProperty().isEmpty())
                .or(balance.textProperty().isEmpty())
                .or(address.textProperty().isEmpty())
                .or(toggleGroup.selectedToggleProperty().isNull());

        Register_Button.disableProperty().bind(requiredFieldsEmpty.or(usernameValid.not()));
    }

    private void setupBalanceValidation() {
        balance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                balance.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void setupLineBindings() {
        MyLine.startYProperty().bind(MyPane.heightProperty().multiply(0.1));
        MyLine.endYProperty().bind(MyPane.heightProperty().multiply(0.9));
    }

    @FXML
    private void RegisterButton() {
        try {
            Attendee attendee = new Attendee(
                    new Wallet(Integer.parseInt(balance.getText())),
                    ((JFXRadioButton) toggleGroup.getSelectedToggle()).getText().equalsIgnoreCase("Male")
                            ? Gender.MALE
                            : Gender.FEMALE,
                    address.getText(),
                    new ArrayList<>(List.of(
                            trait1.getText(),
                            trait2.getText(),
                            trait3.getText()
                    )),
                    username.getText(),
                    password.getText(),
                    DOB.getValue().getYear(),
                    DOB.getValue().getMonthValue(),
                    DOB.getValue().getDayOfMonth()
            );

            sceneManager.switchToAttendeeHscreen(attendee);
            clearForm();
        } catch (Exception e) {
            showErrorAlert("Registration Error", "Please check your inputs");
        }
    }

    private void clearForm() {
        username.clear();
        password.clear();
        balance.clear();
        address.clear();
        trait1.clear();
        trait2.clear();
        trait3.clear();
        DOB.setValue(null);
        toggleGroup.getSelectedToggle().setSelected(false);
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void BackButton() {
        sceneManager.switchToLogin();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
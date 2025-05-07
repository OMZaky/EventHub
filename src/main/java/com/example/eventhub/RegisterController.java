package com.example.eventhub;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class RegisterController implements SceneController {

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
    private ToggleGroup toggleGroup = new ToggleGroup();
    private RadioButton selectedRadioButton;

    private SceneManager sceneManager;

    public void initialize(){
        malebutton.setToggleGroup(toggleGroup);
        femalebutton.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedRadioButton = (RadioButton) newVal;
            }
        });

        MyLine.startYProperty().bind(MyPane.heightProperty().multiply(0.1));
        MyLine.endYProperty().bind(MyPane.heightProperty().multiply(0.9));

    }

    @FXML
    public void RegisterButton(){

        selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

        Attendee attendee = new Attendee(
                new Wallet(Integer.parseInt(balance.getText())),
                switch (selectedRadioButton.getText()){
                    case "Male" -> Gender.MALE;
                    case "Female" -> Gender.FEMALE;
                    default -> throw new IllegalStateException("Unexpected value: " + selectedRadioButton.getText());
                },
                address.getText(),
                new ArrayList<String>(List.of(trait1.getText(),trait2.getText(),trait3.getText())),
                username.getText(),
                password.getText(),
                DOB.getValue().getYear(),
                DOB.getValue().getMonthValue(),
                DOB.getValue().getDayOfMonth()

        );
        sceneManager.switchToAttendeeHscreen(attendee);
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;


    }
}


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
    @FXML private VBox usernamevbox;
    private ToggleGroup toggleGroup = new ToggleGroup();
    private Toggle selectedToggle;


    private RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

    private SceneManager sceneManager;

    public void initialize(){

        boolean valid = Person.ValidateUsername(username.getText(), usernamevbox);



        malebutton.setToggleGroup(toggleGroup);
        femalebutton.setToggleGroup(toggleGroup);

        Register_Button.disableProperty().bind(
                username.textProperty().isEmpty()
                        .or(password.textProperty().isEmpty())
                        .or(balance.textProperty().isEmpty())
                        .or(address.textProperty().isEmpty())
                        .or(toggleGroup.selectedToggleProperty().isNull())
        );

        balance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                balance.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        MyLine.startYProperty().bind(MyPane.heightProperty().multiply(0.1));
        MyLine.endYProperty().bind(MyPane.heightProperty().multiply(0.9));

    }

    @FXML
    public void RegisterButton(){

        selectedToggle = toggleGroup.getSelectedToggle();


        if(!Person.ValidateUsername(username.getText(), usernamevbox)) return;


        try{
            Attendee attendee = new Attendee(
                    new Wallet(Integer.parseInt(balance.getText())),
                    ((JFXRadioButton) selectedToggle).getText().equals("Male")
                            ? Gender.MALE
                            : Gender.FEMALE,
                    address.getText(),
                    new ArrayList<String>(List.of(trait1.getText(),trait2.getText(),trait3.getText())),
                    username.getText(),
                    password.getText(),
                    DOB.getValue().getYear(),
                    DOB.getValue().getMonthValue(),
                    DOB.getValue().getDayOfMonth()

            );
            sceneManager.switchToAttendeeHscreen(attendee);
        }catch (Exception e) {
            System.out.println("Error in Registration");
        }

        username.setText("");
        password.setText("");
        DOB.setValue(null);
        balance.setText("");
        address.setText("");
        trait1.setText("");
        trait2.setText("");
        trait3.setText("");
        malebutton.setSelected(false);
        femalebutton.setSelected(false);
    }

    public void BackButton(){
        sceneManager.switchToLogin();
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;


    }


}


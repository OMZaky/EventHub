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



public class RegisterController implements SceneController {

    @FXML private Line MyLine;
    @FXML private StackPane MyPane;

    private SceneManager sceneManager;

    public void initialize(){

        MyLine.startYProperty().bind(MyPane.heightProperty().multiply(0.1));
        MyLine.endYProperty().bind(MyPane.heightProperty().multiply(0.9));

    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;


    }
}


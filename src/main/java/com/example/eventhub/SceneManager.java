package com.example.eventhub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private Stage primaryStage;
    private Attendee_Dashboard attendeeDashboard;

    FXMLLoader fxmlRegister = new FXMLLoader(getClass().getResource("Register.fxml"));
    Scene RegisterScene = new Scene(fxmlRegister.load(), 320, 240);


    FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("Login.fxml"));
    Scene LoginScene = new Scene(fxmlLogin.load(), 320, 240);


    public SceneManager(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        initializeScenes();
    }

    private void initializeScenes() {
        // Initialize all scenes here
        attendeeDashboard = new Attendee_Dashboard(this);

    }

    public void switchToAttendeeWelcomeScreen() {
        primaryStage.setScene(attendeeDashboard.getScene());
    }

    public void switchToRegister() {
        primaryStage.setScene(RegisterScene);
    }

    public void switchToLogin() {
        primaryStage.setScene(LoginScene);
    }
}
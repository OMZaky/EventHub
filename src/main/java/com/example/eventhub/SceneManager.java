package com.example.eventhub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private Stage primaryStage;
    private Attendee_Dashboard attendeeDashboard;
    private Scene loginScene;
    private Scene registerScene;

//    FXMLLoader fxmlRegister = new FXMLLoader(getClass().getResource("Register.fxml"));
//    Scene RegisterScene = new Scene(fxmlRegister.load(), 320, 240);
//
//    LoginController RegisterController = fxmlRegister.getController();
//
//    {
//        RegisterController.setSceneManager(this);
//    }
//
//
//
//    FXMLLoader fxmlLogin = new FXMLLoader(getClass().getResource("Login.fxml"));
//    Scene LoginScene = new Scene(fxmlLogin.load(), 320, 240);
//
//    LoginController loginController = fxmlLogin.getController();
//
//    {
//        loginController.setSceneManager(this);
//    }

    public SceneManager(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        initializeScenes();
    }

    private void initializeScenes() throws IOException {
        // Load Login Scene
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent loginRoot = loginLoader.load();
        LoginController loginController = loginLoader.getController();
        loginController.setSceneManager(this);
        loginScene = new Scene(loginRoot, 320, 240);

        // Load Register Scene
        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent registerRoot = registerLoader.load();
        RegisterController registerController = registerLoader.getController();
        registerController.setSceneManager(this);
        registerScene = new Scene(registerRoot, 320, 240);

        // Initialize other scenes
        attendeeDashboard = new Attendee_Dashboard(this);
    }

    public void switchToAttendeeDashboard() {
        primaryStage.setScene(attendeeDashboard.getScene());
    }

    public void switchToRegister() {
        primaryStage.setScene(registerScene);
    }

    public void switchToLogin() {
        primaryStage.setScene(loginScene);
    }

}
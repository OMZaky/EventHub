package com.example.eventhub;

import javafx.fxml.FXML;

import static javafx.application.Platform.exit;

public class LogoutController implements FxmlSceneController{

    private SceneManager sceneManager;
    private Person person;

    @FXML
    private void LogoutButton(){
        sceneManager.switchToLogin();
    }

    @FXML
    private void GoBackButton(){

        switch (person) {
            case Attendee w -> sceneManager.switchToAttendeeHscreen(w);
            case Organizer w -> sceneManager.switchToOrganizerHscreen(w);
            case Admin w -> sceneManager.switchToAdminHscreen(w);
            default -> {
                exit(); // Only exit if absolutely necessary
            }
        }
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

    }
}

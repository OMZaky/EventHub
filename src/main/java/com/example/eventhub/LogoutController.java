package com.example.eventhub;

import static javafx.application.Platform.exit;

public class LogoutController implements SceneController{

    private SceneManager sceneManager;
    private Person person;

    private void LogoutButton(){
        sceneManager.switchToLogin();
    }

    private void GoBackButton(){

        switch (person) {
            case Attendee w -> sceneManager.switchToAttendeeHscreen(w);
            //case Organizer w -> sceneManager.switchToOrganizerHscreen(w);
            //case Admin w -> sceneManager.switchToAdminHscreen(w);
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

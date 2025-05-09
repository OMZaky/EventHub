package com.example.eventhub;

import javafx.scene.Scene;

public class AttendeeSearch {
    private Attendee a;
    private Scene root;

    public Scene getScene() {
        return root;
    }

    public AttendeeSearch(Attendee attendee, SceneManager sceneManager){
        this.a = attendee;



    }
}

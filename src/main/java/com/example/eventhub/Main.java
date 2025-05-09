package com.example.eventhub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = new SceneManager(stage);
        sceneManager.switchToLogin(); // Start with the login scene
        stage.setTitle("APP");
        stage.setWidth(700);
        stage.setHeight(450);
        stage.setMinHeight(400);
        stage.setMinWidth(650);
        stage.show();
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();


        calendar1.set(2025, Calendar.JULY, 4);
        calendar2.set(2025, Calendar.AUGUST, 15);
        calendar3.set(2025, Calendar.SEPTEMBER, 27);



         Attendee attendee1 = new Attendee(new Wallet(25000), Gender.MALE, "Nasr City", new ArrayList<>(List.of("Birthdays", "Football", "Tv Shows")), "Mohamed", "MyPassword", 1, 1, 2000);
         Attendee attendee2 = new Attendee(new Wallet(20000), Gender.FEMALE, "Tagamo3", new ArrayList<>(List.of("Wedding", "Movies", "Skating")), "Mariam", "MyPassword", 2, 2, 2000);
         Attendee attendee3 = new Attendee(new Wallet(15000), Gender.MALE, "Rehab", new ArrayList<>(List.of("Parties", "Snorkling", "Bachelor Party")), "Omar", "123", 5, 5, 2000);

         Room room1 = new Room(100);
         Room room2 = new Room(60);
         Room room3 = new Room(30);

         Categories Wedding = new Categories("Wedding", null);
         Categories Birthday = new Categories("Birthday Party", null);
         Categories Conference = new Categories("Conference", null);

         Organizer organizer1 = new Organizer("Maged", "MyPassword", 3, 3, 2000, 50000);
         Organizer organizer2 = new Organizer("Farah", "MyPassword", 4, 4, 2000, 40000);
         Organizer organizer3 = new Organizer("Sarah", "MyPassword", 6, 6, 2000, 30000);

         Admin admin = new Admin("Noah", "123", "Developer", 1990, 5, 5, 8, 18);


         Event event1 = new Event("Ali's Wedding", Wedding, 1000, calendar1, room1, organizer1, "Morning");
         Event event2 = new Event("John's Birthday", Birthday, 500, calendar2, room3, organizer2, "Morning");
         Event event3 = new Event("Siemens Conference", Conference, 700, calendar3, room2, organizer3, "Morning");



         Database.people.addAll(Arrays.asList(attendee1, attendee2, attendee3, organizer1, organizer2, organizer3, admin));
         Database.events.addAll(Arrays.asList(event1, event2, event3));
         Database.rooms.addAll(Arrays.asList(room1, room2, room3));
         Database.categories.addAll(Arrays.asList(Wedding, Birthday, Conference));




        launch();
    }
}
package com.example.eventhub;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public abstract class Person {
    
    private String username;
    private String password;
    private final Calendar dateOfBirth = Calendar.getInstance() ;
    protected boolean loggedIn;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private String formattedDate;
    
    Person(){
    }
    
    Person(String username,String password, int yearOfBirth, int monthOfBirth, int dayOfBirth){

        this.username = username;
        this.loggedIn = false;
        this.password = password;
        this.dateOfBirth.set(Calendar.YEAR, yearOfBirth);
        this.dateOfBirth.set(Calendar.MONTH, monthOfBirth - 1);
        this.dateOfBirth.set(Calendar.DAY_OF_MONTH, dayOfBirth);
        this.formattedDate = dateFormat.format(this.dateOfBirth.getTime());


    }

    public String getFormattedbirthDate() {
        if (formattedDate == null) {
            return dateFormat.format(dateOfBirth.getTime());
        }
        return formattedDate;
    }
    
    protected String getUsername(){
    return this.username;
    }
    protected String getPassword(){
    return this.password;
    }
    protected boolean getLoggedIn(){
    return this.loggedIn;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Person enemy) {
            return this.username.equals(enemy.username);
        } else {
            return false;
        }
    }
    /*
    public static String LogIn(String username, String password, Text text){

        while(true) {
            Person foundUser = null;
            for (Person p : Database.people) {
                if (username.equals(p.username)) {
                    foundUser = p;
                    break;
                }
            }
            if (foundUser == null) {
                text.setText("Wrong Username");
                text.setFill(Color.RED);
                //continue;
            }

            PasswordCheck(foundUser , password, text);
            if (foundUser.loggedIn) {
                switch (foundUser) {
                    case Attendee w -> {return "Attendee";}
                    case Organizer w -> {return "Organizer";}
                    case Admin w -> {return "Admin";}
                    default -> {
                        System.out.println("Error 404");
                        return null;
                    }
                }
            }

        }
    }

     */




    public static final Person LogIn(String username, String password,VBox pwUsername,VBox pwPassword){

        pwUsername.getChildren().removeIf(node -> node instanceof Text);
        pwPassword.getChildren().removeIf(node -> node instanceof Text);

            Person foundUser = null;
            for (Person p : Database.people) {
                if (username.equalsIgnoreCase(p.username)) {
                    foundUser = p;
                    break;
                }
            }
            if (foundUser == null) {
                Text text = new Text("Username Not Found");
                text.setFill(Color.RED);
                text.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
                pwUsername.getChildren().add(text);
                return null;
            }

            PasswordCheck(foundUser , password, pwPassword);
            if (foundUser.loggedIn) {
                foundUser.loggedIn = false;
                return foundUser;
            }else return null;


    }

    public static boolean ValidateUsername(String username, VBox pwUsername){

        pwUsername.getChildren().removeIf(node -> node instanceof Text);

        if (!Person.CheckUsername(username)) {
            return false;
        }else{
            return true;
        }
    }


    /*
    protected static void PasswordCheck(Person p, String password, Text text){
        if(p == null) System.out.println("null");
        if (password.equals(p.password)){
            p.loggedIn = true;
        }else{
            text.setText("Wrong Password");
        }
    }

     */




protected static void PasswordCheck(Person p, String password, VBox pwPassword){
    if (password.equals(p.password)){
        p.loggedIn = true;
    } else {
        Text text = new Text("Password Is Incorrect");
        text.setFill(Color.RED);
        text.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        pwPassword.getChildren().add(text);
    }
}


    public static boolean CheckUsername(String username){

        while (true)
        {
            boolean taken = false;

            for (Person p : Database.people)
            {
                if (p.username.equalsIgnoreCase(username))
                {
                    taken = true;
                    break;
                }
            }

            if (!taken)
            {
                break; // username is available!
            }

            return false;
        }
        return true;
    }
    

    protected void setUsername(String username){
        this.username = username;
    }
    protected void setPassword(String password){
        this.password = password;
    }   


    
}

package com.example.eventhub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class Person {
    
    private String username;
    private String password;
    private final Calendar dateOfBirth = Calendar.getInstance() ;
    protected boolean loggedIn;
    
    Person(){
    }
    
    Person(String username,String password, int yearOfBirth, int monthOfBirth, int dayOfBirth){
        //checkusername
        this.username = username;
        this.loggedIn = false;
        this.password = password;
        this.dateOfBirth.set(Calendar.YEAR, yearOfBirth);
        this.dateOfBirth.set(Calendar.MONTH, monthOfBirth - 1);
        this.dateOfBirth.set(Calendar.DAY_OF_MONTH, dayOfBirth);
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




    public static final String LogIn(String username, String password,VBox pwUsername,VBox pwPassword){

        pwUsername.getChildren().removeIf(node -> node instanceof Text);
        pwPassword.getChildren().removeIf(node -> node instanceof Text);

            Person foundUser = null;
            for (Person p : Database.people) {
                if (username.equals(p.username)) {
                    foundUser = p;
                    break;
                }
            }
            if (foundUser == null) {
                Text text = new Text("Username Not Found");
                text.setFill(Color.RED);
                pwUsername.getChildren().add(text);
                return "null";
            }

            PasswordCheck(foundUser , password, pwPassword);
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
            }else return "null";


    }

    public Person register(String username, String password, RadioButton gender,
                           String address, LocalDate time, String interest1, String interest2,
                           String interest3, String balance, VBox pwGender , VBox pwUsername, VBox pwBalance){
        
        boolean valid = true;
        if(!Person.Checkusername(username))
        {
           pwUsername.getChildren().add(new Label("Username is already taken"));
           valid = false;
        }
        
        String genderstring;
        int day,month,year;
        Gender usableGender;
        while(true){
            genderstring = gender.getText().toLowerCase();
            switch(genderstring){
                case "male" -> {
                    usableGender = Gender.MALE;
                    break;
                }
                case "female" -> {
                    usableGender = Gender.FEMALE;
                    break;
                }
                default ->{
                    pwGender.getChildren().add(new Label("please choose a gender"));
                    valid = false;
                    continue;
                }
            }
            break;
        }


        day = time.getDayOfMonth();
        month = time.getMonthValue();
        year = time.getYear();


        boolean validInput = false;

        if(balance.matches("\\d+") && (Integer.valueOf(balance)>0) ){ 
         validInput = true;
        }
        else{
        pwBalance.getChildren().add(new Label("please enter a positive balance value"));
        valid = false;
        }

        if (valid){
        
        Wallet wallet = new Wallet(Integer.valueOf(balance));
        
        ArrayList<String> interests = new ArrayList<>(3);
        interests.add(interest1);
        interests.add(interest2);
        interests.add(interest3);
        Person newGuy = new Attendee(wallet, usableGender, address, interests ,username, password, day, month, year );
        Database.people.add(newGuy);
        return newGuy;
        }
        return null;
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
    } else{
        Text text = new Text("Password Is Incorrect");
        text.setFill(Color.RED);
        pwPassword.getChildren().add(text);
    }
}


    public static boolean Checkusername(String username){

        while (true)
        {
            boolean taken = false;

            for (Person p : Database.people)
            {
                if (username.equals(p.username))
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
    

    protected abstract void homeScreen();
    protected void setUsername(String username){
        this.username = username;
    }
    protected void setPassword(String password){
        this.password = password;
    }   
    @Override   
    public abstract String toString();


    
}

package com.example.eventhub;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class Admin extends Person implements Employee<Categories> {
    private final Calendar workBegin = Calendar.getInstance();
    private final Calendar workEnd = Calendar.getInstance();
    private String role;
        
    Admin(){
        this(null,null,null, 0,0,0,0,0);
    }
    
    Admin(String username, String password, String role, int yearOfBirth, 
          int monthOfBirth, int dayOfBirth, int start, int End) {
        super(username, password, yearOfBirth, monthOfBirth, dayOfBirth);
        this.role = role; 
        this.workBegin.set(Calendar.HOUR_OF_DAY, start);
        this.workEnd.set(Calendar.HOUR_OF_DAY, End);
    }
    // This constructor is reserved for future use when Admin instances need to be initialized with specific data.
    
    
    public String getRole(){
        return role;
    }

    public String getWorkBegin() {
        SimpleDateFormat militaryTimeFormat = new SimpleDateFormat("HH");
        return militaryTimeFormat.format(workBegin.getTime());
    }

    public String getWorkEnd() {
        SimpleDateFormat militaryTimeFormat = new SimpleDateFormat("HH");
        return militaryTimeFormat.format(workEnd.getTime());
    }


    public int getWorkingHours(){
        return (workEnd.get(Calendar.HOUR_OF_DAY) - workBegin.get(Calendar.HOUR_OF_DAY));
    }
    
    
    public boolean addRoom(String capacity,VBox pwCapacity,VBox theButtonThatCreatesTheRoom) {
        boolean valid;
        if(capacity.matches("\\d+") && (Integer.valueOf(capacity)>0) ){ 
          valid = true;
        }
        else{
        pwCapacity.getChildren().add(new Label("please enter a positive capacity value"));
        valid = false;
        }  
        if (valid){
            Database.rooms.add(new Room(Integer.valueOf(capacity)));
            theButtonThatCreatesTheRoom.getChildren().add(pwCapacity);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public void create(String categoryName, VBox pwCategoryName){
        /*
        Categories o = new Categories(categoryName,pwCategoryName);
        if (!o.getName().equals("<><><>")){
            Database.categories.add(o);
        }
        */
    }

    @Override
    public String read(Categories o){
        int i =Database.categories.indexOf(o);
        return Database.categories.get(i).toString();
    }

    @Override
    public String toString(){
        String adminInfo= "Username: " + this.getUsername()+ "\n"+ "role: " + this.role + "\n" + "Working hours: " +
                (workEnd.get(Calendar.HOUR_OF_DAY)-workBegin.get(Calendar.HOUR_OF_DAY));

        return adminInfo;
    }
    
    @Override 
    public void update(Categories o,String newName, VBox theInputOfTheNewName){
        for(Categories c : Database.categories){
            if(o == c){
                if(!newName.equals("<><><>")){
                o.setName(newName);
                break;
                }
                else{
                theInputOfTheNewName.getChildren().add(new Label("the new category name exists please try again"));
                }     
            }
        }
    }
    
    @Override
    public void delete(Categories o){
        int index;
        index= Database.categories.indexOf(o);
        Database.categories.remove(index);
    }
    
    @Override
    public void show(){
        System.out.printf("%-20s %-20s %-20s %n", "rooms","events","attendees" );
        ArrayList<String> attendees = new ArrayList<>();
        for(Person p:Database.people){
            if(p instanceof Attendee){
                attendees.add(p.getUsername());
            }
        }
        int max1 = Math.max(Database.events.size(),Database.rooms.size());
        int max= Math.max(attendees.size(), max1);
        for(int i = 0 ; i < max ; i++){
        String room = (i<Database.rooms.size()? String.valueOf(Database.rooms.get(i).getRoomNo()) : "");
        String event = (i<Database.events.size()? Database.events.get(i).getName() : "");
        String attendee = (i<attendees.size()? attendees.get(i) : "");
        
        System.out.printf("%-20s %-20s %-20s %n","Room No."+room,event,attendee );
        }
    }


    /*
    public void homeScreen(){
    ArrayList<String> attendees = new ArrayList<>();
    ArrayList<String> organizers = new ArrayList<>();


    for (Person p : Database.people) {
        if (p instanceof Attendee) {
            attendees.add(p.getUsername());
        } else if (p instanceof Organizer) {
            organizers.add(p.getUsername());
        }
    }
    
    System.out.printf("%-20s %-20s %-20s%n", "Attendees", "Organizers", "Events");
    int max1 = Math.max(organizers.size(), Database.events.size());
    int max = Math.max(attendees.size(), max1);

    for (int i = 0; i < max; i++) {
        String attendee = (i < attendees.size() ? attendees.get(i) : "");
        String organizer = (i < organizers.size() ? organizers.get(i) : "");
        String event = (i < Database.events.size() ? Database.events.get(i).getName() : "");

        System.out.printf("%-20s %-20s %-20s%n", attendee, organizer, event);
    }
    }

     */
    
    private ArrayList<String> categorySelection(String mode){
        ArrayList<String> categoriesListForComboBox = new ArrayList<>(1000);
        for(Categories c : Database.categories){
            categoriesListForComboBox.add(c.getName());
        }
        return categoriesListForComboBox;
    }
}

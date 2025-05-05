package com.example.eventhub;

import java.util.Calendar;
import java.util.Scanner;

public class Reservations {
    private Event belongsTo;
    private Calendar Reservationtime=Calendar.getInstance();
    private boolean nightAvailable ;
    private boolean dayAvailable ;
    static Scanner input = new Scanner(System.in);

    public Reservations() {
        this.nightAvailable = true;
        this.dayAvailable = true;
    }
    
    public Event getBelonging(){
    return this.belongsTo;
    }
    
    public void reserve(Room room,Calendar reserveTime,String DnN,Event e){
        this.belongsTo = e;
        this.Reservationtime = reserveTime;
        boolean continueInput =true;
        do{
                switch (DnN) {
                    case "Morning" -> {
                        this.dayAvailable = false;
                        continueInput = false;
                    }
                    case "Night" -> {
                        this.nightAvailable = false;
                        continueInput = false;
                    }
                    default -> System.out.println("proper input must be either 1 or 2");
                }
        }while(continueInput);
        room.getUnavailableDates().add(this);
        
    }
    
    public boolean getDayAvailability() {
        return this.dayAvailable;
    }
    
    public boolean getNightAvailability() {
        return this.nightAvailable;
    }
    
    public Calendar getReservationTime() {
        return this.Reservationtime;
    }
}

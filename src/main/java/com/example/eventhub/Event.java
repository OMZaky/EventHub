package com.example.eventhub;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;


public class Event implements Cloneable{
    static int intialCapacity = 1000;
    private Calendar eventTime = Calendar.getInstance();
    private String name;
    private Categories category;
    private int ticketPrice;
    private Organizer madeBy;
    private final ArrayList<Attendee> IN_EVENT = new ArrayList<>(intialCapacity);
    private int cost;
    private int attendeeNum;
    private Room room;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private String formattedDate; // Remove initialization here


    Event(){}

    Event(String name , Categories category , int ticketPrice , Calendar eventTime ,Room room,Organizer madeBy , String DnN) {
        this.name = name;
        this.category = category;
        this.ticketPrice = ticketPrice;
        this.eventTime.setTime(eventTime.getTime());
        this.formattedDate = dateFormat.format(this.eventTime.getTime());
        this.room = room;
        this.madeBy = madeBy;
        Reservations res = new Reservations();
        res.reserve(room, eventTime, DnN,this);
    }

    public String getFormattedDate() {
        if (formattedDate == null) {
            return dateFormat.format(eventTime.getTime());
        }
        return formattedDate;
    }



    public Organizer getMadeBy() {
        return madeBy;
    }



    public void setMadeBy(Organizer madeBy) {
        this.madeBy = madeBy;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getTicketPrice(){
        return ticketPrice;
    }
    public void setTicketPrice(int ticketPrice){
        this.ticketPrice = ticketPrice;
    }

    public Categories getCategory() {
        return category;
    }
    public String getCategoryname() {
        return category.getName();
    }


    public void setCategory(Categories category) {
        this.category = category;
    }

    public ArrayList<Attendee> getAttendee(){
        return IN_EVENT;
    }


    public int getAttendeeNum() {
        return attendeeNum;
    }

    public void setAttendeeNum(int attendeeNum) {
        this.attendeeNum = attendeeNum;
    }

    public Organizer getOrganizer() {
        return madeBy;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Event)){
            return false;
        }
        else{
        Event otherE = (Event)other;
        if(otherE.name.equals(this.name) ){
            return otherE.madeBy.toString().equals(this.madeBy.toString());
        }
        return false;
        }
    }




      @Override
    public String toString() {
        return "Event Name :" + getName() + "\nEvent Category :" + getCategoryname()
    + "\nEvent Room :" + this.room.getRoomNo()+ "\nTicket Price :" + getTicketPrice() + 
      "\nEvent Time :" +this.eventTime.getTime() + "\n";
    }
    
    
}

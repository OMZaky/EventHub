package com.example.eventhub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Room {
    private static final int INITIAL_CAPACITY = 1000;
    static Scanner input = new Scanner(System.in);
    private final int roomNo;
    private int capacity;
    private static int roomID = 0;
    static private int roomcost = 3000;
    // private final Calendar calendar = Calendar.getInstance();
    // int today = calendar.get(Calendar.DAY_OF_MONTH);
    // int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
private final ArrayList<Reservations> unavalabledates = new ArrayList<>(INITIAL_CAPACITY);

public Room(int capacity) {
    this.roomNo = Room.roomID;
        this.capacity = capacity;
        roomID++;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getRoomCost() {
        return roomcost;
    }

    public void setRoomCost(int roomcost) {
        Room.roomcost = roomcost;
    }

    public ArrayList<Reservations> getUnavailableDates(){
    return unavalabledates;
    }
    
    public String[][] getAvailableRooms() {
        ArrayList<String[]> availableDatesList = new ArrayList<>();

        // Create a calendar instance for iteration, starting 3 days from today
        Calendar baseCalendar = Calendar.getInstance();
        Calendar iterationCalendar = (Calendar) baseCalendar.clone();
        iterationCalendar.add(Calendar.DAY_OF_MONTH, 3); // Start 3 days ahead

        // Loop for the next 15 days
        for (int i = 0; i < 15; i++) {
            boolean isMorningAvailable = true;
            boolean isNightAvailable = true;
            Date currentDate = iterationCalendar.getTime();
            String theDate = dateFormat.format(currentDate);
            String formattedDateOnly = dateFormat.format(currentDate); // For comparison

            // Check against unavailable dates
            for (Reservations r : unavalabledates) {
                // Compare only the date part, ignoring time
                String reservationDateOnly = dateFormat.format(r.getReservationTime().getTime());
                if (formattedDateOnly.equals(reservationDateOnly)) {
                    if (!r.getDayAvailability()) {
                        isMorningAvailable = false;
                    }
                    if (!r.getNightAvailability()) {
                        isNightAvailable = false;
                    }
                }
            }

            // Add available slots for the current day to the list
            String morningSlot = isMorningAvailable ? theDate + " - Morning" : theDate + " - occupied" ;
            String nightSlot = isNightAvailable ? theDate + " - Night" : theDate + " - occupied"  ;
            
            
            // Only add the row if at least one slot is available for that day

            availableDatesList.add(new String[]{morningSlot, nightSlot});
           

            // Move to the next day
            iterationCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return availableDatesList.toArray(new String[0][0]);
    }

    public String chooseAvailableTime() {
        // Get available rooms based on the updated 15-day logic
        String[][] availableRooms = getAvailableRooms();

        if (availableRooms.length == 0) {
            System.out.println("Sorry, no rooms available in the next 15 days (starting 3 days from now).");
            return null;
        }

        System.out.println("Available Time Slots:");
        int optionCounter = 1;
        ArrayList<String> optionsList = new ArrayList<>(); // To map choice number to actual slot string
        // Display available slots with numbers
        for (String[] availableRoom : availableRooms) {
            String morning = availableRoom[0];
            String night = availableRoom[1];
            if (morning != null) {
                System.out.println(optionCounter + ": " + morning);
                optionsList.add(morning); // Add morning slot to options
                optionCounter++;
            }
            if (night != null) {
                System.out.println(optionCounter + ": " + night);
                optionsList.add(night); // Add night slot to options
                optionCounter++;
            }
        }

        
        do {
            System.out.println("Please input the number of the time slot to reserve (or 0 to cancel):");
            String choice;
            int choiceInt;
            choice = input.nextLine();
            // Input validation: Check if it's a number
            if (!choice.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a numeric value.");
                continue;
            }
            choiceInt = Integer.parseInt(choice);
            // Now that we have a valid number, proceed to check if it's 0 or within the valid range

            // Handle cancellation
            if (choiceInt == 0) {
                System.out.println("Reservation cancelled.");
                new Organizer().homeScreen();
            }

            // Validate if the choice number is within the range of displayed options
            if (choiceInt > 0 && choiceInt <= optionsList.size()) {
                if(! optionsList.get(choiceInt - 1).substring(13, optionsList.get(choiceInt - 1).length()).equals("occupied")){
                return optionsList.get(choiceInt - 1);
                }
                else{
                    System.out.println("the chosen room must not be occupied");
                }
            } else {
                System.out.println("Please choose a number corresponding to one of the available options.");
            }
        } while (true); // Loop until a valid choice is made or cancelled
    }
  
}

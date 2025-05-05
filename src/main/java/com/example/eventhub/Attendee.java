package com.example.eventhub;

import java.util.ArrayList;
import java.util.Scanner;

public class Attendee extends Person {
    static Scanner input = new Scanner(System.in);

    private final String address;
    private final Gender gender;
    private final ArrayList<String> interests;
    private ArrayList<Event> myevents = new ArrayList<Event>();
    private Wallet wallet;
    boolean buysuccess = false;

    Attendee() {
        this(null, null, null, null, null, null, 0, 0, 0);
    }
    
   
    Attendee(Wallet wallet, Gender gender, String address, ArrayList<String> interests, String username,String password, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        super(username, password, yearOfBirth, monthOfBirth, dayOfBirth);
        this.wallet = wallet;
        this.address = address;
        this.gender = gender;
        this.interests = interests;
    }

    public String getAddress() {
        return address;
    }
    public Gender getGender() {
        return gender;
    }
    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Event> getMyevents() {
        return myevents;
    }

    public void setMyevents(ArrayList<Event> myevents) {
        this.myevents = myevents;
    }

    public void buy(Event event){
        if (this.wallet.getBalance() >= event.getTicketPrice()){
            if(event.getAttendeeNum() < event.getRoom().getCapacity()){
                this.wallet.pay(event);
                event.setAttendeeNum(event.getAttendeeNum() + 1);
                System.out.println("Ticket bought successfully");
                event.getAttendee().add(this);
                myevents.add(event);
                buysuccess = true;
            }
            else{
                System.out.println("Event number of attendees is complete.");
                System.out.println("Choose another event.");

            }
        }
        else {
            System.out.println("Balance is not sufficient");
        }
    }

    @Override
    public String toString() {
        return "Attendee " + this.getUsername() +
                " {Address='" + address + '\'' +
                ", wallet=" + wallet.getBalance() +
                ", gender=" + gender +
                ", interests=" + interests +
                '}';
    }


    @Override
    public void homeScreen() {

        while(true){

            System.out.println("Welcome, " + this.getUsername() + "!");
            System.out.println("What would you like to do?");
            System.out.println("1- View Profile");
            System.out.println("2- View Wallet Balance");
            System.out.println("3- Buy a Ticket");
            System.out.println("4- Show my Tickets");
            System.out.println("5- Log Out");
            // input.nextLine();
            String choice = input.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.println(this.toString());
                }
                case "2" -> {
                    System.out.println("Wallet Balance: " + this.wallet.getBalance());
                }
                case "3" -> {

                    while (!buysuccess) {
                        for (int i = 0; i < Database.events.size(); i++) {
                            System.out.print(i + 1);
                            System.out.print("- ");
                            System.out.println(Database.events.get(i).toString());
                        }
                        System.out.println("Enter event number:");
                        int eventNum = input.nextInt();
                        input.nextLine();
                        this.buy(Database.events.get(eventNum - 1));
                    }
                    buysuccess = false;

                }
                case "4" -> {

                    for (int i = 0; i < myevents.size(); i++) {
                        System.out.print(i + 1 + "- ");
                        System.out.println(myevents.get(i).toString());
                    }


                }

                case "5" -> {
                    System.out.println("Logging out...");
                    this.loggedIn = false;
                    System.out.println("You have logged out successfully.");

                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }



    }


    }


package com.example.eventhub;

public class Wallet {

    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Wallet() {
        balance = 0;
    }

    public Wallet(double balance) {
        this.balance = balance;
    }

    public void pay(Event event) {
        if (event != null && event.getOrganizer() != null) {
            balance -= event.getTicketPrice();
            event.getOrganizer().getWallet().setBalance(event.getOrganizer().getBalance() + event.getTicketPrice());
            event.setAttendeeNum(event.getAttendeeNum() + 1); // Fixed method name
        }
    }

    public void pay(Attendee attendee) {
        if (attendee != null) {
            balance -= attendee.getWallet().getBalance();
            attendee.getWallet().setBalance(attendee.getWallet().getBalance() + attendee.getWallet().getBalance());
        }
    } 
    
    public boolean pay(Organizer o ,Room room) {
        if (o != null) {
            if (o.getWallet().balance < room.getRoomCost()) {
                System.out.println("Insufficient balance");
                return false;
            } else{
                o.getWallet().setBalance((o.getWallet().getBalance() - room.getRoomCost()));
                return true;
            }
        }
        return false;
    }
    
    public void pay(Room room) {
        if (room != null) {
            balance -= room.getRoomCost(); // Fixed method name
            room.setRoomCost(room.getRoomCost() + room.getRoomCost()); // Fixed method name
        }
    }

    public void addMoney(int amount) {
        balance += amount;
    }

    public void withdrawMoney(int amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
        } else {
            balance -= amount;
        }
    }
}

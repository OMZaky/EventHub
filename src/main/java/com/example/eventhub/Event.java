
import java.util.ArrayList;
import java.util.Calendar;


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



    Event(){

    }

    Event(String name , Categories category , int ticketPrice , Calendar eventTime ,Room room,Organizer madeBy , String DnN) {
        this.name = name;
        this.category = category;
        this.ticketPrice = ticketPrice;
        this.eventTime = (Calendar) eventTime.clone() ;
        this.room = room;
        this.madeBy = madeBy;
        Reservations res = new Reservations();
        res.reserve(room, eventTime, DnN,this);
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
    public String toString() {
        return "Event Name :" + getName() + "\nEvent Category :" + getCategoryname()
    + "\nEvent Room :" + this.room.getRoomNo()+ "\nTicket Price :" + getTicketPrice() + 
      "\nEvent Time :" +this.eventTime.getTime() + "\n";
    }
    
    
}

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Iterator;

public class Organizer extends Person implements Employee<Event> {  

    private Wallet wallet;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ArrayList<Event> mine = new ArrayList<>(1000);

    Organizer(){
        super(null,null,0,0,0);
    
    }
    
    Organizer( String username, String password, int yearOfBirth, int monthOfBirth, int dayOfBirth , int balance) {
        super(username, password, yearOfBirth, monthOfBirth, dayOfBirth);
        this.wallet = new Wallet(balance);
    }

    public double getBalance() {
        return (wallet != null) ? wallet.getBalance() : 0;
    }

    @Override 
    public void create(){
        Categories myCat ;
        Room myRoom;
        int k = 1;
        for(Categories c: Database.categories){
            System.out.print(k + "-");
            System.out.println(c.getName());
            k++;
        }
        System.out.println("Please Choose the category");
        while(true){
            String choice = input.nextLine();
            if ((Integer.parseInt(choice) > Database.categories.size()) || Integer.parseInt(choice) < 0){
                System.out.println("Please choose something in range");
                continue;
            }
            myCat = Database.categories.get(Integer.parseInt(choice)-1);
            break;
        }
        System.out.println("Please Enter the name of the event");
        // input.nextLine();
        String name = input.nextLine();
        // input.nextLine();
        System.out.println("please enter the price");
        int price;
        do { 
            try {
                price = Integer.parseInt(input.nextLine());
                if (price <= 0) {
                    throw new InputMismatchException("Price must be a positive integer.");
                } else {
                    break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for price.");
                // e.printStackTrace();
            }
            System.out.println("please choose the room you would like to rent");
        } while (true);
        int t = 0;
        for(Room r: Database.rooms){
            System.out.print(t + "-");
            System.out.println( "Room no. " + r.getRoomNo());
            t++;
        }
        System.out.println("Please Choose a room");
        while(true){
            String choice = input.nextLine();
            if ((Integer.parseInt(choice) > Database.rooms.size()) || Integer.parseInt(choice) < 0 ){
                System.out.println("please choose something in range");
                continue;
            }
            int ind = -1;
            for (int i = 0; i < Database.rooms.size(); i++) {
                if (Database.rooms.get(i).getRoomNo() == Integer.parseInt(choice)) {
                    ind = i;
                    break;
                }
            }
            myRoom = Database.rooms.get(ind);
            break;
        }
        String myTime = myRoom.chooseAvailableTime();
        String calvalue = myTime.substring(0,10);
        String State = myTime.substring(13,myTime.length());
        LocalDate date;

        do { 
            try {
                date = LocalDate.parse(calvalue, format);
                Calendar cal = Calendar.getInstance();
                cal.set(date.getYear(),date.getMonthValue()-1,date.getDayOfMonth());
                Reservations res = new Reservations();
                this.wallet.pay(this, myRoom);
                Event eve=new Event(name,myCat,price,cal,myRoom,this,State);
                Database.events.add(eve);    
                mine.add(eve);
                System.out.println("Event created successfully");
                break; 
            } catch (Exception e) {
                System.out.println("please enter the day in the correct format DD/MM/YYYY it is very strict with the format");
                continue;
            }
        } while (true); 
    }
    
    @Override
    public void read(Event e){
        System.out.println(e.toString());
    }
    
    @Override
    public void delete(Event e){
        
    Iterator<Reservations> iterator = e.getRoom().getUnavailableDates().iterator();
    while (iterator.hasNext()) {
        Reservations r = iterator.next();
        if (r.getBelonging().equals(e)) {
            iterator.remove(); 
        }
    }
        int index;
        index = Database.events.indexOf(e);
        Database.events.remove(index);
        mine.remove(e);
    }
    
    @Override
    public void update(Event o){
        for(Event e : Database.events){
            if(o == e){
                // input.nextLine();
                o.setName(input.nextLine());
                break;
            }
        }
    }
    
    @Override
    public String toString() {
        return "Organizer{" +
                "Name='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ",balance ='" + wallet.getBalance() + '\'' +
                '}';
    }
    
    @Override
    public void show(){
        ArrayList<String> attendees = new ArrayList<>(1000); //represents his attnedees
        ArrayList<String> myEvents = new ArrayList<>(1000);
        ArrayList<String> AvRooms = new ArrayList<>(1000);
        for(Event e:Database.events){
            if(e.getOrganizer().getUsername().equals(this.getUsername())){
                myEvents.add(e.getName());
                for (Attendee a:e.getAttendee() ){
                attendees.add(a.getUsername());
                }
            }
        }
        Calendar cal;
        String formattedDate;
      while (true){
        cal = this.inputDate();
        Instant instant = cal.toInstant();
        LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        formattedDate = date.format(format);
       LocalDate minDate = LocalDate.now().plusDays(3);
       LocalDate maxDate = LocalDate.now().plusDays(17);
       if(date.isBefore(minDate)||date.isAfter(maxDate)){
           System.out.println("the date must be after 2 days from now and before 18 days");
       }else{
       break;
       }
      }
       for(Room r : Database.rooms){
        String [][] avM = r.getAvailableRooms();
           boolean AV = false;
            for(int i = 0; i<15; i++ ){
                int indStartA= (avM[i][0].indexOf('-')+2);
                int indStartb= (avM[i][1].indexOf('-')+2);
               
                String StateA = avM[i][0].substring(indStartA,avM[i][0].length());
                String StateB = avM[i][1].substring(indStartb,avM[i][1].length());
                int beginIndex = 0, endIndex = 10;

                if(!StateA.equals("occupied")){
                    String theDateA = avM[i][0].substring(beginIndex, endIndex);
                    if(theDateA.equals(formattedDate)){
                    AV = true;
                    break;
                }
                }
                if(!StateB.equals("occupied")){
                    String theDateB = avM[i][1].substring(beginIndex, endIndex);
                    if(theDateB.equals(formattedDate)){
                    AV = true;
                    break;
                    }
                }

            }
            if (AV){
                AvRooms.add(String.valueOf(r.getRoomNo()));
            }
        }
        
        int max1 = Math.max(attendees.size(),myEvents.size());
        int max  = Math.max(AvRooms.size(), max1);
         System.out.printf("%-20s %-20s %-20s %n", "Free rooms","events","attendees" );
        for(int i = 0 ; i < max ; i++){
        
            String room = (i<AvRooms.size()? AvRooms.get(i) : "");
            String event = (i<myEvents.size()? myEvents.get(i) : "");
            String attendee = (i<attendees.size()? attendees.get(i) : "");
            System.out.printf("%-20s %-20s %-20s %n", "Room no."+room,event,attendee );
        } 
    }
    @Override
    public void homeScreen() {
        for(Event e:Database.events){
            if(e.getOrganizer().getUsername().equals(this.getUsername())){
                mine.add(e);
            }
        }
          while(true){
            System.out.println("please choose what you want to do");
            System.out.println("1 - view your profile");
            System.out.println("2 - show relavent information");
            System.out.println("3 - manage and create events");
            System.out.println("4 - log out");
            // input.nextLine();
            String i = input.nextLine();
            switch (i) {
                case "1" -> System.out.println(this.toString());
                case "2" -> this.show();
                case "3" -> {
                    System.out.println("What do you want to do?");
                    System.out.println("1-create event");
                    System.out.println("2-read event");
                    System.out.println("3-update event name");
                    System.out.println("4-delete event");
                    // input.nextLine();
                    String j = input.nextLine();
                    switch (j) {
                        case "1" -> this.create();
                        case "2" -> {
                            System.out.println("Which event do you want to read");
                            this.eventSelection("read");
                        }
                        case "3" -> {
                            System.out.println("Which event do you want to update");
                            this.eventSelection("update");
                        }
                        case "4" -> {
                            System.out.println("Which event do you want to delete");
                            this.eventSelection("delete");
                        }
                        default -> System.out.println("please enter a valid option");
                    }
                }
                case "4" -> {     
                    System.out.println("Are you sure you want to log out?(y/n)");
                    // input.nextLine();
                    
                    while(true){
                    String choice = input.nextLine();
                    switch (choice.toLowerCase()) {
                    case "y" -> {
                        App.main(null);
                    }
                    case "n" -> {
                    
                    }
                    default -> {
                        System.out.println("please enter y to refer to yes or n to refer to no");
                        continue;
                    }   
                    }
                    break;
                    }
                }
                default -> System.out.println("please enter one of the options");
            }

        }
    }
    public Wallet getWallet(){
    return this.wallet;
    }
    
     
    private void eventSelection(String mode){
        int l = 0;
        for(Event e : mine){
            System.out.println(l + " " + e.getName());
            l++;
        }
        do{
            try{
                int k = input.nextInt(); 
                input.nextLine();              
                    if(k < l && k >= 0 ){
                        switch (mode) {
                            case "read" -> this.read(mine.get(k));
                            case "update" -> this.update(mine.get(k));
                            case "delete" -> this.delete(mine.get(k));
                        }
                        break;
                    }else{
                        System.out.println("please enter a number within the valid range");
                    }
               
            }catch(InputMismatchException ex){
                System.out.println("please enter a number in range");
            }
        }while(true);
    }
    
}

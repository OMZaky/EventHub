package com.example.eventhub;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.example.eventhub.Attendee;
import com.example.eventhub.Database;
import com.example.eventhub.Organizer;
import com.example.eventhub.Room;
import com.jfoenix.controls.JFXButton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author omara
 */
public class OrganizerShow {
    private Organizer o;
    private Scene root;

    public Scene getScene() {
        return root;
    }

    public OrganizerShow(Organizer o, SceneManager sceneManager) {
        this.o = o;        
        String styleBg = "-fx-background-color: #2A363F;";
        String ButStyleUA = "-fx-background-color:#6ED9A0; -fx-text-fill: white;";
        String ButStyleA = "-fx-background-color:#2A363F; -fx-text-fill: white;";
        String textHeader = "-fx-font-family:'Century Gothic'; -fx-font-size : 25;-fx-text-fill: #ffffff;";
        String textNormal = "-fx-font-family:'Century Gothic'; -fx-font-size : 16;-fx-text-fill: #ffffff; ";
        String textTable = "-fx-font-family:'Century Gothic'; -fx-font-size : 18;-fx-text-fill: #ffffff; ";
        
        BorderPane BorPane = new BorderPane();
        BorPane.setStyle(styleBg);
        
        HBox leftHbox = new HBox();
        leftHbox.setAlignment(Pos.TOP_LEFT);
        leftHbox.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.226));
        
        VBox ButtonsVbox = new VBox();
        ButtonsVbox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(ButtonsVbox, Priority.ALWAYS); 
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
            double ButtonPadding = ButtonsVbox.getHeight()*0.08;
            ButtonsVbox.setPadding(new Insets(ButtonPadding,0,0,0));
        });
        
        Pane linePane = new Pane();
        linePane.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(linePane, Priority.ALWAYS);
        
        leftHbox.setFillHeight(true); 
        leftHbox.getChildren().addAll(ButtonsVbox,linePane);
        
        JFXButton UserInfoBut = new JFXButton("User Info");
        UserInfoBut.setMaxWidth(Double.MAX_VALUE); 
        UserInfoBut.setStyle(ButStyleA);
        UserInfoBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
            double ButtonPadding = ButtonsVbox.getHeight()*0.08;
            ButtonsVbox.setPadding(new Insets(ButtonPadding,0,0,0));
        });
        
        
        JFXButton ShowBut = new JFXButton("Show Data");
        ShowBut.disableProperty().set(true); 
        ShowBut.setMaxWidth(Double.MAX_VALUE); 
        ShowBut.setStyle(ButStyleUA);
        ShowBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
            double ButtonMar = ButtonsVbox.getHeight()*0.025;
            VBox.setMargin(ShowBut, new Insets(ButtonMar,0,0,0));
        });
        
        JFXButton CRUDbut = new JFXButton("CRUD");
        CRUDbut.setMaxWidth(Double.MAX_VALUE); 
        CRUDbut.setStyle(ButStyleA);
        CRUDbut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
            double ButtonMar = ButtonsVbox.getHeight()*0.025;
            VBox.setMargin(CRUDbut, new Insets(ButtonMar,0,0,0));
        });
        
        
        
        JFXButton LogOutBut = new JFXButton("Log out");
        LogOutBut.setMaxWidth(Double.MAX_VALUE);
        LogOutBut.setStyle(ButStyleA);
        LogOutBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
            double ButtonMar = ButtonsVbox.getHeight()*0.025;
            VBox.setMargin(LogOutBut, new Insets(ButtonMar,0,0,0));
        });
        
        ButtonsVbox.getChildren().addAll(UserInfoBut,ShowBut,CRUDbut,LogOutBut);
        
        Line l = new Line();
        l.setStroke(Color.web("#465058"));
 
        l.startXProperty().bind(linePane.widthProperty().divide(2));
        l.endXProperty().bind(linePane.widthProperty().divide(2));

        l.startYProperty().bind(linePane.heightProperty().multiply(0.1));
        l.endYProperty().bind(linePane.heightProperty().multiply(0.8));
        
        linePane.getChildren().add(l);
        
        
        BorPane.setLeft(leftHbox);
        
        //the datepicker
        VBox totalSituation = new VBox();
        StackPane LabelStack = new StackPane();
        LabelStack.setAlignment(Pos.CENTER);
        LabelStack.prefHeightProperty().bind(totalSituation.heightProperty().multiply(0.07));
        Label theDateLabel = new Label("Please enter the day you want to check");
        theDateLabel.setStyle(textHeader);
        LabelStack.getChildren().add(theDateLabel);

        StackPane initialStack = new StackPane();
        DatePicker datePicker = new DatePicker();
        
        
        datePicker.setValue(LocalDate.now()); // Set default to today
        datePicker.setDayCellFactory(picker -> new DateCell() {
        @Override
        public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        setDisable(date.isBefore(LocalDate.now().plusDays(3)) || 
            date.isAfter(LocalDate.now().plusDays(17)));
         }
        });
        totalSituation.heightProperty().addListener((obs,oldPad,newPad)->{
            double calendarMar = totalSituation.getHeight()*0.05;
            VBox.setMargin(initialStack, new Insets(calendarMar,0,0,0));
        });

        initialStack.getChildren().addAll(datePicker);
        totalSituation.getChildren().addAll(LabelStack,initialStack);
        
        
        datePicker.setOnAction(e -> {
            totalSituation.getChildren().removeIf(node -> node instanceof ScrollPane);
            LocalDate selectedDate = datePicker.getValue();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String thePickedDate = datePicker.getValue().format(format);
        
        //Begin the table       
        ScrollPane scroller = new ScrollPane();
        scroller.prefWidthProperty().bind(totalSituation.widthProperty());
        scroller.setFitToWidth(true);
        scroller.setFitToHeight(true);
        scroller.setStyle("-fx-background: #2A363F; -fx-background-color: #2A363F;");
        StackPane stack = new StackPane();
        stack.prefWidthProperty().bind(totalSituation.widthProperty());
        stack.setStyle("-fx-background-color:#2A363F;");
        VBox infoScroll = new VBox();
        infoScroll.prefWidthProperty().bind(totalSituation.widthProperty());

        stack.getChildren().add(infoScroll);
        stack.heightProperty().addListener((obs,oldval,newVal)->{
            double infomar = scroller.getHeight()*0.05;
            StackPane.setMargin(infoScroll, new Insets(infomar,0,0,0));
        });
        
        
        
        GridPane GraphStart = new GridPane();
        GraphStart.prefWidthProperty().bind(totalSituation.widthProperty());
        GraphStart.setVgap(30);
        GraphStart.setHgap(30);
        GraphStart.setAlignment(Pos.CENTER);
        
        ColumnConstraints colgrid1 = new ColumnConstraints();
        colgrid1.setPercentWidth(33); 
        colgrid1.setHalignment(HPos.CENTER);

        ColumnConstraints colgrid2 = new ColumnConstraints();
        colgrid2.setPercentWidth(33); 
        colgrid2.setHalignment(HPos.CENTER);
        
        ColumnConstraints colgrid3 = new ColumnConstraints();
        colgrid3.setPercentWidth(34); 
        colgrid3.setHalignment(HPos.CENTER);
        
        GraphStart.getColumnConstraints().addAll(colgrid1, colgrid2 ,colgrid3);
        
        Label Ename = new Label("My Events");
        Ename.setStyle(textTable);
        Ename.setAlignment(Pos.CENTER);

        Label Atname = new Label("Attendees");
        Atname.setStyle(textTable);
        Atname.setAlignment(Pos.CENTER);

        Label RoomNo = new Label("Avalable rooms");
        RoomNo.setStyle(textTable);
        RoomNo.setAlignment(Pos.CENTER);
        
        GraphStart.add(Ename, 0 ,0);
        GraphStart.add(Atname, 1 ,0);
        GraphStart.add(RoomNo, 2 ,0);
        
        infoScroll.getChildren().addAll(GraphStart);
        ArrayList<String> attendees = new ArrayList<>(1000); //represents his attnedees
        ArrayList<String> myEvents = new ArrayList<>(1000);
        ArrayList<String> AvRooms = new ArrayList<>(1000);
        for(Event j: Database.events){
            if(j.getOrganizer().getUsername().equals(o.getUsername())){
                myEvents.add(j.getName());
                for (Attendee a:j.getAttendee() ){
                attendees.add(a.getUsername());
                }
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
                    if(theDateA.equals(thePickedDate)){
                    AV = true;
                    break;
                }
                }
                if(!StateB.equals("occupied")){
                    String theDateB = avM[i][1].substring(beginIndex, endIndex);
                    if(theDateB.equals(thePickedDate)){
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
        for(int i = 0 ; i < max ; i++){
        
            String room = (i<AvRooms.size()? AvRooms.get(i) : "");
            String event = (i<myEvents.size()? myEvents.get(i) : "");
            String attendee = (i<attendees.size()? attendees.get(i) : "");
            GridPane Graphcont = new GridPane();
            GraphStart.setVgap(30);
            GraphStart.setHgap(30);
            Graphcont.getColumnConstraints().addAll(colgrid1, colgrid2 ,colgrid3);
            Label TableLab1 = new Label(event);
            TableLab1.setStyle(textTable);
            Label TableLab2 = new Label(attendee);
            TableLab1.setStyle(textTable);
            Label TableLab3 = new Label("Room No. " + room);
            TableLab1.setStyle(textTable);
            Graphcont.add(TableLab1, 0 ,0);
            Graphcont.add(TableLab2, 1 ,0);
            Graphcont.add(TableLab3, 2 ,0);
            infoScroll.getChildren().addAll(Graphcont);
        } 
        stack.getChildren().addAll(scroller);
        scroller.setContent(stack);
        totalSituation.getChildren().add(scroller);
        });
        
        
        BorPane.setCenter(totalSituation);
        
        root = new Scene(BorPane,600,400);
        
        
    }

    
    
}

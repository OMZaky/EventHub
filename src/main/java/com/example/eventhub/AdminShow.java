package com.example.eventhub;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.example.eventhub.Attendee;
import com.example.eventhub.Database;
import com.example.eventhub.Person;
import com.jfoenix.controls.JFXButton;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

public class AdminShow {

    public AdminShow() {
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
        
        JFXButton createRoom = new JFXButton("Create Room");
        createRoom.setMaxWidth(Double.MAX_VALUE); 
        createRoom.setStyle(ButStyleA);
        createRoom.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
        double ButtonMar = ButtonsVbox.getHeight()*0.025;
        VBox.setMargin(createRoom, new Insets(ButtonMar,0,0,0));
        });
        
        JFXButton CRUDbut = new JFXButton("CRUD cate.");
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
        
        ButtonsVbox.getChildren().addAll(UserInfoBut,ShowBut,createRoom,CRUDbut,LogOutBut);
        
        Line l = new Line();
        l.setStroke(Color.web("#465058"));
 
        l.startXProperty().bind(linePane.widthProperty().divide(2));
        l.endXProperty().bind(linePane.widthProperty().divide(2));

        l.startYProperty().bind(linePane.heightProperty().multiply(0.1));
        l.endYProperty().bind(linePane.heightProperty().multiply(0.8));
        
        linePane.getChildren().add(l);
        
        
        BorPane.setLeft(leftHbox);
        
        //Begin the table       
        ScrollPane scroller = new ScrollPane();
        scroller.setFitToWidth(true);
        scroller.setFitToHeight(true);
        scroller.setStyle("-fx-background: #2A363F; -fx-background-color: #2A363F;");
        StackPane stack = new StackPane();
        stack.setStyle("-fx-background-color:#2A363F;");
        
        
        VBox infoScroll = new VBox();
        stack.getChildren().add(infoScroll);
        stack.heightProperty().addListener((obs,oldval,newVal)->{
            double infomar = scroller.getHeight()*0.05;
            StackPane.setMargin(infoScroll, new Insets(infomar,0,0,0));
        });
        
        
        
        GridPane GraphStart = new GridPane();
        GraphStart.setVgap(30);
        GraphStart.setHgap(30);
        
        
        ColumnConstraints colgrid1 = new ColumnConstraints();
        colgrid1.setPercentWidth(33); 

        ColumnConstraints colgrid2 = new ColumnConstraints();
        colgrid2.setPercentWidth(33); 

        ColumnConstraints colgrid3 = new ColumnConstraints();
        colgrid3.setPercentWidth(34); 
        GraphStart.getColumnConstraints().addAll(colgrid1, colgrid2 ,colgrid3);
        
        Label Ename = new Label("Event Name");
        Ename.setStyle(textTable);
        Label Orgname = new Label("Attendees");
        Orgname.setStyle(textTable);
        Label RoomNo = new Label("Room no.");
        RoomNo.setStyle(textTable);
        
        GraphStart.add(Ename, 0 ,0);
        GraphStart.add(Orgname, 1 ,0);
        GraphStart.add(RoomNo, 2 ,0);
        
        infoScroll.getChildren().addAll(GraphStart);
              
        ArrayList<String> attendees = new ArrayList<>();
        for(Person p: Database.people){
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
        
        scroller.setContent(stack);
        BorPane.setCenter(scroller);
        
        Scene root = new Scene(BorPane,600,400);

    
    
    }
    
}

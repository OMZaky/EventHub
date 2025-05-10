package com.example.eventhub;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.example.eventhub.Attendee;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author omara
 */
public class AttendeeInfo{
    private Attendee a;
    private Scene root;

    public AttendeeInfo(Attendee a, SceneManager sceneManager) {
        this.a = a;
        String styleBg = "-fx-background-color: #2A363F;";
        String ButStyleUA = "-fx-background-color:#6ED9A0; -fx-text-fill: white;";
        String ButStyleA = "-fx-background-color:#2A363F; -fx-text-fill: white;";

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
        UserInfoBut.disableProperty().set(true);
        UserInfoBut.setMaxWidth(Double.MAX_VALUE);
        UserInfoBut.setStyle(ButStyleUA);
        UserInfoBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
            double ButtonPadding = ButtonsVbox.getHeight()*0.08;
            ButtonsVbox.setPadding(new Insets(ButtonPadding,0,0,0));
        });


        JFXButton BuyBut = new JFXButton("Buy Tickets");
        BuyBut.setMaxWidth(Double.MAX_VALUE);
        BuyBut.setStyle(ButStyleA);
        BuyBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
            double ButtonMar = ButtonsVbox.getHeight()*0.025;
            VBox.setMargin(BuyBut, new Insets(ButtonMar,0,0,0));
        });

        BuyBut.setOnAction(e -> sceneManager.switchToBuyTickets(a));


        JFXButton but4 = new JFXButton("Search");
        but4.setMaxWidth(Double.MAX_VALUE);
        but4.setStyle(ButStyleA);
        but4.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
            double ButtonMar = ButtonsVbox.getHeight()*0.025;
            VBox.setMargin(but4, new Insets(ButtonMar,0,0,0));
        });

        but4.setOnAction(e -> sceneManager.switchToAttendeeSearch(a));



        JFXButton LogOutBut = new JFXButton("Log out");
        LogOutBut.setMaxWidth(Double.MAX_VALUE);
        LogOutBut.setStyle(ButStyleA);
        LogOutBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
            double ButtonMar = ButtonsVbox.getHeight()*0.025;
            VBox.setMargin(LogOutBut, new Insets(ButtonMar,0,0,0));
        });

        LogOutBut.setOnAction(e -> sceneManager.switchToLogout(a));



        ButtonsVbox.getChildren().addAll(UserInfoBut,BuyBut,but4, LogOutBut);

        Line l = new Line();
        l.setStroke(Color.web("#465058"));

        l.startXProperty().bind(linePane.widthProperty().divide(2));
        l.endXProperty().bind(linePane.widthProperty().divide(2));

        l.startYProperty().bind(linePane.heightProperty().multiply(0.1));
        l.endYProperty().bind(linePane.heightProperty().multiply(0.8));

        linePane.getChildren().add(l);
        BorPane.setLeft(leftHbox);

        String textHeader = "-fx-font-family:'Century Gothic'; -fx-font-size : 25;-fx-text-fill: #ffffff;";
        String textNormal = "-fx-font-family:'Century Gothic'; -fx-font-size : 16;-fx-text-fill: #ffffff; ";
        String textTable = "-fx-font-family:'Century Gothic'; -fx-font-size : 18;-fx-text-fill: #ffffff; ";

        VBox Body = new VBox();
        Label welcomin = new Label("Welcome"+a.getUsername());
        welcomin.setStyle(textHeader);

        GridPane usernameAndProfile = new GridPane();
        BorPane.heightProperty().addListener((obs,oldPad,newPad)->{
            double gridMar = BorPane.getHeight()*0.03;
            Body.setMargin(usernameAndProfile, new Insets(gridMar,0,0,0));
        });

        usernameAndProfile.setVgap(10);
        usernameAndProfile.setHgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);

        usernameAndProfile.getColumnConstraints().addAll(col1, col2);

        Circle profilePlaceholder = new Circle(30);

        profilePlaceholder.setFill(Color.web("#6ED9A0"));
        HBox profileContainer = new HBox();
        profileContainer.setAlignment(Pos.CENTER_RIGHT);
        profileContainer.widthProperty().addListener((obs,oldPad,newPad)->{
            double CirclePadding = profileContainer.getWidth()*0.25;
            profileContainer.setPadding(new Insets(0,CirclePadding,0,0));
        });


        profileContainer.getChildren().add(profilePlaceholder);
        usernameAndProfile.add(welcomin, 0, 0);
        usernameAndProfile.add(profileContainer,1,0);

        HBox info = new HBox();
        VBox main3 = new VBox();
        VBox interests = new VBox();
        info.widthProperty().addListener((obs,oldPad,newPad)->{
            double interestsMar = info.getWidth()*0.525;
            HBox.setMargin(interests, new Insets(0,0,0,interestsMar));
        });
        Label address = new Label("Address : " + a.getAddress());
        address.setStyle(textNormal);

        Label gender = new Label("Gender : " + a.getGender());
        gender.setStyle(textNormal);

        Label balance = new Label("Balance : " + a.getWallet().getBalance());
        balance.setStyle(textNormal);

        main3.getChildren().addAll(address, gender, balance);

        Label interestsLabel = new Label("Interests");
        interestsLabel.setStyle(textNormal);
        interests.getChildren().add(interestsLabel);
        for (String s : a.getInterests()) {
            Label interest = new Label(s);
            interest.setStyle(textNormal);
            interests.getChildren().add(interest);
        }

        info.getChildren().addAll(main3,interests);


        VBox Ticketthings = new VBox();
        Label Ticket = new Label("Ticket");
        Ticket.setStyle(textHeader);



        ScrollPane scroller = new ScrollPane();
        scroller.setFitToWidth(true);
        scroller.setFitToHeight(true);
        scroller.setStyle("-fx-background: #2A363F; -fx-background-color: #2A363F;");
        StackPane stack = new StackPane();
        stack.setStyle("-fx-background-color:#2A363F;");


        VBox ticketscroll = new VBox();
        stack.getChildren().add(ticketscroll);

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
        Label Orgname = new Label("Organizer");
        Orgname.setStyle(textTable);
        Label RoomNo = new Label("Room / Date");
        RoomNo.setStyle(textTable);

        GraphStart.add(Ename, 0 ,0);
        GraphStart.add(Orgname, 1 ,0);
        GraphStart.add(RoomNo, 2 ,0);

        ticketscroll.getChildren().addAll(GraphStart);

        for (Event e : a.getMyevents()) {
            GridPane Graphcont = new GridPane();
            GraphStart.setVgap(30);
            GraphStart.setHgap(30);
            Graphcont.getColumnConstraints().addAll(colgrid1, colgrid2 ,colgrid3);
            Label TableLab1 = new Label(e.getName());
            TableLab1.setStyle(textTable);
            Label TableLab2 = new Label("      " + e.getMadeBy().getUsername());
            TableLab1.setStyle(textTable);
            Label TableLab3 = new Label(String.valueOf("Room: " + e.getRoom().getRoomNo() +" /  Date: " + e.getFormattedDate()));
            TableLab1.setStyle(textTable);
            Graphcont.add(TableLab1, 0 ,0);
            Graphcont.add(TableLab2, 1 ,0);
            Graphcont.add(TableLab3, 2 ,0);
            ticketscroll.getChildren().addAll(Graphcont);
        }

        scroller.setContent(stack);
        Ticketthings.getChildren().addAll(Ticket,scroller);

        Body.getChildren().addAll(usernameAndProfile,info,Ticketthings);
        BorPane.setCenter(Body);

        root = new Scene(BorPane,600,400);

    }
    


    public Scene getScene() {
        return root;
    }
}
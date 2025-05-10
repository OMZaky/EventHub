package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.regex.Pattern;

public class AttendeeSearch {
    private Attendee a;
    private Scene root;

    public Scene getScene() {
        return root;
    }

    public AttendeeSearch(Attendee attendee, SceneManager sceneManager){
        this.a = attendee;
        String styleBg = "-fx-background-color: #2A363F;";
        String ButStyleUA = "-fx-background-color:#6ED9A0; -fx-text-fill: white;";
        String ButStyleA = "-fx-background-color:#2A363F; -fx-text-fill: white;";
        String textHeader = "-fx-font-family:'Century Gothic'; -fx-font-size : 25;-fx-text-fill: #ffffff;";
        String textNormal = "-fx-font-family:'Century Gothic'; -fx-font-size : 15;-fx-text-fill: #ffffff; ";
        String textTable = "-fx-font-family:'Century Gothic'; -fx-font-size : 20;-fx-text-fill: #ffffff; ";

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

        UserInfoBut.setOnAction(e -> sceneManager.switchToAttendeeInfo(a));


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
        but4.disableProperty().set(true);

        but4.setStyle(ButStyleUA);
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



        VBox totalSituation = new VBox();


        StackPane LabelStack = new StackPane();
        LabelStack.setAlignment(Pos.CENTER);
        LabelStack.prefHeightProperty().bind(totalSituation.heightProperty().multiply(0.07));

        GridPane textgrid = new GridPane();
        textgrid.prefHeightProperty().bind(totalSituation.heightProperty().multiply(0.07));


        Label theLabel = new Label("Enter Person's Name");
        theLabel.setStyle(textHeader);

        LabelStack.getChildren().add(theLabel);

        TextField Searchfield = new TextField();
        textgrid.getChildren().add(Searchfield);


        GridPane gridPane = new GridPane();

        VBox.setMargin(LabelStack, new Insets(0, 0, 15, 0)); // top, right, bottom, left


        ColumnConstraints colgrid1 = new ColumnConstraints();
        colgrid1.setPercentWidth(10);
        ColumnConstraints colgrid2 = new ColumnConstraints();
        colgrid2.setPercentWidth(80);
        ColumnConstraints colgrid3 = new ColumnConstraints();
        colgrid3.setPercentWidth(10);


        gridPane.getColumnConstraints().addAll(colgrid1, colgrid2, colgrid3);

        gridPane.add(Searchfield, 1,0);


        totalSituation.getChildren().addAll(LabelStack,gridPane);

        VBox all = new VBox();


        Searchfield.setOnKeyTyped(e -> {



                    switch (searchEvent(Searchfield.getText())){

                        case Event w -> {

                            all.getChildren().clear();
                            totalSituation.getChildren().remove(all);
                            GridPane GraphStart = new GridPane();
                            GraphStart.setVgap(30);
                            GraphStart.setHgap(30);
                            ColumnConstraints col1 = new ColumnConstraints();
                            col1.setPercentWidth(17);
                            ColumnConstraints col2 = new ColumnConstraints();
                            col2.setPercentWidth(20);
                            ColumnConstraints col3 = new ColumnConstraints();
                            col3.setPercentWidth(20);

                            GraphStart.getColumnConstraints().addAll(col1, col2, col3);



                            Label name = new Label("Name");
                            name.setStyle(textTable);
                            Label category = new Label("Category");
                            category.setStyle(textTable);
                            Label ticketprice = new Label("Ticket Price");
                            ticketprice.setStyle(textTable);
                            Label time = new Label("Event Time");
                            time.setStyle(textTable);
                            Label roomno = new Label("Room No.");
                            roomno.setStyle(textTable);
                            Label organizer = new Label("Made By");
                            organizer.setStyle(textTable);

                            Label name1 = new Label(w.getName());
                            name1.setStyle(textNormal);
                            Label category1 = new Label(w.getCategory().getName());
                            category1.setStyle(textNormal);
                            Label ticketprice1 = new Label(String.valueOf(w.getTicketPrice()));
                            ticketprice1.setStyle(textNormal);
                            Label time1 = new Label(w.getFormattedDate());
                            time1.setStyle(textNormal);
                            Label roomno1 = new Label("Room No." + String.valueOf(w.getRoom().getRoomNo()));
                            roomno1.setStyle(textNormal);
                            Label organizer1 = new Label(w.getMadeBy().getUsername());
                            organizer1.setStyle(textNormal);


                            GraphStart.add(name, 0, 0);
                            GraphStart.add(category, 1, 0);
                            GraphStart.add(ticketprice, 2, 0);
                            GraphStart.add(time, 0, 2);
                            GraphStart.add(roomno, 1,2);
                            GraphStart.add(organizer, 2, 2);
                            GraphStart.add(name1, 0, 1);
                            GraphStart.add(category1, 1, 1);
                            GraphStart.add(ticketprice1, 2, 1);
                            GraphStart.add(time1, 0, 3);
                            GraphStart.add(roomno1, 1,3);
                            GraphStart.add(organizer1, 2, 3);


                            all.getChildren().add(GraphStart);
                            totalSituation.getChildren().add(all);

                        }

                        case null -> {

                            all.getChildren().clear();
                            totalSituation.getChildren().remove(all);
                        }

                    }







        });

        VBox.setMargin(all, new Insets(25, 0, 0, 0)); // top, right, bottom, left

        BorPane.setCenter(totalSituation);


        root = new Scene(BorPane, 600, 400);





    }

    public Event searchEvent(String name){
        Event foundevent = null;
        for( Event e : Database.events){
            if(name.matches(e.getName()+"*")){
                foundevent = e;

            }


        }
        return foundevent;
    }
}

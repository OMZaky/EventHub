package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Organizer_CRUD {

    public Organizer_CRUD(Organizer organizer, SceneManager sceneManager){

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
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonPadding = ButtonsVbox.getHeight() * 0.08;
            ButtonsVbox.setPadding(new Insets(ButtonPadding, 0, 0, 0));
        });

        Pane linePane = new Pane();
        linePane.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(linePane, Priority.ALWAYS);

        leftHbox.setFillHeight(true);
        leftHbox.getChildren().addAll(ButtonsVbox, linePane);

        JFXButton UserInfoBut = new JFXButton("User Info");
        UserInfoBut.setMaxWidth(Double.MAX_VALUE);
        UserInfoBut.setStyle(ButStyleA);
        UserInfoBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonPadding = ButtonsVbox.getHeight() * 0.08;
            ButtonsVbox.setPadding(new Insets(ButtonPadding, 0, 0, 0));
        });


        JFXButton ShowBut = new JFXButton("Show Data");
        ShowBut.setMaxWidth(Double.MAX_VALUE);
        ShowBut.setStyle(ButStyleA);
        ShowBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(ShowBut, new Insets(ButtonMar, 0, 0, 0));
        });

        JFXButton CRUDbut = new JFXButton("CRUD");
        CRUDbut.disableProperty().set(true);
        CRUDbut.setMaxWidth(Double.MAX_VALUE);
        CRUDbut.setStyle(ButStyleUA);
        CRUDbut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(CRUDbut, new Insets(ButtonMar, 0, 0, 0));
        });


        JFXButton LogOutBut = new JFXButton("Log out");
        LogOutBut.setMaxWidth(Double.MAX_VALUE);
        LogOutBut.setStyle(ButStyleA);
        LogOutBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(LogOutBut, new Insets(ButtonMar, 0, 0, 0));
        });

        ButtonsVbox.getChildren().addAll(UserInfoBut, ShowBut, CRUDbut, LogOutBut);

        Line l = new Line();
        l.setStroke(Color.web("#465058"));

        l.startXProperty().bind(linePane.widthProperty().divide(2));
        l.endXProperty().bind(linePane.widthProperty().divide(2));

        l.startYProperty().bind(linePane.heightProperty().multiply(0.1));
        l.endYProperty().bind(linePane.heightProperty().multiply(0.8));

        linePane.getChildren().add(l);


        BorPane.setLeft(leftHbox);


        VBox crudMain = new VBox();

        HBox crudnamelocate = new HBox();
        crudMain.widthProperty().addListener((obs, oldPad, newPad) -> {
            double Labelmar = crudMain.getWidth()* 0.05;
            VBox.setMargin(crudnamelocate, new Insets(Labelmar, 0, 0, 0));
        });
        crudnamelocate.setAlignment(Pos.CENTER_LEFT);
        Label CRUD = new Label("CRUD");
        CRUD.setStyle(textHeader);
        crudnamelocate.getChildren().add(CRUD);

        Pane functionality = new Pane();
        functionality.prefWidthProperty().bind(crudMain.widthProperty());
        functionality.prefHeightProperty().bind(crudMain.heightProperty().multiply(0.44));
        crudMain.heightProperty().addListener((obs, oldPad, newPad) -> {
            double pane = crudMain.getHeight()* 0.05;
            VBox.setMargin(crudnamelocate, new Insets(pane, 0, 0, 0));
        });
        functionality.setStyle("-fx-background-color:#ffffff;");


        GridPane Buttons = new GridPane();
        Buttons.prefHeightProperty().bind(crudMain.heightProperty().multiply(0.20));
        Buttons.setVgap(30);
        Buttons.setHgap(30);
        crudMain.widthProperty().addListener((obs,oldPad,newPad)->{
            double ButtonMar = crudMain.getWidth()*0.045;
            VBox.setMargin(Buttons, new Insets(0,ButtonMar,0,ButtonMar));
        });

        ColumnConstraints colgrid1 = new ColumnConstraints();
        colgrid1.setPercentWidth(25);

        ColumnConstraints colgrid2 = new ColumnConstraints();
        colgrid2.setPercentWidth(25);

        ColumnConstraints colgrid3 = new ColumnConstraints();
        colgrid3.setPercentWidth(25);

        ColumnConstraints colgrid4 = new ColumnConstraints();
        colgrid4.setPercentWidth(25);

        Buttons.getColumnConstraints().addAll(colgrid1, colgrid2 ,colgrid3,colgrid4);

        JFXButton but1 = new JFXButton("Create Cate.");
        but1.setStyle(ButStyleUA);
        GridPane.setHgrow(but1, Priority.ALWAYS);
        GridPane.setVgrow(but1, Priority.ALWAYS);
        but1.setMaxWidth(Double.MAX_VALUE);


        JFXButton but2 = new JFXButton("Read Cate.");
        but2.setStyle(ButStyleUA);
        GridPane.setHgrow(but2, Priority.ALWAYS);
        GridPane.setVgrow(but2, Priority.ALWAYS);
        but2.setMaxWidth(Double.MAX_VALUE);


        JFXButton but3 = new JFXButton("Update Cate.");
        but3.setStyle(ButStyleUA);
        GridPane.setHgrow(but3, Priority.ALWAYS);
        GridPane.setVgrow(but3, Priority.ALWAYS);
        but3.setMaxWidth(Double.MAX_VALUE);


        JFXButton but4 = new JFXButton("Delete Cate.");
        but4.setStyle(ButStyleUA);
        GridPane.setHgrow(but4, Priority.ALWAYS);
        GridPane.setVgrow(but4, Priority.ALWAYS);
        but4.setMaxWidth(Double.MAX_VALUE);

        Buttons.add(but1, 0 ,0);
        Buttons.add(but2, 1 ,0);
        Buttons.add(but3, 2 ,0);
        Buttons.add(but4, 3 ,0);

        VBox Room = new VBox();
        Room.setAlignment(Pos.TOP_CENTER);

        JFXButton createroom = new JFXButton("Create Room");
        createroom.prefWidthProperty().bind(Room.widthProperty().multiply(0.25));
        createroom.setStyle(ButStyleUA);
        crudMain.heightProperty().addListener((obs, oldPad, newPad) -> {
            double pane = crudMain.getHeight()* 0.05;
            VBox.setMargin(Room, new Insets(pane, 0, 0, 0));
        });


        Label shitman  = new Label("my name is omar akram");
        shitman.setStyle(textTable);
        Room.getChildren().addAll(createroom,shitman);



        crudMain.getChildren().addAll(crudnamelocate,Buttons,functionality,Room);

        BorPane.setCenter(crudMain);
        Scene root = new Scene(BorPane, 600, 400);


    }

}

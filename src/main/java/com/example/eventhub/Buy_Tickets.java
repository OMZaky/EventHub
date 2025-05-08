package com.example.eventhub;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.jfoenix.controls.JFXButton;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
/**
 *
 * @author omara
 */
public class Buy_Tickets{
        private Scene root;
        private Attendee a;


        public Buy_Tickets(Attendee a ,SceneManager sceneManager){
                this.a = a;
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

                UserInfoBut.setOnAction(e -> sceneManager.switchToAttendeeInfo(a));



                JFXButton BuyBut = new JFXButton("Buy Tickets");
                BuyBut.disableProperty().set(true);
                BuyBut.setMaxWidth(Double.MAX_VALUE);
                BuyBut.setStyle(ButStyleUA);
                BuyBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
                ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
                        double ButtonMar = ButtonsVbox.getHeight()*0.025;
                        VBox.setMargin(BuyBut, new Insets(ButtonMar,0,0,0));
                });

                JFXButton LogOutBut = new JFXButton("Log out");
                LogOutBut.setMaxWidth(Double.MAX_VALUE);
                LogOutBut.setStyle(ButStyleA);
                LogOutBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
                ButtonsVbox.heightProperty().addListener((obs,oldPad,newPad)->{
                        double ButtonMar = ButtonsVbox.getHeight()*0.025;
                        VBox.setMargin(LogOutBut, new Insets(ButtonMar,0,0,0));
                });

                LogOutBut.setOnAction(e -> sceneManager.switchToLogout(a));


                ButtonsVbox.getChildren().addAll(UserInfoBut,BuyBut,LogOutBut);

                Line l = new Line();
                l.setStroke(Color.web("#465058"));

                l.startXProperty().bind(linePane.widthProperty().divide(2));
                l.endXProperty().bind(linePane.widthProperty().divide(2));

                l.startYProperty().bind(linePane.heightProperty().multiply(0.1));
                l.endYProperty().bind(linePane.heightProperty().multiply(0.8));

                linePane.getChildren().add(l);
                BorPane.setLeft(leftHbox);

                ScrollPane scroller = new ScrollPane();
                scroller.setFitToWidth(true);
                scroller.setFitToHeight(true);
                scroller.setStyle("-fx-background: #2A363F; -fx-background-color: #2A363F;");
                ArrayList<Event> events = a.getBuyableEvents();
                VBox PLEASE = new VBox();
                PLEASE.prefWidthProperty().bind(scroller.widthProperty().multiply(0.95));
                for (Event e : events) {
                        HBox per1 = new HBox();
                        per1.prefWidthProperty().bind(PLEASE.widthProperty().multiply(1));
                        per1.setStyle("-fx-background-color:#2A363F;");
                        VBox details = new VBox();
                        details.prefWidthProperty().bind(per1.widthProperty().multiply(0.7));
                        Label name = new Label("Event name : " + e.getName());
                        name.setStyle(textHeader);
                        Label Cate = new Label("Category : " + e.getCategoryname());
                        Cate.setStyle(textNormal);
                        Label MadeBy = new Label("Made by : " + e.getMadeBy().getUsername());
                        MadeBy.setStyle(textNormal);
                        Label price = new Label("Price : " + e.getTicketPrice());
                        price.setStyle(textNormal);
                        details.getChildren().addAll(name,Cate,MadeBy,price);


                        HBox magicBut = new HBox();
                        magicBut.prefWidthProperty().bind(per1.widthProperty().multiply(0.25));
                        magicBut.setAlignment(Pos.BOTTOM_RIGHT);

                        JFXButton buy= new JFXButton("Buy");
                        buy.setStyle(ButStyleUA);
                        magicBut.getChildren().add(buy);
                        per1.getChildren().addAll(details,magicBut);
                        PLEASE.getChildren().addAll(per1);

                        buy.setOnAction(x -> {
                                buy.setDisable(true);
                                a.buy(e);});
                }
                scroller.setContent(PLEASE);
                BorPane.setCenter(scroller);

                root = new Scene(BorPane,600,400);


        }


        public Scene getScene() {
                return root;
        }
}

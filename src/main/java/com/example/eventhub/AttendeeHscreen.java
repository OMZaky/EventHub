package com.example.eventhub;

import com.example.eventhub.Attendee;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class AttendeeHscreen{
       private Attendee a;
       private Scene root;

    public AttendeeHscreen(Attendee a, SceneManager sceneManager) {
        this.a = a;
        String styleBg = "-fx-background-color: #2A363F;";
        String ButStyleUA = "-fx-background-color:#6ED9A0; -fx-text-fill: white;";
        String ButStyleA = "-fx-background-color:#2A363F; -fx-text-fill: white;";

        String textHeader = "-fx-font-family:'Century Gothic'; -fx-font-size : 40;-fx-text-fill: #ffffff;";
        String textNormal = "-fx-font-family:'Century Gothic'; -fx-font-size : 16;-fx-text-fill: #ffffff; ";
        String textTable = "-fx-font-family:'Century Gothic'; -fx-font-size : 18;-fx-text-fill: #ffffff; ";

        VBox HomePageAT = new VBox();
        StackPane name = new StackPane();
        name.prefHeightProperty().bind(HomePageAT.heightProperty().multiply(0.33));        HomePageAT.setStyle(styleBg);
        Label Welcome= new Label("Hello");
        Welcome.setStyle(textHeader);
        name.getChildren().add(Welcome);

        Pane theLines = new Pane();
        theLines.prefHeightProperty().bind(HomePageAT.heightProperty().multiply(0.10));
        Line l1 = new Line();
        Line l2 = new Line();
        l1.setStroke(Color.web("#465058"));
        l1.setStrokeWidth(10);
        l2.setStroke(Color.web("#465058"));
        l2.setStrokeWidth(5);

        l1.startXProperty().bind(theLines.widthProperty());
        l1.endXProperty().bind(theLines.widthProperty().multiply(0.3));

        l1.startYProperty().bind(theLines.heightProperty().multiply(0.0));
        l1.endYProperty().bind(theLines.heightProperty().multiply(0.0));


        l2.startXProperty().bind(theLines.widthProperty());
        l2.endXProperty().bind(theLines.widthProperty().multiply(0.7));

        l2.startYProperty().bind(theLines.heightProperty().multiply(0.3));
        l2.endYProperty().bind(theLines.heightProperty().multiply(0.3));
        theLines.getChildren().addAll(l1,l2);



        GridPane Buttons = new GridPane();
        Buttons.prefHeightProperty().bind(HomePageAT.heightProperty().multiply(0.20));
        Buttons.setVgap(30);
        Buttons.setHgap(30);
        HomePageAT.widthProperty().addListener((obs,oldPad,newPad)->{
            double ButtonMar = HomePageAT.getWidth()*0.045;
            VBox.setMargin(Buttons, new Insets(0,ButtonMar,0,ButtonMar));
        });

        ColumnConstraints colgrid1 = new ColumnConstraints();
        colgrid1.setPercentWidth(33);

        ColumnConstraints colgrid2 = new ColumnConstraints();
        colgrid2.setPercentWidth(33);

        ColumnConstraints colgrid3 = new ColumnConstraints();
        colgrid3.setPercentWidth(34);
        Buttons.getColumnConstraints().addAll(colgrid1, colgrid2 ,colgrid3);

        JFXButton but1 = new JFXButton("User Info");
        but1.setStyle(ButStyleUA);
        GridPane.setHgrow(but1, Priority.ALWAYS);
        GridPane.setVgrow(but1, Priority.ALWAYS);
        but1.setMaxWidth(Double.MAX_VALUE);


        JFXButton but2 = new JFXButton("Buy Tickets");
        but2.setStyle(ButStyleUA);
        GridPane.setHgrow(but2, Priority.ALWAYS);
        GridPane.setVgrow(but2, Priority.ALWAYS);
        but2.setMaxWidth(Double.MAX_VALUE);


        JFXButton but3 = new JFXButton("Logout");
        but3.setStyle(ButStyleUA);
        GridPane.setHgrow(but3, Priority.ALWAYS);
        GridPane.setVgrow(but3, Priority.ALWAYS);
        but3.setMaxWidth(Double.MAX_VALUE);


        Buttons.add(but1, 0 ,0);
        Buttons.add(but2, 1 ,0);
        Buttons.add(but3, 2 ,0);


        HomePageAT.getChildren().addAll(name,theLines,Buttons);
        root = new Scene(HomePageAT,600,400);
    }


    public Scene getScene() {
        return root;
    }
}

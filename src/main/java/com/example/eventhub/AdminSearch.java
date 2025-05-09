package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class AdminSearch {
    
    private TextArea textBox;
    private Admin a;
    private Scene root;




    public AdminSearch(Admin admin, SceneManager sceneManager){

        this.a = admin;
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

        UserInfoBut.setOnAction(e -> sceneManager.switchToAdminInfo(a));


        JFXButton ShowBut = new JFXButton("Show Data");
        ShowBut.setMaxWidth(Double.MAX_VALUE);
        ShowBut.setStyle(ButStyleA);
        ShowBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(ShowBut, new Insets(ButtonMar, 0, 0, 0));
        });

        ShowBut.setOnAction(e -> sceneManager.switchToAdminShow(a));

        JFXButton CRUDbut = new JFXButton("CRUD");
        CRUDbut.setMaxWidth(Double.MAX_VALUE);
        CRUDbut.setStyle(ButStyleA);
        CRUDbut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(CRUDbut, new Insets(ButtonMar, 0, 0, 0));
        });

        //CRUDbut.setOnAction(e -> sceneManager.switchToAdminCRUD(a));



        JFXButton Searchbut = new JFXButton("Search");
        Searchbut.setMaxWidth(Double.MAX_VALUE);
        Searchbut.disableProperty().set(true);
        Searchbut.setStyle(ButStyleUA);
        Searchbut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(Searchbut, new Insets(ButtonMar, 0, 0, 0));
        });



        JFXButton LogOutBut = new JFXButton("Log out");
        LogOutBut.setMaxWidth(Double.MAX_VALUE);
        LogOutBut.setStyle(ButStyleA);
        LogOutBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(LogOutBut, new Insets(ButtonMar, 0, 0, 0));
        });

        LogOutBut.setOnAction(e -> sceneManager.switchToLogout(a));

        ButtonsVbox.getChildren().addAll(UserInfoBut, ShowBut,Searchbut, CRUDbut, LogOutBut);

        Line l = new Line();
        l.setStroke(Color.web("#465058"));

        l.startXProperty().bind(linePane.widthProperty().divide(2));
        l.endXProperty().bind(linePane.widthProperty().divide(2));

        l.startYProperty().bind(linePane.heightProperty().multiply(0.1));
        l.endYProperty().bind(linePane.heightProperty().multiply(0.8));

        linePane.getChildren().add(l);

        BorPane.setLeft(leftHbox);

        root = new Scene(BorPane, 600, 400);

//        textBox = new TextArea();
//
//        // Add a listener to the text property of the text box
//        textBox.textProperty().addListener((observable, oldValue, newValue) -> {
//            for (Event event : Database.events) {
//                if (event.getName().equalsIgnoreCase(newValue)) {
//                    System.out.println("Event found.");
//                    return;
//                }
//            }
//            System.out.println("Event not found.");
//        });









    }


    public Scene getScene() {
        return root;
    }


    // public boolean match(String input, String target) {
    //     if (input == null || target == null || target.isEmpty() || input.length() < target.length()) {
    //         return false;
    //     }

    //     for (int i = 0; i <= input.length() - target.length(); i++) {
    //         boolean foundMatch = true;
    //         for (int j = 0; j < target.length(); j++) {
    //             if (input.charAt(i + j) != target.charAt(j)) {
    //                 foundMatch = false;
    //                 break;
    //             }
    //         }
    //         if (foundMatch) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
}

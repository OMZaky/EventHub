package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class OrganizerInfo{

    private Organizer o;
    private Scene root;

    public Scene getScene() {
        return root;
    }

    public OrganizerInfo(Organizer o, SceneManager sceneManager) {
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
        UserInfoBut.disableProperty().set(true);
        UserInfoBut.setMaxWidth(Double.MAX_VALUE);
        UserInfoBut.setStyle(ButStyleUA);
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
        CRUDbut.setMaxWidth(Double.MAX_VALUE);
        CRUDbut.setStyle(ButStyleA);
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


        VBox Body = new VBox();
        Label welcomin = new Label("Welcome" + o.getUsername() );
        welcomin.setStyle(textHeader);

        GridPane usernameAndProfile = new GridPane();
        BorPane.heightProperty().addListener((obs, oldPad, newPad) -> {
            double gridMar = BorPane.getHeight() * 0.03;
            Body.setMargin(usernameAndProfile, new Insets(gridMar, 0, 0, 0));
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
        profileContainer.widthProperty().addListener((obs, oldPad, newPad) -> {
            double CirclePadding = profileContainer.getWidth() * 0.25;
            profileContainer.setPadding(new Insets(0, CirclePadding, 0, 0));
        });


        profileContainer.getChildren().add(profilePlaceholder);
        usernameAndProfile.add(welcomin, 0, 0);
        usernameAndProfile.add(profileContainer, 1, 0);

        HBox info = new HBox();
        VBox main3 = new VBox();
        BorPane.heightProperty().addListener((obs, oldPad, newPad) -> {
            double gridMar = BorPane.getHeight() * 0.05;
            Body.setMargin(main3, new Insets(gridMar, 0, 0, 0));
        });

        Label password = new Label("Password : " + o.getPassword());
        password.setStyle(textNormal);

        Label balance = new Label("Balance : " + o.getBalance());
        balance.setStyle(textNormal);


        main3.getChildren().addAll(password, balance);


        Pane theLines = new Pane();
        theLines.prefHeightProperty().bind(Body.heightProperty());
        Line l1 = new Line();
        Line l2 = new Line();
        l1.setStroke(Color.web("#465058"));
        l1.setStrokeWidth(7);
        l2.setStroke(Color.web("#465058"));
        l2.setStrokeWidth(4);

        l1.startXProperty().bind(theLines.widthProperty());
        l1.endXProperty().bind(theLines.widthProperty().multiply(0.3));

        l1.startYProperty().bind(theLines.heightProperty().multiply(0.29));
        l1.endYProperty().bind(theLines.heightProperty().multiply(0.29));


        l2.startXProperty().bind(theLines.widthProperty().multiply(0));
        l2.endXProperty().bind(theLines.widthProperty().multiply(0.8));

        l2.startYProperty().bind(theLines.heightProperty().multiply(0.37));
        l2.endYProperty().bind(theLines.heightProperty().multiply(0.37));
        theLines.getChildren().addAll(l1, l2);


        info.getChildren().addAll(main3);

        Body.getChildren().addAll(usernameAndProfile, info, theLines);
        BorPane.setCenter(Body);

        root = new Scene(BorPane, 600, 400);
    }

}

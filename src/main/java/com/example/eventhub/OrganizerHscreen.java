package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class OrganizerHscreen {
    private Organizer o;
    private Scene root;

    public OrganizerHscreen(Organizer o, SceneManager sceneManager) {
        this.o = o;
        String styleBg = "-fx-background-color: #2A363F;";
        String ButStyleUA = "-fx-background-color:#6ED9A0; -fx-text-fill: white;";
        String ButStyleA = "-fx-background-color:#2A363F; -fx-text-fill: white;";

        String textHeader = "-fx-font-family:'Century Gothic'; -fx-font-size : 40;-fx-text-fill: #ffffff;";
        String textNormal = "-fx-font-family:'Century Gothic'; -fx-font-size : 16;-fx-text-fill: #ffffff; ";
        String textTable = "-fx-font-family:'Century Gothic'; -fx-font-size : 18;-fx-text-fill: #ffffff; ";

        VBox HomePageAT = new VBox();
        StackPane name = new StackPane();
        name.prefHeightProperty().bind(HomePageAT.heightProperty().multiply(0.33));
        HomePageAT.setStyle(styleBg);
        Label Welcome = new Label("Hello");
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
        theLines.getChildren().addAll(l1, l2);

        GridPane Buttons1 = new GridPane();
        GridPane Buttons2 = new GridPane();

        Buttons1.prefHeightProperty().bind(HomePageAT.heightProperty().multiply(0.20));
        Buttons1.setVgap(30);
        Buttons1.setHgap(30);
        HomePageAT.widthProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = HomePageAT.getWidth() * 0.045;
            VBox.setMargin(Buttons1, new Insets(0, ButtonMar, 0, ButtonMar));
        });

        Buttons2.prefHeightProperty().bind(HomePageAT.heightProperty().multiply(0.20));
        Buttons2.setVgap(30);
        Buttons2.setHgap(30);
        HomePageAT.widthProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = HomePageAT.getWidth() * 0.045;
            VBox.setMargin(Buttons2, new Insets(0, ButtonMar, 0, ButtonMar));
        });

        ColumnConstraints colgrid1 = new ColumnConstraints();
        colgrid1.setPercentWidth(33);
        ColumnConstraints colgrid2 = new ColumnConstraints();
        colgrid2.setPercentWidth(33);
        ColumnConstraints colgrid3 = new ColumnConstraints();
        colgrid3.setPercentWidth(34);
        ColumnConstraints colgrid4 = new ColumnConstraints();
        colgrid4.setPercentWidth(17);
        ColumnConstraints colgrid5 = new ColumnConstraints();
        colgrid5.setPercentWidth(33);
        ColumnConstraints colgrid6 = new ColumnConstraints();
        colgrid6.setPercentWidth(33);
        ColumnConstraints colgrid7 = new ColumnConstraints();
        colgrid7.setPercentWidth(17);
        Buttons1.getColumnConstraints().addAll(colgrid1, colgrid2, colgrid3);
        Buttons2.getColumnConstraints().addAll(colgrid4,colgrid5,colgrid6,colgrid7);

        JFXButton but1 = new JFXButton("Organizer Info");
        but1.setStyle(ButStyleUA);
        GridPane.setHgrow(but1, Priority.ALWAYS);
        GridPane.setVgrow(but1, Priority.ALWAYS);
        but1.setMaxWidth(Double.MAX_VALUE);

        but1.setOnAction(e -> sceneManager.switchToOrganizerInfo(o));

        JFXButton but2 = new JFXButton("Show Data");
        but2.setStyle(ButStyleUA);
        GridPane.setHgrow(but2, Priority.ALWAYS);
        GridPane.setVgrow(but2, Priority.ALWAYS);
        but2.setMaxWidth(Double.MAX_VALUE);

        but2.setOnAction(e -> sceneManager.switchToOrganizerShow(o));

        JFXButton but3 = new JFXButton("Crud Events");
        but3.setStyle(ButStyleUA);
        GridPane.setHgrow(but3, Priority.ALWAYS);
        GridPane.setVgrow(but3, Priority.ALWAYS);
        but3.setMaxWidth(Double.MAX_VALUE);


        but3.setOnAction(e -> sceneManager.switchToOrganizerCRUD(o));


        JFXButton Searchbut = new JFXButton("Search");
        Searchbut.setStyle(ButStyleUA);
        GridPane.setHgrow(Searchbut, Priority.ALWAYS);
        GridPane.setVgrow(Searchbut, Priority.ALWAYS);
        Searchbut.setMaxWidth(Double.MAX_VALUE);

        Searchbut.setOnAction(e -> sceneManager.switchToOrganizerSearch(o));

        JFXButton but4 = new JFXButton("Logout");
        but4.setStyle(ButStyleUA);
        GridPane.setHgrow(but4, Priority.ALWAYS);
        GridPane.setVgrow(but4, Priority.ALWAYS);
        but4.setMaxWidth(Double.MAX_VALUE);

        but4.setOnAction(e -> sceneManager.switchToLogout(o));

        Buttons1.add(but1, 0, 0);
        Buttons1.add(but2, 1, 0);
        Buttons1.add(but3, 2, 0);
        Buttons2.add(Searchbut,1,0);
        Buttons2.add(but4, 2,0);
        HomePageAT.getChildren().addAll(name, theLines, Buttons1,Buttons2);
        root = new Scene(HomePageAT, 600, 400);
    }

    public Scene getScene() {
        return root;
    }
}

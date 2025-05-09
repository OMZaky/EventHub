package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import javax.swing.*;
import java.util.ArrayList;

public class AdminHscreen {
    private Admin admin;
    private Scene root;

    private Attendee attendee  ;

    public AdminHscreen(Admin admin, SceneManager sceneManager) {
        this.admin = admin;
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

        GridPane Buttons = new GridPane();
        Buttons.prefHeightProperty().bind(HomePageAT.heightProperty().multiply(0.20));
        Buttons.setVgap(30);
        Buttons.setHgap(30);
        HomePageAT.widthProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = HomePageAT.getWidth() * 0.045;
            VBox.setMargin(Buttons, new Insets(0, ButtonMar, 0, ButtonMar));
        });

        ColumnConstraints colgrid1 = new ColumnConstraints();
        colgrid1.setPercentWidth(33);
        ColumnConstraints colgrid2 = new ColumnConstraints();
        colgrid2.setPercentWidth(33);
        ColumnConstraints colgrid3 = new ColumnConstraints();
        colgrid3.setPercentWidth(34);
        Buttons.getColumnConstraints().addAll(colgrid1, colgrid2, colgrid3);

        JFXButton but1 = new JFXButton("Admin Info");
        but1.setStyle(ButStyleUA);
        GridPane.setHgrow(but1, Priority.ALWAYS);
        GridPane.setVgrow(but1, Priority.ALWAYS);
        but1.setMaxWidth(Double.MAX_VALUE);
        //but1.setOnAction(e -> sceneManager.switchToAdminInfo(admin));

        JFXButton but2 = new JFXButton("Show Data");
        but2.setStyle(ButStyleUA);
        GridPane.setHgrow(but2, Priority.ALWAYS);
        GridPane.setVgrow(but2, Priority.ALWAYS);
        but2.setMaxWidth(Double.MAX_VALUE);
        //but2.setOnAction(e -> sceneManager.switchToShowdata(admin));

        JFXButton but3 = new JFXButton("Crud Categories");
        but3.setStyle(ButStyleUA);
        GridPane.setHgrow(but3, Priority.ALWAYS);
        GridPane.setVgrow(but3, Priority.ALWAYS);
        but3.setMaxWidth(Double.MAX_VALUE);
        //but3.setOnAction(e -> sceneManager.switchToCrudCategories(admin));

        JFXButton but4 = new JFXButton("Logout");
        but4.setStyle(ButStyleUA);
        GridPane.setHgrow(but4, Priority.ALWAYS);
        GridPane.setVgrow(but4, Priority.ALWAYS);
        but4.setMaxWidth(Double.MAX_VALUE);
        but4.setOnAction(e -> sceneManager.switchToLogout(admin));

        Buttons.add(but1, 0, 0);
        Buttons.add(but2, 1, 0);
        Buttons.add(but3, 2, 0);
        Buttons.add(but4, 1, 1);
        ScrollPane scroller = new ScrollPane();
        scroller.setFitToWidth(true);
        scroller.setFitToHeight(true);
        scroller.setStyle("-fx-background: #2A363F; -fx-background-color: #2A363F;");
        StackPane stack = new StackPane();
        stack.setStyle("-fx-background-color:#2A363F;");


        VBox adminscroll = new VBox();
        stack.getChildren().add(adminscroll);

        GridPane GraphStart = new GridPane();
        GraphStart.setVgap(30);
        GraphStart.setHgap(30);

        GraphStart.getColumnConstraints().addAll(colgrid1, colgrid2, colgrid3);

        Label Attendeename = new Label("Attendee");
        Attendeename.setStyle(textTable);
        Label Orgname = new Label("Organizer");
        Orgname.setStyle(textTable);
        Label EventName = new Label("Event name");
        EventName.setStyle(textTable);
        Label EventDate = new Label("Event date");

        GraphStart.add(Attendeename, 0, 0);
        GraphStart.add(Orgname, 1, 0);
        GraphStart.add(EventName, 2, 0);
        GraphStart.add(EventDate, 3, 0);

        adminscroll.getChildren().addAll(GraphStart);

        ArrayList<String> attendees = new ArrayList<>();
        ArrayList<String> organizers = new ArrayList<>();


        for (Person p : Database.people) {
            if (p instanceof Attendee) {
                attendees.add(p.getUsername());
            } else if (p instanceof Organizer) {
                organizers.add(p.getUsername());
            }
        }


        int max1 = Math.max(organizers.size(), Database.events.size());
        int max = Math.max(attendees.size(), max1);

        for (int i = 0; i < max; i++) {
            String attendee = (i < attendees.size() ? attendees.get(i) : "");
            String organizer = (i < organizers.size() ? organizers.get(i) : "");
            String event = (i < Database.events.size() ? Database.events.get(i).getName() : "");
            GridPane Graphcont = new GridPane();
            GraphStart.setVgap(30);
            GraphStart.setHgap(30);
            Graphcont.getColumnConstraints().addAll(colgrid1, colgrid2, colgrid3);
            Label TableLab1 = new Label(attendee);
            TableLab1.setStyle(textTable);
            Label TableLab2 = new Label(organizer);
            TableLab1.setStyle(textTable);
            Label TableLab3 = new Label(event);
            TableLab1.setStyle(textTable);
            Label TableLab4 = new Label();
            TableLab1.setStyle(textTable);

            Graphcont.add(TableLab1, 0, 0);
            Graphcont.add(TableLab2, 1, 0);
            Graphcont.add(TableLab3, 2, 0);
            Graphcont.add(TableLab4, 3, 0);
            adminscroll.getChildren().addAll(Graphcont);
        }





        scroller.setContent(stack);


        HomePageAT.getChildren().addAll(name, theLines, Buttons);
        root = new Scene(HomePageAT, 600, 400);
    }

    public Scene getScene() {
        return root;
    }
}

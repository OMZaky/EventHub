package com.example.eventhub;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AdminSearch {

    private TextArea textBox;
    private Admin a;
    private Scene root;




    public AdminSearch(Admin admin, SceneManager sceneManager){

        this.a = admin;
        String styleBg = "-fx-background-color: #2A363F;";
        String ButStyleUA = "-fx-background-color:#6ED9A0; -fx-text-fill: white;";
        String ButStyleA = "-fx-background-color:#2A363F; -fx-text-fill: white;";
        String textHeader = "-fx-font-family:'Century Gothic'; -fx-font-size : 15;-fx-text-fill: #ffffff;";
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

            switch (searchusername(Searchfield.getText())){
                case Attendee w-> {



                    all.getChildren().clear();
                    totalSituation.getChildren().remove(all);
                    GridPane GraphStart = new GridPane();
                    GraphStart.setVgap(30);
                    GraphStart.setHgap(30);
                    ColumnConstraints col1 = new ColumnConstraints();
                    col1.setPercentWidth(20);
                    ColumnConstraints col2 = new ColumnConstraints();
                    col2.setPercentWidth(20);
                    ColumnConstraints col3 = new ColumnConstraints();
                    col3.setPercentWidth(20);
                    ColumnConstraints col4 = new ColumnConstraints();
                    col4.setPercentWidth(20);
                    ColumnConstraints col5 = new ColumnConstraints();
                    col5.setPercentWidth(20);

                    GraphStart.getColumnConstraints().addAll(col1, col2, col3, col4,col5);



                    Label Attendeename = new Label("Attendee");
                    Attendeename.setStyle(textTable);
                    Label gender = new Label("Gender");
                    gender.setStyle(textTable);
                    Label balance = new Label("Balance");
                    balance.setStyle(textTable);
                    Label Dob = new Label("DOB");
                    Dob.setStyle(textTable);
                    Label address = new Label("Address");
                    address.setStyle(textTable);

                    Label Attendeename1 = new Label(w.getUsername());
                    Attendeename1.setStyle(textNormal);
                    Label gender1 = new Label(
                            switch (w.getGender()){
                                case Gender.MALE -> "Male";
                                case Gender.FEMALE -> "Female";
                            });
                    gender1.setStyle(textNormal);
                    Label balance1 = new Label(String.valueOf(w.getWallet().getBalance()));
                    balance1.setStyle(textNormal);
                    Label Dob1 = new Label(w.getFormattedbirthDate());
                    Dob1.setStyle(textNormal);
                    Label address1 = new Label(w.getAddress());
                    address1.setStyle(textNormal);


                    GraphStart.add(Attendeename, 0, 0);
                    GraphStart.add(gender, 1, 0);
                    GraphStart.add(balance, 2, 0);
                    GraphStart.add(Dob, 3, 0);
                    GraphStart.add(address, 4,0);
                    GraphStart.add(Attendeename1, 0, 1);
                    GraphStart.add(gender1, 1, 1);
                    GraphStart.add(balance1, 2, 1);
                    GraphStart.add(Dob1, 3, 1);
                    GraphStart.add(address1, 4,1);


                    all.getChildren().add(GraphStart);
                    totalSituation.getChildren().add(all);
                }
                case Organizer w-> {



                    all.getChildren().clear();
                    totalSituation.getChildren().remove(all);
                    GridPane GraphStart = new GridPane();
                    GraphStart.setVgap(30);
                    GraphStart.setHgap(30);
                    ColumnConstraints col1 = new ColumnConstraints();
                    col1.setPercentWidth(33);
                    ColumnConstraints col2 = new ColumnConstraints();
                    col2.setPercentWidth(33);
                    ColumnConstraints col3 = new ColumnConstraints();
                    col3.setPercentWidth(34);


                    GraphStart.getColumnConstraints().addAll(col1, col2, col3);



                    Label Attendeename = new Label("Attendee");
                    Attendeename.setStyle(textTable);

                    Label balance = new Label("Balance");
                    balance.setStyle(textTable);

                    Label Dob = new Label("DOB");
                    Dob.setStyle(textTable);

                    Label Attendeename1 = new Label(w.getUsername());
                    Attendeename1.setStyle(textNormal);

                    Label balance1 = new Label(String.valueOf(w.getWallet().getBalance()));
                    balance1.setStyle(textNormal);

                    Label Dob1 = new Label(w.getFormattedbirthDate());
                    Dob1.setStyle(textNormal);



                    GraphStart.add(Attendeename, 0, 0);
                    GraphStart.add(balance, 1, 0);
                    GraphStart.add(Dob, 2, 0);
                    GraphStart.add(Attendeename1, 0, 1);
                    GraphStart.add(balance1, 1, 1);
                    GraphStart.add(Dob1, 2, 1);


                    all.getChildren().add(GraphStart);
                    totalSituation.getChildren().add(all);
                }
                case null -> {
                    all.getChildren().clear();
                    totalSituation.getChildren().remove(all);
                }

                default ->{
                }
            }



        });

        VBox.setMargin(all, new Insets(25, 0, 0, 0)); // top, right, bottom, left

        BorPane.setCenter(totalSituation);


        root = new Scene(BorPane, 600, 400);










    }

    public Person searchusername(String name){
        Person founduser = null;
        for( Person p : Database.people){
            if(name.equalsIgnoreCase(p.getUsername())){
                founduser = p;

            }


        }
        return founduser;
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

package com.example.eventhub;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Organizer_CRUD {
    private Organizer organizer;
    private Scene root;

    public Scene getScene() {
        return root;
    }

    public Organizer_CRUD(Organizer organizer, SceneManager sceneManager){

        this.organizer = organizer;

        String styleBg = "-fx-background-color: #2A363F;";
        String ButStyleUA = "-fx-background-color:#6ED9A0; -fx-text-fill: white;";
        String ButStyleA = "-fx-background-color:#2A363F; -fx-text-fill: white;";
        String textHeader = "-fx-font-family:'Century Gothic'; -fx-font-size : 25;-fx-text-fill: #ffffff;";
        String textNormal = "-fx-font-family:'Century Gothic'; -fx-font-size : 16;-fx-text-fill: #ffffff; ";
        String texterr = "-fx-font-family:'Century Gothic'; -fx-font-size : 16;-fx-text-fill: #FF0000; ";
        String textgood = "-fx-font-family:'Century Gothic'; -fx-font-size : 16;-fx-text-fill: #00ff00; ";
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

        UserInfoBut.setOnAction(e -> sceneManager.switchToOrganizerInfo(organizer));

        JFXButton ShowBut = new JFXButton("Show Data");
        ShowBut.setMaxWidth(Double.MAX_VALUE);
        ShowBut.setStyle(ButStyleA);
        ShowBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(ShowBut, new Insets(ButtonMar, 0, 0, 0));
        });

        ShowBut.setOnAction(e -> sceneManager.switchToOrganizerShow(organizer));

        JFXButton CRUDbut = new JFXButton("CRUD");
        CRUDbut.disableProperty().set(true);
        CRUDbut.setMaxWidth(Double.MAX_VALUE);
        CRUDbut.setStyle(ButStyleUA);
        CRUDbut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(CRUDbut, new Insets(ButtonMar, 0, 0, 0));
        });

        CRUDbut.setOnAction(e -> sceneManager.switchToOrganizerCRUD(organizer));

        JFXButton Searchbut = new JFXButton("Search");
        Searchbut.setMaxWidth(Double.MAX_VALUE);
        Searchbut.setStyle(ButStyleA);
        Searchbut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(Searchbut, new Insets(ButtonMar, 0, 0, 0));
        });

        Searchbut.setOnAction(e -> sceneManager.switchToOrganizerSearch(organizer));


        JFXButton LogOutBut = new JFXButton("Log out");
        LogOutBut.setMaxWidth(Double.MAX_VALUE);
        LogOutBut.setStyle(ButStyleA);
        LogOutBut.prefWidthProperty().bind(BorPane.widthProperty().multiply(0.175));
        ButtonsVbox.heightProperty().addListener((obs, oldPad, newPad) -> {
            double ButtonMar = ButtonsVbox.getHeight() * 0.025;
            VBox.setMargin(LogOutBut, new Insets(ButtonMar, 0, 0, 0));
        });

        LogOutBut.setOnAction(e -> sceneManager.switchToLogout(organizer));

        ButtonsVbox.getChildren().addAll(UserInfoBut, ShowBut, CRUDbut,Searchbut, LogOutBut);

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
        crudnamelocate.setAlignment(Pos.CENTER);
        Label CRUD = new Label("CRUD");
        CRUD.setStyle(textHeader);
        crudnamelocate.getChildren().add(CRUD);

        StackPane stackFunctionality = new StackPane();
        Pane functionality = new Pane();
        stackFunctionality.getChildren().add(functionality);
        functionality.prefWidthProperty().bind(crudMain.widthProperty());
        functionality.prefHeightProperty().bind(crudMain.heightProperty().multiply(0.44));
        crudMain.heightProperty().addListener((obs, oldPad, newPad) -> {
            double pane = crudMain.getHeight()* 0.05;
            VBox.setMargin(crudnamelocate, new Insets(pane, 0, 0, 0));
        });

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

        JFXButton but1 = new JFXButton("Create Event");
        but1.setStyle(ButStyleUA);
        GridPane.setHgrow(but1, Priority.ALWAYS);
        GridPane.setVgrow(but1, Priority.ALWAYS);
        but1.setMaxWidth(Double.MAX_VALUE);


        JFXButton but2 = new JFXButton("Update Event");
        but2.setStyle(ButStyleUA);
        GridPane.setHgrow(but2, Priority.ALWAYS);
        GridPane.setVgrow(but2, Priority.ALWAYS);
        but2.setMaxWidth(Double.MAX_VALUE);


        JFXButton but3 = new JFXButton("Read Event");
        but3.setStyle(ButStyleUA);
        GridPane.setHgrow(but3, Priority.ALWAYS);
        GridPane.setVgrow(but3, Priority.ALWAYS);
        but3.setMaxWidth(Double.MAX_VALUE);


        JFXButton but4 = new JFXButton("Delete Event");
        but4.setStyle(ButStyleUA);
        GridPane.setHgrow(but4, Priority.ALWAYS);
        GridPane.setVgrow(but4, Priority.ALWAYS);
        but4.setMaxWidth(Double.MAX_VALUE);

        Buttons.add(but1, 0 ,0);

        but1.setOnAction(e -> {
            functionality.getChildren().clear();

            VBox cap= new VBox();
            cap.prefWidthProperty().bind(functionality.widthProperty().multiply(0.98));
            cap.setAlignment(Pos.CENTER);

            VBox pwName = new VBox();
            pwName.setAlignment(Pos.CENTER);
            VBox pwCat = new VBox();
            pwCat.setAlignment(Pos.CENTER);
            VBox pwPrice = new VBox();
            pwPrice.setAlignment(Pos.CENTER);

            TextField name = new TextField();
            name.setPromptText("Name");

            ObservableList<Categories> categories = FXCollections.observableArrayList(Database.categories);
            ComboBox<Categories> cateName = new ComboBox<>();
            cateName.setItems(categories);
            TextField Price = new TextField();
            Price.setPromptText("Price");

            GridPane field1 = new GridPane();
            field1.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));
            field1.translateYProperty().bind(functionality.heightProperty().multiply(0.02));


            field1.setVgap(10);
            field1.setHgap(10);

            crudMain.widthProperty().addListener((obs,oldPad,newPad)->{
                double ButtonMar = crudMain.getWidth()*0.02;
                VBox.setMargin(field1, new Insets(0,ButtonMar,0,ButtonMar));
            });

            ColumnConstraints colgridtxt1 = new ColumnConstraints();
            colgridtxt1.setPercentWidth(33);

            ColumnConstraints colgridtxt2 = new ColumnConstraints();
            colgridtxt2.setPercentWidth(33);

            ColumnConstraints colgridtxt3 = new ColumnConstraints();
            colgridtxt3.setPercentWidth(34);
            field1.getColumnConstraints().addAll(colgridtxt1, colgridtxt2 ,colgridtxt3);

            field1.add(pwName, 0 ,0);
            pwName.setPrefWidth(200);
            pwName.setFillWidth(true);
            GridPane.setHgrow(pwName, Priority.ALWAYS);
            field1.add(pwCat, 1 ,0);
            pwCat.setPrefWidth(200);
            pwCat.setFillWidth(true);
            GridPane.setHgrow(pwCat, Priority.ALWAYS);
            field1.add(pwPrice, 2 ,0);
            pwPrice.setPrefWidth(200);
            pwPrice.setFillWidth(true);
            GridPane.setHgrow(pwPrice, Priority.ALWAYS);

            functionality.getChildren().clear();

            cap.prefWidthProperty().bind(functionality.widthProperty().multiply(0.98));
            cap.setAlignment(Pos.CENTER);

            VBox pwRoom = new VBox();
            pwRoom.setAlignment(Pos.CENTER);
            VBox pwDate = new VBox();
            pwDate.setAlignment(Pos.CENTER);
            VBox pwState = new VBox();
            pwState.setAlignment(Pos.CENTER);

            ObservableList<Room> rooms = FXCollections.observableArrayList(Database.rooms);
            ComboBox<Room> RoomNo = new ComboBox<>();
            RoomNo.setItems(rooms);

            DatePicker datePicker = new DatePicker();


            datePicker.setValue(LocalDate.now()); // Set default to today
            datePicker.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(date.isBefore(LocalDate.now().plusDays(3)) ||
                            date.isAfter(LocalDate.now().plusDays(17)));
                }
            });


            ToggleGroup timeToggleGroup = new ToggleGroup();
            JFXRadioButton morningBtn = new JFXRadioButton("Morning");
            JFXRadioButton nightBtn = new JFXRadioButton("Night");
            morningBtn.setToggleGroup(timeToggleGroup);
            nightBtn.setToggleGroup(timeToggleGroup);
            morningBtn.setStyle(textNormal);
            nightBtn.setStyle(textNormal);
            VBox radio = new VBox(10);
            radio.getChildren().addAll(morningBtn,nightBtn);


            datePicker.setDisable(true);
            morningBtn.setDisable(true);
            nightBtn.setDisable(true);

            RoomNo.setOnAction(j -> {
                        datePicker.setDisable(false);

                System.out.println(RoomNo.getValue());
                Room x = RoomNo.getValue();
                System.out.println(x.getRoomNo());

                 String[][] availableRooms = x.getAvailableRooms();
                datePicker.setOnAction(k ->{

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate theThing = datePicker.getValue();
                    String date = theThing.format(format);
                    morningBtn.setDisable(false);
                    nightBtn.setDisable(false);
                    for (int i = 0; i < availableRooms.length; i++) {
                        if (date.equals(availableRooms[i][0].substring(0, 10))) {
                            int indStartA = availableRooms[i][0].indexOf('-') + 2;
                            int indStartB = availableRooms[i][1].indexOf('-') + 2;
                            String StateA = availableRooms[i][0].substring(indStartA);
                            String StateB = availableRooms[i][1].substring(indStartB);

                            // Correct: disable only if occupied
                            morningBtn.setDisable(StateA.equals("occupied"));
                            nightBtn.setDisable(StateB.equals("occupied"));
                            break; // Exit after finding the date
                        }
                    }


                });
                    });



            pwRoom.getChildren().add(RoomNo);
            pwDate.getChildren().add(datePicker);
            pwState.getChildren().add(radio);

            GridPane field2 = new GridPane();
            field2.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));
            field2.translateYProperty().bind(functionality.heightProperty().multiply(0.1));

            field2.setVgap(10);
            field2.setHgap(10);

            crudMain.widthProperty().addListener((obs,oldPad,newPad)->{
                double ButtonMar = crudMain.getWidth()*0.1;
                VBox.setMargin(field2, new Insets(0,ButtonMar,0,ButtonMar));
            });

            ColumnConstraints colgridtxt11 = new ColumnConstraints();
            colgridtxt11.setPercentWidth(33);

            ColumnConstraints colgridtxt22 = new ColumnConstraints();
            colgridtxt22.setPercentWidth(33);

            ColumnConstraints colgridtxt33 = new ColumnConstraints();
            colgridtxt33.setPercentWidth(34);
            field2.getColumnConstraints().addAll(colgridtxt11, colgridtxt22 ,colgridtxt33);


            field2.add(pwRoom, 0 ,0);
            pwRoom.setPrefWidth(400);
            RoomNo.prefWidthProperty().bind(pwRoom.widthProperty());
            pwRoom.setFillWidth(true);
            GridPane.setHgrow(pwRoom, Priority.ALWAYS);
            field2.add(pwDate, 1 ,0);
        pwDate.setPrefWidth(200);
        pwDate.setFillWidth(true);
            GridPane.setHgrow(pwDate, Priority.ALWAYS);
            field2.add(pwState, 2 ,0);
        pwState.setPrefWidth(200);
        pwState.setFillWidth(true);
            GridPane.setHgrow(pwState, Priority.ALWAYS);

            JFXButton confirm = new JFXButton("Confirm");
            confirm.prefWidthProperty().bind(functionality.widthProperty().multiply(0.15));
            confirm.setStyle(ButStyleUA);
            confirm.translateYProperty().bind(functionality.heightProperty().multiply(0.40));

            confirm.setDisable(true);
            confirm.disableProperty().bind(
            Bindings.createBooleanBinding(() ->
                RoomNo.getValue() == null || name.getText().trim().isEmpty() || Price.getText().trim().isEmpty()||cateName.getValue() == null||timeToggleGroup.getSelectedToggle()==null,

                RoomNo.valueProperty(),
                name.textProperty(),
                Price.textProperty(),
                cateName.valueProperty(),
                timeToggleGroup.selectedToggleProperty()
            )
        );

            confirm.setOnAction(z -> {

                pwPrice.getChildren().removeIf(node -> node instanceof Label);
                organizer.create(name.getText(),cateName.getValue(),RoomNo.getValue(),Price.getText(),datePicker.getValue(),((JFXRadioButton)timeToggleGroup.getSelectedToggle()).getText(),pwPrice);
                timeToggleGroup.selectToggle(null);
                name.clear();
                Price.clear();
                cateName.setValue(null);
                timeToggleGroup.selectToggle(null);
                datePicker.setValue(LocalDate.now());
            });

            pwName.getChildren().add(name);
            pwCat.getChildren().add(cateName);
            pwPrice.getChildren().add(Price);

            cap.getChildren().addAll(field1,field2,confirm);
            functionality.getChildren().addAll(cap);
        });


            Buttons.add(but2, 1 ,0);
            but2.setOnAction(e -> {
            functionality.getChildren().clear();

            VBox cap= new VBox();
            cap.prefWidthProperty().bind(functionality.widthProperty());
            cap.setAlignment(Pos.CENTER);

            HBox container = new HBox(10);
            container.setAlignment(Pos.CENTER);
            container.translateYProperty().bind(functionality.heightProperty().multiply(0.250));

            VBox pwCap = new VBox();
            pwCap.setAlignment(Pos.CENTER);
            HBox fieldContainer = new HBox();
            fieldContainer.setAlignment(Pos.CENTER);
            fieldContainer.setMaxWidth(Double.MAX_VALUE);
            TextField capacity = new TextField();
            capacity.setPromptText("Capacity");
            capacity.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));


            VBox pwCom = new VBox();
            pwCom.setAlignment(Pos.CENTER);
            HBox combContainer = new HBox();
            combContainer.setAlignment(Pos.CENTER);
            combContainer.setMaxWidth(Double.MAX_VALUE);
            ObservableList<Event> observableList = FXCollections.observableArrayList(Database.events);

            ComboBox<Event> combobox = new ComboBox<>(observableList);
            combobox.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));




            container.getChildren().addAll(pwCap,pwCom);

            JFXButton confirm = new JFXButton("Confirm");
            confirm.prefWidthProperty().bind(functionality.widthProperty().multiply(0.15));
            confirm.setStyle(ButStyleUA);
            confirm.translateYProperty().bind(functionality.heightProperty().multiply(0.40));
            confirm.disableProperty().bind(
                    Bindings.createBooleanBinding(() ->
                                    combobox.getValue() == null || capacity.getText().trim().isEmpty(),

                            combobox.valueProperty(),
                            capacity.textProperty()
                    )
            );
            confirm.setOnAction(z -> {

                pwCap.getChildren().removeIf(node -> node instanceof Label);
                organizer.update(((Event)combobox.getValue()), capacity.getText(), pwCap,pwCom);
                capacity.clear();
                combobox.setValue(null);
            });


            combContainer.getChildren().add(combobox);
            fieldContainer.getChildren().add(capacity);
            pwCom.getChildren().add(combContainer);
            pwCap.getChildren().add(fieldContainer);
            cap.getChildren().addAll(container,confirm);
            functionality.getChildren().addAll(cap);
        });



        Buttons.add(but3, 2 ,0);

        but3.setOnAction(e -> {
            functionality.getChildren().clear();

            VBox cap= new VBox();
            cap.prefWidthProperty().bind(functionality.widthProperty());
            cap.setAlignment(Pos.CENTER);

            VBox pwCom = new VBox();
            pwCom.setAlignment(Pos.CENTER);
            HBox combContainer = new HBox();
            combContainer.translateYProperty().bind(functionality.heightProperty().multiply(0.250));
            pwCom.translateYProperty().bind(functionality.heightProperty().multiply(0.250));

            combContainer.setAlignment(Pos.CENTER);
            combContainer.setMaxWidth(Double.MAX_VALUE);
            ObservableList<Event> observableList = FXCollections.observableArrayList(Database.events);

            ComboBox<Event> combobox = new ComboBox<>(observableList);
            combobox.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));


            JFXButton confirm = new JFXButton("Confirm");
            confirm.prefWidthProperty().bind(functionality.widthProperty().multiply(0.15));
            confirm.setStyle(ButStyleUA);
            confirm.translateYProperty().bind(functionality.heightProperty().multiply(0.40));

            confirm.setDisable(true);
            confirm.disableProperty().bind(Bindings.isNull(combobox.valueProperty()));



            confirm.setOnAction(z -> {

                pwCom.getChildren().removeIf(node -> node instanceof Label);
                String readvalue = (organizer.read(((Event)combobox.getValue())));
                String[] readArr = readvalue.split("\\|");
                VBox hb = new VBox(5);
                for (String s : readArr) {
                    Label newlab = new Label(s);
                    newlab.setStyle(textNormal);
                    hb.getChildren().add(newlab);
                }

            hb.translateYProperty().bind(functionality.heightProperty().multiply(0.30));
            cap.getChildren().add(hb);
                combobox.setValue(null);
            });

            combContainer.getChildren().add(combobox);
            pwCom.getChildren().add(combobox);

            cap.getChildren().addAll(pwCom,confirm);

            functionality.getChildren().addAll(cap);
        });


        Buttons.add(but4, 3 ,0);

        but4.setOnAction(e -> {
            functionality.getChildren().clear();

            VBox cap= new VBox();
            cap.prefWidthProperty().bind(functionality.widthProperty());
            cap.setAlignment(Pos.CENTER);

            VBox pwCom = new VBox();
            pwCom.translateYProperty().bind(functionality.heightProperty().multiply(0.250));

            pwCom.setAlignment(Pos.CENTER);
            HBox combContainer = new HBox();
            combContainer.translateYProperty().bind(functionality.heightProperty().multiply(0.250));
            combContainer.setAlignment(Pos.CENTER);
            combContainer.setMaxWidth(Double.MAX_VALUE);
            ObservableList<Event> observableList = FXCollections.observableArrayList(Database.events);
            ComboBox<Event> combobox = new ComboBox<>(observableList);
            combobox.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));


            JFXButton confirm = new JFXButton("Confirm");
            confirm.prefWidthProperty().bind(functionality.widthProperty().multiply(0.15));
            confirm.setStyle(ButStyleUA);
            confirm.translateYProperty().bind(functionality.heightProperty().multiply(0.40));

            confirm.setDisable(true);
            confirm.disableProperty().bind(Bindings.isNull(combobox.valueProperty()));



            confirm.setOnAction(z -> {

                pwCom.getChildren().removeIf(node -> node instanceof Label);
                organizer.delete(((Event)combobox.getValue()));
                Label confmes = new Label("success");
                confmes.setStyle(textgood);
                pwCom.getChildren().add(confmes);
                observableList.setAll(Database.events);
                combobox.setValue(null);
            });

            combContainer.getChildren().add(combobox);
            pwCom.getChildren().add(combobox);

            cap.getChildren().addAll(pwCom,confirm);

            functionality.getChildren().addAll(cap);
        });




        crudMain.getChildren().addAll(crudnamelocate,Buttons,stackFunctionality);

        BorPane.setCenter(crudMain);
        root = new Scene(BorPane, 600, 400);


    }

}

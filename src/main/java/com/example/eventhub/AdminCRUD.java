package com.example.eventhub;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.example.eventhub.Admin;
import com.example.eventhub.Categories;
import com.example.eventhub.Database;
import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author omara
 */
public class AdminCRUD {
    private Admin a;
    private Scene root;

    public Scene getScene() {
        return root;
    }

    public AdminCRUD(Admin a, SceneManager sceneManager) {

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


        LogOutBut.setOnAction(e -> sceneManager.switchToLogout(a));

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
        
        JFXButton but1 = new JFXButton("Create Cate.");
        but1.setStyle(ButStyleUA);
        GridPane.setHgrow(but1, Priority.ALWAYS);
        GridPane.setVgrow(but1, Priority.ALWAYS);
        but1.setMaxWidth(Double.MAX_VALUE);


        JFXButton but2 = new JFXButton("Update Cate.");
        but2.setStyle(ButStyleUA);
        GridPane.setHgrow(but2, Priority.ALWAYS);
        GridPane.setVgrow(but2, Priority.ALWAYS);
        but2.setMaxWidth(Double.MAX_VALUE);


        JFXButton but3 = new JFXButton("Read Cate.");
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
        
        but1.setOnAction(e -> {
        functionality.getChildren().clear();
        
        VBox cap= new VBox();
        cap.prefWidthProperty().bind(functionality.widthProperty());
        cap.setAlignment(Pos.CENTER);
        
        VBox pwCap = new VBox();
        pwCap.translateYProperty().bind(functionality.heightProperty().multiply(0.250));

        pwCap.setAlignment(Pos.CENTER);
        HBox fieldContainer = new HBox();
  
        fieldContainer.setAlignment(Pos.CENTER);
        fieldContainer.setMaxWidth(Double.MAX_VALUE); 
        TextField capacity = new TextField();
        capacity.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));

        JFXButton confirm = new JFXButton("Confirm");
        confirm.prefWidthProperty().bind(functionality.widthProperty().multiply(0.15));
        confirm.setStyle(ButStyleUA);
        confirm.translateYProperty().bind(functionality.heightProperty().multiply(0.40));
        
        confirm.setDisable(true);
        confirm.disableProperty().bind(capacity.textProperty().isEmpty());
        
            confirm.setOnAction(z -> {
            pwCap.getChildren().removeIf(node -> node instanceof Label);
            a.create(capacity.getText(), pwCap);
            capacity.clear();
            });
        
        fieldContainer.getChildren().add(capacity);
        pwCap.getChildren().add(fieldContainer);
        cap.getChildren().addAll(pwCap,confirm);
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
        capacity.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));
        
        
        VBox pwCom = new VBox();
        pwCom.setAlignment(Pos.CENTER);
        HBox combContainer = new HBox();
        combContainer.setAlignment(Pos.CENTER);
        combContainer.setMaxWidth(Double.MAX_VALUE); 
        ObservableList<Categories> observableList = FXCollections.observableArrayList(Database.categories);
        
        ComboBox combobox = new ComboBox(observableList);
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
            a.update(((Categories)combobox.getValue()), capacity.getText(), pwCap,pwCom);
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
        ObservableList<Categories> observableList = FXCollections.observableArrayList(Database.categories);
        
        ComboBox combobox = new ComboBox(observableList);
        combobox.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));
        

        JFXButton confirm = new JFXButton("Confirm");
        confirm.prefWidthProperty().bind(functionality.widthProperty().multiply(0.15));
        confirm.setStyle(ButStyleUA);
        confirm.translateYProperty().bind(functionality.heightProperty().multiply(0.40));
        
        confirm.setDisable(true);
        confirm.disableProperty().bind(Bindings.isNull(combobox.valueProperty()));
        
        
        
            confirm.setOnAction(z -> {
            
            pwCom.getChildren().removeIf(node -> node instanceof Label);
            Label read = new Label (a.read(((Categories)combobox.getValue())));
            read.translateYProperty().bind(functionality.heightProperty().multiply(0.30));
            read.setStyle(textTable);
            pwCom.getChildren().add(read);
            combobox.setValue(null);
            });
        
        combContainer.getChildren().add(combobox);
        pwCom.getChildren().add(combobox);
        
        cap.getChildren().addAll(pwCom,confirm);
        
        functionality.getChildren().addAll(cap);
        });
        
        
        Buttons.add(but4, 3 ,0);
        
        but3.setOnAction(e -> {
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
        ObservableList<Categories> observableList = FXCollections.observableArrayList(Database.categories); 
        ComboBox combobox = new ComboBox(observableList);
        combobox.prefWidthProperty().bind(functionality.widthProperty().multiply(0.25));
        

        JFXButton confirm = new JFXButton("Confirm");
        confirm.prefWidthProperty().bind(functionality.widthProperty().multiply(0.15));
        confirm.setStyle(ButStyleUA);
        confirm.translateYProperty().bind(functionality.heightProperty().multiply(0.40));
        
        confirm.setDisable(true);
        confirm.disableProperty().bind(Bindings.isNull(combobox.valueProperty()));
        
        
        
            confirm.setOnAction(z -> {
            
            pwCom.getChildren().removeIf(node -> node instanceof Label);
            a.delete(((Categories)combobox.getValue()));
            Label confmes = new Label("sucsess");
            confmes.setStyle(textgood);
            pwCom.getChildren().add(confmes);
            observableList.setAll(Database.categories);
            combobox.setValue(null);
            });
        
        combContainer.getChildren().add(combobox);
        pwCom.getChildren().add(combobox);
        
        cap.getChildren().addAll(pwCom,confirm);
        
        functionality.getChildren().addAll(cap);
        });
        
        
        
        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        JFXButton createroom = new JFXButton("Create Room");
        createroom.prefWidthProperty().bind(buttonContainer.widthProperty().multiply(0.25));
        createroom.setStyle(ButStyleUA);
        buttonContainer.heightProperty().addListener((obs, oldPad, newPad) -> {
            double pane = buttonContainer.getHeight()* 0.05;
            HBox.setMargin(createroom, new Insets(pane, 0, 0, 0));
        });
        
        buttonContainer.getChildren().add(createroom);
        createroom.setOnAction(e -> {
        functionality.getChildren().clear();
        
        VBox cap= new VBox();
        cap.prefWidthProperty().bind(functionality.widthProperty());
        cap.setAlignment(Pos.CENTER);
        
        VBox pwCap = new VBox();
        pwCap.translateYProperty().bind(functionality.heightProperty().multiply(0.250));

        pwCap.setAlignment(Pos.CENTER);
        HBox fieldContainer = new HBox();
  
        fieldContainer.setAlignment(Pos.CENTER);
        fieldContainer.setMaxWidth(Double.MAX_VALUE); 
        TextField capacity = new TextField();
        capacity.prefWidthProperty().bind(buttonContainer.widthProperty().multiply(0.25));

        JFXButton confirm = new JFXButton("Confirm");
        confirm.prefWidthProperty().bind(buttonContainer.widthProperty().multiply(0.15));
        confirm.setStyle(ButStyleUA);
        confirm.translateYProperty().bind(functionality.heightProperty().multiply(0.40));
        
        confirm.setDisable(true);
        confirm.disableProperty().bind(capacity.textProperty().isEmpty());
        
            confirm.setOnAction(z -> {
            
            pwCap.getChildren().removeIf(node -> node instanceof Label);
            if(a.addRoom(capacity.getText(), pwCap)){
            Label confmes = new Label("sucsess");
            confmes.setStyle(textgood);
            pwCap.getChildren().add(confmes);
            }else{
            Label errormsg = new Label("please enter a positive capacity value");
            errormsg.setStyle(texterr);
            pwCap.getChildren().add(errormsg);
            }
            capacity.clear();
            });
        
        fieldContainer.getChildren().add(capacity);
        pwCap.getChildren().add(fieldContainer);
        cap.getChildren().addAll(pwCap,confirm);
        functionality.getChildren().addAll(cap);
        });
        
        crudMain.getChildren().addAll(crudnamelocate,Buttons,stackFunctionality,buttonContainer);
        
        BorPane.setCenter(crudMain);
        root = new Scene(BorPane, 600, 400);
    }
    }

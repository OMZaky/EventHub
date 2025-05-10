package com.example.eventhub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private Stage primaryStage;
    private Scene loginScene;
    private Scene registerScene;
    private AttendeeHscreen attendeeHscreen;
    private AttendeeInfo attendeeInfo;
    private Buy_Tickets buyTickets;
    private Scene logoutScene;
    private AdminHscreen adminHscreen;
    private AdminInfo adminInfo;
    private AdminShow adminShow;
    private AdminSearch adminSearch;
    private AdminCRUD adminCRUD;
    private OrganizerHscreen organizerHscreen;
    private OrganizerInfo organizerInfo;
    private Organizer_CRUD organizerCrud;
    private OrganizerShow organizerShow;
    private AttendeeSearch attendeeSearch;
    private OrganizerSearch organizerSearch;



    // put here for method access (Zaky)
    FXMLLoader logoutLoader = new FXMLLoader(getClass().getResource("Logout.fxml"));
    Parent logoutRoot = logoutLoader.load();
    LogoutController logoutController = logoutLoader.getController();



    public SceneManager(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        initializeScenes();
    }

    private void initializeScenes() throws IOException {
        // Load Login Scene
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent loginRoot = loginLoader.load();
        LoginController loginController = loginLoader.getController();
        loginController.setSceneManager(this);
        loginScene = new Scene(loginRoot, 320, 240);

        // Load Register Scene
        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent registerRoot = registerLoader.load();
        RegisterController registerController = registerLoader.getController();
        registerController.setSceneManager(this);
        registerScene = new Scene(registerRoot, 320, 240);

        // Load Logout Scene

        logoutController.setSceneManager(this);
        logoutScene = new Scene(logoutRoot, 320, 240);

    }

    public void switchToOrganizerInfo(Organizer organizer){
        organizerInfo = new OrganizerInfo(organizer, this);
        primaryStage.setScene(organizerInfo.getScene());
    }

    public void switchToOrganizerShow(Organizer organizer){
        organizerShow = new OrganizerShow(organizer, this);
        primaryStage.setScene(organizerShow.getScene());
    }

    public void switchToOrganizerSearch(Organizer organizer){
        organizerSearch = new OrganizerSearch(organizer, this);
        primaryStage.setScene(organizerSearch.getScene());
    }

    public void switchToOrganizerCRUD(Organizer organizer){
        organizerCrud = new Organizer_CRUD(organizer, this);
        primaryStage.setScene(organizerCrud.getScene());
    }

    public void switchToOrganizerHscreen(Organizer organizer){
        organizerHscreen = new OrganizerHscreen(organizer, this);
        primaryStage.setScene(organizerHscreen.getScene());
    }

    public void switchToAdminHscreen(Admin admin){
    adminHscreen = new AdminHscreen(admin, this);
    primaryStage.setScene(adminHscreen.getScene());
    }

    public void switchToAdminSearch(Admin admin){
        adminSearch = new AdminSearch(admin, this);
        primaryStage.setScene(adminSearch.getScene());
    }

    public void switchToAdminCRUD(Admin admin){
        adminCRUD = new AdminCRUD(admin, this);
        primaryStage.setScene(adminCRUD.getScene());
    }

    public void switchToAdminShow(Admin admin){
        adminShow= new AdminShow(admin, this);
        primaryStage.setScene(adminShow.getScene());
    }

    public void switchToAdminInfo(Admin admin){
        adminInfo = new AdminInfo(admin, this);
        primaryStage.setScene(adminInfo.getScene());
    }

    public void switchToAttendeeHscreen(Attendee a) {
        attendeeHscreen = new AttendeeHscreen(a, this);
        primaryStage.setScene(attendeeHscreen.getScene());
    }

    public void switchToAttendeeSearch(Attendee a) {
        attendeeSearch = new AttendeeSearch(a, this);
        primaryStage.setScene(attendeeSearch.getScene());
    }

    public void switchToAttendeeInfo(Attendee a) {
        attendeeInfo = new AttendeeInfo(a, this);
        primaryStage.setScene(attendeeInfo.getScene());
    }

    public void switchToBuyTickets(Attendee a) {
        buyTickets = new Buy_Tickets(a, this);
        primaryStage.setScene(buyTickets.getScene());
    }


    public void switchToRegister() {
        primaryStage.setScene(registerScene);
    }

    public void switchToLogin() {
        primaryStage.setScene(loginScene);
    }

    public void switchToLogout(Person a){
        logoutController.setPerson(a);
        primaryStage.setScene(logoutScene);

    }

}
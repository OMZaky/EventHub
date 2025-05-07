package com.example.eventhub;

import javafx.scene.control.Label;
import java.util.ArrayList;

public class Database {
    static final int INITIAL_CAPACITY = 1000;
    public static ArrayList<Person> people = new ArrayList<>(INITIAL_CAPACITY);
    public static ArrayList<Event> events = new ArrayList<>(INITIAL_CAPACITY);
    public static ArrayList<Room> rooms = new ArrayList<>(INITIAL_CAPACITY);
    public static ArrayList<Categories> categories = new ArrayList<>(INITIAL_CAPACITY);
    public static ArrayList<Label> wrongLabels = new ArrayList<>(INITIAL_CAPACITY);
}

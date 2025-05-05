package com.example.eventhub;

import java.util.Scanner;

public class Categories {
    
    Scanner input = new Scanner(System.in);
    private String name;

    public Categories() {
    }
    
    public Categories(String name){
       while (true) {
        boolean taken = false;
        
        if(name.equals("<><><>")){
        taken = true;
        }
        
        for (Categories c : Database.categories) {
            if (name.equals(c.name)) {
                taken = true;
                break;
            }
        }

        if (!taken) {
            break; // username is available!
        }

        System.out.println("Category name already taken. Please enter a new Category name:");
        System.out.println("if you no longer want to create category enter 0");
        name = input.nextLine(); // read new category name from user
        if(name.equals("0")) {
            name = "<><><>";
            break;}
    }
        this.name = name;
        
    }

    public String getName(){
        return name;
    }
    
    public String toString(){
    return this.name;
    }
    
    public void setName(String name){
      this.name = name;
    }

  
}

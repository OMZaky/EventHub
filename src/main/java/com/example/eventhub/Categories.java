package com.example.eventhub;

import java.util.Scanner;

import com.example.eventhub.Database;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Categories {
    
    Scanner input = new Scanner(System.in);
    private String name;

    public Categories() {
    }
    
    public Categories(String name,VBox pwCategoryName){

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
        this.name = name;
        }else{
            pwCategoryName.getChildren().add(new Label("Category name already in use"));
            this.name = "<><><>";
        }
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

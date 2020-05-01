package com.example.todo;







//import android.app.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class myItems  {
    List<reminder> myItems;

    public myItems(List<reminder> myItems) {

        this.myItems = myItems;
    }

    public myItems() {
        String[] startingNames = { "Shopping","Call prof.", "Pick up product"};
        this.myItems = new ArrayList<>();
        Random rng = new Random();
        for(int i = 0; i<startingNames.length; i++){
            reminder p = new reminder(startingNames[i], rng.nextInt(24), "no description");
            myItems.add(p);
        }
    }

    public List<reminder> getMyItems() {
        return myItems;
    }

    public void setMyItems(List<reminder> myItems) {
        this.myItems = myItems;
    }
}

package com.example.todo;

import android.app.Application;

import java.sql.Time;

public class reminder extends Application implements Comparable<reminder> {
    private String title;
    private int time;
   private String description;

    public reminder(String title, int time, String description) {
        this.title = title;
        this.time = time;
        this.description = description;
    }
    //CompareTo sorting...
    @Override
    public int compareTo(reminder other) {
        return this.title.compareTo(other.title);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}

package com.example.todo;

import android.app.Application;

public class MyApplication extends Application {
    private myItems myItems = new myItems();

    public com.example.todo.myItems getMyItems() {
        return myItems;
    }

    public void setMyItems(com.example.todo.myItems myItems) {
        this.myItems = myItems;
    }
}

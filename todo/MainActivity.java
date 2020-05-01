package com.example.todo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity { //THIS IS THE MASTER VERSION
    FloatingActionButton addButt;
    Button sortABC;
    Button sortTime;
    ListView lv_list;
    String title;
    int time;
    String des;
    Bundle incoming;
    int positionEdited;
    reminder p;
    listAdapter adapter;
    int  newPos;
    myItems myItems;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.delete_menu, menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myItems = ((MyApplication) this.getApplication()).getMyItems();

        addButt = findViewById(R.id.add);
        sortABC = findViewById(R.id.ABC);
        sortTime = findViewById(R.id.time_sort);
        lv_list = findViewById(R.id.list);
        registerForContextMenu(lv_list);


        adapter = new listAdapter(MainActivity.this, myItems);

        lv_list.setAdapter(adapter);

        //listen for incoming messages
        Bundle incoming = getIntent().getExtras();

        if (incoming != null) {
            // capture incoming data
            title = incoming.getString("Title");
            time = Integer.parseInt(incoming.getString("Time"));
            des = incoming.getString("Description");
            positionEdited = incoming.getInt("edit");
            //create new reminder object
            p = new reminder(title, time, des);
            //add reminder to the list and update adapter
            if (positionEdited > -1) {
                myItems.getMyItems().remove(positionEdited);
            }
            myItems.getMyItems().add(p);

            //myItems.getMyItems().remove(p);

            adapter.notifyDataSetChanged();
        }


        addButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), newReminderForm.class);
                startActivity(i);
            }

        });

        sortTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(myItems.getMyItems(), new Comparator<reminder>() {
                    @Override
                    public int compare(reminder o1, reminder o2) {
                        return o1.getTime() - o2.getTime();
                    }
                });

                adapter.notifyDataSetChanged();
            }
        });
        sortABC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(myItems.getMyItems());
                adapter.notifyDataSetChanged();

            }
        });


        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                newPos = position;
                editItem(position);
            }
        });


    }

    public void editItem(int position) {
        Intent i = new Intent(getApplicationContext(), newReminderForm.class);

        //get the contents of the item clicked
        reminder r = myItems.getMyItems().get(position);
        i.putExtra("edit", position);
        i.putExtra("Title", r.getTitle());
        i.putExtra("Time", r.getTime());
        i.putExtra("Description", r.getDescription());

        startActivity(i);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
       // AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item;

        if (item.getItemId() == R.id.delete) {
                myItems.getMyItems().remove(newPos = 0);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Removed", Toast.LENGTH_SHORT);
                return true;
            }

        return super.onContextItemSelected(item);
    }
}



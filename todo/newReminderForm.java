package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class newReminderForm extends AppCompatActivity {
    Button ok, cancel;
    EditText title, time, des;

    int positionToEdit = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder_form);

        ok = findViewById(R.id.Ok);
        time = findViewById(R.id.et_time);
        des = findViewById(R.id.et_des);
        title = findViewById(R.id.et_name);
        cancel = findViewById(R.id.Cancel);
        // Listen fo incoming data

        Bundle incomingIntent = getIntent().getExtras();

        if(incomingIntent != null) {
            String name = incomingIntent.getString("Title");
            int hour = incomingIntent.getInt("Time");
            String description = incomingIntent.getString("Description");
            positionToEdit = incomingIntent.getInt("edit");

            //fill in the form
            title.setText(name);
            time.setText(Integer.toString(hour));
            des.setText(description);
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // get strings from et_view object
                String newTitle = title.getText().toString();
                String newTime = time.getText().toString();
                String newDes = des.getText().toString();

                // put the string into message for MainActivity
                Intent i = new Intent(v.getContext(), MainActivity.class);
                i.putExtra("edit", positionToEdit);
                i.putExtra("Title", newTitle);
                i.putExtra("Time", newTime);
                i.putExtra("Description", newDes);

                //start the main activity again


                startActivity(i);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);

                startActivity(i);
            }

        });
    }
}

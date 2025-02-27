package com.jtdev.practicecodefest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class update extends AppCompatActivity {


    EditText eName,ePerson,eDate;
    Button bCancel, bUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        eName = findViewById(R.id.eName);
        ePerson = findViewById(R.id.ePerson);
        eDate = findViewById(R.id.eDate);

        bCancel = findViewById(R.id.bCancel);
        bUpdate = findViewById(R.id.bUpdate);



        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String person = intent.getStringExtra("person");
        String date = intent.getStringExtra("date");


        eName.setText(name);
        ePerson.setText(person);
        eDate.setText(date);




        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        eDate.setOnClickListener(v->{
            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this,(view1, hourOfDay, minute) -> {

                    String dateChosen = year1+"/"+(month1+1)+"/"+dayOfMonth+" "+hourOfDay+":"+minute;
                    eDate.setText(dateChosen);


                },hour , min, true);
                timePickerDialog.show();




            },year,month,day);
            datePickerDialog.show();


        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(update.this);

                String newName = eName.getText().toString().trim();
                String newPerson = ePerson.getText().toString().trim();
                String newDate = eDate.getText().toString().trim();


                dbHelper.edit(id,newName,newPerson,newDate);

                finish();

            }
        });

    }
}
package com.jtdev.practicecodefest;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class add extends AppCompatActivity {


    EditText eName,ePerson,eDate;
    Button bCancel, bSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        eName = findViewById(R.id.eName);
        ePerson = findViewById(R.id.ePerson);
        eDate = findViewById(R.id.eDate);

        bCancel = findViewById(R.id.bCancel);
        bSubmit = findViewById(R.id.bUpdate);



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
        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(add.this);

                String name = eName.getText().toString().trim();
                String person = ePerson.getText().toString().trim();
                String date = eDate.getText().toString().trim();


                dbHelper.add(name,person,date);
                finish();
            }
        });




    }
}
package com.jtdev.practicecodefest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ReservationFragment extends Fragment {



    FloatingActionButton fab;
    RecyclerView rc;
    customAdapter adapter;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> person = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> timestamp = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_reservation, container, false);

        fab = view.findViewById(R.id.fab);
        rc = view.findViewById(R.id.rc);


        storeInArray();
        adapter = new customAdapter(getContext(),id,name,person,date,timestamp);


        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),add.class));
            }
        });



        return view;
    }

    public void storeInArray(){

        DBHelper dbHelper = new DBHelper(getContext());
        Cursor cursor = dbHelper.read();

        if (cursor.getCount()==0){
            Toast.makeText(getContext(), "no data", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                person.add(cursor.getString(2));
                date.add(cursor.getString(3));
                timestamp.add(cursor.getString(4));


            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        id.clear();
        name.clear();
        person.clear();
        date.clear();
        timestamp.clear();

        storeInArray();

        adapter.notifyDataSetChanged();




    }
}
package com.jtdev.practicecodefest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder> {

    private Context context;
    ArrayList<String> id;
    ArrayList<String> name;
    ArrayList<String> person;
    ArrayList<String> date;
    ArrayList<String> timestamp;
    public customAdapter(Context context, ArrayList<String> id, ArrayList<String> name, ArrayList<String> person, ArrayList<String> date, ArrayList<String> timestamp) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.person = person;
        this.date = date;
        this.timestamp = timestamp;
    }




    @NonNull
    @Override
    public customAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull customAdapter.MyViewHolder holder, int position) {

        holder.tID.setText(String.valueOf(id.get(position)));
        holder.tName.setText(String.valueOf(name.get(position)));
        holder.tPerson.setText(String.valueOf(person.get(position)));
        holder.tDate.setText(String.valueOf(date.get(position)));
        holder.tTimestamp.setText(String.valueOf(timestamp.get(position)));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update.class);

                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("person",String.valueOf(person.get(position)));
                intent.putExtra("date",String.valueOf(date.get(position)));


                context.startActivity(intent);
            }
        });

        holder.bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);

                alert.setTitle("Delete Confirmation");
                alert.setMessage("Are you sure you want to delete item? ");
                alert.setPositiveButton("Yes",(dialog, which) -> {
                    DBHelper dbHelper = new DBHelper(context);
                    dbHelper.delete_row(String.valueOf(id.get(position)));

                    id.remove(position);
                    name.remove(position);
                    person.remove(position);
                    date.remove(position);
                    timestamp.remove(position);

                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, id.size());





                });
                alert.setNegativeButton("No",(dialog, which) -> {
                    dialog.dismiss();
                });
                alert.show();




            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tID,tName,tPerson,tDate,tTimestamp;
        Button bDelete;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tID = itemView.findViewById(R.id.tID);
            tName = itemView.findViewById(R.id.tName);
            tPerson = itemView.findViewById(R.id.tPerson);
            tDate = itemView.findViewById(R.id.tDate);
            tTimestamp = itemView.findViewById(R.id.tTimestamp);
            bDelete = itemView.findViewById(R.id.bDelete);
            cardView = itemView.findViewById(R.id.cardview);

        }
    }
}

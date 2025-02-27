package com.jtdev.practicecodefest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "reservation.db";
    public static final String TABLE_NAME = "my_reservation";
    public static final String ID = "id";
    public static final String name = "name";
    public static final String person = "person";
    public static final String date = "date";
    public static final String timestamp = "timestamp";
    private Context context;

    String dateFormat = new SimpleDateFormat("yyyyy:MM:dd HH:mm", Locale.getDefault()).format(new Date());

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PERSON TEXT, DATE TEXT, TIMESTAMP TEXT DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void add(String name, String person, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("person", person);
        cv.put("date",date);
        long res = db.insert(TABLE_NAME, null, cv);
        if(res==-1){
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor read(){
        Cursor cursor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        if (db!=null) {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        }
        return cursor;
    }
    void edit(String id, String name, String person, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("person", person);
        cv.put("date",date);
        cv.put("timestamp", dateFormat);
        long res = db.update(TABLE_NAME, cv, "ID=?", new String[]{id});
        if(res==-1){
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
        }
    }
    void delete_row(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, "id=?", new String[]{id});
        if(res==-1){
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }
}

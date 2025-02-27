import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "reservation.db";
    public static final String TABLE_NAME = "my_reservation";
    public static final String ID = "id";
    public static final String name = "name";
    public static final String person = "person";
    public static final String date = "date";
    public static final String timestamp = "timestamp";
    private Context context;

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

    void insert(String name, String person, String date, String timestamp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("person", person);
        cv.put("date",date);
        long res = db.insert(TABLE_NAME, null, cv);
        if(res==-1){
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
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
    void update(String id, String name, String person, String date, String timestamp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("person", person);
        cv.put("date",date);
        long res = db.update(TABLE_NAME, cv, "ID=?", new String[]{id});
        if(res==-1){
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }
    void delete_row(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, "id=?", new String[]{id});
        if(res==-1){
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }
}

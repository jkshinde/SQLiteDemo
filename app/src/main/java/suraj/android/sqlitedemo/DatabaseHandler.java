package suraj.android.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Tata Strive on 9/20/2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper
{
    Context context;
    public DatabaseHandler(Context context, String dbNAme, SQLiteDatabase.CursorFactory factory, int versionCode)
    {
        super(context, dbNAme, factory, versionCode);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table if not exists friends(_id integer primary key autoincrement, name text, age integer, hobby text, quality text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    /* ---------------------Our Methods -------------------*/

    /*public Cursor getAllFriends()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("friends", null, null, null, null, null, null);

        return c;
    }*/

    public Cursor getAllFriends(String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
       // Toast.makeText(context, "I am here", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("friends", projection, selection, selectionArgs, null, null, sortOrder);
        return c;
    }

    /*public void addFriend(String name, String age, String hobby, String quality)
    {
        ContentValues emptyRow = new ContentValues();
        emptyRow.put("name",name);
        emptyRow.put("age", Integer.parseInt(age));
        emptyRow.put("hobby", hobby);
        emptyRow.put("quality", quality);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("friends",null,emptyRow);

        Toast.makeText(context, "Added to database", Toast.LENGTH_SHORT).show();
    }*/

    public long addFriend(ContentValues emptyRow)
    {
        SQLiteDatabase db = getWritableDatabase();
        long insert_id = db.insert("friends", null, emptyRow);

        Toast.makeText(context, "Added to database", Toast.LENGTH_SHORT).show();

        return insert_id;
    }

    public void deleteFriend(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String col_names[] = {String.valueOf(id)};
        db.delete("friends","_id=?", col_names);

        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
    }

    public void updateFriend(int id, String newName, String newAge, String newHobby, String newQuality)
    {
        SQLiteDatabase db= getWritableDatabase();

        ContentValues emptyRow = new ContentValues();
        emptyRow.put("name",newName);
        emptyRow.put("age",newAge);
        emptyRow.put("hobby",newHobby);
        emptyRow.put("quality",newQuality);

        String colmns[] = {String.valueOf(id)};

        db.update("friends", emptyRow, "_id=?", colmns);

        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();

    }
}
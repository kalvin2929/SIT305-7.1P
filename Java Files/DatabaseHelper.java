package com.techroid.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class DatabaseHelper  extends SQLiteOpenHelper  {
    public static  final  String DATABASE_NAME="Notes.db";
    public static  final  String TABLE_NAME="notes_table";
    public static  final  String COL_1="ID";
    public static  final  String COL_2="NOTE";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "+ TABLE_NAME+
                   "(ID Integer primary key  autoincrement, " +
                    "NOTE TEXT  )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String note)
    {
       SQLiteDatabase database = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put(COL_2,note);
       long result = database.insert(TABLE_NAME,null,contentValues);
       if(result==-1)
           return  false;
       else
           return  true;

    }

    public boolean updateData(String oldNote, String newNote)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        System.out.println(oldNote);
        System.out.println(newNote);
        contentValues.put(COL_2,newNote);
        long result = database.update(TABLE_NAME,contentValues,"NOTE=?",new String[]{oldNote});
            return  true;

    }
    public Integer deleteData(String note)
    {
        SQLiteDatabase database = this.getWritableDatabase();
       return database.delete(TABLE_NAME,"Note=?",new String[]{note});

    }
    public ArrayList<String> getAllData()
    {
        ArrayList<String > results  = new ArrayList<>();
        String query  = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase newDB = this.getWritableDatabase();
        Cursor c = newDB.rawQuery(query,null);
//Now iterate with cursor
        if (c != null ) {
            if  (c.moveToFirst()) {
                do {  results.add(c.getString(c.getColumnIndex(COL_2)));
                }while (c.moveToNext());
            }
        }
     return  results;
    }

}

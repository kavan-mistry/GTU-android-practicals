package com.oneparcent.practical10;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="SAL.db";
    public static final String TABLE_NAME="std.db";
    public static final String COL_1 = "cID";
    public static final String COL_2 = "cNAME";
    public static final String COL_3 = "cOrderID";

    public DBHelper(@Nullable Context context) {
        super (context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL ("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,cNAME TEXT, cOrderID TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate (db);
    }

    public boolean Insert(String id, String name, String surname){
        SQLiteDatabase db=this.getWritableDatabase ();
        ContentValues contentValues=new ContentValues ();
        contentValues.put (COL_1, id);
        contentValues.put (COL_2,name);
        contentValues.put (COL_3,surname);
        long results= db.insert (TABLE_NAME,null,contentValues);
        if(results==-1)
            return false;
        else
            return true;

    }
}
package com.example.football;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database = "Game.db";
    public static final String TabelName = "FootBall";
    public static final String COL_1 = "id";
    public static final String COL_2 = "City";
    public static final String COL_3 = "Date";
    public static final String COL_4 = "Team1";
    public static final String COL_5 = "Team2";
    public static final String Creat = "create table " + TabelName + "(ID integer primary key AUTOINCREMENT not null  , " + "City text not null ,Date text not null ,Team1 text not null,Team2 text not null);";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, Database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Creat);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TabelName);
        onCreate(db);
    }

    public boolean chkDate(String Date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM FootBall WHERE Date=?", new String[]{Date});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }


    }
    public boolean chkTeam1(String Team1) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM FootBall WHERE Team1=?", new String[]{Team1});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }


    }
    public boolean chkTeam2(String Team2) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM FootBall WHERE Team2=?", new String[]{Team2});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }


    }

    public boolean chkid(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM FootBall WHERE ID=?", new String[]{id});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public Cursor getListContent() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TabelName, null);
        return data;
    }

    public Cursor getListDate(String Date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data2 = db.rawQuery("SELECT * FROM " + TabelName + " WHERE date='" + Date + "'", null);
        return data2;
    }

    public Cursor getListTeam(String Team1) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data3 = db.rawQuery("SELECT * FROM " + TabelName + " WHERE Team1='" + Team1 + "'"+"OR Team2='" + Team1 + "'",null);
        return data3;
    }


    public Integer deleted(String id) {
        if (chkid(id) == true) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete(TabelName, "ID=?", new String[]{id});
        }
        else
            return 0;
    }

    public boolean UpdateData(String id,String City,String date,String Team1,String Team2){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,City);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,Team1);
        contentValues.put(COL_5,Team2);
        db.update(TabelName, contentValues, "ID=?", new String[]{id});
        return true;

    }


}

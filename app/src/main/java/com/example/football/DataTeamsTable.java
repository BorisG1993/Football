package com.example.football;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataTeamsTable extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Teams.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TEAMS = "teams";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_Team_Name = "team_name";

    public static final String CREATE_TABLE_Teams = "CREATE TABLE " + TEAMS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_Team_Name + " TEXT)";

    public DataTeamsTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_Teams);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades if needed
        db.execSQL("DROP TABLE IF EXISTS " + TEAMS);
        onCreate(db);
    }

    public ArrayList<Team> getAllTeams() {
        ArrayList<Team> teamsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TEAMS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String teamName = cursor.getString(cursor.getColumnIndex(COLUMN_Team_Name));

                Team team= new Team(teamName);
                team.setId(id);
                teamsList.add(team);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return teamsList;
    }


}
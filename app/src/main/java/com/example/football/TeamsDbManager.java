package com.example.football;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TeamsDbManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Teams.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TEAMS = "teams";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_Team_Name = "team_name";
    public static final String CREATE_TABLE_Teams = "CREATE TABLE " + TEAMS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_Team_Name + " TEXT)";
    public TeamsDbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_Teams);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TEAMS);
        onCreate(db);
    }
    public boolean addTeam(String teamName) {
        if (doesTeamExist(teamName)) return false;
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_Team_Name, teamName);
        db.insert(TEAMS,null,values);
        db.close();
        return true;
    }
    public Team getTeam(String teamName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "LOWER(" + COLUMN_Team_Name + ") = ?";
        String[] selectionArgs = {teamName.toLowerCase()};
        Cursor cursor = db.query(TEAMS, null, selection, selectionArgs, null, null, null);
        Team team = null;

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_Team_Name));
            team = new Team(name);
        }

        cursor.close();
        db.close();
        return team;
    }
    public ArrayList<Team> getAllTeams() {
        ArrayList<Team> teamsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TEAMS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String teamName = cursor.getString(cursor.getColumnIndex(COLUMN_Team_Name));

                Team team = new Team(teamName);
                teamsList.add(team);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return teamsList;
    }

    public void removeTeam(String teamName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_Team_Name + " = ?";
        String[] selectionArgs = {teamName};
        db.delete(TEAMS, selection, selectionArgs);
        db.close();
    }

    public boolean doesTeamExist(String teamName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "LOWER(" + COLUMN_Team_Name + ") = ?";
        String[] selectionArgs = {teamName.toLowerCase()};
        Cursor cursor = db.query(TEAMS, null, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

}

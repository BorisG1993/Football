package com.example.football;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {
    private SQLiteDatabase db;

    public DataManager(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void addGame(String city, String date, String teamHome, String teamGuest, int teamHomeScore, int teamAwayScore) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_DATE, date);
        values.put(DatabaseHelper.COLUMN_HOME_TEAM, teamHome);
        values.put(DatabaseHelper.COLUMN_AWAY_TEAM, teamGuest);
        values.put(DatabaseHelper.COLUMN_HOME_SCORE, teamHomeScore);
        values.put(DatabaseHelper.COLUMN_AWAY_SCORE, teamAwayScore);

        db.insert(DatabaseHelper.TABLE_GAMES, null, values);
    }

    public Cursor getAllGames() {
        return db.query(DatabaseHelper.TABLE_GAMES, null, null, null, null, null, null);
    }
}

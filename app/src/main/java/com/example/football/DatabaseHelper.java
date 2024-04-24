package com.example.football;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "football_app.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_GAMES = "games";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_HOME_TEAM = "home_team";
    public static final String COLUMN_AWAY_TEAM = "away_team";
    public static final String COLUMN_HOME_SCORE = "home_score";
    public static final String COLUMN_AWAY_SCORE = "away_score";
    public static final String COLUMN_DATE = "date";

    public static final String CREATE_TABLE_GAMES = "CREATE TABLE " + TABLE_GAMES + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_DATE + " TEXT," +
            COLUMN_HOME_TEAM + " TEXT, " +
            COLUMN_AWAY_TEAM + " TEXT, " +
            COLUMN_HOME_SCORE + " INTEGER, " +
            COLUMN_AWAY_SCORE + " INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades if needed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
        onCreate(db);
    }

    public void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HOME_TEAM, game.getHomeTeamName());
        values.put(COLUMN_AWAY_TEAM, game.getAwayTeamName());
        values.put(COLUMN_HOME_SCORE, game.getHomeTeamScore());
        values.put(COLUMN_AWAY_SCORE, game.getAwayTeamScore());
        values.put(COLUMN_DATE, game.getDate());
        db.insert(TABLE_GAMES, null, values);
        db.close();
    }

    public ArrayList<Game> getAllGames() {
        ArrayList<Game> gameList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_GAMES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String homeTeam = cursor.getString(cursor.getColumnIndex(COLUMN_HOME_TEAM));
                @SuppressLint("Range") String awayTeam = cursor.getString(cursor.getColumnIndex(COLUMN_AWAY_TEAM));
                @SuppressLint("Range") int homeScore = cursor.getInt(cursor.getColumnIndex(COLUMN_HOME_SCORE));
                @SuppressLint("Range") int awayScore = cursor.getInt(cursor.getColumnIndex(COLUMN_AWAY_SCORE));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));

                Game game = new Game(homeTeam, awayTeam, homeScore, awayScore, date);
                game.setId(id);
                gameList.add(game);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return gameList;
    }

    public boolean updateGame(Game game) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_HOME_TEAM, game.getHomeTeamName());
            values.put(COLUMN_AWAY_TEAM, game.getAwayTeamName());
            values.put(COLUMN_HOME_SCORE, game.getHomeTeamScore());
            values.put(COLUMN_AWAY_SCORE, game.getAwayTeamScore());
            values.put(COLUMN_DATE, game.getDate());
            db.update(TABLE_GAMES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(game.getId())});
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void deleteGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GAMES, COLUMN_ID + " = ?", new String[]{String.valueOf(game.getId())});
        db.close();
    }


}

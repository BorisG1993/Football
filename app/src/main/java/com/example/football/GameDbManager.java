package com.example.football;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Connects with the database storing games data
public class GameDbManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Games.db";
    public static final int DATABASE_VERSION = 1;
    public static final String GAMES_TABLE = "games";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_HOME_TEAM_NAME = "home_team_name";
    public static final String COLUMN_AWAY_TEAM_NAME = "away_team_name";
    public static final String COLUMN_HOME_TEAM_SCORE = "home_team_score";
    public static final String COLUMN_AWAY_TEAM_SCORE = "away_team_score";

    public static final String CREATE_TABLE_GAMES = "CREATE TABLE " + GAMES_TABLE + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_HOME_TEAM_NAME + " TEXT, " +
            COLUMN_AWAY_TEAM_NAME + " TEXT, " +
            COLUMN_HOME_TEAM_SCORE + " INTEGER, " +
            COLUMN_AWAY_TEAM_SCORE + " INTEGER)";

    public GameDbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GAMES_TABLE);
        onCreate(db);
    }

    // Adds game data to the database
    public boolean addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, game.getDate());
        values.put(COLUMN_HOME_TEAM_NAME, game.getHomeTeamName());
        values.put(COLUMN_AWAY_TEAM_NAME, game.getAwayTeamName());
        values.put(COLUMN_HOME_TEAM_SCORE, game.getHomeTeamScore());
        values.put(COLUMN_AWAY_TEAM_SCORE, game.getAwayTeamScore());

        long insertedId = -1;
        try {
            insertedId = db.insertOrThrow(GAMES_TABLE, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return insertedId != -1;
    }

    // Returns game data based on team's name
    public List<Game> getGames(String teamName) {
        List<Game> gamesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_HOME_TEAM_NAME + " = ? OR " + COLUMN_AWAY_TEAM_NAME + " = ?";
        String[] selectionArgs = {teamName, teamName};
        Cursor cursor = db.query(GAMES_TABLE, null, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Game game = new Game();
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                if (idIndex >= 0) {
                    game.setId(cursor.getLong(idIndex));
                }

                int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
                if (dateIndex >= 0) {
                    game.setDate(cursor.getString(dateIndex));
                }

                int homeTeamNameIndex = cursor.getColumnIndex(COLUMN_HOME_TEAM_NAME);
                if (homeTeamNameIndex >= 0) {
                    game.setHomeTeamName(cursor.getString(homeTeamNameIndex));
                }

                int awayTeamNameIndex = cursor.getColumnIndex(COLUMN_AWAY_TEAM_NAME);
                if (awayTeamNameIndex >= 0) {
                    game.setAwayTeamName(cursor.getString(awayTeamNameIndex));
                }

                int homeTeamScoreIndex = cursor.getColumnIndex(COLUMN_HOME_TEAM_SCORE);
                if (homeTeamScoreIndex >= 0 && !cursor.isNull(homeTeamScoreIndex)) {
                    game.setHomeTeamScore(cursor.getInt(homeTeamScoreIndex));
                }

                int awayTeamScoreIndex = cursor.getColumnIndex(COLUMN_AWAY_TEAM_SCORE);
                if (awayTeamScoreIndex >= 0 && !cursor.isNull(awayTeamScoreIndex)) {
                    game.setAwayTeamScore(cursor.getInt(awayTeamScoreIndex));
                }

                gamesList.add(game);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return gamesList;
    }

    // Removes game from database using id given when inserted
    public void removeGame(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String deleteQuery = "DELETE FROM " + GAMES_TABLE + " WHERE " + COLUMN_ID + " = ?";
            db.execSQL(deleteQuery, new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            Log.e("GameDbManager", "Error deleting game with ID " + id, e);
        } finally {
            db.close();
        }
    }
}
package com.example.football;
import androidx.annotation.NonNull;

import java.util.UUID;

// Holds data of the game
public class Game {
    private long id;
    private String date;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Game date(String date){
        this.date = date;
        return this;
    }
    public Game homeTeamName(String name){
        this.homeTeamName = name;
        return this;
    }
    public Game awayTeamName(String name){
        this.awayTeamName = name;
        return this;
    }

    public Game homeTeamScore(int score){
        this.homeTeamScore = score;
        return this;
    }

    public Game awayTeamScore(int score){
        this.awayTeamScore = score;
        return this;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    @NonNull
    @Override
    public String toString() {
        return date + "  " + homeTeamName + " " + homeTeamScore + " - " + awayTeamScore + " " + awayTeamName;
    }
}
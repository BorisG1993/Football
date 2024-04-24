package com.example.football;

public class FootballGame {

    private String Date;
    private String TeamHome;
    private String TeamAway;
    private int TeamHomeScore;
    private int TeamAwayScore;

    public FootballGame(String Date, String TeamHome, String TeamAway, int TeamHomeScore, int TeamAwayScore){
        this.Date=Date;
        this.TeamHome = TeamHome;
        this.TeamAway = TeamAway;
        this.TeamHomeScore = TeamHomeScore;
        this.TeamAwayScore = TeamAwayScore;
    }

    public String getDate(){
        return this.Date;
    }
    public void setDate(String Date){
        this.Date = Date;
    }
    public String getTeamHome(){
        return this.TeamHome;
    }
    public void setTeamHome(String TeamHome){
        this.TeamHome = TeamHome;
    }
    public String getTeamAway(){
        return this.TeamAway;
    }

    public void setTeamAway(String TeamAway){
        this.TeamAway = TeamAway;
    }
    public int getTeamHomeScore(){
        return this.TeamHomeScore;
    }
    public void setTeamHomeScore(int TeamHomeScore){
        this.TeamHomeScore = TeamHomeScore;
    }

    public int getTeamAwayScore(){
        return this.TeamAwayScore;
    }

    public void setTeamAwayScore(int TeamAwayScore){
        this.TeamAwayScore = TeamAwayScore;
    }
    public String getScore(){ return getTeamHomeScore() + "-" + getTeamAwayScore();}

    @Override
    public String toString() {
        return "Date : " + getDate() + "\n Team Home : " + getTeamHome() + "\n Team Away : " + getTeamAway()
                +"\n Score : " + getScore();
    }
}

package com.example.football;

public class Team {
    private int id;
    private String TeamName;

    public Team(String TeamName){
        this.TeamName = TeamName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String TeamName) {
        this.TeamName = TeamName;
    }
}

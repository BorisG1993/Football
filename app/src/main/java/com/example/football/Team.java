package com.example.football;
import androidx.annotation.NonNull;

// Holds team name and getters/setters
public class Team {
    private String name;

    public Team(String TeamName){
        this.name = TeamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String TeamName) {
        this.name = TeamName;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}

package com.example.football;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// Class for the main menu
public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnTeamsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnAdd.setOnClickListener(v -> openAddGame());
        btnTeamsList.setOnClickListener(v -> openTeamsList());
    }

    public void init(){
        btnAdd=(Button)findViewById(R.id.BtnAddGame);
        btnTeamsList =(Button)findViewById(R.id.BtnTeamsList);

    }

    // Opens the list of teams
    public void openTeamsList(){
        Intent intent=new Intent(this, TeamsList.class);
        startActivity(intent);

    }

    // Opens the list of games
    public void openAddGame(){
        Intent intent=new Intent(this, AddGame.class);
        startActivity(intent);
    }
}

package com.example.football;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnTeamsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd();
            }
        });
        btnTeamsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeamsList();
            }
        });
    }

    public void init(){
        btnAdd=(Button)findViewById(R.id.BtnAddGame);
        btnTeamsList =(Button)findViewById(R.id.BtnTeamsList);

    }

    public void openTeamsList(){
        Intent intent=new Intent(this, TeamsList.class);
        startActivity(intent);
    }

    public void openAdd(){
        Intent intent=new Intent(this, AddGame.class);
        startActivity(intent);
    }
}

package com.example.football;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TeamsList extends AppCompatActivity {
    TeamsDbManager teamsDbManager;
    ArrayList<Team> teamsList;
    EditText editTextSearch;
    Button btnAdd, btnBack, btnSearch;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_list);
        init();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeam();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFilteredList(editTextSearch.getText().toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openGamesList(teamsList.get(position).toString());
            }
        });
    }
    public void init(){
        teamsDbManager = new TeamsDbManager(this);
        teamsList = teamsDbManager.getAllTeams();
        btnBack = (Button) findViewById(R.id.BtnBack);
        btnAdd = (Button) findViewById(R.id.BtnAddTeam);
        btnSearch = (Button) findViewById(R.id.BtnSearchTeam);
        editTextSearch = (EditText) findViewById(R.id.EditTextSearchTeam);
        listView = (ListView) findViewById(R.id.ListTeams);
        setList();
    }

    private void openMenu(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void openGamesList(String teamName){
        Intent intent = new Intent(this, GamesList.class);
        intent.putExtra("team", teamName);
        startActivity(intent);
    }

    public void addTeam(){
        // Add team from search box using db
    }

    private void setList(){
        ArrayAdapter<Team> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teamsList);
        listView.setAdapter(adapter);
    }

    private void setFilteredList(String teamName){
        ArrayAdapter<Team> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                                    teamsList.stream().filter(item -> item.getName().equals(teamName)).collect(Collectors.toList()));
        listView.setAdapter(adapter);
    }
}

package com.example.football;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Class for the teams list view
public class TeamsList extends AppCompatActivity {
    TeamsDbManager teamsDbManager;
    boolean flag;
    ArrayList<Team> teamsList;
    EditText editTextSearch;
    Button btnAdd, btnBack, btnSearch;
    ListView listView;
    ArrayAdapter<Team> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_list);
        init();

        btnBack.setOnClickListener(v -> backToMenu());
        btnAdd.setOnClickListener(v -> addTeam(editTextSearch.getText().toString()));
        btnSearch.setOnClickListener(v -> findTeam(editTextSearch.getText().toString()));

        // Goes to games list on team name press
        listView.setOnItemClickListener((parent, view, position, id) -> openGamesList(teamsList.get(position).toString()));
    }

    public void init(){
        teamsDbManager = new TeamsDbManager(this);
        btnBack = (Button) findViewById(R.id.BtnBackTeams);
        btnAdd = (Button) findViewById(R.id.BtnAddTeam);
        btnSearch = (Button) findViewById(R.id.BtnSearchTeam);
        editTextSearch = (EditText) findViewById(R.id.EditTextSearchTeam);
        listView = (ListView) findViewById(R.id.ListTeams);
        flag = false;
        setList("");
    }

    // Opens games list by team name
    private void openGamesList(String teamName){
        Intent intent = new Intent(this, GamesList.class);
        intent.putExtra("team", teamName);
        startActivity(intent);
    }

    // Adds team from the text view to the database using team database manager + updates the list view
    public void addTeam(String teamName){
        if (editTextSearch.getText().toString().isEmpty()) return;
        if (!teamsDbManager.addTeam(teamName)) {
            Toast.makeText(this, "Team Already Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        setList("");
    }

    // sets the list view for all teams in the database
    private void setList(String name){

        if (!Objects.equals(name, "")) {
            Team team = teamsDbManager.getTeam(name);
            teamsList = new ArrayList<>();
            teamsList.add(team);
            if (team == null) {
                Toast.makeText(this, "Team Not Found", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else teamsList = teamsDbManager.getAllTeams();

        ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this, android.R.layout.simple_list_item_1, teamsList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view;
                textView.setTextColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark));
                textView.setTypeface(null, Typeface.BOLD);
                return view;
            }
        };

        listView.setAdapter(adapter);
    }

    // Finds team from the text view and shows only it on the teams list
    private void findTeam(String teamName){
        if (editTextSearch.getText().toString().isEmpty()) {
            if (flag) {
                setList("");
                flag = false;
            }

            return;
        }
        flag = true;
        setList(teamName);
    }

    // Returns to main menu
    private void backToMenu(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}

package com.example.football;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
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
                backToMenu();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeam(editTextSearch.getText().toString());
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findTeam(editTextSearch.getText().toString());
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                openGamesList(teamsList.get(position).toString());
//            }
//        });
    }
    public void init(){
        teamsDbManager = new TeamsDbManager(this);
        btnBack = (Button) findViewById(R.id.BtnBack);
        btnAdd = (Button) findViewById(R.id.BtnAddTeam);
        btnSearch = (Button) findViewById(R.id.BtnSearchTeam);
        editTextSearch = (EditText) findViewById(R.id.EditTextSearchTeam);
        listView = (ListView) findViewById(R.id.ListTeams);
        setList();
    }

    private void backToMenu(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

//    private void openGamesList(String teamName){
//        Intent intent = new Intent(this, GamesList.class);
//        intent.putExtra("team", teamName);
//        startActivity(intent);
//    }

    public void addTeam(String teamName){
        if (editTextSearch.getText().toString().isEmpty()) return;
        if (!teamsDbManager.addTeam(teamName)) {
            Toast.makeText(this, "Team Already Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        setList();
    }

    private void setList(){
        teamsList = teamsDbManager.getAllTeams();
        ArrayAdapter<Team> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teamsList);
        listView.setAdapter(adapter);
    }

    private void findTeam(String teamName){
        if (editTextSearch.getText().toString().isEmpty()) {
            setList();
            return;
        }
        Team team = teamsDbManager.getTeam(teamName);
        List<Team> singleTeamList = new ArrayList<>();
        singleTeamList.add(team);
        if (team == null) {
            Toast.makeText(this, "Team Not Found", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayAdapter<Team> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, singleTeamList);
        listView.setAdapter(adapter);
    }
}

package com.example.football;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTeam extends AppCompatActivity {
    EditText gameId, gameDate, teamHome, teamAway, teamHomeScore, teamAwayScore;
    DatabaseHelper db2;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase Db;
    Button BtnAdd;
    Button btnback2;
    public int flag=0;
    String gameIdText, gameDateText, teamHomeText, teamAwayText;
    int teamHomeScoreText, teamAwayScoreText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();

        btnback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmenu();
            }
        });
        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;//Todo should changed
                //gameIdText = gameId.getText().toString();
                gameDateText = gameDate.getText().toString();
                teamHomeText = teamHome.getText().toString();
                teamAwayText = teamAway.getText().toString();
                teamHomeScoreText = Integer.parseInt(teamHomeScore.getText().toString());
                teamAwayScoreText = Integer.parseInt(teamAwayScore.getText().toString());
                FootballGame game = new FootballGame(gameDateText, teamHomeText, teamAwayText, teamHomeScoreText, teamAwayScoreText);
                Db = openHelper.getWritableDatabase();
                //Todo those if should changed
                if(game.getDate().isEmpty() || game.getTeamHome().isEmpty() || game.getTeamAway().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Field all Information",Toast.LENGTH_LONG).show();
                    flag=1;
                }
                if(game.getDate().isEmpty() && game.getTeamHome().isEmpty() && game.getTeamAway().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Field all Information",Toast.LENGTH_LONG).show();
                    flag=2;
                }
                else{
                    flag=0;
                }
                if(flag==0){

                    insert(game);
                    Toast.makeText(getApplicationContext(),"Added Succefull",Toast.LENGTH_LONG).show();
                    //gameId.setText("");
                    gameDate.setText("");
                    teamHome.setText("");
                    teamAway.setText("");
                    teamHomeScore.setText("");
                    teamAwayScore.setText("");
                }
            }
        });

    }
    public void init(){
        gameDate =(EditText)findViewById(R.id.DateInput);
        teamHome =(EditText)findViewById(R.id.TeamHomeInput);
        teamAway =(EditText)findViewById(R.id.TeamAwayInput);
        teamHomeScore =(EditText)findViewById(R.id.TeamHomeScoreInput);
        teamAwayScore =(EditText)findViewById(R.id.TeamAwayScoreInput);
        db2=new DatabaseHelper(this);
        openHelper=new DatabaseHelper(this);
        BtnAdd=(Button)findViewById(R.id.BtnAddTeam);
        btnback2=(Button)findViewById(R.id.btnBack);
    }
    public void insert(FootballGame f){
        ContentValues content = new ContentValues();
        content.put(DatabaseHelper.COLUMN_DATE, f.getDate());
        content.put(DatabaseHelper.COLUMN_HOME_TEAM,f.getTeamHome());
        content.put(DatabaseHelper.COLUMN_AWAY_TEAM,f.getTeamAway());
        content.put(DatabaseHelper.COLUMN_HOME_SCORE,f.getTeamHomeScore());
        content.put(DatabaseHelper.COLUMN_AWAY_SCORE,f.getTeamAwayScore());
        long id= Db.insert(DatabaseHelper.TABLE_GAMES,null,content);
        Db.close();
    }
    public void openmenu(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

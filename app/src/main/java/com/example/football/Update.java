package com.example.football;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    EditText gameId, gameDate, teamHome, teamAway, teamHomeScore, teamAwayScore;
    Button btnCheckId, btnUpdate, btnBack;
    TextView resultText;
    DatabaseHelper db;
    String gameIdText, gameDateText, teamHomeText, teamAwayText;
    int teamHomeScoreText, teamAwayScoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        init();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
//        btnCheckId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gameIdText = gameId.getText().toString();
//                if (gameIdText.isEmpty()) {
//                    resultText.setText("Please enter an ID to update");
//                } else {
//                    boolean idExists = db.chkid(gameIdText);
//                    if (!idExists) {
//                        resultText.setText("The ID was not found in the database. You can't update it.");
//                    } else {
//                        resultText.setText("The ID was found. You can update.");
//                    }
//                }
//            }
//        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameIdText = gameId.getText().toString();
                gameDateText = gameDate.getText().toString();
                teamHomeText = teamHome.getText().toString();
                teamAwayText = teamAway.getText().toString();
                teamHomeScoreText = Integer.parseInt(teamHomeScore.getText().toString());
                teamAwayScoreText = Integer.parseInt(teamAwayScore.getText().toString());

                if (gameIdText.isEmpty()) {
                    Toast.makeText(Update.this, "Please select a game ID.", Toast.LENGTH_LONG).show();
                } else if ( gameDateText.isEmpty() || teamHomeText.isEmpty() || teamAwayText.isEmpty()) {
                    Toast.makeText(Update.this, "Please fill in all fields.", Toast.LENGTH_LONG).show();
                } else {
                    boolean isUpdated = db.updateGame( new Game(teamHomeText, teamAwayText, teamHomeScoreText, teamAwayScoreText, gameDateText));
                    if (isUpdated) {
                        Toast.makeText(Update.this, "Game data updated successfully.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Update.this, "Failed to update game data.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void init() {
        gameId = findViewById(R.id.editText);
        gameDate = findViewById(R.id.editText3);
        teamHome = findViewById(R.id.editText4);
        teamAway = findViewById(R.id.editText5);
        teamHomeScore = findViewById(R.id.scoreInput);
        btnCheckId = findViewById(R.id.chk);
        btnUpdate = findViewById(R.id.updatebtn);
        resultText = findViewById(R.id.textView14);
        btnBack = findViewById(R.id.Backupdate);
        db = new DatabaseHelper(this);
    }

    public void openMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


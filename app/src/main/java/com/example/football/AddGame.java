package com.example.football;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;

// Class for the add game activity
public class AddGame extends AppCompatActivity {

    EditText gameDate, homeTeamName, awayTeamName, homeTeamScore, awayTeamScore;
    GameDbManager gameDbManager;
    TeamsDbManager teamsDbManager;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        init();

        btnAdd.setOnClickListener(v -> addGame());
        btnBack.setOnClickListener(v -> backToMenu());
    }

    public void init() {
        gameDbManager = new GameDbManager(this);
        teamsDbManager = new TeamsDbManager(this);
        gameDate =(EditText)findViewById(R.id.DateInput);
        homeTeamName =(EditText)findViewById(R.id.TeamHomeInput);
        awayTeamName =(EditText)findViewById(R.id.TeamAwayInput);
        homeTeamScore =(EditText)findViewById(R.id.TeamHomeScoreInput);
        awayTeamScore =(EditText)findViewById(R.id.TeamAwayScoreInput);
        btnAdd = (Button) findViewById(R.id.BtnAddGame);
        btnBack = (Button) findViewById(R.id.BtnBackAddGame);
    }

    // Reads game data from view fields, checks it and sends it to the database using game database manager
    private void addGame() {
        Game game = new Game();
        try {
            game = game .date(gameDate.getText().toString())
                        .homeTeamName(homeTeamName.getText().toString())
                        .awayTeamName(awayTeamName.getText().toString())
                        .homeTeamScore(Integer.parseInt(homeTeamScore.getText().toString()))
                        .awayTeamScore(Integer.parseInt(awayTeamScore.getText().toString()));
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Wrong Score Format", Toast.LENGTH_SHORT).show();
            return;
        }
        if (game.getHomeTeamScore() < 0 || game.getAwayTeamScore() < 0) {
            Toast.makeText(this, "Negative Score", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidDateFormat(game.getDate())) {
            Toast.makeText(this, "Wrong Date Format", Toast.LENGTH_SHORT).show();
            return;
        }
        if (! (teamsDbManager.doesTeamExist(game.getHomeTeamName()) && teamsDbManager.doesTeamExist(game.getAwayTeamName()))) {
            Toast.makeText(this, "One Or More Teams Are Not In Teams List", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!gameDbManager.addGame(game)) {
            Toast.makeText(this, "Something Went Wrong, Couldn't Add To Database", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
    }

    // Validates input date format
    private boolean isValidDateFormat(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        dateFormat.setLenient(false);

        try {
            if (dateStr.length() == 10 && dateStr.charAt(2) == '/' && dateStr.charAt(5) == '/') {
                dateFormat.parse(dateStr);
                return true;
            } else {
                return false;
            }
        } catch (java.text.ParseException e) {
            return false;
        }
    }

    // Returns back to main menu
    private void backToMenu(){
        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}


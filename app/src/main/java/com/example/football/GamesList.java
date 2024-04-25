package com.example.football;
import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// Class for the games list activity
public class GamesList extends AppCompatActivity {

    private String teamName;
    private GameDbManager gameDbManager;
    private TeamsDbManager teamsDbManager;
    private ListView listView;
    List<Game> gamesList;
    private TextView teamNameTitle;
    private Button btnBack, btnRemove;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        Intent intent = getIntent();
        if (intent.hasExtra("team")) {
            teamName = intent.getStringExtra("team");
        }
        init();

        btnBack.setOnClickListener(v -> back());
        btnRemove.setOnClickListener(v -> removeTeam());

        //Long press on game to remove it
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete");
            builder.setMessage("Are you sure you want to remove the game?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                Game gameToRemove = gamesList.get(position);
                if (gameToRemove != null) {
                    removeGame(gameToRemove);
                }
            });
            builder.setNegativeButton("No", null);
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        });
    }

    public void init(){
        gameDbManager = new GameDbManager(this);
        teamsDbManager = new TeamsDbManager(this);
        teamNameTitle = findViewById(R.id.TeamName);
        btnBack = findViewById(R.id.BtnBackGamesList);
        btnRemove = findViewById(R.id.BtnRemoveTeam);
        listView = findViewById(R.id.ListGames);
        gamesList = new ArrayList<>();

        teamNameTitle.setText(teamName);
        setList();
    }

    // Removes team from the database if no games for that team
    private void removeTeam() {
        if (gamesList.isEmpty()) {
            teamsDbManager.removeTeam(teamName);
            back();
            return;
        }
        Toast.makeText(this, "Team Games List Must Be Empty First", Toast.LENGTH_SHORT).show();
    }

    // Removes the game from the database and updates the list
    private void removeGame(Game game) {
        gameDbManager.removeGame(game.getId());
        setList();
    }

    // Updates the list sorting it by date
    private void setList(){
        gamesList = gameDbManager.getGames(teamName);

        gamesList.sort(new Comparator<Game>() {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.US);
            @Override
            public int compare(Game game1, Game game2) {
                try {
                    Date date1 = dateFormat.parse(game1.getDate());
                    Date date2 = dateFormat.parse(game2.getDate());
                    return date2.compareTo(date1);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        // Creates the adapter with custom cosmetics to put the game data into the list view
        ArrayAdapter<Game> adapter = new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, gamesList) {
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

    // Returns back to teams list
    private void back(){
        Intent intent=new Intent(this,TeamsList.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}



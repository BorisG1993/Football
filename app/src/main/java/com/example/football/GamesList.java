package com.example.football;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class GamesList(String teamName) extends AppCompatActivity {

    DatabaseHelper Db;
    DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);
        ListView listView = (ListView)findViewById(R.id.ListView);
        Db =new DatabaseHelper(this);
        dataManager = new DataManager(this);
        ArrayList<FootballGame> listGame = new ArrayList<>();
        Cursor data = dataManager.getAllGames();

        if(data.getCount()==0){
            Toast.makeText(GamesList.this,"The Database is Empty",Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                String dateText = data.getString(1);
                String teamHomeText = data.getString(2);
                String teamAwayText = data.getString(3);
                int teamHomeScoreText = data.getInt(4);
                int teamAwayScoreText = data.getInt(5);
                listGame.add(new FootballGame(dateText, teamHomeText, teamAwayText, teamHomeScoreText, teamAwayScoreText));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listGame);
                listView.setAdapter(listAdapter);

            }
        }
    }
}

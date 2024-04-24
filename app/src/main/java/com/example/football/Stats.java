package com.example.football;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    Button search22;
    EditText txt66;
    String txt77;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        search22=(Button)findViewById(R.id.button2);
        search22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfGames();
            }
        });

    }

    public void listOfGames(){
        txt66=(EditText)findViewById(R.id.editText9);
        txt77=txt66.getText().toString();
        ListView listView=(ListView)findViewById(R.id.llll);
        mydb=new DatabaseHelper(this);
        ArrayList<FootballGame> thelist=new ArrayList<>();
        Cursor data = (Cursor) mydb.getAllGames();
        if(data.getCount()==0){
            Toast.makeText(Stats.this,"The Database is Empty",Toast.LENGTH_LONG).show();

        }else{
            while(data.moveToNext()){
                String dateText = data.getString(1);
                String teamHomeText = data.getString(2);
                String teamAwayText = data.getString(3);
                int teamHomeScoreText = data.getInt(4);
                int teamAwayScoreText = data.getInt(5);
                thelist.add(new FootballGame(dateText, teamHomeText, teamAwayText, teamHomeScoreText, teamAwayScoreText));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listAdapter);

            }
        }

    }
}

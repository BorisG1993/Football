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

public class ViewListContentsDate extends AppCompatActivity {
    DatabaseHelper myDB,mydb2;
    SearchDate dd;
    String date;
    EditText txt22,txt55;
    String txt33,txt44;
    Button search;
    public int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout2);
        search = (Button) findViewById(R.id.btn44);
        txt22 = (EditText) findViewById(R.id.editText8);
        txt44 = txt22.getText().toString();
        mydb2=new DatabaseHelper(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openvoid();
            }
        });
    }

    public void openvoid() {
        myDB = new DatabaseHelper(this);
        txt55 = (EditText) findViewById(R.id.editText8);
        txt33 = txt22.getText().toString();
        ListView listView = (ListView) findViewById(R.id.lll);
        ArrayList<FootballGame> thelist2 = new ArrayList<>();
        Cursor data = (Cursor) myDB.getAllGames();
        if (data.getCount() == 0) {
            Toast.makeText(ViewListContentsDate.this, "The Database is Empty", Toast.LENGTH_LONG).show();

        } else {
            while (data.moveToNext()) {
                String dateText = data.getString(1);
                String teamHomeText = data.getString(2);
                String teamAwayText = data.getString(3);
                int teamHomeScoreText = data.getInt(4);
                int teamAwayScoreText = data.getInt(5);
                thelist2.add(new FootballGame(dateText, teamHomeText, teamAwayText, teamHomeScoreText, teamAwayScoreText));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist2);
                listView.setAdapter(listAdapter);

            }

        }
    }
}

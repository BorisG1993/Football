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
        ArrayList<FootBall> thelist2 = new ArrayList<>();
        Cursor data = myDB.getListDate(txt33);
        if (data.getCount() == 0) {
            Toast.makeText(ViewListContentsDate.this, "The Database is Empty", Toast.LENGTH_LONG).show();

        } else {
            while (data.moveToNext()) {
                String city = data.getString(1);
                String date = data.getString(2);
                String Team1 = data.getString(3);
                String Team2 = data.getString(4);
                thelist2.add(new FootBall(city, date, Team1, Team2));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thelist2);
                listView.setAdapter(listAdapter);

            }

        }
    }
}

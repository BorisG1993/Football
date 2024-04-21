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

public class ViewContetn2 extends AppCompatActivity {

    Button search22;
            EditText txt66;
            String txt77;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontent2);
search22=(Button)findViewById(R.id.button2);
search22.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startsearch();
    }
});

    }

    public void startsearch(){
        txt66=(EditText)findViewById(R.id.editText9);
        txt77=txt66.getText().toString();
        ListView listView=(ListView)findViewById(R.id.llll);
        mydb=new DatabaseHelper(this);
        ArrayList<FootBall> thelist=new ArrayList<>();
        Cursor data=mydb.getListTeam(txt77);
        if(data.getCount()==0){
            Toast.makeText(ViewContetn2.this,"The Database is Empty",Toast.LENGTH_LONG).show();

        }else{
            while(data.moveToNext()){
                String city=data.getString(1);
                String date=data.getString(2);
                String Team1=data.getString(3);
                String Team2=data.getString(4);
                thelist.add(new FootBall(city,date,Team1,Team2));
                ListAdapter listAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listAdapter);

            }
        }

    }
}

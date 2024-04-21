package com.example.football;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);
        ListView listView=(ListView)findViewById(R.id.ListView);
myDB=new DatabaseHelper(this);
        ArrayList<FootBall> thelist=new ArrayList<>();
        Cursor data=myDB.getListContent();

if(data.getCount()==0){
    Toast.makeText(ViewListContents.this,"The Database is Empty",Toast.LENGTH_LONG).show();

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

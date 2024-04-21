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

public class Add extends AppCompatActivity {
EditText Txtcity,TxtDate,TxtTeam1,TxtTeam2;
DatabaseHelper db2;
SQLiteOpenHelper openHelper;
SQLiteDatabase db;
Button BtnAdd;
Button btnback2;
public int flag=0;
private String gettxtcity,gettxtDate,getTxttem1,getTxttem2;
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
                flag=0;
                gettxtcity=Txtcity.getText().toString();
                gettxtDate=TxtDate.getText().toString();
                getTxttem1=TxtTeam1.getText().toString();
                getTxttem2=TxtTeam2.getText().toString();
                FootBall f1=new FootBall(gettxtcity,gettxtDate,getTxttem1,getTxttem2);
db=openHelper.getWritableDatabase();
                if(f1.getCity().isEmpty() || f1.getDate().isEmpty() || f1.getTeam1().isEmpty() || f1.getTeam2().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Field all Information",Toast.LENGTH_LONG).show();
                    flag=1;
                }
               if(f1.getCity().isEmpty() && f1.getDate().isEmpty() && f1.getTeam1().isEmpty() && f1.getTeam2().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Field all Information",Toast.LENGTH_LONG).show();
                    flag=2;
                }
                else{

flag=0;
                }
                if(flag==0){

                    insert(f1);
                    Toast.makeText(getApplicationContext(),"Added Succefull",Toast.LENGTH_LONG).show();
                    Txtcity.setText("");
                    TxtDate.setText("");
                    TxtTeam1.setText("");
                    TxtTeam2.setText("");
                }
            }
        });

    }
    public void init(){
        Txtcity=(EditText)findViewById(R.id.txtCity);
        TxtDate=(EditText)findViewById(R.id.TxtDate);
        TxtTeam1=(EditText)findViewById(R.id.TxtTeam1);
        TxtTeam2=(EditText)findViewById(R.id.TxtTeam2);
        db2=new DatabaseHelper(this);
        openHelper=new DatabaseHelper(this);
        BtnAdd=(Button)findViewById(R.id.BtnAdd);

btnback2=(Button)findViewById(R.id.btnBack);


    }
    public void insert(FootBall f){
        ContentValues content=new ContentValues();
        content.put(DatabaseHelper.COL_2,f.getCity());
        content.put(DatabaseHelper.COL_3,f.getDate());
        content.put(DatabaseHelper.COL_4,f.getTeam1());
        content.put(DatabaseHelper.COL_5,f.getTeam2());
long id=db.insert(DatabaseHelper.TabelName,null,content);
db.close();
    }
    public void openmenu(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

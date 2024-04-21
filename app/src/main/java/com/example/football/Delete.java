package com.example.football;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    DatabaseHelper db2;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button chk,delete;
    EditText date;
    TextView results;
    String date1;
    Button bk2;
    public int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        init();
        deletedata();
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date1=date.getText().toString();
                db=openHelper.getWritableDatabase();
                flag=0;
                if(date1.isEmpty()){
                    results.setText("Please Enter Something");
                    flag=1;
                }
                if (db2.chkid(date1)==false){
                    results.setText("The ID Not Found in Database no Cant Deleted");
                    flag=2;
                }
                 if(db2.chkid(date1)==true){
                    flag=0;
                    results.setText("Founded you Can Deleted");
                }


            }
        });
        bk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmenu();
            }
        });

    }
    public void init(){
        db2=new DatabaseHelper(this);
        openHelper=new DatabaseHelper(this);
        delete=(Button)findViewById(R.id.Deletebtn);
        chk=(Button)findViewById(R.id.chk2);
        date=(EditText)findViewById(R.id.editText6);
        results=(TextView)findViewById(R.id.txtfound);
bk2=(Button)findViewById(R.id.Bk);


    }
    public void deletedata(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0){
                    date1=date.getText().toString();
                    Integer deleterows=db2.deleted(date1);
                    if(deleterows>0){
                        Toast.makeText(Delete.this,"Data Deleted",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(Delete.this,"Data  not Deleted",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(Delete.this,"id not found in database",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void openmenu(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

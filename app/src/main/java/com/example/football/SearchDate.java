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

public class SearchDate extends AppCompatActivity {
Button btn1,btn2;
EditText txt1;
TextView textres;
DatabaseHelper db2;
SQLiteOpenHelper openHelper;
SQLiteDatabase db;
  String txt2,txt3;
  public int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch_date);
        init();
        txt2=txt1.getText().toString();
        txt3=txt2;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt2=txt1.getText().toString();
                if(txt2.isEmpty()){
                    flag=1;
                    Toast.makeText(SearchDate.this,"please enter information search",Toast.LENGTH_LONG).show();

                }
//                if(db2.chkDate(txt2)==false){
//                    flag=2;
//                    textres.setText("this Date not found in Database");
//                }
//                if(db2.chkDate(txt2)==true){
//                    flag=0;
//                    textres.setText("Founded go list Date to show all this date");
//                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0)
                {
                    open();

                }
                else{
                    Toast.makeText(SearchDate.this,"Somthing Wron you may tybe Date wrong or not found",Toast.LENGTH_LONG).show();
                }
                open();
            }
        });
    }
    public void init(){

        btn1=(Button)findViewById(R.id.button);
        btn2=(Button)findViewById(R.id.btn22);
        txt1=(EditText)findViewById(R.id.editText7);
        textres=(TextView)findViewById(R.id.textView13);
        db2=new DatabaseHelper(this);
        openHelper=new DatabaseHelper(this);


    }
    public void open(){
        Intent intent=new Intent(this,ViewListContentsDate.class);
        startActivity(intent);
    }

}

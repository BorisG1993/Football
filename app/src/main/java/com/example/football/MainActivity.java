package com.example.football;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnAdd,BtnUpdate,BtnDelete,Btnlist,btnsearch1,btnsearch2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdd();
            }
        });
        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdate();
            }
        });
        Btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlist();
            }
        });
        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDelete();
            }
        });
        btnsearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchdate();
            }
        });
        btnsearch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensearchteam();
            }
        });
    }

    public void init(){
        btnAdd=(Button)findViewById(R.id.Add);
        BtnUpdate=(Button)findViewById(R.id.Update) ;
        BtnDelete=(Button)findViewById(R.id.Delete);
        Btnlist=(Button)findViewById(R.id.FootBallList);
        btnsearch1=(Button)findViewById(R.id.Sdate);
        btnsearch2=(Button)findViewById(R.id.SearchTeam);

    }
    public void openAdd(){
        Intent intent=new Intent(this,Add.class);
        startActivity(intent);
    }
    public void openUpdate()
    {
        Intent intent=new Intent(this,Update.class);
        startActivity(intent);
    }
    public void openlist(){
        Intent intent=new Intent(this,ViewListContents.class);
        startActivity(intent);
    }
    public void openDelete()
    {
        Intent intent=new Intent(this,Delete.class);
        startActivity(intent);
    }
public void openSearchdate(){
    Intent intent=new Intent(this,ViewListContentsDate.class);
    startActivity(intent);
}
public void opensearchteam(){
        Intent inten=new Intent(this,ViewContetn2.class);
        startActivity(inten);
}

}

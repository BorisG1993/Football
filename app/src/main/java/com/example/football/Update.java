package com.example.football;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends AppCompatActivity {
Button btnupdate,chkid1,back2;
EditText text1,text2,text3,text4,text5;
TextView res;
DatabaseHelper db;
String gettext1,gettext2,gettext3,gettext4,gettext5;
public int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        init();
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmenu();
            }
        });
        chkid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettext1=text1.getText().toString();
                flag=0;
                if(gettext1.isEmpty())
                {
                    res.setText("please enter id to update");
                    flag=1;
                }
                 if(db.chkid(gettext1)==false){
                    flag=2;
                    res.setText("The id its not found in database you cant update this");
                }
                 if(db.chkid(gettext1)==true){
                    flag=0;
                     res.setText("The id is founded, you can update");

                }
            }
        });

        isupdatefine ();

    }

    public void init(){
        chkid1=(Button)findViewById(R.id.chk);
        btnupdate=(Button)findViewById(R.id.updatebtn);
        text1=(EditText)findViewById(R.id.editText);
        text2=(EditText)findViewById(R.id.editText2);
        text3=(EditText)findViewById(R.id.editText3);
        text4=(EditText)findViewById(R.id.editText4);
        text5=(EditText)findViewById(R.id.editText5);
        db=new DatabaseHelper(this);
        res=(TextView)findViewById(R.id.textView14);
        back2=(Button)findViewById(R.id.Backupdate);
    }
    public void isupdatefine (){
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    gettext1 = text1.getText().toString();
                    gettext2 = text2.getText().toString();
                    gettext3 = text3.getText().toString();
                    gettext4 = text4.getText().toString();
                    gettext5 = text5.getText().toString();
                    if(gettext1.isEmpty()){

                        Toast.makeText(Update.this,"You cant update because may id not good or not found",Toast.LENGTH_LONG).show();
                    }
                    else if(gettext2.isEmpty() || gettext3.isEmpty() || gettext4.isEmpty() || gettext5.isEmpty()){
                        Toast.makeText(Update.this,"please fild all information",Toast.LENGTH_LONG).show();
                    }
                    else if(gettext2.isEmpty() && gettext3.isEmpty() && gettext4.isEmpty() && gettext5.isEmpty()){
                        Toast.makeText(Update.this,"please fild all information",Toast.LENGTH_LONG).show();
                    }
                    else {
                        boolean isupdated = db.UpdateData(gettext1, gettext2, gettext3, gettext4, gettext5);
                        if (isupdated == true) {
                            Toast.makeText(Update.this, "Data updated", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Update.this, "Data not updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(Update.this,"You cant update because may id not good or not found",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    public void openmenu(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

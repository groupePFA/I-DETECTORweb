package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.username);
        TextView opassword =(TextView) findViewById(R.id.password);

        DB = new DBHelper(this);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String opass=opassword.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(opass)){
                    Toast.makeText(MainActivity.this, "all field are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean check = DB.checkpassword(user,opass);
                    if (check==false){
                        Boolean insert = DB.insertData(user,opass);
                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                        if (insert==true){
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent(getApplicationContext(),LoginActivity.class);
                        }else{
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });
    }
}
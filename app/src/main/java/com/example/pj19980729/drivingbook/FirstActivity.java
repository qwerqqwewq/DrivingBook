package com.example.pj19980729.drivingbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {


    Button login;
    Button regist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        login = (Button) findViewById(R.id.login);
        regist = (Button) findViewById(R.id.regist);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this,RegistActivity.class);
                startActivity(intent);
            }
        });


    }
}

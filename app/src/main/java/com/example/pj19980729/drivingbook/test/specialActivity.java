package com.example.pj19980729.drivingbook.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pj19980729.drivingbook.R;

public class specialActivity extends AppCompatActivity {

    private Button click;
    private Button judge;
    private Button multiple;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        click = (Button) findViewById(R.id.button11);
        judge = (Button) findViewById(R.id.button12);
        multiple = (Button) findViewById(R.id.button13);

        final int subjectId = getIntent().getIntExtra("sid", 0);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(specialActivity.this,specialtestActivity.class);
                intent.putExtra("sid",subjectId);
                intent.putExtra("tid",1);
            }
        });

        judge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(specialActivity.this,specialtestActivity.class);
                intent.putExtra("sid",subjectId);
                intent.putExtra("tid",2);
            }
        });

        multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(specialActivity.this,specialtestActivity.class);
                intent.putExtra("sid",subjectId);
                intent.putExtra("tid",6);
            }
        });



    }
}

package com.example.pj19980729.drivingbook.emptyactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pj19980729.drivingbook.test.ExamActivity;
import com.example.pj19980729.drivingbook.R;
import com.example.pj19980729.drivingbook.test.TestActivity;
import com.example.pj19980729.drivingbook.test.WrongActivity;

public class ForthActivity extends AppCompatActivity {

    Button test2,exam2,wrong2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);

        test2=findViewById(R.id.button7);
        exam2=findViewById(R.id.button8);
        wrong2=findViewById(R.id.button9);


        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ForthActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });

        exam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ForthActivity.this,ExamActivity.class);
                startActivity(intent);
            }
        });

        wrong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ForthActivity.this,WrongActivity.class);
                startActivity(intent);
            }
        });


    }
}

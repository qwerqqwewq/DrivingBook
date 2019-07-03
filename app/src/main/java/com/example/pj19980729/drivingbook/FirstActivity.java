package com.example.pj19980729.drivingbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button test,exam,wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        test=findViewById(R.id.button3);
        exam=findViewById(R.id.button5);
        wrong=findViewById(R.id.button6);




    }
}

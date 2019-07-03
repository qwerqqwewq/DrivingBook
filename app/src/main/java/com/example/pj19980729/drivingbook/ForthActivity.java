package com.example.pj19980729.drivingbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ForthActivity extends AppCompatActivity {

    Button test,exam,wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);

        test=findViewById(R.id.button7);
        exam=findViewById(R.id.button8);
        wrong=findViewById(R.id.button9);
    }
}

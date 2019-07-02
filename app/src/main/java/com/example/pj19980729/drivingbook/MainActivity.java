package com.example.pj19980729.drivingbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup2);
        tabHost=(TabHost)findViewById(R.id.tabHost);

    }
}

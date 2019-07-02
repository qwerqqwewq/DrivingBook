package com.example.pj19980729.drivingbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegistActivity extends AppCompatActivity {

    RadioGroup sexgroup;
    RadioButton sexman,sexwoman;
    String sex;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        sexgroup = findViewById(R.id.radioGroup);
        sexman = findViewById(R.id.radioButton1);
        sexwoman = findViewById(R.id.radioButton2);
        exit = findViewById(R.id.button4);


        sexgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        sex="男";
                        break;
                    case R.id.radioButton2:
                        sex="女";
                        break;
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistActivity.this.finish();
            }
        });


    }
}

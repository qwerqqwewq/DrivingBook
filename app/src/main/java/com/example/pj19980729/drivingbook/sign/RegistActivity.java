package com.example.pj19980729.drivingbook.sign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pj19980729.drivingbook.R;

public class RegistActivity extends AppCompatActivity {


    Button exit,confirm;
    EditText registname,registpassword,repeatpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);


        exit = findViewById(R.id.button4);
        confirm=findViewById(R.id.button);


        registname=findViewById(R.id.editText);
        registpassword=findViewById(R.id.editText2);
        repeatpwd=findViewById(R.id.editText3);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

package com.example.pj19980729.drivingbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText uname;
    EditText upwd;
    EditText confirm;
    Button login;
    Button exit;
    TextView captcha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = (EditText) findViewById(R.id.editText3);
        upwd = (EditText) findViewById(R.id.editText4);
        login = (Button) findViewById(R.id.button);
        exit = (Button) findViewById(R.id.button2);
        captcha= (TextView) findViewById(R.id.textView5);
        confirm= (EditText) findViewById(R.id.editText6);

        captcha.setText(""+(int)(Math.random()*9000+1000));
        captcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captcha.setText(""+(int)(Math.random()*9000+1000));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirm.getText().toString().equals(captcha.getText().toString())) {
                    if (uname.getText().toString().equals("123") && upwd.getText().toString().equals("123")) {
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"验证码错误",Toast.LENGTH_LONG).show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
    }
}

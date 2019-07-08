package com.example.pj19980729.drivingbook.sign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj19980729.drivingbook.okhttp.RequestManager;

import com.example.pj19980729.drivingbook.MainActivity;
import com.example.pj19980729.drivingbook.R;
import com.example.pj19980729.drivingbook.utils.Values;

import java.util.HashMap;

import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity {

    EditText uname;
    EditText upwd;
    EditText confirm;
    Button login;
    Button exit;
    TextView captcha;
    TextView regist;

    private OkHttpClient okHttpClient;
    private RequestManager requestManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = (EditText) findViewById(R.id.editText3);
        upwd = (EditText) findViewById(R.id.editText4);
        login = (Button) findViewById(R.id.button);
        exit = (Button) findViewById(R.id.button2);
        captcha = (TextView) findViewById(R.id.textView5);
        confirm = (EditText) findViewById(R.id.editText6);
        regist = (TextView) findViewById(R.id.textView12);

        captcha.setText("" + (int) (Math.random() * 9000 + 1000));
        captcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captcha.setText("" + (int) (Math.random() * 9000 + 1000));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirm.getText().toString().equals(captcha.getText().toString())) {
                    HashMap<String, String> map = new HashMap();
                    map.put("name",uname.getText().toString());
                    map.put("pwd",upwd.getText().toString());
                    requestManager.requestSyn("login/do", RequestManager.TYPE_POST_JSON, map);
                    if (Values.asyresponse.equals("-1")){
                        Toast.makeText(LoginActivity.this,"同户名不存在或密码不正确",Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this,MainActivity.class);
                        intent.putExtra("name",uname.getText().toString());
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "验证码错误", Toast.LENGTH_LONG).show();
                }
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
    }

//    //异步GET请求
//    public void getAsyn(View View){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String url="http://";
//                OkHttpClient client = new OkHttpClient();
//                //get方式
//                //Request request = new Request.Builder().url(url).build();
//                //post方式
//                RequestBody body = new FormBody.Builder().add("password","111")
//                                                            .add("username","111").build();
//                Request request= new Request.Builder().post(body).url(url).build();
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                        res=response.body().toString();
//                    }
//                });
//            }
//        }).start();
}
package com.example.pj19980729.drivingbook.sign;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj19980729.drivingbook.application.AppVariables;
import com.example.pj19980729.drivingbook.constant.Constants;
import com.example.pj19980729.drivingbook.entity.User;
import com.example.pj19980729.drivingbook.okhttp.RequestManager;

import com.example.pj19980729.drivingbook.MainActivity;
import com.example.pj19980729.drivingbook.R;
import com.example.pj19980729.drivingbook.utils.MD5Util;
import com.example.pj19980729.drivingbook.utils.Values;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    EditText uname;
    EditText upwd;
    EditText confirm;
    Button login;
    Button exit;
    TextView captcha;
    TextView regist;

    String res;

    private OkHttpClient okHttpClient;

    private RequestManager requestManager;

    private User user;


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

        requestManager  = new RequestManager(LoginActivity.this);

        user = new User();

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
                    postAsyn(uname.getText().toString(),upwd.getText().toString());
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

    //异步GET请求
    public void getAsyn(View View) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://";
                OkHttpClient client = new OkHttpClient();
                //get方式
                //Request request = new Request.Builder().url(url).build();
                //post方式
                RequestBody body = new FormBody.Builder().add("password", "111")
                        .add("username", "111").build();
                Request request = new Request.Builder().post(body).url(url).build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        res = response.body().toString();
                    }
                });
            }
        }).start();
    }


    //异步POST请求
    public void postAsyn(final String name, final String pwd){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                user.setName(name);

                RequestBody body = new FormBody.Builder()
                        .add("name",name)
                        .add("pwd",pwd)
                        .build();
                String url = String.format("%s/%s", Constants.context, "user/login/do");
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String str = response.body().string();
                        Map<String,Object> map = com.alibaba.fastjson.JSONObject.parseObject(str,Map.class) ;
                        int flag =(Integer)map.get("status");

                            if (flag==1){
                                Intent intent = new Intent();
                                intent.setClass(LoginActivity.this,MainActivity.class);
                                Integer uid = (Integer) map.get("uid");
                                user.setId(uid);
                                AppVariables.map.put("user", user);
                                startActivity(intent);
                            }else if (flag==0){
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this,"用户名或密码不正确",Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }else {
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this,"未知错误",Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }


                    }
                });
            }
        }).start();
    }





}


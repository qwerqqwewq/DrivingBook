package com.example.pj19980729.drivingbook.sign;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pj19980729.drivingbook.MainActivity;
import com.example.pj19980729.drivingbook.R;
import com.example.pj19980729.drivingbook.constant.Constants;
import com.example.pj19980729.drivingbook.utils.MD5Util;

import org.jetbrains.annotations.NotNull;
import com.alibaba.fastjson.JSONObject;


import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                if (registpassword.getText().toString().equals(repeatpwd.getText().toString())){
                    postAsyn(registname.getText().toString(),registpassword.getText().toString());
                }else {
                    Toast.makeText(RegistActivity.this,"两次输入的用户名必须相同",Toast.LENGTH_LONG).show();
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

    //异步POST请求
    public void postAsyn(final String name, final String pwd){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("name",name)
                        .add("pwd",pwd)
                        .build();
                String url = String.format("%s/%s", Constants.context, "user/regist/do");
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(RegistActivity.this,"网路连接错误哦",Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String str = response.body().string();
                        Map<String,Object> map = JSONObject.parseObject(str,Map.class) ;
                        int flag =(Integer)map.get("status");
                            if (flag==1){
                            Intent intent = new Intent();
                                intent.setClass(RegistActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }else if (flag==0){
                                Looper.prepare();
                                Toast.makeText(RegistActivity.this,"该用户名已存在",Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }else if (flag==2){
                                Looper.prepare();
                                Toast.makeText(RegistActivity.this,"用户名或密码不能为空",Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }else {
                                Looper.prepare();
                                Toast.makeText(RegistActivity.this,"未知错误，请联系管理员",Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                        }
                    }
                });
            }
        }).start();
    }

}

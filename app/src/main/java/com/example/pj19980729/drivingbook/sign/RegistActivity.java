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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
    Boolean isflag;

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
                    if (isflag==true){
                        Intent intent = new Intent();
                        intent.setClass(RegistActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
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
                String upwd = MD5Util.MD5Encode(pwd);
                RequestBody body = new FormBody.Builder()
                        .add("name",name)
                        .add("pwd",upwd)
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

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String str = response.body().string();
                        Integer flag = null;
                        try {
                            JSONArray jsonArray = new JSONArray(str);
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Integer status = jsonObject.getInt("status");
                                flag=status;
                            }
                            if (flag==1){
                                Looper.prepare();
                                Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                isflag=true;
                                Looper.loop();
                            }else if (flag==0){
                                Looper.prepare();
                                Toast.makeText(RegistActivity.this,"该用户名已存在",Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }
}

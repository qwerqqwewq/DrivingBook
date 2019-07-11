package com.example.pj19980729.drivingbook.test;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.example.pj19980729.drivingbook.R;
import com.example.pj19980729.drivingbook.application.AppVariables;
import com.example.pj19980729.drivingbook.entity.User;
import com.example.pj19980729.drivingbook.okhttp.RequestUtil;
import com.example.pj19980729.drivingbook.utils.ViewPageAdapter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoveActivity extends AppCompatActivity {

    List<Integer> qids = new ArrayList<>();
    //    WebView question1;
    ViewPager lovevp;
    List<String> listk=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);

        lovevp = findViewById(R.id.lovevp);

        getQuestionIds();


        listk.add("https//m.baidu.com");
        listk.add("https://www.csdn.net/");

        ViewPageAdapter adapter =new ViewPageAdapter(this,listk);
        lovevp.setAdapter(adapter);
    }


    public void getQuestionIds() {
        Map map = new HashMap();
        map.put("uid", ((User)AppVariables.map.get("user")).getId());

        RequestUtil requestUtil = new RequestUtil();
        requestUtil.doPost("question/getIds/", map, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data= response.body().string();
                Map map1 = (Map) JSON.parse(data);
                qids = (List<Integer>) map1.get("qids");
            }
        });

    }
}

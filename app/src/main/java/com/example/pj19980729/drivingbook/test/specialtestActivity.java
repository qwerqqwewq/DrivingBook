package com.example.pj19980729.drivingbook.test;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.example.pj19980729.drivingbook.R;
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

public class specialtestActivity extends AppCompatActivity {


    List<Integer> qids = new ArrayList<>();
    //    WebView question1;
    ViewPager specialvp;
    List<String> listk=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialtest);

        specialvp = findViewById(R.id.specialvp);

        getQuestionIds();


        listk.add("https//m.baidu.com");
        listk.add("https://www.csdn.net/");

        ViewPageAdapter adapter =new ViewPageAdapter(this,listk);
        specialvp.setAdapter(adapter);




    }


    public void getQuestionIds() {
        int sid = getIntent().getIntExtra("sid", 0);
        Integer tid = getIntent().getIntExtra("tid",0);
        Integer subjectId;
        Map map = new HashMap();
        List<Integer> tids = new ArrayList<>();
        tids.add(tid);
        if (sid != 0) {
            subjectId = sid;
            map.put("subjectId", subjectId);
            map.put("tid",tids);
        }
        map.put("exam", false);

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

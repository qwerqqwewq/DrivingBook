package com.example.pj19980729.drivingbook.test;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.example.pj19980729.drivingbook.R;
import com.example.pj19980729.drivingbook.application.AppVariables;
import com.example.pj19980729.drivingbook.constant.Constants;
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

public class WrongActivity extends AppCompatActivity {


    List<Integer> qids = new ArrayList<>();
//    WebView question1;
    ViewPager wrongvp;
    List<String> listk=new ArrayList<>();
    ViewPageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);

        wrongvp = findViewById(R.id.wrongvp);

        getQuestionIds();


        String urlx = String.format("%s/%s", Constants.context,Constants.quiz);
        for (int i=0;i<qids.size();i++){
            String qurl=String.format("%s/%s?num=%s",urlx,qids.get(i),i+1);
            listk.add(qurl);
        }


        adapter =new ViewPageAdapter(this,listk);
        wrongvp.setAdapter(adapter);
        wrongvp.setOffscreenPageLimit(qids.size());

    }


    public void getQuestionIds() {
        Map map = new HashMap();
        map.put("uid", ((User)AppVariables.map.get("user")).getId());
        RequestUtil requestUtil = new RequestUtil();
        requestUtil.doPost("mistake/find/", map, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data= response.body().string();
                Map map1 = (Map) JSON.parse(data);
                qids = (List<Integer>) map1.get("qid");
                String urlx = String.format("%s/%s", Constants.context,Constants.quiz);
                for (int i=0;i<qids.size();i++){
                    int k= i+1;
                    String qurl=String.format("%s/%s?num=%s&uid=%s",urlx,qids.get(i),k,((User)AppVariables.map.get("user")).getId());
                    listk.add(qurl);
                }
                adapter.notifyDataSetChanged();
            }
        });

    }
}


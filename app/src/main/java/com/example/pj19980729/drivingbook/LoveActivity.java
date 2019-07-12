package com.example.pj19980729.drivingbook;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.alibaba.fastjson.JSON;
import com.example.pj19980729.drivingbook.application.AppVariables;
import com.example.pj19980729.drivingbook.constant.Constants;
import com.example.pj19980729.drivingbook.entity.QuestionVO;
import com.example.pj19980729.drivingbook.entity.User;
import com.example.pj19980729.drivingbook.okhttp.RequestUtil;
import com.example.pj19980729.drivingbook.utils.ListViewAdapter;
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

    ListView lovelv;
    List<Integer> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();

    List<String> listk=new ArrayList<>();

    List<Integer> qids = new ArrayList<>();
    String qid;

    List<QuestionVO> questionVOList = new ArrayList<>();

    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);

        getQuestionIds();
        Map<String, Object> map = null;

        lovelv = findViewById(R.id.lovelv);
        adapter = new ListViewAdapter(this, list1, list2);
        lovelv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(LoveActivity.this,LoveCommentActivity.class);
                intent.putExtra("qid", qids.get(position));
                adapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });

    }


    public void getQuestionIds() {
        final Map map = new HashMap();
        map.put("uid", ((User) AppVariables.map.get("user")).getId());
        RequestUtil requestUtil = new RequestUtil();
        requestUtil.doPost("fav/find", map, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();
                Map map1 = (Map) JSON.parse(data);
                qids = (List<Integer>) JSON.parse( map1.get("qid").toString());

                for (int i = 0; i < qids.size(); i++) {
                    getQuestion(qids.get(i));
                    list1.add(qids.get(i));
                    list2.add(questionVOList.get(i).getContent());
                }
            }
        });
    }

    public void getQuestion(Integer qid){

        RequestUtil requestUtil = new RequestUtil();

        Response response = null;
        try {
            response = requestUtil.doPostSy("question/get/" + qid, null);
            String data = response.body().string();
            questionVOList.add(JSON.parseObject(data,QuestionVO.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

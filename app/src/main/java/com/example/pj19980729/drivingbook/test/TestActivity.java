package com.example.pj19980729.drivingbook.test;

import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj19980729.drivingbook.R;
import com.example.pj19980729.drivingbook.constant.Constants;

import org.jetbrains.annotations.NotNull;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {

    ViewPager testvp;
    TextView username,usercomment,replyname,replycomment,selectall;
    ArrayList<View> vpList;
    ListView comment,question;

    List<Integer> qids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        testvp=findViewById(R.id.testvp);

        //testLayout
        comment = findViewById(R.id.comment);
        question = findViewById(R.id.question);


        //commentLayout
        username = (TextView) findViewById(R.id.textView11);
        usercomment = (TextView) findViewById(R.id.textView13);
        replyname = (TextView) findViewById(R.id.textView17);
        replycomment = (TextView) findViewById(R.id.textView18);
        selectall = (TextView) findViewById(R.id.textView19);


        LayoutInflater inflater=LayoutInflater.from(this);
        vpList=new ArrayList<View>();



        resume();
    }

    protected void resume() {
        int sid = getIntent().getIntExtra("sid",0);
        Integer subjectId;
        if (sid != 0) {
            subjectId = sid;
        } else {
            subjectId = null;
        }

        getIds(subjectId, null);


    }

    //    //异步GET请求
//    public void getAsyn(View View) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String url = String.format("%s/%s", Constants.context, "question/getIds/");
//                OkHttpClient client = new OkHttpClient();
//                //get方式
//                Request request = new Request.Builder().url(url).build();
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                        res = response.body().toString();
//                    }
//                });
//            }
//        }).start();
//    }
    public void getQuestion(final Integer qid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String url = String.format("%s/%s", Constants.context, "question/"+qid);
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(TestActivity.this,"获取题目失败，请检查网络设置", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    }
                });
            }
        });
    }

    //异步POST请求
    public void getIds(final Integer subjectId, final Integer[] tids){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                JSONObject jsonObject = new JSONObject();

                if (subjectId !=null) {
                    try {
                        jsonObject.put("subjectId", subjectId.intValue());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (tids!=null) {
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < tids.length; i++) {
                        jsonArray.add(tids[i]);
                    }
                    try {
                        jsonObject.put("tid[]", jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                //封装请求参数于RequestBody的body中
                String data = jsonObject.toString();
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), data);

                String url = String.format("%s/%s", Constants.context, "question/getIds/");
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(TestActivity.this,"网络连接异常，请检查你的网络！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String str = response.body().string();
                        Map<String,Object> map = JSONObject.parseObject(str,Map.class) ;

                        JSONArray jsonArray = (JSONArray) map.get("qids");
                        Iterator iterator = jsonArray.iterator();
                        qids = new ArrayList<>();
                        while (iterator.hasNext()) {
                            qids.add((Integer) iterator.next());
                        }


                        Log.i("***************o:***************", String.valueOf(qids.get(0)));
                    }
                });
            }
        }).start();
    }
}

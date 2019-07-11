package com.example.pj19980729.drivingbook.test;

import android.net.http.SslError;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.pj19980729.drivingbook.R;
import org.jetbrains.annotations.NotNull;

import com.example.pj19980729.drivingbook.constant.Constants;
import com.example.pj19980729.drivingbook.entity.QuestionVO;
import com.example.pj19980729.drivingbook.okhttp.RequestUtil;
import com.example.pj19980729.drivingbook.utils.ListAdapter;
import com.example.pj19980729.drivingbook.utils.ViewPageAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {

    ViewPager testvp;
    TextView username, usercomment, replyname, replycomment, selectall;
    ListView comment, lvQuestion;

    List<Integer> qids = new ArrayList<>();

    List<QuestionVO> questionVOList = new ArrayList<>();

    WebView question1;

    List<String> listk=new ArrayList<>();

    ViewPageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testvp = findViewById(R.id.testvp);

        //testLayout
        LayoutInflater inflater = LayoutInflater.from(this);
        View test=inflater.inflate(R.layout.testlayout,null);
        comment = test.findViewById(R.id.comment);
        lvQuestion = test.findViewById(R.id.question);


        //commentLayout
        username = (TextView) findViewById(R.id.textView11);
        usercomment = (TextView) findViewById(R.id.textView13);
        replyname = (TextView) findViewById(R.id.textView17);
        replycomment = (TextView) findViewById(R.id.textView18);
        selectall = (TextView) findViewById(R.id.textView19);

        question1 = findViewById(R.id.question1);



//        testAdapter= new ViewPageAdapter(this,pageList);
//        testvp.setAdapter(testAdapter);


        //向服务端发送请求并将收到的题目id存入qids
        getQuestionIds();




//        View q =inflater.inflate(R.layout.question,null);
//        vpList.add(q);
        adapter =new ViewPageAdapter(this,listk);
        testvp.setAdapter(adapter);
        testvp.setOffscreenPageLimit(qids.size());




    }


    public void getQuestionIds() {
        int sid = getIntent().getIntExtra("sid", 0);
        Integer subjectId;
        Map map = new HashMap();
        if (sid != 0) {
            subjectId = sid;
            map.put("subjectId", subjectId);
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
                String urlx = String.format("%s/%s", Constants.context,Constants.quiz);
                for (int i=0;i<qids.size();i++){
                    int k= i+1;
                    String qurl=String.format("%s/%s?num=%s",urlx,qids.get(i),k);
                    listk.add(qurl);
                }
                adapter.notifyDataSetChanged();

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

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//
//        //给页面配置ListView
//        //1.获取questionVO
//        for (int k = 0; k < qids.size(); k++) {
//            getQuestion(qids.get(k));
//            QuestionVO questionVO = questionVOList.get(k);
//            //2.将需要显示的内容放置于泛型中
//            List<String> list = new ArrayList<>();
//            for (int i = 1; i <= 2 + questionVO.getOptionList().size() + questionVO.getTypes().size(); i++) {
//                String object;
//                if (i == 1) {
//                    object = questionVO.getContent();
//                } else if (i > 1 && i <= questionVO.getOptionList().size() + 1) {
//                    object = "<p>" + questionVO.getOptionList().get(i - 2) + "</p>";
//                } else if (i > questionVO.getOptionList().size() + 1
//                        && i < 2 + questionVO.getOptionList().size() + questionVO.getTypes().size()) {
//                    object = "<p>" + questionVO.getTypes().get(i - questionVO.getOptionList().size() - 2).getType() + "</p>";
//                } else {
//                    object = questionVO.getResolve();
//                }
//                list.add(object);
//            }
//            listAdapter = new ListAdapter(TestActivity.this, list);
//            lvQuestion.setAdapter(listAdapter);
//            pageList.add(lvQuestion);
//        }
//        testAdapter.notifyDataSetChanged();
//
//    }
}

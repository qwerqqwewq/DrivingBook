package com.example.pj19980729.drivingbook;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    ViewPager testvp;
    WebView question;
    WebView answerA,answerB,answerC,answerD,answer;
    TextView username,usercomment,replyname,replycomment,selectall;
    ArrayList<View> vpList;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testvp=findViewById(R.id.testvp);

        //testLayout
        question = (WebView) findViewById(R.id.question);
        answerA = (WebView) findViewById(R.id.answerA);
        answerB = (WebView) findViewById(R.id.answerB);
        answerC = (WebView) findViewById(R.id.answerC);
        answerD = (WebView) findViewById(R.id.answerD);
        answer = (WebView) findViewById(R.id.answer);

        //commentLayout
        username = (TextView) findViewById(R.id.textView11);
        usercomment = (TextView) findViewById(R.id.textView13);
        replyname = (TextView) findViewById(R.id.textView17);
        replycomment = (TextView) findViewById(R.id.textView18);
        selectall = (TextView) findViewById(R.id.textView19);


        LayoutInflater inflater=LayoutInflater.from(this);
        vpList=new ArrayList<View>();
        int count=10;
        for (int i=0;i<=count;i++){
            View v=inflater.inflate(R.layout.testlayout,null);
            vpList.add(v);
        }

    }
}

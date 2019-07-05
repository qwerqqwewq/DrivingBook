package com.example.pj19980729.drivingbook.test;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pj19980729.drivingbook.R;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    ViewPager testvp;
    TextView username,usercomment,replyname,replycomment,selectall;
    ArrayList<View> vpList;
    ListView comment,question;






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
        int count=10;
        for (int i=0;i<=count;i++){
            View v=inflater.inflate(R.layout.testlayout,null);
            vpList.add(v);
        }







    }
}

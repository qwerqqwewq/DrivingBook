package com.example.pj19980729.drivingbook.emptyactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.pj19980729.drivingbook.R;
import com.example.pj19980729.drivingbook.constant.Constants;

public class SecondActivity extends AppCompatActivity {


    //SecondActivity
//    WebView secondwv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //SecondActivity的按钮事件监听
//        LayoutInflater inflater1 = LayoutInflater.from(this);
//        View view1 = inflater1.inflate(R.layout.activity_second,null);
//        secondwv = view1.findViewById(R.id.secondwv);
//
//        secondwv.getSettings().setJavaScriptEnabled(true);
//        secondwv.requestFocus();
//        String htmlStr = String.format("%s/%s/%s", Constants.context,Constants.video,2);
//        secondwv.loadUrl(htmlStr);
//        secondwv.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });
    }
}

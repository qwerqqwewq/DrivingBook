package com.example.pj19980729.drivingbook;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
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

public class LoveCommentActivity extends AppCompatActivity {


    //    WebView question1;
    WebView lovewv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lovecomment);

        lovewv = findViewById(R.id.lovewv);
        String qid=getIntent().getStringExtra("qid");

        WebSettings webSettings=lovewv.getSettings();
        //允许执行javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //允许JavaScript可以自动打开Windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置是否缓存
        webSettings.setAppCacheEnabled(true);
        //设置缓存模式
        webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置缓存存放路径
        //webSettings.setAppCachePath("");
        //支持缩放（适配到当前屏幕）
        webSettings.setSupportZoom(true);
        //调整图片到合适大小
        webSettings.setUseWideViewPort(true);
        //调整支持内容的重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置可以控制屏幕
        webSettings.setDisplayZoomControls(true);
        //设置默认字体大小
        //webSettings.setDefaultFontSize();
        //是否开始内容存储
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        lovewv.requestFocus();

        String urlx = String.format("%s/%s", Constants.context, Constants.quiz);
        String qurl = String.format("%s/%s?num=%s&uid=%s", urlx, qid,1,((User)AppVariables.map.get("user")).getId());

        lovewv.loadUrl(qurl);



    }





}

package com.example.pj19980729.drivingbook;

import android.content.Intent;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.pj19980729.drivingbook.application.AppVariables;
import com.example.pj19980729.drivingbook.constant.Constants;
import com.example.pj19980729.drivingbook.emptyactivity.ViewPlayerActivity;
import com.example.pj19980729.drivingbook.entity.User;
import com.example.pj19980729.drivingbook.okhttp.RequestUtil;
import com.example.pj19980729.drivingbook.test.ExamActivity;
import com.example.pj19980729.drivingbook.test.TestActivity;
import com.example.pj19980729.drivingbook.test.WrongActivity;
import com.example.pj19980729.drivingbook.test.specialActivity;
import com.example.pj19980729.drivingbook.utils.MyAdapter;
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

public class MainActivity extends AppCompatActivity{

    //全局变量
    int flag=0;//获取radiobutton的位置，从而判断页面位置

    //MainActivity
    TabHost tabHost;
    RadioGroup radioGroup;
    Fragment fragment_Main;

    //MainFragment
    RadioGroup radioGroup1;
    RadioButton radioButton, radioButton3, radioButton4, radioButton5;
    ViewPager vp;
    ArrayList<View> vpList;
    View view;

    //MycenterFragment
    private TextView textView14;
    private TextView textView15;
    private TextView textView16;
    private Button exit;


    List<Integer> sids = new ArrayList<>();
    List<String> listk=new ArrayList<>();
    ViewPageAdapter adapter;


    //FirstActivity
    Button test1, exam1, wrong1;

    //secondActivity
    WebView secondwv;


    //thirdActivity
    WebView thirdwv;

    //ForthActivity
    Button test4, exam4, wrong4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup2);
        tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("main").setIndicator("main").setContent(R.id.fragment_Main));
        tabHost.addTab(tabHost.newTabSpec("mycenter").setIndicator("mycenter").setContent(R.id.fragment_mycenter));
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragment_Main = fragmentManager.findFragmentById(R.id.fragment_Main);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_main:
                        tabHost.setCurrentTabByTag("main");
                        break;
                    case R.id.radio_mycenter:
                        tabHost.setCurrentTabByTag("mycenter");
                        break;
                }
                getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
            }
        });

        radioGroup1 = findViewById(R.id.radioGroup);
        radioButton = findViewById(R.id.radioButton);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton5 = findViewById(R.id.radioButton5);
        vp = findViewById(R.id.vp);


        LayoutInflater inflater = LayoutInflater.from(this);
        View v1 = inflater.inflate(R.layout.activity_first, null);
        View v2 = inflater.inflate(R.layout.activity_second, null);
        View v3 = inflater.inflate(R.layout.activity_third, null);
        View v4 = inflater.inflate(R.layout.activity_forth, null);

        vpList = new ArrayList<View>();
        vpList.add(v1);
        vpList.add(v2);
        vpList.add(v3);
        vpList.add(v4);


        MyAdapter pagerAdapter = new MyAdapter(vpList);
        vp.setAdapter(pagerAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        radioButton.setChecked(true);
                        flag=1;
                        break;
                    case 1:
                        radioButton3.setChecked(true);
                        flag=2;
                        break;
                    case 2:
                        radioButton4.setChecked(true);
                        flag=3;
                        break;
                    case 3:
                        radioButton5.setChecked(true);
                        flag=4;
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButton.getId() == checkedId) {
                    vp.setCurrentItem(0);
                } else if (radioButton3.getId() == checkedId) {
                    vp.setCurrentItem(1);
                } else if (radioButton4.getId() == checkedId) {
                    vp.setCurrentItem(2);
                } else {
                    vp.setCurrentItem(3);
                }
            }
        });

        //FirstActivity的按钮事件监听
        test1 = v1.findViewById(R.id.button5);
        exam1 = v1.findViewById(R.id.button3);
        wrong1 = v1.findViewById(R.id.button6);

        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                intent.putExtra("sid",1);
                startActivity(intent);
            }
        });

        exam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ExamActivity.class);
                intent.putExtra("sid",1);
                startActivity(intent);
            }
        });

        wrong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, specialActivity.class);
                intent.putExtra("sid",1);
                startActivity(intent);
            }
        });


        //secondActivity的按钮事件监听
        secondwv = v2.findViewById(R.id.secondwv);
        WebSettings webSettings=secondwv.getSettings();
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
        String htmlStr = String.format("%s/%s/%s", Constants.context,Constants.video,2);
        secondwv.loadUrl(htmlStr);
        secondwv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        //ThirdActivity的按钮事件监听
        thirdwv = v3.findViewById(R.id.thirdwv);
        WebSettings webSettings1=thirdwv.getSettings();
        //允许执行javascript脚本
        webSettings1.setJavaScriptEnabled(true);
        //允许JavaScript可以自动打开Windows
        webSettings1.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置是否缓存
        webSettings1.setAppCacheEnabled(true);
        //设置缓存模式
        webSettings1.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置缓存存放路径
        //webSettings.setAppCachePath("");
        //支持缩放（适配到当前屏幕）
        webSettings1.setSupportZoom(true);
        //调整图片到合适大小
        webSettings1.setUseWideViewPort(true);
        //调整支持内容的重新布局
        webSettings1.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置可以控制屏幕
        webSettings1.setDisplayZoomControls(true);
        //设置默认字体大小
        //webSettings.setDefaultFontSize();
        //是否开始内容存储
        webSettings1.setDomStorageEnabled(true);
        webSettings1.setLoadWithOverviewMode(true);
        String Str = String.format("%s/%s/%s", Constants.context,Constants.video,3);
        thirdwv.loadUrl(Str);
        thirdwv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });





        //ForthActivity的按钮事件监听
        test4=v4.findViewById(R.id.button8);
        exam4=v4.findViewById(R.id.button7);
        wrong4=v4.findViewById(R.id.button9);


        test4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TestActivity.class);
                intent.putExtra("sid",4);
                startActivity(intent);
            }
        });

        exam4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ExamActivity.class);
                intent.putExtra("sid",4);
                startActivity(intent);
            }
        });

        wrong4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,specialActivity.class);
                intent.putExtra("sid",4);
                startActivity(intent);
            }
        });


        //mycenter
        textView14 = (TextView) findViewById(R.id.textView14);
        textView15 = (TextView) findViewById(R.id.textView15);
        textView16 = (TextView) findViewById(R.id.textView16);
        exit = (Button) findViewById(R.id.button10);
        textView14.setText(((User)AppVariables.map.get("user")).getName());


        textView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,WrongActivity.class);
                startActivity(intent);
            }
        });

        textView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoveActivity.class);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppVariables.map.clear();
                System.exit(0);
            }
        });


    }


}


package com.example.pj19980729.drivingbook.emptyactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.pj19980729.drivingbook.R;
import com.example.pj19980729.drivingbook.constant.Constants;

public class ViewPlayerActivity extends AppCompatActivity {


    WebView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_player);

        int sid = getIntent().getIntExtra("sid",0);

        video = findViewById(R.id.video);
        WebSettings webSettings=video.getSettings();
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
        String htmlStr = String.format("%s/%s/%s", Constants.context,Constants.video,sid);
        video.loadUrl(htmlStr);
        video.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}

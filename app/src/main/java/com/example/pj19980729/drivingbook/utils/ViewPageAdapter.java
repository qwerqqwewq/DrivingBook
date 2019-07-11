package com.example.pj19980729.drivingbook.utils;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import com.example.pj19980729.drivingbook.R;

import java.util.ArrayList;
import java.util.List;


public class ViewPageAdapter extends PagerAdapter {

    private Context context;
    List<String> list;
    View view;
    List<View> viewList=new ArrayList<>();

    WebView question;


    public ViewPageAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {



        LayoutInflater inflater =LayoutInflater.from(context);
        view = inflater.inflate(R.layout.question,null,false);
        question = view.findViewById(R.id.question1);

        WebSettings webSettings=question.getSettings();
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
        question.requestFocus();
        String htmlStr = list.get(position);
        question.loadUrl(htmlStr);
        question.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        viewList.add(question);

        if (question!=null) {
            ViewGroup viewGroup = (ViewGroup)question.getParent();
            if (viewGroup!=null) {
                viewGroup.removeView(question);
                container.addView(viewList.get(position));
            }else {
                container.addView(viewList.get(position));
            }
        }

        return viewList.get(position);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(viewList.get(position));
    }
}

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
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import com.example.pj19980729.drivingbook.R;

import java.util.List;


public class ViewPageAdapter extends PagerAdapter {

    private Context context;
    List<String> list;

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
        View view = inflater.inflate(R.layout.question,null);
        question = view.findViewById(R.id.question1);

        question.getSettings().setJavaScriptEnabled(true);
        question.requestFocus();

        String htmlStr = list.get(position);
        question.loadUrl(htmlStr);

        container.addView(question);
        return question;
    }

}

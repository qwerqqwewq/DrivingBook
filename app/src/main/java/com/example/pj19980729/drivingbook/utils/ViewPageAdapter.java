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

        question.getSettings().setJavaScriptEnabled(true);
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

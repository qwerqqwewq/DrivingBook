package com.example.pj19980729.drivingbook.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;

import java.util.List;


public class ViewPageAdapter extends PagerAdapter {

    private List<ListView> viewList;
    private Context context;



    WebView question;


    public ViewPageAdapter(Context context,List<ListView> viewList){
        this.context = context;
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    public List<ListView> getViewList() {
        return viewList;
    }
}

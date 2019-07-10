package com.example.pj19980729.drivingbook.utils;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import com.example.pj19980729.drivingbook.R;

import java.util.List;


public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<String> lists;
    private WebView webView;

    public ListAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.question, null);
        }

        webView = convertView.findViewById(R.id.question1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.requestFocus();
        String first= "<html><head><meta charset='utf-8'></head><body>";
        String last = "</body></html>";
        String htmlStr = first + lists.get(position) + last;
        webView.loadDataWithBaseURL(null,htmlStr,"text/html", "UTF-8",null);


        return convertView;
    }

    public WebView getWebView() {
        return this.webView;
    }
}

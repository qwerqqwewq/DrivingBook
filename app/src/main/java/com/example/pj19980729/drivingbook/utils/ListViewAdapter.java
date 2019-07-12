package com.example.pj19980729.drivingbook.utils;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.pj19980729.drivingbook.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> qidlist = new ArrayList<>();
    private List<String> typelist = new ArrayList<>();
    List<View> viewList=new ArrayList<>();


    public ListViewAdapter(Context context, List<Integer> list,List<String> list2){
        this.context = context;
        this.qidlist = list;
        this.typelist = list2;
    }

    @Override
    public int getCount() {
        return qidlist.size();
    }

    @Override
    public Object getItem(int position) {
        return qidlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.s1,null);
        TextView textView11 = view.findViewById(R.id.textView11);
        TextView textView13 = view.findViewById(R.id.textView13);


        for (int i = 0; i < qidlist.size(); i++) {
            textView11.setText(qidlist.get(i).toString());
            textView13.setText(typelist.get(i));
        }

        return view;
    }


}

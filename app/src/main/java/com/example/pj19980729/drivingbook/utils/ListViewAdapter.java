package com.example.pj19980729.drivingbook.utils;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.pj19980729.drivingbook.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int resource;
    private ArrayList<String> list;


    public ListViewAdapter(Context context, int resource, ArrayList<String> list){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        if(list==null){
            this.list=new ArrayList<>();
        }else{
            this.list = list;
        }
    }

    @Override
    public int getCount() {
        if(list.size()%2>0) {
            return list.size()/2+1;
        } else {
            return list.size()/2;
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh ;
        if (convertView == null ) {
            convertView = inflater.inflate(resource, null);
            vh = new ViewHolder();
            vh.tv1=(TextView)convertView.findViewById(R.id.textView23);
            vh.tv2=(TextView)convertView.findViewById(R.id.textView24);
            vh.image1=convertView.findViewById(R.id.imageView3);
            vh.image2=convertView.findViewById(R.id.imageView4);
            vh.l1=convertView.findViewById(R.id.linear1);
            vh.l2=convertView.findViewById(R.id.linear2);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder)convertView.getTag();
        }
        int distance =  list.size() - position*2;
        int cellCount = distance >= 2? 2:distance;
        final List<String> itemList = list.subList(position*2,position*2+cellCount);
        if (itemList.size() >0) {
            //根据路径插入图片，和文字
            String s="1";
            Uri uri=Uri.fromFile(new File(s));
            vh.l1.setVisibility(View.VISIBLE);
            vh.tv1.setText(itemList.get(0));
            vh.image1.setImageURI(uri);
            vh.tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, itemList.get(0), Toast.LENGTH_SHORT).show();
                }
            });
            if (itemList.size() >1){
                vh.l2.setVisibility(View.VISIBLE);
                vh.tv2.setText(itemList.get(1));
                vh.tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, itemList.get(1), Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                vh.tv2.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }

    public static class ViewHolder{
        TextView tv1;
        TextView tv2;
        ImageView image1;
        ImageView image2;
        LinearLayout l1,l2;
    }
}

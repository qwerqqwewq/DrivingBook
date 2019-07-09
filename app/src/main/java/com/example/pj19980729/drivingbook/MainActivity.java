package com.example.pj19980729.drivingbook;

import android.content.Intent;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.pj19980729.drivingbook.application.AppVariables;
import com.example.pj19980729.drivingbook.entity.Type;
import com.example.pj19980729.drivingbook.entity.User;
import com.example.pj19980729.drivingbook.test.ExamActivity;
import com.example.pj19980729.drivingbook.test.TestActivity;
import com.example.pj19980729.drivingbook.test.WrongActivity;
import com.example.pj19980729.drivingbook.utils.ListViewAdapter;
import com.example.pj19980729.drivingbook.utils.MyAdapter;

import java.util.ArrayList;

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

    //FirstActivity
    Button test1, exam1, wrong1;

    //SecondActivity
    ListView lv1;
    ImageView image1,image2;
    TextView tv1,tv2;


    //thirdActivity

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


//        //适配器中
//        PagerAdapter pagerAdapter = new PagerAdapter() {
//
//            //获取当前有多少个界面
//            @Override
//            public int getCount() {
//                return vpList.size();
//            }
//
//            //判断是否由对象生成的界面
//            @Override
//            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//                return view == o;
//            }
//
//            //获取当前界面的位置
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                container.addView(vpList.get(position));
//                return vpList.get(position);
//            }
//
//            //销毁上一个界面
//            @Override
//            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                container.removeView(vpList.get(position));
//            }
//        };
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
                intent.setClass(MainActivity.this, WrongActivity.class);
                intent.putExtra("sid",1);
                startActivity(intent);
            }
        });

        //SecondActivity的按钮事件监听
        if (flag==2) {
            lv1 = v2.findViewById(R.id.lv1);
            tv1 = findViewById(R.id.textView23);
            tv2 = findViewById(R.id.textView24);
            image1 = findViewById(R.id.imageView3);
            image2 = findViewById(R.id.imageView4);
            ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < 21; i++) {
                list.add("测试:" + i);
            }
            ListViewAdapter adapter = new ListViewAdapter(this, R.layout.studyview, list);
            lv1.setAdapter(adapter);
        }




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
                intent.setClass(MainActivity.this,WrongActivity.class);
                intent.putExtra("sid",4);
                startActivity(intent);
            }
        });

        textView14 = (TextView) findViewById(R.id.textView14);
        textView14.setText(((User)AppVariables.map.get("user")).getName());
    }


}


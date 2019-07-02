package com.example.pj19980729.drivingbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup2);
        tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("main").setIndicator("main").setContent(R.id.fragment_Main));
        tabHost.addTab(tabHost.newTabSpec("mycenter").setIndicator("mycenter").setContent(R.id.fragment_mycenter));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
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



    }
}

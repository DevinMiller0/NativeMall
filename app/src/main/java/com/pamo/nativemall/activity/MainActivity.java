package com.pamo.nativemall.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import com.pamo.nativemall.R;
import com.pamo.nativemall.fragment.HomeFragment;
import com.pamo.nativemall.utils.StatusBarUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fragmentContent;
    private RadioButton home;
    private RadioButton sort;
    private RadioButton navigation;
    private RadioButton mine;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    private void initView() {

        fragmentContent = (FrameLayout) findViewById(R.id.fragment_content);
        home = (RadioButton) findViewById(R.id.rb_home);
        sort = (RadioButton) findViewById(R.id.rb_sort);
        navigation = (RadioButton) findViewById(R.id.rb_navigation);
        mine = (RadioButton) findViewById(R.id.rb_mine);

        home.setOnClickListener(this);
        sort.setOnClickListener(this);
        navigation.setOnClickListener(this);
        mine.setOnClickListener(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, new HomeFragment());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb_home:{
                break;
            }
            case R.id.rb_sort:{
                break;
            }
            case R.id.rb_navigation:{
                break;
            }
            case R.id.rb_mine:{
                break;
            }
        }
    }
}

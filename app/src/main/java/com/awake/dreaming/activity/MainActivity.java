package com.awake.dreaming.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.awake.dreaming.R;
import com.awake.dreaming.fragment.ClassifyFragment;
import com.awake.dreaming.fragment.PersonCenterFragment;
import com.awake.dreaming.fragment.NavigationFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fragmentContent;
    private RadioButton sort;
    private RadioButton navigation;
    private RadioButton mine;

    private long firstTime = 0;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    private void initView() {

        fragmentContent = (FrameLayout) findViewById(R.id.fragment_content);
        sort = (RadioButton) findViewById(R.id.rb_sort);
        navigation = (RadioButton) findViewById(R.id.rb_navigation);
        mine = (RadioButton) findViewById(R.id.rb_mine);

        sort.setOnClickListener(this);
        navigation.setOnClickListener(this);
        mine.setOnClickListener(this);

        setIcon(sort, R.mipmap.btn_classify_choosen);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, new ClassifyFragment());
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (view.getId()){

            case R.id.rb_sort:{
                ft.replace(R.id.fragment_content, new ClassifyFragment());
                setIcon(sort, R.mipmap.btn_classify_choosen);
                setIcon(navigation, R.mipmap.btn_nav_normal1);
                setIcon(mine, R.mipmap.btn_person_normal);
                break;
            }
            case R.id.rb_navigation:{
                ft.replace(R.id.fragment_content, new NavigationFragment());
                setIcon(sort, R.mipmap.btn_classify_normal);
                setIcon(navigation, R.mipmap.btn_nav_choosen);
                setIcon(mine, R.mipmap.btn_person_normal);
                break;
            }
            case R.id.rb_mine:{
                ft.replace(R.id.fragment_content, new PersonCenterFragment());
                setIcon(sort, R.mipmap.btn_classify_normal);
                setIcon(navigation, R.mipmap.btn_nav_normal1);
                setIcon(mine, R.mipmap.btn_person_choosen);
                break;
            }
        }
        ft.commit();
    }

    private void setIcon(RadioButton radioButton, int icon){
        Drawable drawable = getResources().getDrawable(icon);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        radioButton.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * 双击退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        long secondTime = System.currentTimeMillis();
        if(secondTime - firstTime > 2000){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            finish();
        }
        return true;
    }
}

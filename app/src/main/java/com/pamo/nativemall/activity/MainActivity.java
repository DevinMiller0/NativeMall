package com.pamo.nativemall.activity;

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

import com.pamo.nativemall.R;
import com.pamo.nativemall.fragment.HomeFragment;
import com.pamo.nativemall.fragment.MineFragment;
import com.pamo.nativemall.fragment.NavigationFragment;
import com.pamo.nativemall.fragment.SortFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fragmentContent;
    private RadioButton home;
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
        home = (RadioButton) findViewById(R.id.rb_home);
        sort = (RadioButton) findViewById(R.id.rb_sort);
        navigation = (RadioButton) findViewById(R.id.rb_navigation);
        mine = (RadioButton) findViewById(R.id.rb_mine);

        home.setOnClickListener(this);
        sort.setOnClickListener(this);
        navigation.setOnClickListener(this);
        mine.setOnClickListener(this);

        setIcon(home, R.mipmap.home_selected, R.color.colorRadioSelect);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, new HomeFragment());
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (view.getId()){
            case R.id.rb_home:{
                ft.replace(R.id.fragment_content, new HomeFragment());
                setIcon(home, R.mipmap.home_selected, R.color.colorRadioSelect);
                setIcon(sort, R.mipmap.sort, R.color.colorRadioButton);
                setIcon(navigation, R.mipmap.navigation, R.color.colorRadioButton);
                setIcon(mine, R.mipmap.mine, R.color.colorRadioButton);
                break;
            }
            case R.id.rb_sort:{
                ft.replace(R.id.fragment_content, new SortFragment());
                setIcon(home, R.mipmap.home, R.color.colorRadioButton);
                setIcon(sort, R.mipmap.sort_select, R.color.colorRadioSelect);
                setIcon(navigation, R.mipmap.navigation, R.color.colorRadioButton);
                setIcon(mine, R.mipmap.mine, R.color.colorRadioButton);
                break;
            }
            case R.id.rb_navigation:{
                ft.replace(R.id.fragment_content, new NavigationFragment());
                setIcon(home, R.mipmap.home, R.color.colorRadioButton);
                setIcon(sort, R.mipmap.sort, R.color.colorRadioButton);
                setIcon(navigation, R.mipmap.navigation_selected, R.color.colorRadioSelect);
                setIcon(mine, R.mipmap.mine, R.color.colorRadioButton);
                break;
            }
            case R.id.rb_mine:{
                ft.replace(R.id.fragment_content, new MineFragment());
                setIcon(home, R.mipmap.home, R.color.colorRadioButton);
                setIcon(sort, R.mipmap.sort, R.color.colorRadioButton);
                setIcon(navigation, R.mipmap.navigation, R.color.colorRadioButton);
                setIcon(mine, R.mipmap.mine_selected, R.color.colorRadioSelect);
                break;
            }
        }
        ft.commit();
    }

    private void setIcon(RadioButton radioButton, int icon, int color){
        Drawable drawable = getResources().getDrawable(icon);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        radioButton.setCompoundDrawables(null, drawable, null, null);
        radioButton.setTextColor(getResources().getColor(color));
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

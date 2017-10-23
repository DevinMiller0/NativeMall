package com.pamo.nativemall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pamo.nativemall.R;
import com.pamo.nativemall.fragment.BusinessCardFragment;
import com.pamo.nativemall.fragment.MyCardFragment;
import com.pamo.nativemall.utils.StatusBarUtils;
import com.pamo.nativemall.widget.TabView;
import com.pamo.nativemall.widget.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class MyCarteActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Fragment> fragments;
    private ViewPager viewPager;
    private TabView tab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_carte);

        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    private void initView() {
        TopBar topBar = (TopBar) findViewById(R.id.my_carte_topBar);
        topBar.titleBack.setOnClickListener(this);

        tab = (TabView) findViewById(R.id.tabs_view);

        fragments = new ArrayList<>();
        fragments.add(new MyCardFragment());
        fragments.add(new BusinessCardFragment());

        viewPager = (ViewPager) findViewById(R.id.vp_card);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        tab.setTabs("我的名片", "商家名片");
        tab.setOnTabsItemClickListener(new TabView.OnTabsItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                viewPager.setCurrentItem(position, true);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tab.setCurrentTab(position, true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.animation_x_off);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.topbar_back:{
                finish();
                break;
            }
        }
    }

    private class PageAdapter extends FragmentPagerAdapter{

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}

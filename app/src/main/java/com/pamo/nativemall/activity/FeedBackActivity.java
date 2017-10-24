package com.pamo.nativemall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pamo.nativemall.R;
import com.pamo.nativemall.utils.StatusBarUtils;
import com.pamo.nativemall.widget.TopBar;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener {

    private TopBar topBar;

    private final String TAG = "FeedBackActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    private void initView() {
        topBar = (TopBar) findViewById(R.id.feed_back_topBar);
        topBar.titleBack.setOnClickListener(this);
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
}

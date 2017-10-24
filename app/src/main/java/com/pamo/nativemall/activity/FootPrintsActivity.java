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

public class FootPrintsActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "FootPrintsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_prints);

        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        TopBar topBar = (TopBar) findViewById(R.id.foot_prints_topBar);
        topBar.titleBack.setOnClickListener(this);
    }

    /**
     * activity销毁时动画
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.animation_x_off);
    }

    /**
     * 页面点击事件
     * @param view
     */
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

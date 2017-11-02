package com.awake.dreaming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;
import com.awake.dreaming.widget.TopBar1;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class FootPrintsActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "FootPrintsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_prints);
        overridePendingTransition(R.anim.animation_x_on, R.anim.animation_x_off);
        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        TopBar1 topBar1 = (TopBar1) findViewById(R.id.foot_prints_topBar);
        topBar1.titleBack.setOnClickListener(this);
    }

    /**
     * activity销毁时动画
     */
    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(0, R.anim.animation_x_off);
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

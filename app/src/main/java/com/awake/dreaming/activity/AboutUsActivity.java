package com.awake.dreaming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;
import com.awake.dreaming.widget.TopBar;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "AboutUsActivity";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    private void initView() {

        TopBar topBar = (TopBar) findViewById(R.id.about_us_topBar);
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

package com.awake.dreaming.activity;

import android.view.View;

import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;
import com.awake.dreaming.widget.TopBar1;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class FeedBackActivity extends BaseActivity{

    private final String TAG = "FeedBackActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void setLayout() {
        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    @Override
    protected void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.topbar_back: {
                finish();
                break;
            }
        }
    }

    private void initView() {
        TopBar1 topBar1 = (TopBar1) findViewById(R.id.feed_back_topBar);
        topBar1.titleBack.setOnClickListener(this);
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(0, R.anim.animation_x_off);
    }
}

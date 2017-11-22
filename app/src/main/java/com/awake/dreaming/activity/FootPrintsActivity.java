package com.awake.dreaming.activity;

import android.view.View;

import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;
import com.awake.dreaming.widget.TopBar1;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class FootPrintsActivity extends BaseActivity {

    private final String TAG = "FootPrintsActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_foot_prints;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        TopBar1 topBar1 = (TopBar1) findViewById(R.id.foot_prints_topBar);
        topBar1.titleBack.setOnClickListener(this);
    }

    @Override
    protected void setLayout() {
        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    @Override
    protected void onViewClick(View view) {
        switch (view.getId()){
            case R.id.topbar_back:{
                finish();
                break;
            }
        }
    }
}

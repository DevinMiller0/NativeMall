package com.awake.dreaming.activity;

import android.view.View;
import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;
import com.awake.dreaming.widget.TopBar1;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class AboutUsActivity extends BaseActivity {

    private final String TAG = "AboutUsActivity";

    private void initView() {
        TopBar1 topBar1 = (TopBar1) findViewById(R.id.about_us_topBar);
        topBar1.titleBack.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about_us;
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

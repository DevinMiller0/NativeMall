package com.pamo.nativemall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pamo.nativemall.R;
import com.pamo.nativemall.utils.StatusBarUtils;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class MyCarteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_carte);

        StatusBarUtils.transparentStatusBar(this);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.animation_x_off);
    }
}

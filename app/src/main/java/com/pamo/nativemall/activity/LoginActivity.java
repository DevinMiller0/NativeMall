package com.pamo.nativemall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pamo.nativemall.R;
import com.pamo.nativemall.utils.StatusBarUtils;

/**
 * Created by wangdesheng on 2017/10/16 0016.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtils.transparentStatusBar(this);
    }

}

package com.pamo.nativemall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wangdesheng on 2017/10/23 0023.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setLayout();
    }

    @Override
    public void onClick(View view) {
        onViewClick(view);
    }

    protected abstract int getLayout();
    protected abstract void setLayout();
    protected abstract void onViewClick(View view);
}

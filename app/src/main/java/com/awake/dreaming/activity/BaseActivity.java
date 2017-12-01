package com.awake.dreaming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wangdesheng on 2017/10/23 0023.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setLayout();
        //overridePendingTransition(R.anim.animation_y_on, R.anim.animation_x_off);
    }

    @Override
    public void onClick(View view) {
        onViewClick(view);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //onViewTouch(view, motionEvent);
        return onViewTouch(view, motionEvent);
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(0, R.anim.animation_y_off);
    }

    protected abstract int getLayout();
    protected abstract void setLayout();
    protected abstract void onViewClick(View view);
    protected boolean onViewTouch(View view, MotionEvent motionEvent){
        return false;
    }
}
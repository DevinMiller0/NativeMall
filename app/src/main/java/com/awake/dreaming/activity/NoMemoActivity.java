package com.awake.dreaming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;
import com.awake.dreaming.widget.TopBar1;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class NoMemoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView clickAdd;
    private final String TAG = "MemoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        overridePendingTransition(R.anim.animation_x_on, R.anim.animation_x_off);
        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    private void initView() {
        TopBar1 topBar1 = (TopBar1) findViewById(R.id.memo_topBar);
        topBar1.titleBack.setOnClickListener(this);
        clickAdd = (TextView) findViewById(R.id.tv_click_add);
        clickAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{
                        clickAdd.setBackgroundResource(R.drawable.bg_btn_memo);
                        startActivity(new Intent(NoMemoActivity.this, AddMemoActivity.class));
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        clickAdd.setBackgroundResource(R.drawable.bg_btn_memo_clicked);
                        break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(0, R.anim.animation_x_off);
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

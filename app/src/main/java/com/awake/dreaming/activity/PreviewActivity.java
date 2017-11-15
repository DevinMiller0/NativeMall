package com.awake.dreaming.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;
import com.bumptech.glide.Glide;

/**
 * Created by wangdesheng on 2017/11/13 0013.
 */

public class PreviewActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_preview;
    }

    @Override
    protected void setLayout() {

        Intent intent = getIntent();
        String path = intent.getStringExtra("path");

        ImageView preview = (ImageView) findViewById(R.id.img_preview);
        preview.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(this).load(path).into(preview);
        preview.setOnClickListener(this);

        StatusBarUtils.transparentStatusBar(this);
    }

    @Override
    protected void onViewClick(View view) {
        switch (view.getId()){
            case R.id.img_preview:{
                break;
            }
        }
    }
}

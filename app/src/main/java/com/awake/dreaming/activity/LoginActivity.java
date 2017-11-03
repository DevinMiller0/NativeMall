package com.awake.dreaming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.awake.dreaming.R;
import com.awake.dreaming.datas.Image;
import com.awake.dreaming.utils.StatusBarUtils;

/**
 * Created by wangdesheng on 2017/10/16 0016.
 */

public class LoginActivity extends AppCompatActivity {

    private TranslateAnimation animation;
    private ImageView balloonBottom;
    private ImageView cloudTop;
    private ImageView cloudBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.animation_x_on, R.anim.animation_x_off);
        StatusBarUtils.transparentStatusBar(this);

        balloonBottom = (ImageView) findViewById(R.id.img_balloon_bottom);
        cloudTop = (ImageView) findViewById(R.id.img_cloud_bottom);
        cloudBottom = (ImageView) findViewById(R.id.img_cloud);

        animation = new TranslateAnimation(0, 50, 50, 0);
        animation.setDuration(2400);
        animation.setRepeatCount(1000);
        animation.setRepeatMode(Animation.REVERSE);
        balloonBottom.setAnimation(animation);
        animation.start();
        top();
        bottom();
    }

    private void top() {
        animation = new TranslateAnimation(0, 40, 40, 0);
        animation.setDuration(2200);
        animation.setRepeatCount(1000);
        animation.setRepeatMode(Animation.REVERSE);
        cloudTop.setAnimation(animation);
        animation.start();
    }

    private void bottom() {
        animation = new TranslateAnimation(0, 40, 40, 0);
        animation.setDuration(2500);
        animation.setRepeatCount(1000);
        animation.setRepeatMode(Animation.REVERSE);
        cloudBottom.setAnimation(animation);
        animation.start();
    }
}

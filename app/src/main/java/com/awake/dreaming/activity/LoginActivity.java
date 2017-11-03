package com.awake.dreaming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;

/**
 * Created by wangdesheng on 2017/10/16 0016.
 */

public class LoginActivity extends AppCompatActivity {

    private TranslateAnimation animation;
    private ImageView translate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.animation_x_on, R.anim.animation_x_off);
        StatusBarUtils.transparentStatusBar(this);

        translate = (ImageView) findViewById(R.id.translation);
        animation = new TranslateAnimation(0, 100, 100, 0);
        animation.setDuration(2000);
        animation.setRepeatCount(1000);
        animation.setRepeatMode(Animation.REVERSE);
        translate.setAnimation(animation);
        animation.start();

        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setRepeatCount(1000);
        rotateAnimation.setDuration(1000*20);
        translate.setAnimation(rotateAnimation);
        rotateAnimation.start();
    }
}

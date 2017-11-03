package com.awake.dreaming.fragment;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.awake.dreaming.R;
import com.awake.dreaming.activity.LoginActivity;
import com.awake.dreaming.widget.TopBar;

/**
 * Created by 德胜 on 2017/10/13.
 */

public class ClassifyFragment extends BaseFragment {

    private Button share;
    LinearLayout lload;
    ImageView imgLoad;

    private final String TAG = "HomeFragment";

    @Override
    protected int getLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void setLayout() {
        initTopBar();
        getActivity().findViewById(R.id.btn_start_loading).setOnClickListener(this);
    }

    private void initTopBar() {
        TopBar topBar = getActivity().findViewById(R.id.top_bar);
        topBar.setTitle(getString(R.string.category_distribution));
        topBar.imgBack.setVisibility(View.INVISIBLE);
        topBar.setOnMoreClickListener(new TopBar.OnMoreClickListener() {
            @Override
            public void moreClick(View view) {

            }
        });
        topBar.setOnBackClickListener(new TopBar.OnBackClickListener() {
            @Override
            public void backClick(View view) {

            }
        });
    }

    @Override
    protected void onViewClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_loading:{
                //loading();
                //startActivity(new Intent(getActivity(), ImageSelectorActivity.class));
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            }

        }
    }

    private void loading(){
        lload = getActivity().findViewById(R.id.ll_loading);
        imgLoad = getActivity().findViewById(R.id.img_loading);
        lload.setVisibility(View.VISIBLE);
        Animation myAlphaAnimation = new RotateAnimation(0f, 360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        myAlphaAnimation.setRepeatCount(300);//设置旋转重复次数，即转几圈
        myAlphaAnimation.setDuration(400);//设置持续时间，注意这里是每一圈的持续时间，如果上面设置的圈数为
        myAlphaAnimation.setInterpolator(new LinearInterpolator());
        imgLoad.setAnimation(myAlphaAnimation);
        myAlphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

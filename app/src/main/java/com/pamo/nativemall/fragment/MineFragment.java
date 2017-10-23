package com.pamo.nativemall.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pamo.nativemall.R;
import com.pamo.nativemall.activity.AboutUsActivity;
import com.pamo.nativemall.activity.CollectionActivity;
import com.pamo.nativemall.activity.FeedBackActivity;
import com.pamo.nativemall.activity.FootPrintsActivity;
import com.pamo.nativemall.activity.HelpCenterActivity;
import com.pamo.nativemall.activity.LoginActivity;
import com.pamo.nativemall.activity.MemoActivity;
import com.pamo.nativemall.activity.MyCarteActivity;

/**
 * Created by 德胜 on 2017/10/13.
 */

public class MineFragment extends BaseFragment {

    private final String TAG = "MineFragment";

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void setLayout() {
        //StatusBarUtils.transparentStatusBar(getActivity());
        initView();
    }

    @Override
    protected void onViewClick(View view) {

        switch (view.getId()){
            case R.id.rb_foot_print:{
                Intent intent = new Intent(getActivity(), FootPrintsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.rb_collection:{
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.rb_business:{
                Intent intent = new Intent(getActivity(), MyCarteActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_memo:{
                Intent intent = new Intent(getActivity(), MemoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_help_center:{
                Intent intent = new Intent(getActivity(), HelpCenterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_feedback:{
                Intent intent = new Intent(getActivity(), FeedBackActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_about_us:{
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private void initView() {
        RadioButton footPrint = getActivity().findViewById(R.id.rb_foot_print);
        RadioButton collection = getActivity().findViewById(R.id.rb_collection);
        RadioButton mCard = getActivity().findViewById(R.id.rb_business);
        TextView memo = getActivity().findViewById(R.id.tv_memo);
        TextView helpCenter = getActivity().findViewById(R.id.tv_help_center);
        TextView feedback = getActivity().findViewById(R.id.tv_feedback);
        TextView aboutUs = getActivity().findViewById(R.id.tv_about_us);

        footPrint.setOnClickListener(this);
        collection.setOnClickListener(this);
        mCard.setOnClickListener(this);
        memo.setOnClickListener(this);
        helpCenter.setOnClickListener(this);
        feedback.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
    }

    private void startActivities(Intent intent){
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.animation_x_on, R.anim.animation_alpha);
    }
    private void startLoginActivity(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}

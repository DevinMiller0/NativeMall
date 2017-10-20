package com.pamo.nativemall.fragment;

import android.content.Intent;
import android.view.View;

import com.pamo.nativemall.R;
import com.pamo.nativemall.activity.LoginActivity;
import com.pamo.nativemall.utils.StatusBarUtils;

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
        StatusBarUtils.transparentStatusBar(getActivity());
        initView();
    }

    @Override
    protected void onViewClick(View view) {

    }

    private void initView() {
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

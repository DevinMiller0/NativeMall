package com.awake.dreaming.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.awake.dreaming.R;
import com.awake.dreaming.activity.AboutUsActivity;
import com.awake.dreaming.activity.CollectionActivity;
import com.awake.dreaming.activity.FeedBackActivity;
import com.awake.dreaming.activity.FootPrintsActivity;
import com.awake.dreaming.activity.HelpCenterActivity;
import com.awake.dreaming.activity.MemoActivity;
import com.awake.dreaming.activity.MyCarteActivity;
import com.awake.dreaming.widget.TopBar;

/**
 * Created by 德胜 on 2017/10/13.
 */

public class PersonCenterFragment extends BaseFragment {

    private final String TAG = "PersonCenterFragment";

    @Override
    protected int getLayout() {
        return R.layout.fragment_person_center;
    }

    @Override
    protected void setLayout() {
        initView();
    }

    @Override
    protected void onViewClick(View view) {

        switch (view.getId()){
            case R.id.ll_collection:{
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_card_holder:{
                Intent intent = new Intent(getActivity(), MyCarteActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_memo:{
                Intent intent = new Intent(getActivity(), MemoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_foot_print:{
                Intent intent = new Intent(getActivity(), FootPrintsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_help_center:{
                Intent intent = new Intent(getActivity(), HelpCenterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_feed_back:{
                Intent intent = new Intent(getActivity(), FeedBackActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_about_us:{
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private void initView() {
        initTopBar();
        LinearLayout collection = getActivity().findViewById(R.id.ll_collection);
        LinearLayout cardHolder = getActivity().findViewById(R.id.ll_card_holder);
        LinearLayout memo = getActivity().findViewById(R.id.ll_memo);
        LinearLayout footPrint = getActivity().findViewById(R.id.ll_foot_print);
        LinearLayout helpCenter = getActivity().findViewById(R.id.ll_help_center);
        LinearLayout feedback = getActivity().findViewById(R.id.ll_feed_back);
        LinearLayout aboutUs = getActivity().findViewById(R.id.ll_about_us);

        collection.setOnClickListener(this);
        cardHolder.setOnClickListener(this);
        memo.setOnClickListener(this);
        footPrint.setOnClickListener(this);
        helpCenter.setOnClickListener(this);
        feedback.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
    }

    private void initTopBar() {
        TopBar topBar = getActivity().findViewById(R.id.top_bar);
        topBar.setTitle(getString(R.string.personal_center));
        topBar.imgBack.setVisibility(View.INVISIBLE);
        topBar.setOnBackClickListener(new TopBar.OnBackClickListener() {
            @Override
            public void backClick(View view) {

            }
        });
        topBar.setOnMoreClickListener(new TopBar.OnMoreClickListener() {
            @Override
            public void moreClick(View view) {

            }
        });
    }
}

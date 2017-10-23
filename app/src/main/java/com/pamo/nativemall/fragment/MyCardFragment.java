package com.pamo.nativemall.fragment;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pamo.nativemall.R;
import com.pamo.nativemall.activity.CreatedCardActivity;

/**
 * Created by wangdesheng on 2017/10/23 0023.
 */

public class MyCardFragment extends BaseFragment {

    private TextView creatBtn;

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_card;
    }

    @Override
    protected void setLayout() {
        initView();
    }

    private void initView() {
        creatBtn = getActivity().findViewById(R.id.tv_created);
        creatBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{
                        creatBtn.setBackgroundResource(R.drawable.bg_creat_card);
                        Intent intent = new Intent(getContext(), CreatedCardActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        creatBtn.setBackgroundResource(R.drawable.bg_created_click);
                        break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onViewClick(View view) {

    }
}

package com.pamo.nativemall.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.pamo.nativemall.R;

/**
 * Created by 德胜 on 2017/10/13.
 */

public class HomeFragment extends BaseFragment {

    private Button share;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setLayout() {
        share = getActivity().findViewById(R.id.btn_share);
        share.setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View view) {

        switch (view.getId()){
            case R.id.btn_share:{
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_PROCESS_TEXT, "THERE IS A TEXT OF SHARE");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "分享到..."));
                break;
            }
        }
    }
}

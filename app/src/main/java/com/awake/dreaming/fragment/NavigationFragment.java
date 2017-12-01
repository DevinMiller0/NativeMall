package com.awake.dreaming.fragment;

import android.view.View;

import com.awake.dreaming.R;
import com.awake.dreaming.widget.TopBar;

/**
 * Created by 德胜 on 2017/10/13.
 */

public class NavigationFragment extends BaseFragment {

    private final String TAG = "NavigationFragment";

    @Override
    protected int getLayout() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void setLayout() {
        initTopBar();
    }

    private void initTopBar() {
        TopBar topBar = (TopBar) getActivity().findViewById(R.id.top_bar);
        topBar.setTitle(getString(R.string.navigation));
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

    @Override
    protected void onViewClick(View view) {

    }
}

package com.pamo.nativemall.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pamo.nativemall.R;
import com.pamo.nativemall.activity.AboutUsActivity;
import com.pamo.nativemall.activity.BusinessCardActivity;
import com.pamo.nativemall.activity.CollectionActivity;
import com.pamo.nativemall.activity.FeedBackActivity;
import com.pamo.nativemall.activity.FootPrintsActivity;
import com.pamo.nativemall.activity.HelpCenterActivity;
import com.pamo.nativemall.activity.MainActivity;
import com.pamo.nativemall.activity.MemoActivity;
import com.pamo.nativemall.activity.MyCarteActivity;
import com.pamo.nativemall.adapter.GridAdapter;
import com.pamo.nativemall.widget.itemDecoration;

/**
 * Created by 德胜 on 2017/10/13.
 */

public class MineFragment extends BaseFragment {

    private RecyclerView gridView;
    private GridAdapter adapter;
    private final String TAG = "MineFragment";

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void setLayout() {
        initView();
    }

    @Override
    protected void onViewClick(View view) {

    }

    private void initView() {
        gridView = getActivity().findViewById(R.id.recycler_grid);
        gridView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.recyclerview_divider);
        gridView.addItemDecoration(new itemDecoration(getActivity(), 2, drawable));
        adapter = new GridAdapter(getActivity());
        gridView.setAdapter(adapter);

        adapter.setOnItemClickListener(new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:{
                        Intent intent = new Intent(getActivity(), FootPrintsActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 1:{
                        Intent intent = new Intent(getActivity(), CollectionActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 2:{
                        Intent intent = new Intent(getActivity(), BusinessCardActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 3:{
                        Intent intent = new Intent(getActivity(), MyCarteActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 4:{
                        Intent intent = new Intent(getActivity(), MemoActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 5:{
                        Intent intent = new Intent(getActivity(), HelpCenterActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 6:{
                        Intent intent = new Intent(getActivity(), FeedBackActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 7:{
                        Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                        startActivities(intent);
                        break;
                    }
                }
            }
        });
    }

    private void startActivities(Intent intent){
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.animation_x_on, R.anim.animation_alpha);
    }
}

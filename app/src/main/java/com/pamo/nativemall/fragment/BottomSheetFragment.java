package com.pamo.nativemall.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pamo.nativemall.R;
import com.pamo.nativemall.activity.ImageSelectorActivity;
import com.pamo.nativemall.adapter.FolderAdapter;
import com.pamo.nativemall.datas.Folder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdesheng on 2017/10/31 0031.
 */

public class BottomSheetFragment extends BaseFragment {

    private List<Folder> list;
    private static final String TAG = "BottomSheetFragment";

    @Override
    protected int getLayout() {
        return R.layout.fragment_bottom_sheet;
    }

    @Override
    protected void setLayout() {
        RecyclerView bottomSheet = getActivity().findViewById(R.id.recycler_bottom_sheet);
        bottomSheet.setLayoutManager(new LinearLayoutManager(getActivity()));
        Bundle bundle = getArguments();
        ArrayList<Folder> list =  bundle.getParcelableArrayList("key");
        Log.e(TAG, "setLayout: " + list.size() );
        bottomSheet.setAdapter(new FolderAdapter(getActivity(), list));
    }

    @Override
    protected void onViewClick(View view) {

    }

}

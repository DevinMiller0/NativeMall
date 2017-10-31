package com.pamo.nativemall.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pamo.nativemall.R;
import com.pamo.nativemall.adapter.FolderAdapter;
import com.pamo.nativemall.datas.Folder;

import java.util.List;

/**
 * Created by wangdesheng on 2017/10/31 0031.
 */

public class BottomSheetFragment extends BaseFragment {

    private List<Folder> list;
    public BottomSheetFragment(List<Folder> list){
        this.list = list;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_bottom_sheet;
    }

    @Override
    protected void setLayout() {
        RecyclerView bottomSheet = getActivity().findViewById(R.id.recycler_bottom_sheet);
        bottomSheet.setLayoutManager(new LinearLayoutManager(getActivity()));
        bottomSheet.setAdapter(new FolderAdapter(getActivity(), list));
    }

    @Override
    protected void onViewClick(View view) {

    }
}

package com.awake.dreaming.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.awake.dreaming.R;
import com.awake.dreaming.activity.ImageSelectorActivity;
import com.awake.dreaming.adapter.FolderAdapter;
import com.awake.dreaming.datas.Folder;
import com.awake.dreaming.datas.Image;

import java.util.ArrayList;

/**
 * Created by wangdesheng on 2017/10/31 0031.
 */

public class BottomSheetFragment extends BaseFragment{

    private ArrayList<Folder> list;
    private static final String TAG = "BottomSheetFragment";

    @Override
    protected int getLayout() {
        return R.layout.fragment_bottom_sheet;
    }

    @Override
    protected void setLayout() {
        RecyclerView bottomSheet = (RecyclerView) getActivity().findViewById(R.id.recycler_bottom_sheet);
        bottomSheet.setLayoutManager(new LinearLayoutManager(getActivity()));
        //接收从ImageSelectorActivity传过来的list。
        Bundle bundle = getArguments();
        final ArrayList<Folder> list =  bundle.getParcelableArrayList("key");
        //设置adapter
        FolderAdapter adapter = new FolderAdapter(getActivity(), list);
        bottomSheet.setAdapter(adapter);
        //设置folderList的Item的点击事件。
        adapter.setOnItemClickListener(new FolderAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, String name) {
                assert list != null;
                ArrayList<Image> mList = list.get(position).getImages();
                ImageSelectorActivity imageSelector = (ImageSelectorActivity) getActivity();
                imageSelector.setImages(mList, name);
            }
        });
    }

    @Override
    protected void onViewClick(View view) {

    }

    private void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}

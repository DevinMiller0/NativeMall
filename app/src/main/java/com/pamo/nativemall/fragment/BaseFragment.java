package com.pamo.nativemall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 德胜 on 2017/10/13.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setLayout();
    }

    @Override
    public void onClick(View view) {
        onViewClick(view);
    }

    protected abstract int getLayout();
    protected abstract void setLayout();
    protected abstract void onViewClick(View view);
}

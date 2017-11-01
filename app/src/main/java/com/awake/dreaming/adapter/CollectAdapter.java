package com.awake.dreaming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awake.dreaming.R;

/**
 * Created by wangdesheng on 2017/10/23 0023.
 */

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder> {

    public CollectAdapter(){

    }

    @Override
    public CollectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_collect_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CollectAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

package com.awake.dreaming.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.awake.dreaming.R;
import com.awake.dreaming.datas.HelpCenterData;

import java.util.ArrayList;

/**
 * Created by wangdesheng on 2017/11/24 0024.
 */

public class HelpCenterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<HelpCenterData> list;

    public HelpCenterAdapter(ArrayList<HelpCenterData> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder holder = null;

        switch (viewType) {
            case 0: {
                view = inflater.inflate(R.layout.layout_help_center_title, parent, false);
                holder = new TitleHolder(view);
                break;
            }
            case 1: {
                view = inflater.inflate(R.layout.layout_help_center_content, parent, false);
                holder = new ContentHolder(view);
                break;
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        Log.e("TAG", "onBindViewHolder: " + list.get(position).getIsTitle());
        switch (getItemViewType(position)) {
            case 0: {
                //Log.e("TAG", "onBindViewHolder: " + list.get(position).getContent());
                TitleHolder titleHolder = (TitleHolder) holder;
                titleHolder.title.setText(list.get(position).getContent());
                break;
            }
            case 1: {
                ContentHolder content = (ContentHolder) holder;
                //Log.e("TAG", "onBindViewHolder: " + list.get(position).getContent());
                content.content.setText(list.get(position).getContent());
                break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        //Log.e("TAG", "getItemViewType: " + list.get(position).getIsTitle() );
        return list.get(position).getIsTitle();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class TitleHolder extends RecyclerView.ViewHolder {
        TextView title;
        TitleHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
        }
    }

    private class ContentHolder extends RecyclerView.ViewHolder {
        TextView content;
        ContentHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.item_content);
        }
    }
}
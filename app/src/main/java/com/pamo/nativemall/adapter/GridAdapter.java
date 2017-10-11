package com.pamo.nativemall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pamo.nativemall.R;

import java.util.List;

/**
 * Created by wangdesheng on 2017/10/9 0009.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<String> mList;
    private LayoutInflater inflater;
    private String[] list = {"我的足迹", "收藏店铺", "名片夹", "我的名片", "备忘录", "帮助中心", "意见反馈", "关于我们", "敬请期待"};

    private GridAdapter.OnItemClickListener onItemClickListener = null;

    public GridAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.layout_grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GridAdapter.ViewHolder holder, int position) {

        holder.title.setText(list[position]);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }

    public void setOnItemClickListener(GridAdapter.OnItemClickListener listener) {

        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }
}

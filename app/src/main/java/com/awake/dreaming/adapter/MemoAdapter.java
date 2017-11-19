package com.awake.dreaming.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awake.dreaming.R;
import com.awake.dreaming.activity.HasMemoActivity;

import java.util.HashMap;

/**
 * Created by wangdesheng on 2017/11/18 0018.
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private Context context;
    private LongClickListener longClickListener;
    public HashMap<Integer, Integer> visibleCheck;

    @SuppressLint("UseSparseArrays")
    public MemoAdapter(HasMemoActivity context) {
        this.context = context;
        visibleCheck = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            visibleCheck.put(i, CheckBox.GONE);
        }
    }

    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_memo_list_item, null);
        ViewHolder holder;
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MemoAdapter.ViewHolder holder, final int position) {

        holder.checkBox.setVisibility(visibleCheck.get(position));
        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClickListener.longClick(3, view, holder.checkBox);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout parent;
        private ImageView imgMemo;
        private TextView desc;
        private TextView time;
        private CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent_item_memo);
            imgMemo = itemView.findViewById(R.id.img_memo_list_item);
            desc = itemView.findViewById(R.id.tv_remark);
            time = itemView.findViewById(R.id.tv_created_time);
            checkBox = itemView.findViewById(R.id.check_box);
        }
    }

    public void setOnLongClickListener(LongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public interface LongClickListener {
        void longClick(int position, View view, CheckBox checkBox);
    }
}

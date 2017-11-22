package com.awake.dreaming.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awake.dreaming.R;
import com.awake.dreaming.activity.HasMemoActivity;
import com.awake.dreaming.datas.MemoDatas;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Created by wangdesheng on 2017/11/18 0018.
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MemoDatas> list;
    private LongClickListener longClickListener;
    private OnItemClickListener itemClickListener;
    private OnCheckChangeListener checkChangeListener;
    public HashMap<Integer, Integer> visibleCheck;
    //public HashMap<Integer, Boolean> isCheck;

    @SuppressLint("UseSparseArrays")
    public MemoAdapter(HasMemoActivity context, ArrayList<MemoDatas> list) {
        this.context = context;
        this.list = list;
        visibleCheck = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
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

        holder.checkBox.setChecked(list.get(position).getChosen());

        TranslateAnimation showAnimation = getAnimation(1.5f, 0.0f, 0.0f, 0.0f);
        TranslateAnimation hiddenAnimation = getAnimation(0.0f, 1.5f, 0.0f, 0.0f);

        if (visibleCheck.get(position) == CheckBox.VISIBLE) {
            //holder.checkBox.startAnimation(showAnimation);
            holder.checkBox.setVisibility(visibleCheck.get(position));
        }else {
            //holder.checkBox.startAnimation(hiddenAnimation);
            holder.checkBox.setVisibility(visibleCheck.get(position));
        }

        holder.desc.setText(list.get(position).getDesc());

        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClickListener.longClick(position, view, holder.checkBox);
                return false;
            }
        });

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.itemClick(position, view, holder.checkBox);
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                checkChangeListener.checkChange(position, compoundButton, checked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnCheckChangeListener(OnCheckChangeListener checkChangeListener) {
        this.checkChangeListener = checkChangeListener;
    }

    public interface LongClickListener {
        void longClick(int position, View view, CheckBox checkBox);
    }

    public interface OnItemClickListener {
        void itemClick(int position, View view, CheckBox checkBox);
    }

    public interface OnCheckChangeListener {
        void checkChange(int position, CompoundButton compoundButton, boolean checked);
    }

    private TranslateAnimation getAnimation(float fromXValue, float toXValue,
                                           float fromYValue, float toYValue ) {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromXValue,
                Animation.RELATIVE_TO_SELF, toXValue, Animation.RELATIVE_TO_SELF, fromYValue,
                Animation.RELATIVE_TO_SELF, toYValue);
        animation.setDuration(500);
        return animation;
    }
}
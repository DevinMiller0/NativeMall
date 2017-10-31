package com.pamo.nativemall.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pamo.nativemall.R;
import com.pamo.nativemall.datas.Image;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by wangdesheng on 2017/10/30 0030.
 */

public class ImageSelectorAdapter extends RecyclerView.Adapter<ImageSelectorAdapter.ViewHolder>{

    private ArrayList<Image> images;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ImageSelectorAdapter(Context context, ArrayList<Image> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_img_selector_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        if (images.get(position).isYes()){
            holder.selectImg.setImageResource(R.mipmap.carmera);
            holder.selectImg.setBackgroundResource(R.color.colorCameraBg);
            holder.choose.setVisibility(View.INVISIBLE);
        }else {
            Uri uri = Uri.fromFile(new File(images.get(position).getPath()));
            holder.selectImg.setScaleType(ImageView.ScaleType.CENTER);
            Glide.with(context).load(uri).into(holder.selectImg);
        }

        holder.selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = images.get(position).getPath();
                onItemClickListener.itemClick(holder, position, path);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView selectImg;
        private CheckBox choose;

        private ViewHolder(View itemView) {
            super(itemView);
            selectImg = itemView.findViewById(R.id.img_selected);
            choose = itemView.findViewById(R.id.cb_choose);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void itemClick(ViewHolder holder, int position, String path);
    }
}

package com.awake.dreaming.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.awake.dreaming.R;
import com.awake.dreaming.datas.Folder;

import java.util.List;

/**
 * Created by wangdesheng on 2017/10/31 0031.
 */

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private List<Folder> folderList;
    private Context context;
    private OnItemClickListener itemClickListener;
    private int position;

    public FolderAdapter(Context context, List<Folder> folderList){
        this.folderList = folderList;
        this.context = context;
    }

    @Override
    public FolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_foler_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FolderAdapter.ViewHolder holder, final int position) {
        String path;
        if (folderList.get(position).getName().equals("全部图片")){
            path = folderList.get(position).getImages().get(1).getPath();
        }else {
            path = folderList.get(position).getImages().get(0).getPath();
        }
        ImageView headImg = holder.avatar;
        //set head image for CENTER_CROP.
        headImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //set head image and folder name.
        Glide.with(context).load(path).into(headImg);
        holder.name.setText(folderList.get(position).getName());
        holder.count.setText(folderList.get(position).getImages().size()+"张");
        holder.itemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.itemClick(position, folderList.get(position).getName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout itemParent;
        private ImageView avatar;
        private TextView name;
        private TextView count;

        ViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.img_folder_avatar);
            name = (TextView) itemView.findViewById(R.id.tv_folder_name);
            count = (TextView) itemView.findViewById(R.id.tv_folder_count);
            itemParent = (LinearLayout) itemView.findViewById(R.id.folder_item_parent);
        }
    }

    public interface OnItemClickListener{
        void itemClick(int position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}

package com.pamo.nativemall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pamo.nativemall.R;
import com.pamo.nativemall.datas.Folder;

import java.util.List;

/**
 * Created by wangdesheng on 2017/10/31 0031.
 */

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private List<Folder> folderList;
    private Context context;

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
    public void onBindViewHolder(FolderAdapter.ViewHolder holder, int position) {
        //Glide.with(context).load(folderList.get(position).getImages().get(0).getPath()).into(holder.avatar);
//        String path = folderList.get(position).getImages().get(1).getPath();
        //Log.e("TAG", "onBindViewHolder: " + path );
        holder.name.setText(folderList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView avatar;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.img_folder_avatar);
            name = itemView.findViewById(R.id.tv_folder_name);
        }
    }
}

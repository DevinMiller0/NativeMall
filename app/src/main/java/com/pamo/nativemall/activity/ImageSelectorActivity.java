package com.pamo.nativemall.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pamo.nativemall.R;
import com.pamo.nativemall.adapter.ImageSelectorAdapter;
import com.pamo.nativemall.datas.Folder;
import com.pamo.nativemall.datas.Image;
import com.pamo.nativemall.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by wangdesheng on 2017/10/27 0027.
 */

public class ImageSelectorActivity extends BaseActivity {

    private RecyclerView recyclerImg;
    private static String TAG = "ImageSelectorActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_image_selector;
    }

    @Override
    protected void setLayout() {
        recyclerImg = (RecyclerView) findViewById(R.id.rl_img_selector);
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.tv_complete).setOnClickListener(this);
        findViewById(R.id.tv_all_images).setOnClickListener(this);
        findViewById(R.id.tv_preview).setOnClickListener(this);
        checkPermission();
    }

    @Override
    protected void onViewClick(View view) {
        switch (view.getId()){
            case R.id.tv_complete:{
                Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_back:{
                this.finish();
                break;
            }
            case R.id.tv_all_images:{
                Toast.makeText(this, "All images", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.tv_preview:{
                Toast.makeText(this, "Preview", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * check permission and load image.
     */
    private void checkPermission(){
        int writePermission = ContextCompat.checkSelfPermission(getApplication(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writePermission == PackageManager.PERMISSION_GRANTED){
            //has permission, can load image.
            loadImage();
        }else {
            //no permission that apply for it.
            ActivityCompat.requestPermissions(ImageSelectorActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    /**
     * callback of handle permission
     */
    @Override
    public void onRequestPermissionsResult
            (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
             if (grantResults.length > 0
                     && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                 //allow permission, loading image.
                 loadImage();
             }else {
                 //refuse permission and pop prompt dialog that there no permission.
                 Toast.makeText(ImageSelectorActivity.this, "No Permission", Toast.LENGTH_SHORT).show();
             }
        }
    }

    /**
     * loading image method.
     */
    private void loadImage() {
        ImageUtils.loadImageForSDCard(this, new ImageUtils.CallBack() {
            @Override
            public void onSuccess(ArrayList<Folder> folders, final ArrayList<Image> images) {
                Log.e(TAG, "扫描到的图片数量: " + images.size() );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerImg.setLayoutManager
                                (new GridLayoutManager(ImageSelectorActivity.this, 3));

                        ImageSelectorAdapter adapter =
                                new ImageSelectorAdapter(ImageSelectorActivity.this, images);
                        recyclerImg.setAdapter(adapter);

                        adapter.setOnItemClickListener
                                (new ImageSelectorAdapter.OnItemClickListener() {
                            @Override
                            public void itemClick
                                    (ImageSelectorAdapter.ViewHolder holder, int position) {
                                if (position == 0){
                                    Toast.makeText(ImageSelectorActivity.this,
                                            "Open camera", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(ImageSelectorActivity.this,
                                            "Preview picture", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}

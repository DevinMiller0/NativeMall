package com.pamo.nativemall.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.pamo.nativemall.R;
import com.pamo.nativemall.datas.Folder;
import com.pamo.nativemall.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by wangdesheng on 2017/10/27 0027.
 */

public class ImageSelectorActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_image_selector;
    }

    @Override
    protected void setLayout() {

    }

    @Override
    protected void onViewClick(View view) {

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
             }
        }
    }

    /**
     * loading image method.
     */
    private void loadImage() {
        ImageUtils.loadImageForSDCard(this, new ImageUtils.CallBack() {
            @Override
            public void onSuccess(ArrayList<Folder> folders) {

            }
        });
    }
}

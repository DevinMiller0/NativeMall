package com.pamo.nativemall.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.pamo.nativemall.datas.Folder;
import com.pamo.nativemall.datas.Image;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by wangdesheng on 2017/10/27 0027.
 */

public class ImageUtils {

    /**
     * loading image from SDCard.
     */
    public static void loadImageForSDCard(final Context context, final DataCallBack callBack){
        // Because of scanning image would take long time, do it at child thread.
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = context.getContentResolver();
                Cursor cursor = contentResolver.query(imgUri, new String[]{
                        MediaStore.Images.Media.DATA,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.DATE_ADDED,
                        MediaStore.Images.Media._ID
                }, null, null, MediaStore.Images.Media.DATE_ADDED);

                ArrayList<Image> images = new ArrayList<>();
                //Read image of scanned.
                if (cursor != null) {
                    while (cursor.moveToNext()){
                        //obtain path of image.
                        String path = cursor.getString
                                (cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        //obtain name of image.
                        String name = cursor.getString
                                (cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        //obtain time of image.
                        long time = cursor.getLong
                                (cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
                        images.add(new Image(path, time, name));
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                Collections.reverse(images);
                callBack.onSuccess(splitFolder(images));
            }
        }).start();
    }

    /**
     * dismantling image by folder, first folder is used to keeping all images.
     */
    private static ArrayList<Folder> splitFolder(ArrayList<Image> images){
        ArrayList<Folder> folders = new ArrayList<>();
        folders.add(new Folder("全部图片", images));
        if (images != null && images.isEmpty()){
            int size = images.size();
            for (int i = 0; i < size; i++){
                String path = images.get(i).getPath();
                String name =
            }
        }
    }

    public interface DataCallBack {
        void onSuccess(ArrayList<Folder> folders);
    }
}

package com.pamo.nativemall.datas;

import org.apache.commons.codec.binary.StringUtils;

import java.util.ArrayList;

/**
 * Created by wangdesheng on 2017/10/27 0027.
 */

public class Folder {

    private String name;
    private ArrayList<Image> images;

    public Folder(String name){
        this.name = name;
    }

    public Folder(String name, ArrayList<Image> images){
        this.name = name;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public void addImage(Image image){
        if (image != null && image.equals("")){
            if (images == null){
                images = new ArrayList<>();
            }
            images.add(image);
        }
    }

    private static String getFolderName(String path){
        if (StringUtils.)
    }
}

package com.pamo.nativemall.datas;

/**
 * Created by wangdesheng on 2017/10/27 0027.
 */

public class Image {

    private String path;
    private long time;
    private String name;
    private boolean yes;

    public Image(String path, long time, String name, boolean yes){
        this.path = path;
        this.time = time;
        this.name = name;
        this.yes = yes;
    }

    public Image(boolean yes){
        this.yes = yes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isYes() {
        return yes;
    }

    public void setYes(boolean yes) {
        this.yes = yes;
    }
}

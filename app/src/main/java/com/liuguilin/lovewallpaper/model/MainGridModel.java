package com.liuguilin.lovewallpaper.model;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.model
 *  文件名:   MainGridModel
 *  创建者:   LGL
 *  创建时间:  2017/1/10 13:10
 *  描述：    时间
 */

public class MainGridModel {

    private String time;
    private String url;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MainGridModel{" +
                "time='" + time + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

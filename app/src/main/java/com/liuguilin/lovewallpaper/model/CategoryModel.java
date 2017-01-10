package com.liuguilin.lovewallpaper.model;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.model
 *  文件名:   CategoryModel
 *  创建者:   LGL
 *  创建时间:  2017/1/10 15:49
 *  描述：    数据模型
 */

public class CategoryModel {

    private String name;
    private String cover;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

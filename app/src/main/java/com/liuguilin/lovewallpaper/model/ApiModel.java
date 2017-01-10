package com.liuguilin.lovewallpaper.model;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.model
 *  文件名:   ApiModel
 *  创建者:   LGL
 *  创建时间:  2017/1/10 9:28
 *  描述：    api模型
 */

public class ApiModel {

    //排名
    private String ranking;
    //横幅
    private String banner;
    //壁纸
    private String wallpaper;
    //推荐
    private String recommend;
    //分类
    private String category;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ApiModel{" +
                "ranking='" + ranking + '\'' +
                ", banner='" + banner + '\'' +
                ", wallpaper='" + wallpaper + '\'' +
                ", recommend='" + recommend + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

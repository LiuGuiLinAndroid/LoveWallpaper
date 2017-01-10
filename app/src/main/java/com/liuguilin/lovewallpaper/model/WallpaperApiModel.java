package com.liuguilin.lovewallpaper.model;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.model
 *  文件名:   WallpaperApiModel
 *  创建者:   LGL
 *  创建时间:  2017/1/10 10:54
 *  描述：    爱壁纸api
 */

import java.util.List;

public class WallpaperApiModel {

    private String ranking;
    private String banner;
    private String wallpaper;
    private String recommend;
    private List<CategoryBean> category;
    private List<EverydayBean> everyday;
    private List<SpecialBean> special;

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

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public List<EverydayBean> getEveryday() {
        return everyday;
    }

    public void setEveryday(List<EverydayBean> everyday) {
        this.everyday = everyday;
    }

    public List<SpecialBean> getSpecial() {
        return special;
    }

    public void setSpecial(List<SpecialBean> special) {
        this.special = special;
    }

    public static class CategoryBean {

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
    }

    public static class EverydayBean {

        private String date;
        private String image;
        private String name;
        private String url;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class SpecialBean {

        private String name;
        private String desc;
        private String icon;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

package com.liuguilin.lovewallpaper.model;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.model
 *  文件名:   SpecialGridModel
 *  创建者:   LGL
 *  创建时间:  2017/1/10 14:48
 *  描述：    模型
 */

public class SpecialGridModel {

    private String key;
    private String small;
    private String big;
    private int down;
    private String down_stat;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getDown_stat() {
        return down_stat;
    }

    public void setDown_stat(String down_stat) {
        this.down_stat = down_stat;
    }

    @Override
    public String toString() {
        return "SpecialGridModel{" +
                "key='" + key + '\'' +
                ", small='" + small + '\'' +
                ", big='" + big + '\'' +
                ", down='" + down + '\'' +
                ", down_stat='" + down_stat + '\'' +
                '}';
    }
}

package com.liuguilin.lovewallpaper.model;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.model
 *  文件名:   WeatherGridModel
 *  创建者:   LGL
 *  创建时间:  2017/1/11 19:53
 *  描述：    数据模型
 */

public class WeatherGridModel {

    private int type;
    private String text;
    private int code;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "WeatherGridModel{" +
                "type=" + type +
                ", text='" + text + '\'' +
                ", code=" + code +
                '}';
    }
}

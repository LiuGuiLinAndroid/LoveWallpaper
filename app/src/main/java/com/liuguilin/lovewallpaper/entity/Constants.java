package com.liuguilin.lovewallpaper.entity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.entity
 *  文件名:   Constants
 *  创建者:   LGL
 *  创建时间:  2017/1/9 17:16
 *  描述：    常量类
 */

import com.liuguilin.lovewallpaper.R;

public class Constants {

    //第一次启动
    public static final int HANDLER_FIRST_START = 1000;

    public static final String WALLPAPER_BASE_URL = "http://open.lovebizhi.com/";
    public static final String WEATHER_BASE_URL = "https://api.thinkpage.cn/";

    //心知天气key
    public static final String THINKPAPE_KEY = "cjfbaiq6lln0oqk1";

    //天气图标
    public static final int WEATHER_ICON[] =
            {R.drawable.w0, R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4
                    , R.drawable.w5, R.drawable.w6, R.drawable.w7, R.drawable.w8, R.drawable.w9
                    , R.drawable.w10, R.drawable.w11, R.drawable.w12, R.drawable.w13, R.drawable.w14
                    , R.drawable.w15, R.drawable.w16, R.drawable.w17, R.drawable.w18, R.drawable.w19
                    , R.drawable.w20, R.drawable.w21, R.drawable.w22, R.drawable.w23, R.drawable.w24
                    , R.drawable.w25, R.drawable.w26, R.drawable.w27, R.drawable.w28, R.drawable.w29
                    , R.drawable.w30, R.drawable.w31, R.drawable.w32, R.drawable.w33, R.drawable.w34
                    , R.drawable.w35, R.drawable.w36, R.drawable.w37, R.drawable.w38, R.drawable.w99};
    //新闻类别
    public static final String NEWS_TYPE_EN[] = {"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};
    public static final String NEWS_TYPE_ZH[] = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
}

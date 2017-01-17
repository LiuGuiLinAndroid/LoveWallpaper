package com.liuguilin.lovewallpaper.entity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.entity
 *  文件名:   Constants
 *  创建者:   LGL
 *  创建时间:  2017/1/9 17:16
 *  描述：    常量类
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Gravity;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.view.CustomDialog;

public class Constants {

    //返回码
    public static final int REQUEST_CODE = 101;

    //第一次启动
    public static final int HANDLER_FIRST_START = 1000;
    //无限轮播
    public static final int HANDLER_AUTO_SHUFFLING = 1001;
    //下拉刷新
    public static final int HANDLER_REFRESH = 1002;
    //停止动画
    public static final int HANDLER_STOP_ANIMATION = 1003;

    public static final String WALLPAPER_BASE_URL = "http://open.lovebizhi.com/";
    public static final String WEATHER_BASE_URL = "https://api.thinkpage.cn/";

    //视频地址
    public static final String IMOOC_VIDEO_URL = "http://szv1.mukewang.com/5876eb5be520e572618b458b/H.mp4";
    //图片地址
    public static final String IMOOC_IMAGE_URL = "http://szimg.mukewang.com/5876eedd0001d20909000500.jpg";

    //Blog
    public static final String BLOG = "http://blog.csdn.net/qq_26787115";
    //Github
    public static final String GITHUB = "https://github.com/LiuGuiLinAndroid";

    //心知天气key
    public static final String THINKPAPE_KEY = "cjfbaiq6lln0oqk1";

    //生活指数图片
    public static final int WEATHER_LIFE_ICON[] = {R.drawable.icon_car_washing, R.drawable.icon_dressing
            , R.drawable.icon_flu, R.drawable.icon_sport, R.drawable.icon_travel, R.drawable.icon_uv};

    //设置图标
    public static final int SETTING_ICON[] = {R.drawable.icon_setting_msg, R.drawable.icon_clear
            , R.drawable.icon_version, R.drawable.icon_git, R.drawable.icon_about};

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

    //封装dialog
    public static CustomDialog showDialog(Context mContext, int layout) {
        //初始化提示框
        CustomDialog dialog = new CustomDialog(mContext, 0, 0,
                layout, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        return dialog;
    }

    //获取版本号
    public static String getAppVersion(Context mContext) {
        String version = "";
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
            version = info.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            version = "获取失败";
        }
        return version;
    }
}

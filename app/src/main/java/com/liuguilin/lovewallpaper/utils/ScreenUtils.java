package com.liuguilin.lovewallpaper.utils;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.utils
 *  文件名:   ScreenUtils
 *  创建者:   LGL
 *  创建时间:  2017/1/9 17:44
 *  描述：    屏幕工具类
 */

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {

    private static ScreenUtils sInstance;
    public static WindowManager windowManager;

    public static ScreenUtils getInstance(Context mContext) {
        synchronized (ScreenUtils.class) {
            if (sInstance == null) {
                sInstance = new ScreenUtils();
                windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            }
        }
        return sInstance;
    }

    //获取屏幕的宽度
    public int getScreenWidth() {
        return windowManager.getDefaultDisplay().getWidth();
    }

    //获取屏幕的高度
    public int getScreenHeight() {
        return windowManager.getDefaultDisplay().getHeight();
    }

    //获取设备分辨率
    public String getDisplayMetrics(Activity mActivity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels + "x"
                + displayMetrics.heightPixels;
    }
}

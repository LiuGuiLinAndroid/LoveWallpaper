package com.liuguilin.lovewallpaper.utils;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.utils
 *  文件名:   L
 *  创建者:   LGL
 *  创建时间:  2017/1/9 17:28
 *  描述：    Log封装
 */

import android.util.Log;

public class L {

    private static final boolean DEBUG = true;

    private static final String TAG = "LoveWallpaper";

    public static void i(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void i(String TAG,String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    public static void d(String text) {
        if (DEBUG) {
            Log.d(TAG, text);
        }
    }

    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text);
        }
    }

    public static void v(String text) {
        if (DEBUG) {
            Log.v(TAG, text);
        }
    }

    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text);
        }
    }

}

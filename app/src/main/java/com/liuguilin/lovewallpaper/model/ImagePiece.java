package com.liuguilin.lovewallpaper.model;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.model
 *  文件名:   ImagePiece
 *  创建者:   LGL
 *  创建时间:  2017/1/21 11:54
 *  描述：    游戏实体
 */

import android.graphics.Bitmap;

public class ImagePiece {
    private int index;
    private Bitmap bitmap;

    //构造方法
    public ImagePiece() {
    }

    //有参构造方法
    public ImagePiece(int index, Bitmap bitmap) {
        this.index = index;
        this.bitmap = bitmap;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}

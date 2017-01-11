package com.liuguilin.lovewallpaper.adapter;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.adapter
 *  文件名:   GalleryAdapter
 *  创建者:   LGL
 *  创建时间:  2017/1/11 13:24
 *  描述：    画廊适配器
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.liuguilin.lovewallpaper.utils.GlideUtils;

import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mList;

    public GalleryAdapter(Context mContext, ArrayList<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(mContext);
        GlideUtils.loadImageCrop(mContext, mList.get(i), imageView);
        imageView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.WRAP_CONTENT));
        return imageView;
    }
}

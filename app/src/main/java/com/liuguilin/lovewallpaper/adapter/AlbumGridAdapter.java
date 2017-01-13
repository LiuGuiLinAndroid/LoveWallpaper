package com.liuguilin.lovewallpaper.adapter;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.adapter
 *  文件名:   AlbumGridAdapter
 *  创建者:   LGL
 *  创建时间:  2017/1/13 16:17
 *  描述：    相册适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.utils.GlideUtils;

import java.util.ArrayList;

public class AlbumGridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mList;
    private LayoutInflater inflater;

    public AlbumGridAdapter(Context mContext, ArrayList<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.fragment_album_grid_item, null);
            viewHolder.iv_image = (ImageView) view.findViewById(R.id.iv_image);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (mList.size() > 0) {
            GlideUtils.loadImageCrop(mContext, mList.get(i), viewHolder.iv_image);
        }
        return view;
    }

    class ViewHolder {
        private ImageView iv_image;
    }
}

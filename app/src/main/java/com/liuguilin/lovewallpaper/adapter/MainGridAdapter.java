package com.liuguilin.lovewallpaper.adapter;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.adapter
 *  文件名:   MainGridAdapter
 *  创建者:   LGL
 *  创建时间:  2017/1/10 11:49
 *  描述：    每日
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.model.MainGridModel;
import com.liuguilin.lovewallpaper.utils.GlideUtils;

import java.util.List;

public class MainGridAdapter extends BaseAdapter {

    public Context mContext;
    private LayoutInflater inflater;
    private List<MainGridModel> mList;
    private MainGridModel model;

    public MainGridAdapter(Context mContext, List<MainGridModel> mList) {
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
            view = inflater.inflate(R.layout.fragment_main_grid_item, null);
            viewHolder.iv_main_grid_icon = (ImageView) view.findViewById(R.id.iv_main_grid_icon);
            viewHolder.tv_main_grid_time = (TextView) view.findViewById(R.id.tv_main_grid_time);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        model = mList.get(i);
        GlideUtils.loadImageCrop(mContext, model.getUrl(), viewHolder.iv_main_grid_icon);
        viewHolder.tv_main_grid_time.setText(model.getTime());
        return view;
    }

    class ViewHolder {
        private ImageView iv_main_grid_icon;
        private TextView tv_main_grid_time;
    }
}

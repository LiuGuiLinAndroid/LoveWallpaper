package com.liuguilin.lovewallpaper.adapter;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.adapter
 *  文件名:   CategoryListAdapter
 *  创建者:   LGL
 *  创建时间:  2017/1/10 15:47
 *  描述：    分类
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.model.CategoryModel;
import com.liuguilin.lovewallpaper.utils.GlideUtils;

import java.util.List;

public class CategoryListAdapter extends BaseAdapter {

    public Context mContext;
    private LayoutInflater inflater;
    private List<CategoryModel> mList;
    private CategoryModel model;

    public CategoryListAdapter(Context mContext, List<CategoryModel> mList) {
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
            view = inflater.inflate(R.layout.fragment_category_list_item, null);
            viewHolder.iv_category_list_icon = (ImageView) view.findViewById(R.id.iv_category_list_icon);
            viewHolder.tv_category_list_name = (TextView) view.findViewById(R.id.tv_category_list_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        model = mList.get(i);
        GlideUtils.loadImageCrop(mContext, model.getCover(), viewHolder.iv_category_list_icon);
        viewHolder.tv_category_list_name.setText(model.getName());
        return view;
    }

    class ViewHolder {
        private ImageView iv_category_list_icon;
        private TextView tv_category_list_name;
    }

}

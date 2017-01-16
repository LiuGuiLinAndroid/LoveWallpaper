package com.liuguilin.lovewallpaper.adapter;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.adapter
 *  文件名:   SettingAdapter
 *  创建者:   LGL
 *  创建时间:  2017/1/16 17:42
 *  描述：    设置适配器
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.entity.Constants;

import java.util.List;

public class SettingAdapter extends BaseAdapter {

    public Context mContext;
    private LayoutInflater inflater;
    private List<String> mList;

    public SettingAdapter(Context mContext, List<String> mList) {
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
            view = inflater.inflate(R.layout.fragment_setting_item, null);
            viewHolder.iv_setting_icon = (ImageView) view.findViewById(R.id.iv_setting_icon);
            viewHolder.tv_setting_content = (TextView) view.findViewById(R.id.tv_setting_content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.iv_setting_icon.setBackgroundResource(Constants.SETTING_ICON[i]);
        viewHolder.tv_setting_content.setText(mList.get(i));
        return view;
    }

    class ViewHolder {
        private ImageView iv_setting_icon;
        private TextView tv_setting_content;
    }

    //更新单个item
    public void updataOneItemView(int position, ListView listView,String text) {
        int visibleFirstPosi = listView.getFirstVisiblePosition();
        int visibleLastPosi = listView.getLastVisiblePosition();
        if (position >= visibleFirstPosi && position <= visibleLastPosi) {
            View view = listView.getChildAt(position - visibleFirstPosi);
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.tv_setting_content.setText(text);
            mList.set(position, text);
        } else {
            mList.set(position, text);
        }
    }
}

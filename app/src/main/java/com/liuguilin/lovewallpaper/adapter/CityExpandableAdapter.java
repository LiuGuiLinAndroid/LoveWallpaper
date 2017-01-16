package com.liuguilin.lovewallpaper.adapter;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.adapter
 *  文件名:   CityExpandableAdapter
 *  创建者:   LGL
 *  创建时间:  2017/1/16 14:05
 *  描述：    城市扩展
 */

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

public class CityExpandableAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> groupArray;
    private List<List<String>> childArray;

    public CityExpandableAdapter(Context mContext, List<String> groupArray, List<List<String>> childArray) {
        this.mContext = mContext;
        this.groupArray = groupArray;
        this.childArray = childArray;
    }

    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childArray.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupArray.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childArray.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String string = groupArray.get(i);
        return getGenericView(string);
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        List<String> string = childArray.get(i);
        TextView textView = getGenericView(string.get(i1));
        textView.setText(getChild(i, i1).toString());
        return textView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public TextView getGenericView(String string) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 64);
        TextView text = new TextView(mContext);
        text.setLayoutParams(layoutParams);
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        text.setPadding(36, 0, 0, 0);
        text.setText(string);
        return text;
    }
}

package com.liuguilin.lovewallpaper.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuguilin.lovewallpaper.R;

/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.fragment
 *  文件名:   NewsPageFragment
 *  创建者:   LiuGuiLin
 *  创建时间:  2017/1/10 0010 下午 11:22
 *  描述：    新闻详情
 */
public class NewsPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_page,container,false);
        return view;
    }
}

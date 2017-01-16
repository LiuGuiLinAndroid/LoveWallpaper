package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   SettingActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/9 19:47
 *  描述：    设置
 */

import android.os.Bundle;
import android.widget.ListView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.adapter.SettingAdapter;
import com.liuguilin.lovewallpaper.entity.Constants;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends BaseActivity {

    private ListView mListView;
    private SettingAdapter mSettingAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        mList.add("系统信息");
        mList.add("清理缓存");
        mList.add("版本号:" + Constants.getAppVersion(this));
        mList.add("Github");
        mList.add("关于软件");

        mListView = (ListView) findViewById(R.id.mListView);
        mSettingAdapter = new SettingAdapter(this, mList);
        mListView.setAdapter(mSettingAdapter);
    }
}

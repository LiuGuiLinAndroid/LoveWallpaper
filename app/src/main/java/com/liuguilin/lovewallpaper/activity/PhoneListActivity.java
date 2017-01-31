package com.liuguilin.lovewallpaper.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liuguilin.lovewallpaper.R;

import java.util.ArrayList;
import java.util.List;

/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   PhoneListActivity
 *  创建者:   LiuGuiLin
 *  创建时间:  2017/1/31 0031 上午 10:29
 *  描述：    手机系列
 */
public class PhoneListActivity extends BaseActivity {

    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);

        initView();
    }

    private void initView() {
        mList.add("iphone 5");
        mList.add("iphone 5s");
        mList.add("iphone 6");
        mList.add("iphone 6s");
        mList.add("iphone 7");
        mListView = (ListView) findViewById(R.id.mListView);
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mList);
        mListView.setAdapter(mAdapter);
    }
}

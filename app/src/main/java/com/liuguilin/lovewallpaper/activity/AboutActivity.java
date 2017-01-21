package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   AboutActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/16 19:57
 *  描述：    关于
 */

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.entity.Constants;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private CircleImageView profile_image;
    private ListView mListView;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {

        mList.add("作者:刘某人");
        mList.add("Github");
        mList.add("CSDN Blog");
        mList.add("QQ群:555974449");

        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.mListView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_image:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                //不需要
                break;
            case 1:
                Constants.startWebView(this, "Github", Constants.GITHUB);
                break;
            case 2:
                Constants.startWebView(this, "CSDN Blog", Constants.BLOG);
                break;
            case 3:
                Constants.joinQQGroup(this, "WKsVihQjloOtstvRIXUWxU2M4QRKUwO0");
                break;
        }
    }
}

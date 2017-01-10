package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   CategoryActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/10 16:04
 *  描述：    分类详情
 */

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.liuguilin.lovewallpaper.R;

public class CategoryActivity extends BaseActivity {

    private GridView mGridView;

    private String name;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        url = intent.getStringExtra("url");
    }
}

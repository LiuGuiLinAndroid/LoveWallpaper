package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   SpecialActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/10 13:45
 *  描述：    轮播详情
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.utils.GlideUtils;

public class SpecialActivity extends BaseActivity {

    private String name;
    private String desc;
    private String icon;
    private String url;
    private ImageView iv_icon;
    private TextView tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        initView();
    }

    private void initView() {

        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_desc = (TextView) findViewById(R.id.tv_desc);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        desc = intent.getStringExtra("desc");
        icon = intent.getStringExtra("icon");
        url = intent.getStringExtra("url");

        //设置标题
        if (!TextUtils.isEmpty(name)) {
            getSupportActionBar().setTitle(name);
        }

        if (!TextUtils.isEmpty(desc)) {
            tv_desc.setText(desc);
        } else {
            tv_desc.setText("加载失败!");
        }

        if (!TextUtils.isEmpty(icon)) {
            GlideUtils.loadImageCrop(this, icon, iv_icon);
        }
    }
}

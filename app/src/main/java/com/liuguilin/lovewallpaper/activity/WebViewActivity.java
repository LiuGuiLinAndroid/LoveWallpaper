package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   WebViewActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/17 16:23
 *  描述：    浏览器
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import com.liuguilin.lovewallpaper.R;

public class WebViewActivity extends BaseActivity {

    private WebView mWebView;
    private String title, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.mWebView);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");

        if (!TextUtils.isEmpty(title)) {
            getSupportActionBar().setTitle(title);
        } else {
            getSupportActionBar().setTitle("加载失败");
        }


    }
}

package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   SplashActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/9 17:34
 *  描述：    引导页
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.liuguilin.lovewallpaper.MainActivity;
import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.entity.Constants;

public class SplashActivity extends AppCompatActivity{

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Constants.HANDLER_FIRST_START:
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {
        mHandler.sendEmptyMessageDelayed(Constants.HANDLER_FIRST_START,2000);
    }
}

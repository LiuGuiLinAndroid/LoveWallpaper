package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   SettingActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/9 19:47
 *  描述：    设置
 */

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.adapter.SettingAdapter;
import com.liuguilin.lovewallpaper.entity.Constants;
import com.liuguilin.lovewallpaper.utils.CleanMessageUtils;
import com.liuguilin.lovewallpaper.view.CustomDialog;
import com.liuguilin.lovewallpaper.view.PaperShredderView;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private SettingAdapter mSettingAdapter;
    private List<String> mList = new ArrayList<>();

    private CustomDialog dialog_clear_data;
    private PaperShredderView mPaperShredderView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.HANDLER_STOP_ANIMATION:
                    mPaperShredderView.stopAnim();
                    dialog_clear_data.dismiss();
                    try {
                        mSettingAdapter.updataOneItemView(1, mListView, "清理缓存\t\t\t" + CleanMessageUtils.getTotalCacheSize(SettingActivity.this));
                    } catch (Exception e) {
                        mSettingAdapter.updataOneItemView(1, mListView, "清理缓存");
                    }
                    mSettingAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        mList.add("系统信息");
        try {
            mList.add("清理缓存\t\t\t" + CleanMessageUtils.getTotalCacheSize(this));
        } catch (Exception e) {
            mList.add("清理缓存");
        }
        mList.add("版本号\t\t\t" + Constants.getAppVersion(this));
        mList.add("Github");
        mList.add("关于软件");

        mListView = (ListView) findViewById(R.id.mListView);
        mSettingAdapter = new SettingAdapter(this, mList);
        mListView.setAdapter(mSettingAdapter);
        mListView.setOnItemClickListener(this);

        dialog_clear_data = Constants.showDialog(this, R.layout.dialog_clear_data);
        mPaperShredderView = (PaperShredderView) dialog_clear_data.findViewById(R.id.mPaperShredderView);
        mPaperShredderView.setSherderProgress(true);
        mPaperShredderView.setTitle("清理缓存");
        mPaperShredderView.setTextColor(Color.WHITE);
        mPaperShredderView.setPaperColor(Color.WHITE);
        mPaperShredderView.setBgColor(R.color.colorAccent);
        mPaperShredderView.setTextShadow(true);
        mPaperShredderView.setPaperEnterColor(R.color.colorAccent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                startActivity(new Intent(this, SystemInformationActivity.class));
                break;
            case 1:
                if (mList.get(1).length() > "清理缓存".length()) {
                    dialog_clear_data.show();
                    mPaperShredderView.startAnim(3000);
                    CleanMessageUtils.clearAllCache(this);
                    mHandler.sendEmptyMessageDelayed(Constants.HANDLER_STOP_ANIMATION, 3000);
                }
                break;
            case 2:
                Toast.makeText(this, "已经是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Constants.startWebView(this, getString(R.string.app_name), Constants.LOVE_WALLPAPER_GITHUB);
                break;
            case 4:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
    }
}

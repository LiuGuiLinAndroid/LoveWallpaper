package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   GalleryActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/11 11:19
 *  描述：    画廊预览
 */

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.ProgressListener;
import com.kymjs.rxvolley.http.VolleyError;
import com.kymjs.rxvolley.toolbox.FileUtils;
import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.adapter.GalleryAdapter;
import com.liuguilin.lovewallpaper.utils.L;
import com.liuguilin.lovewallpaper.view.CustomDialog;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {

    private Gallery mGallery;
    private int position;
    private ArrayList<String> mListBigUrl;
    private ImageView iv_back;
    private ImageView iv_preview_share;
    private ImageView iv_preview_fav;
    private Button btn_set_wallpaper;
    private ImageView iv_preview_down;
    private ImageView iv_preview_menu;

    private WallpaperManager wpManager;
    private CustomDialog dialog_setwallpaper;
    private Button btn_lock;
    private Button btn_desktop;
    private Button btn_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initView();
    }

    private void initView() {

        dialog_setwallpaper = new CustomDialog(this, 0, 0,
                R.layout.dialog_set_wallpaper, R.style.Theme_dialog, Gravity.BOTTOM, R.style.pop_anim_style);
        btn_lock = (Button) dialog_setwallpaper.findViewById(R.id.btn_lock);
        btn_lock.setOnClickListener(this);
        btn_desktop = (Button) dialog_setwallpaper.findViewById(R.id.btn_desktop);
        btn_desktop.setOnClickListener(this);
        btn_all = (Button) dialog_setwallpaper.findViewById(R.id.btn_all);
        btn_all.setOnClickListener(this);

        mGallery = (Gallery) findViewById(R.id.mGallery);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        iv_preview_share = (ImageView) findViewById(R.id.iv_preview_share);
        iv_preview_share.setOnClickListener(this);
        iv_preview_fav = (ImageView) findViewById(R.id.iv_preview_fav);
        iv_preview_fav.setOnClickListener(this);
        btn_set_wallpaper = (Button) findViewById(R.id.btn_set_wallpaper);
        btn_set_wallpaper.setOnClickListener(this);
        iv_preview_down = (ImageView) findViewById(R.id.iv_preview_down);
        iv_preview_down.setOnClickListener(this);
        iv_preview_menu = (ImageView) findViewById(R.id.iv_preview_menu);
        iv_preview_menu.setOnClickListener(this);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        mListBigUrl = intent.getStringArrayListExtra("bigUrl");

        //壁纸管理器
        wpManager = WallpaperManager.getInstance(this);

        if (mListBigUrl.size() > 0) {
            mGallery.setAdapter(new GalleryAdapter(this, mListBigUrl));
            mGallery.setSelection(position);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_preview_share:

                break;
            case R.id.iv_preview_fav:

                break;
            case R.id.iv_preview_down:
                RxVolley.download(FileUtils.getSDCardPath() + "/LoveWallpaper/" + System.currentTimeMillis() + ".png"
                        , mListBigUrl.get(mGallery.getSelectedItemPosition())
                        , new ProgressListener() {
                            @Override
                            public void onProgress(long transferredBytes, long totalSize) {

                            }
                        }, new HttpCallback() {
                            @Override
                            public void onSuccess(String t) {
                                Toast.makeText(GalleryActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(VolleyError error) {
                                Toast.makeText(GalleryActivity.this, "下载失败" + error.toString(), Toast.LENGTH_SHORT).show();
                                L.i(error.toString());
                            }
                        });
                break;
            case R.id.iv_preview_menu:

                break;
            case R.id.btn_set_wallpaper:
                dialog_setwallpaper.show();
                break;
            case R.id.btn_lock:
                setLockScreenWallpaper();
                dialog_setwallpaper.dismiss();
                break;
            case R.id.btn_desktop:
                setDesktopWallpaper();
                dialog_setwallpaper.dismiss();
                break;
            case R.id.btn_all:
                setAllWallpaper();
                dialog_setwallpaper.dismiss();
                break;
        }
    }

    //设置桌面壁纸
    private void setDesktopWallpaper() {
        Glide.with(this).load(mListBigUrl.get(mGallery.getSelectedItemPosition())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                try {
                    wpManager.setBitmap(resource);
                    Toast.makeText(GalleryActivity.this, "桌面壁纸设置成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(GalleryActivity.this, "桌面壁纸设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //设置锁屏壁纸
    private void setLockScreenWallpaper() {
        Glide.with(this).load(mListBigUrl.get(mGallery.getSelectedItemPosition())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                try {
                    //获取类名
                    Class class1 = wpManager.getClass();
                    //获取设置锁屏壁纸的函数
                    Method setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper", Bitmap.class);
                    //调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
                    setWallPaperMethod.invoke(wpManager, resource);
                    Toast.makeText(GalleryActivity.this, "锁屏壁纸设置成功", Toast.LENGTH_SHORT).show();
                } catch (Throwable e) {
                    Toast.makeText(GalleryActivity.this, "锁屏壁纸设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //设置所有壁纸
    private void setAllWallpaper() {
        Glide.with(this).load(mListBigUrl.get(mGallery.getSelectedItemPosition())).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                try {
                    //获取类名
                    Class class1 = wpManager.getClass();
                    //获取设置锁屏壁纸的函数
                    Method setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper", Bitmap.class);
                    //调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
                    setWallPaperMethod.invoke(wpManager, resource);
                    Toast.makeText(GalleryActivity.this, "锁屏壁纸设置成功", Toast.LENGTH_SHORT).show();
                } catch (Throwable e) {
                    Toast.makeText(GalleryActivity.this, "锁屏壁纸设置失败", Toast.LENGTH_SHORT).show();
                }
                try {
                    wpManager.setBitmap(resource);
                    Toast.makeText(GalleryActivity.this, "桌面壁纸设置成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(GalleryActivity.this, "桌面壁纸设置失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


package com.liuguilin.lovewallpaper.fragment;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.fragment
 *  文件名:   AlbumFragment
 *  创建者:   LGL
 *  创建时间:  2017/1/13 15:49
 *  描述：    相册
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.activity.GalleryActivity;
import com.liuguilin.lovewallpaper.adapter.AlbumGridAdapter;
import com.liuguilin.lovewallpaper.entity.Constants;

import java.util.ArrayList;

public class AlbumFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private GridView mGridView;
    //相册路径
    private ArrayList<String> paths = new ArrayList<>();
    private AlbumGridAdapter albumGridAdapter;
    //下拉刷新
    private SwipeRefreshLayout mSwipeRefreshLayout;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.HANDLER_REFRESH:
                    paths.clear();
                    getAllImagePath();
                    albumGridAdapter.notifyDataSetChanged();
                    if (mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mGridView = (GridView) view.findViewById(R.id.mGridView);
        getPermission();
        albumGridAdapter = new AlbumGridAdapter(getActivity(), paths);
        mGridView.setAdapter(albumGridAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                intent.putExtra("position", i);
                intent.putStringArrayListExtra("bigUrl", paths);
                startActivity(intent);
            }
        });
    }

    //获取读取内存卡权限
    private void getPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        } else {
            getAllImagePath();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getAllImagePath();
                } else {
                    Toast.makeText(getActivity(), "请打开读/写权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //获取本地相册
    private void getAllImagePath() {
        Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        //遍历相册
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            //将图片路径添加到集合
            paths.add(path);
        }
        cursor.close();
    }

    //下拉回调
    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessage(Constants.HANDLER_REFRESH);
    }

    //视图改变
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            getAllImagePath();
            albumGridAdapter.notifyDataSetChanged();
        }
    }
}

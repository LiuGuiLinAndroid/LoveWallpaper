package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   CityActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/16 11:04
 *  描述：    选择城市
 */

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.adapter.CityExpandableAdapter;
import com.liuguilin.lovewallpaper.entity.Constants;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends BaseActivity {

    private ExpandableListView mExpandableListView;
    private CityExpandableAdapter mCityExpandableAdapter;
    private List<String> groupArray = new ArrayList<>();
    private List<List<String>> childArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        initView();
    }

    private void initView() {
        mExpandableListView = (ExpandableListView) findViewById(R.id.mExpandableListView);


        mCityExpandableAdapter = new CityExpandableAdapter(this, groupArray, childArray);
        mExpandableListView.setAdapter(mCityExpandableAdapter);
    }


    private void test() {
        Intent intent = new Intent();
        intent.putExtra("city_name", "I am second!");
        setResult(Constants.REQUEST_CODE, intent);
        finish();
    }
}

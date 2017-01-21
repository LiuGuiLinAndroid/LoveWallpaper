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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.entity.Constants;
import com.liuguilin.lovewallpaper.model.CityApiModel;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends BaseActivity {

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        initView();
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.mListView);
        String json = Constants.getFromAssets(this, "allcity.json");

        //使用JSONObject可以优化
        Gson gson = new Gson();
        CityApiModel cityMoel = gson.fromJson(json, CityApiModel.class);
        for (int i = 0; i < cityMoel.getCity().get(0).getHot().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getHot().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getA().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getA().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getB().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getB().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getC().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getC().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getD().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getD().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getE().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getE().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getF().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getF().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getG().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getG().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getH().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getH().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getJ().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getJ().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getK().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getK().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getL().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getL().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getM().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getM().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getN().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getN().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getP().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getP().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getQ().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getQ().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getR().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getR().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getS().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getS().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getT().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getT().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getW().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getW().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getX().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getX().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getY().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getY().get(i).getName());
        }
        for (int i = 0; i < cityMoel.getCity().get(0).getZ().size(); i++) {
            mList.add(cityMoel.getCity().get(0).getZ().get(i).getName());
        }

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("city_name", mList.get(position));
                setResult(Constants.REQUEST_CODE, intent);
                finish();
            }
        });
    }
}

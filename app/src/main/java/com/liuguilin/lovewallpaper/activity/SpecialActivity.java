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
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;
import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.adapter.SpecialGridAdapter;
import com.liuguilin.lovewallpaper.model.SpecialApiModel;
import com.liuguilin.lovewallpaper.model.SpecialGridModel;
import com.liuguilin.lovewallpaper.utils.GlideUtils;
import com.liuguilin.lovewallpaper.utils.L;

import java.util.ArrayList;
import java.util.List;

public class SpecialActivity extends BaseActivity {

    private String name;
    private String desc;
    private String icon;
    private String url;
    private ImageView iv_icon;
    private TextView tv_desc;
    private GridView mGridView;

    private LinearLayout ll_special_title;

    private SpecialGridAdapter mSpecialGridAdapter;
    private List<SpecialGridModel> mList = new ArrayList<>();
    private ArrayList<String> mListBigUrl = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        initView();
    }

    private void initView() {

        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        mGridView = (GridView) findViewById(R.id.mGridView);
        ll_special_title = (LinearLayout) findViewById(R.id.ll_special_title);

        final Intent intent = getIntent();
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
            ll_special_title.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(icon)) {
            GlideUtils.loadImageCrop(this, icon, iv_icon);
        }

        if (!TextUtils.isEmpty(url)) {
            RxVolley.get(url, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    parsingJson(t);
                }

                @Override
                public void onFailure(VolleyError error) {
                    L.i(error.toString());
                }
            });
        }

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SpecialActivity.this, GalleryActivity.class);
                intent.putExtra("position", i);
                intent.putStringArrayListExtra("bigUrl", mListBigUrl);
                startActivity(intent);
            }
        });
    }

    //解析
    private void parsingJson(String t) {
        Gson gson = new Gson();
        SpecialApiModel model = gson.fromJson(t, SpecialApiModel.class);
        for (int i = 0; i < model.getData().size(); i++) {
            SpecialGridModel models = new SpecialGridModel();
            models.setKey(model.getData().get(i).getKey());
            models.setBig(model.getData().get(i).getBig());
            mListBigUrl.add(model.getData().get(i).getBig());
            models.setDown(model.getData().get(i).getDown());
            models.setDown_stat(model.getData().get(i).getDown_stat());
            models.setSmall(model.getData().get(i).getSmall());
            mList.add(models);
        }
        mSpecialGridAdapter = new SpecialGridAdapter(this, mList);
        mGridView.setAdapter(mSpecialGridAdapter);
    }
}

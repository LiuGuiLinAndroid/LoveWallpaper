package com.liuguilin.lovewallpaper.fragment;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.fragment
 *  文件名:   WallpaperFragment
 *  创建者:   LGL
 *  创建时间:  2017/1/10 10:13
 *  描述：    壁纸
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;
import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.activity.GalleryActivity;
import com.liuguilin.lovewallpaper.adapter.SpecialGridAdapter;
import com.liuguilin.lovewallpaper.model.ApiModel;
import com.liuguilin.lovewallpaper.model.SpecialApiModel;
import com.liuguilin.lovewallpaper.model.SpecialGridModel;

import java.util.ArrayList;
import java.util.List;

public class WallpaperFragment extends Fragment{

    private GridView mGridView;
    private SpecialGridAdapter mSpecialGridAdapter;
    private List<SpecialGridModel> mList = new ArrayList<>();
    private ArrayList<String>mListBigUrl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallpaper,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mGridView = (GridView) view.findViewById(R.id.mGridView);

        if (!TextUtils.isEmpty(ApiModel.wallpaper)) {
            RxVolley.get(ApiModel.wallpaper, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    parsingJson(t);
                }

                @Override
                public void onFailure(VolleyError error) {
                    super.onFailure(error);
                }
            });
        }

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                intent.putExtra("position", i);
                intent.putStringArrayListExtra("bigUrl", mListBigUrl);
                startActivity(intent);
            }
        });
    }

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
        mSpecialGridAdapter = new SpecialGridAdapter(getActivity(), mList);
        mGridView.setAdapter(mSpecialGridAdapter);
    }
}

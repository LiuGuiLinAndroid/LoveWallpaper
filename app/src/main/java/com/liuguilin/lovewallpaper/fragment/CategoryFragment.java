package com.liuguilin.lovewallpaper.fragment;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.fragment
 *  文件名:   CategoryFragment
 *  创建者:   LGL
 *  创建时间:  2017/1/10 9:48
 *  描述：    分类
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.activity.CategoryActivity;
import com.liuguilin.lovewallpaper.adapter.CategoryListAdapter;
import com.liuguilin.lovewallpaper.imp.ApiImp;
import com.liuguilin.lovewallpaper.model.CategoryModel;
import com.liuguilin.lovewallpaper.model.WallpaperApiModel;
import com.liuguilin.lovewallpaper.utils.L;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryFragment extends Fragment {

    private ApiImp apiImp;

    private ListView mListView;
    private CategoryListAdapter mCategoryListAdapter;
    private List<CategoryModel> mList = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mListView = (ListView) view.findViewById(R.id.mListView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("name",mList.get(i).getName());
                intent.putExtra("url",mList.get(i).getUrl());
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://open.lovebizhi.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiImp = retrofit.create(ApiImp.class);
        Call<WallpaperApiModel> call = apiImp.getWallpaperApi();
        //请求数据
        call.enqueue(new Callback<WallpaperApiModel>() {
            @Override
            public void onResponse(Call<WallpaperApiModel> call, Response<WallpaperApiModel> response) {
                if (response.isSuccessful()) {
                    parsingJson(response.body().getCategory());
                }
            }

            @Override
            public void onFailure(Call<WallpaperApiModel> call, Throwable t) {
                L.i(t.toString());
            }
        });
    }

    //解析
    private void parsingJson(List<WallpaperApiModel.CategoryBean> category) {
        for (int i = 0; i < category.size(); i++) {
            CategoryModel model = new CategoryModel();
            model.setName(category.get(i).getName());
            model.setCover(category.get(i).getCover());
            model.setUrl(category.get(i).getUrl());
            mList.add(model);
        }
        mCategoryListAdapter = new CategoryListAdapter(getActivity(), mList);
        mListView.setAdapter(mCategoryListAdapter);
    }
}

package com.liuguilin.lovewallpaper.fragment;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.fragment
 *  文件名:   RecommendFragment
 *  创建者:   LGL
 *  创建时间:  2017/1/10 10:18
 *  描述：    推荐
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;
import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.activity.GalleryActivity;
import com.liuguilin.lovewallpaper.adapter.CategoryDataAdapter;
import com.liuguilin.lovewallpaper.model.ApiModel;
import com.liuguilin.lovewallpaper.model.CategoryDataModel;
import com.liuguilin.lovewallpaper.model.CategoryGridModel;
import com.liuguilin.lovewallpaper.utils.L;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends Fragment {

    private String nextUrl;
    private GridView mGridView;
    private LinearLayout ll_load_more;

    private CategoryDataAdapter mCategoryDataAdapter;
    private List<CategoryGridModel> mList = new ArrayList<>();
    private ArrayList<String>mListBigUrl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mGridView = (GridView) view.findViewById(R.id.mGridView);
        ll_load_more = (LinearLayout) view.findViewById(R.id.ll_load_more);

        if (!TextUtils.isEmpty(ApiModel.recommend)) {
            getData(ApiModel.recommend);
        }

        //监听滑动到最后
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (mGridView.getLastVisiblePosition() == mGridView.getCount() - 1) {
                        ll_load_more.setVisibility(View.VISIBLE);
                        loadData();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

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

    //加载数据
    private void loadData() {
        RxVolley.get(nextUrl, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                ll_load_more.setVisibility(View.GONE);
                parsingJson(t);
                mCategoryDataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
            }
        });
    }

    //解析
    private void getData(String url) {
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                parsingJson(t);
                mCategoryDataAdapter = new CategoryDataAdapter(getActivity(), mList);
                mGridView.setAdapter(mCategoryDataAdapter);
            }

            @Override
            public void onFailure(VolleyError error) {
                L.i(error.toString());
            }
        });
    }

    private void parsingJson(String t) {
        Gson gson = new Gson();
        CategoryDataModel model = gson.fromJson(t, CategoryDataModel.class);
        nextUrl = model.getLink().getNext();
        for (int i = 0; i < model.getData().size(); i++) {
            CategoryGridModel models = new CategoryGridModel();
            models.setSmall(model.getData().get(i).getSmall());
            models.setDown_stat(model.getData().get(i).getDown_stat());
            models.setDown(model.getData().get(i).getDown());
            models.setBig(model.getData().get(i).getBig());
            mListBigUrl.add(model.getData().get(i).getBig());
            models.setKey(model.getData().get(i).getKey());
            mList.add(models);
        }
    }
}

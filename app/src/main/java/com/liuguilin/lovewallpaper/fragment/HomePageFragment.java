package com.liuguilin.lovewallpaper.fragment;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.fragment
 *  文件名:   HomePageFragment
 *  创建者:   LGL
 *  创建时间:  2017/1/10 9:37
 *  描述：    首页
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.activity.SpecialActivity;
import com.liuguilin.lovewallpaper.adapter.MainGridAdapter;
import com.liuguilin.lovewallpaper.imp.ApiImp;
import com.liuguilin.lovewallpaper.model.ApiModel;
import com.liuguilin.lovewallpaper.model.MainGridModel;
import com.liuguilin.lovewallpaper.model.WallpaperApiModel;
import com.liuguilin.lovewallpaper.utils.GlideUtils;
import com.liuguilin.lovewallpaper.utils.L;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageFragment extends Fragment {

    private ApiImp apiImp;
    private ViewPager mViewPager;
    private GridView mGridView;
    private List<View> mView = new ArrayList<>();
    private List<MainGridModel> mList = new ArrayList<>();
    private List<String> mListUrl = new ArrayList<>();
    private List<String> mListTitle = new ArrayList<>();
    private MainGridAdapter mainGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);
        mGridView = (GridView) view.findViewById(R.id.mGridView);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),SpecialActivity.class);
                intent.putExtra("url",mListUrl.get(i));
                intent.putExtra("name",mListTitle.get(i));
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
                L.i("Retrofit onResponse");
                if (response.isSuccessful()) {
                    ApiModel model = new ApiModel();
                    model.setRanking(response.body().getRanking());
                    model.setBanner(response.body().getBanner());
                    model.setWallpaper(response.body().getWallpaper());
                    model.setRecommend(response.body().getRecommend());
                    parsingBanner(response.body().getSpecial());
                    parsingEveryDay(response.body().getEveryday());
                }
            }

            @Override
            public void onFailure(Call<WallpaperApiModel> call, Throwable t) {
                L.i(t.toString());
            }
        });
    }

    //解析每天
    private void parsingEveryDay(List<WallpaperApiModel.EverydayBean> everyday) {
        for (int i = 0; i < everyday.size(); i++) {
            MainGridModel model = new MainGridModel();
            model.setTime(everyday.get(i).getDate());
            model.setUrl(everyday.get(i).getImage());
            mListUrl.add(everyday.get(i).getUrl());
            mListTitle.add(everyday.get(i).getDate());
            mList.add(model);
        }
        mainGridAdapter = new MainGridAdapter(getActivity(), mList);
        mGridView.setAdapter(mainGridAdapter);
    }

    //解析标题
    private void parsingBanner(final List<WallpaperApiModel.SpecialBean> special) {
        for (int i = 0; i < special.size(); i++) {
            View view = View.inflate(getActivity(), R.layout.fragment_home_pager_banner_item, null);
            ImageView iv_banner_icon = (ImageView) view.findViewById(R.id.iv_banner_icon);
            GlideUtils.loadImageCrop(getActivity(), special.get(i).getIcon(), iv_banner_icon);
            TextView tv_banner_title = (TextView) view.findViewById(R.id.tv_banner_title);
            TextView tv_banner_content = (TextView) view.findViewById(R.id.tv_banner_content);
            tv_banner_title.setText(special.get(i).getName());
            tv_banner_content.setText(special.get(i).getDesc());
            RelativeLayout rl_banner = (RelativeLayout) view.findViewById(R.id.rl_banner);
            final int finalI = i;
            rl_banner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), SpecialActivity.class);
                    intent.putExtra("name", special.get(finalI).getName());
                    intent.putExtra("desc", special.get(finalI).getDesc());
                    intent.putExtra("icon", special.get(finalI).getIcon());
                    intent.putExtra("url", special.get(finalI).getUrl());
                    startActivity(intent);

                }
            });
            mView.add(view);
        }
        mViewPager.setAdapter(new BannerAdapter());
    }

    private class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mView.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(mView.get(position));
            return mView.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mView.get(position));
            //super.destroyItem(container, position, object);
        }
    }
}

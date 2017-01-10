package com.liuguilin.lovewallpaper.fragment;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.fragment
 *  文件名:   NewsFragment
 *  创建者:   LGL
 *  创建时间:  2017/1/10 18:07
 *  描述：    新闻
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.entity.Constants;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {

    //TabLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Fragment
    private List<Fragment> mFragment = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mTabLayout = (TabLayout) view.findViewById(R.id.mTabLayout);
        //设置模式
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);

        //test
        mFragment.add(new NewsPageFragment());
        mFragment.add(new NewsPageFragment());
        mFragment.add(new NewsPageFragment());
        mFragment.add(new NewsPageFragment());
        mFragment.add(new NewsPageFragment());
        mFragment.add(new NewsPageFragment());
        mFragment.add(new NewsPageFragment());
        mFragment.add(new NewsPageFragment());
        mFragment.add(new NewsPageFragment());
        mFragment.add(new NewsPageFragment());
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:

                        break;
                    case 1:

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return Constants.NEWS_TYPE_ZH[position];
            }
        });

        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }
}

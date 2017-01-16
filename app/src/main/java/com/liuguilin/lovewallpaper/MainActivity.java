package com.liuguilin.lovewallpaper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.liuguilin.lovewallpaper.activity.SettingActivity;
import com.liuguilin.lovewallpaper.fragment.AlbumFragment;
import com.liuguilin.lovewallpaper.fragment.CategoryFragment;
import com.liuguilin.lovewallpaper.fragment.HomePageFragment;
import com.liuguilin.lovewallpaper.fragment.NewsFragment;
import com.liuguilin.lovewallpaper.fragment.RankingFragment;
import com.liuguilin.lovewallpaper.fragment.RecommendFragment;
import com.liuguilin.lovewallpaper.fragment.WallpaperFragment;
import com.liuguilin.lovewallpaper.fragment.WeatherFragment;
import com.liuguilin.lovewallpaper.view.CustomDialog;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private HomePageFragment homePageFragment;
    private CategoryFragment categoryFragment;
    private RankingFragment rankingFragment;
    private WallpaperFragment wallpaperFragment;
    private RecommendFragment recommendFragment;
    private NewsFragment newsFragment;
    private WeatherFragment weatherFragment;
    private AlbumFragment albumFragment;

    private CustomDialog dialogShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {

        dialogShare = new CustomDialog(this, 0, 0, R.layout.dialog_share_item, R.style.Theme_dialog, Gravity.BOTTOM, R.style.pop_anim_style);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initHomePager();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home_page:
                initHomePager();
                break;
            case R.id.nav_category:
                initCategory();
                break;
            case R.id.nav_ranking:
                initRanking();
                break;
            case R.id.nav_wallpaper:
                initWallpaper();
                break;
            case R.id.nav_recommend:
                initRecommend();
                break;
            case R.id.nav_weather:
                initWeather();
                break;
            case R.id.nav_album:
                inintAlbum();
                break;
            case R.id.nav_news:
                initNews();
                break;
            case R.id.nav_share:
                drawer.closeDrawer(GravityCompat.START);
                dialogShare.show();
                break;
            case R.id.nav_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //取消提示框
            new AlertDialog.Builder(this)
                    .setMessage("是否退出应用？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //首页
    private void initHomePager() {
        getSupportActionBar().setTitle(getString(R.string.app_name));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (homePageFragment == null) {
            homePageFragment = new HomePageFragment();
            transaction.add(R.id.main_frame_layout, homePageFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(homePageFragment);
        transaction.commit();
    }

    //分类
    private void initCategory() {
        getSupportActionBar().setTitle(getString(R.string.text_category));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (categoryFragment == null) {
            categoryFragment = new CategoryFragment();
            transaction.add(R.id.main_frame_layout, categoryFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(categoryFragment);
        transaction.commit();
    }

    //排名
    private void initRanking() {
        getSupportActionBar().setTitle(getString(R.string.text_ranking));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (rankingFragment == null) {
            rankingFragment = new RankingFragment();
            transaction.add(R.id.main_frame_layout, rankingFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(rankingFragment);
        transaction.commit();
    }

    //壁纸
    private void initWallpaper() {
        getSupportActionBar().setTitle(getString(R.string.text_wallpaper));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (wallpaperFragment == null) {
            wallpaperFragment = new WallpaperFragment();
            transaction.add(R.id.main_frame_layout, wallpaperFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(wallpaperFragment);
        transaction.commit();
    }

    //推荐
    private void initRecommend() {
        getSupportActionBar().setTitle(getString(R.string.text_recommend));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (recommendFragment == null) {
            recommendFragment = new RecommendFragment();
            transaction.add(R.id.main_frame_layout, recommendFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(recommendFragment);
        transaction.commit();
    }

    //天气
    private void initWeather() {
        getSupportActionBar().setTitle(getString(R.string.text_weather));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (weatherFragment == null) {
            weatherFragment = new WeatherFragment();
            transaction.add(R.id.main_frame_layout, weatherFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(weatherFragment);
        transaction.commit();
    }

    //新闻
    private void initNews() {
        getSupportActionBar().setTitle(getString(R.string.text_news));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (newsFragment == null) {
            newsFragment = new NewsFragment();
            transaction.add(R.id.main_frame_layout, newsFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(newsFragment);
        transaction.commit();
    }

    //相册
    private void inintAlbum() {
        getSupportActionBar().setTitle(getString(R.string.text_album));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (albumFragment == null) {
            albumFragment = new AlbumFragment();
            transaction.add(R.id.main_frame_layout, albumFragment);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(albumFragment);
        transaction.commit();
    }

    //隐藏所有Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (homePageFragment != null) {
            transaction.hide(homePageFragment);
        }
        if (categoryFragment != null) {
            transaction.hide(categoryFragment);
        }
        if (rankingFragment != null) {
            transaction.hide(rankingFragment);
        }
        if (wallpaperFragment != null) {
            transaction.hide(wallpaperFragment);
        }
        if (recommendFragment != null) {
            transaction.hide(recommendFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (weatherFragment != null) {
            transaction.hide(weatherFragment);
        }
        if (albumFragment != null) {
            transaction.hide(albumFragment);
        }

    }
}

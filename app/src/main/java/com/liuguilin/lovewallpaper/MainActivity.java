package com.liuguilin.lovewallpaper;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.liuguilin.lovewallpaper.fragment.CategoryFragment;
import com.liuguilin.lovewallpaper.fragment.HomePageFragment;
import com.liuguilin.lovewallpaper.fragment.RankingFragment;
import com.liuguilin.lovewallpaper.fragment.RecommendFragment;
import com.liuguilin.lovewallpaper.fragment.WallpaperFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private HomePageFragment homePageFragment;
    private CategoryFragment categoryFragment;
    private RankingFragment rankingFragment;
    private WallpaperFragment wallpaperFragment;
    private RecommendFragment recommendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
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
            case R.id.nav_share:
                Toast.makeText(this, "Share App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Toast.makeText(this, "Setting App", Toast.LENGTH_SHORT).show();
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
            super.onBackPressed();
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

    }
}

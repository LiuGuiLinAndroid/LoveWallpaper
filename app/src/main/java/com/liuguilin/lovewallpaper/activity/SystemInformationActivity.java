package com.liuguilin.lovewallpaper.activity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.activity
 *  文件名:   SystemInformationActivity
 *  创建者:   LGL
 *  创建时间:  2017/1/19 13:36
 *  描述：    系统信息
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.entity.Constants;
import com.liuguilin.lovewallpaper.utils.ScreenUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemInformationActivity extends BaseActivity {

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_information);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermission();
        }else {
            initData();
            initView();
        }
    }

    //初始化数据
    @SuppressLint("NewApi")
    private void initData() {


        mList.add("IP:" + Constants.getPhoneIp(this));
        mList.add("MAC地址:" + Constants.getMacAddress(this));
        mList.add("SD卡存储:" + Constants.getSdAvailableMemory());
        mList.add("设备分辨率:" + ScreenUtils.getInstance(this).getDisplayMetrics(this));
        mList.add("网络状态:" + Constants.getNetworkState(this));
        mList.add("内存状况:" + "可用内存:" + Constants.getAvailMemory(this) + "\n" + "总内存:" + Constants.getTotalMemory(this));
        Constants.getCpuInfo();
        mList.add("CPU:" + "CPU型号:" + Constants.cpuInfo[0] + "\n" + "CPU频率:" + Constants.cpuInfo[1]);

        mList.add("IMEI:" + Constants.getImei(this));

        mList.add("主板:" + Build.BOARD);
        mList.add("Android系统定制商:" + Build.BRAND);
        String[] mStrAbis = Build.SUPPORTED_ABIS;
        StringBuffer abis = new StringBuffer();

        for (int i = 0; i < mStrAbis.length; i++) {
            abis.append(mStrAbis[i]);
        }
        mList.add("CPU指令集:" + abis.toString());

        mList.add("设备参数:" + Build.DEVICE);
        mList.add("显示屏参数:" + Build.DISPLAY);
        mList.add("唯一编号:" + Build.FINGERPRINT);
        mList.add("硬件序列号:" + Build.SERIAL);
        mList.add("硬件序列号:" + Build.SERIAL);
        mList.add("修订版本列表:" + Build.ID);
        mList.add("硬件制造商:" + Build.MANUFACTURER);
        mList.add("版本:" + Build.MODEL);
        mList.add("硬件名:" + Build.HARDWARE);
        mList.add("手机产品名:" + Build.PRODUCT);
        mList.add("Build的标签:" + Build.TAGS);
        mList.add("Builder类型:" + Build.TYPE);
        mList.add("开发代号:" + Build.VERSION.CODENAME);
        mList.add("源码控制版本号:" + Build.VERSION.INCREMENTAL);
        mList.add("版本字符串:" + Build.VERSION.RELEASE);
        mList.add("版本号:" + Build.VERSION.SDK_INT);
        mList.add("Host:" + Build.HOST);
        mList.add("User名:" + Build.USER);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mList.add("编译时间:" + sdf.format(new Date(Long.parseLong(String.valueOf(Build.TIME)))));

        mList.add("OS版本：" + System.getProperty("os.version"));
        mList.add("OS名称：" + System.getProperty("os.name"));
        mList.add("OS架构：" + System.getProperty("os.arch"));
        mList.add("Home：" + System.getProperty("user.home"));
        mList.add("Name：" + System.getProperty("user.name"));
        mList.add("Dir：" + System.getProperty("user.dir"));
        mList.add("时区：" + System.getProperty("user.timezone"));
        mList.add("路径分隔符：" + System.getProperty("path.separator"));
        mList.add("行分隔符：" + System.getProperty("line.separator"));
        mList.add("文件分隔符：" + System.getProperty("file.separator"));

        mList.add("Java vender Url：" + System.getProperty("java.vendor.ur"));
        mList.add("Java Class：" + System.getProperty("java.class.path"));
        mList.add("Java Class版本：" + System.getProperty("java.class.version"));
        mList.add("Java Vender：" + System.getProperty("java.vendor"));
        mList.add("Java版本：" + System.getProperty("java.version"));
        mList.add("Java Home：" + System.getProperty("java.home"));
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);
    }

    //获取手机状态权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 102);
        } else {
            initData();
            initView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 102:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initData();
                    initView();
                } else {
                    Toast.makeText(this, "请打开读/写权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}

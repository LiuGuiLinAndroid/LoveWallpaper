package com.liuguilin.lovewallpaper.entity;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.entity
 *  文件名:   Constants
 *  创建者:   LGL
 *  创建时间:  2017/1/9 17:16
 *  描述：    常量类
 */

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.Gravity;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.activity.WebViewActivity;
import com.liuguilin.lovewallpaper.utils.L;
import com.liuguilin.lovewallpaper.view.CustomDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Constants {

    //返回码
    public static final int REQUEST_CODE = 101;

    //第一次启动
    public static final int HANDLER_FIRST_START = 1000;
    //无限轮播
    public static final int HANDLER_AUTO_SHUFFLING = 1001;
    //下拉刷新
    public static final int HANDLER_REFRESH = 1002;
    //停止动画
    public static final int HANDLER_STOP_ANIMATION = 1003;

    public static final String WALLPAPER_BASE_URL = "http://open.lovebizhi.com/";
    public static final String WEATHER_BASE_URL = "https://api.thinkpage.cn/";

    //视频地址
    public static final String IMOOC_VIDEO_URL = "http://szv1.mukewang.com/5876eb5be520e572618b458b/H.mp4";
    //图片地址
    public static final String IMOOC_IMAGE_URL = "http://szimg.mukewang.com/5876eedd0001d20909000500.jpg";

    //QQ下载地址
    public static final String URL_DOWNLOAD_QQ = "http://app.sina.cn/appdetail.php?appID=100928";
    //微博下载地址
    public static final String URL_DOWNLOAD_SINA = "http://app.sina.cn/appdetail.php?appID=84560";
    //微信下载地址
    public static final String URL_DOWNLOAD_WECHAT = "http://app.sina.cn/appdetail.php?appID=93134";

    //Blog
    public static final String BLOG = "http://blog.csdn.net/qq_26787115";
    //Github
    public static final String GITHUB = "https://github.com/LiuGuiLinAndroid";
    //项目 Github
    public static final String LOVE_WALLPAPER_GITHUB = "https://github.com/LiuGuiLinAndroid/LoveWallpaper";

    //心知天气key
    public static final String THINKPAPE_KEY = "cjfbaiq6lln0oqk1";

    public static String shareText = "我在Github上找到一款非常棒的软件:" + Constants.LOVE_WALLPAPER_GITHUB;

    //生活指数图片
    public static final int WEATHER_LIFE_ICON[] = {R.drawable.icon_car_washing, R.drawable.icon_dressing
            , R.drawable.icon_flu, R.drawable.icon_sport, R.drawable.icon_travel, R.drawable.icon_uv};

    //设置图标
    public static final int SETTING_ICON[] = {R.drawable.icon_setting_msg, R.drawable.icon_clear
            , R.drawable.icon_version, R.drawable.icon_git, R.drawable.icon_about};

    //城市字母

    //天气图标
    public static final int WEATHER_ICON[] =
            {R.drawable.w0, R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4
                    , R.drawable.w5, R.drawable.w6, R.drawable.w7, R.drawable.w8, R.drawable.w9
                    , R.drawable.w10, R.drawable.w11, R.drawable.w12, R.drawable.w13, R.drawable.w14
                    , R.drawable.w15, R.drawable.w16, R.drawable.w17, R.drawable.w18, R.drawable.w19
                    , R.drawable.w20, R.drawable.w21, R.drawable.w22, R.drawable.w23, R.drawable.w24
                    , R.drawable.w25, R.drawable.w26, R.drawable.w27, R.drawable.w28, R.drawable.w29
                    , R.drawable.w30, R.drawable.w31, R.drawable.w32, R.drawable.w33, R.drawable.w34
                    , R.drawable.w35, R.drawable.w36, R.drawable.w37, R.drawable.w38, R.drawable.w99};

    //封装dialog
    public static CustomDialog showDialog(Context mContext, int layout) {
        //初始化提示框
        CustomDialog dialog = new CustomDialog(mContext, 0, 0,
                layout, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        return dialog;
    }

    //获取版本号
    public static String getAppVersion(Context mContext) {
        String version = "";
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
            version = info.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            version = "获取失败";
        }
        return version;
    }

    //跳转网页
    public static void startWebView(Context mContext, String title, String url) {
        Intent intent2 = new Intent(mContext, WebViewActivity.class);
        intent2.putExtra("title", title);
        intent2.putExtra("url", url);
        mContext.startActivity(intent2);
    }

    //获取手机IP
    public static String getPhoneIp(Context mContext) {
        // 获取wifi服务
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    //地址算法
    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }

    //获取MAC地址
    public static String getMacAddress(Context mContext) {
        WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    //获取内存卡可用内存
    public static String getSdAvailableMemory() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStorageDirectory();
            StatFs statfs = new StatFs(path.getPath());
            // 获取block的SIZE
            long blocSize = statfs.getBlockSize();
            // 获取BLOCK数量
            long totalBlocks = statfs.getBlockCount();
            // 空闲的Block的数量
            long availaBlock = statfs.getAvailableBlocks();
            // 计算总空间大小和空闲的空间大小
            long availableSize = blocSize * availaBlock;
            long allSize = blocSize * totalBlocks;
            return "可用：" + availableSize / 1024 / 1024 / 1024 + "GB"
                    + "  总共：" + allSize / 1024 / 1024 / 1024 + "GB";
        } else {
            return "SD卡不可用";
        }
    }

    //获取当前网络状态
    public static String getNetworkState(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            return "无网络连接";
        } else {
            return "网络正常";
        }
    }

    //获得可用内存
    public static String getAvailMemory(Context mContext) {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // mi.availMem; 当前系统的可用内存
        return Formatter.formatFileSize(mContext, mi.availMem);
    }

    //获取总内存
    public static String getTotalMemory(Context mContext) {
        // 系统内存信息文件
        String str1 = "/proc/meminfo";
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            // 读取meminfo第一行，系统总内存大小
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            // 获得系统总内存，单位是KB，乘以1024转换为Byte
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
            localBufferedReader.close();

        } catch (IOException e) {
            L.e("error:" + e.toString());
        }
        // Byte转换为KB或者MB，内存大小规格化
        return Formatter.formatFileSize(mContext, initial_memory);
    }

    // 1-cpu型号 2-cpu频率
    public static String[] cpuInfo = {"", ""};

    //获取CPU属性
    public static void getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
            L.i("error:" + e.toString());
        }
    }

    //获取IMEI
    public static String getImei(Context mContext) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) mContext.
                getSystemService(Context.TELEPHONY_SERVICE);
        String imei = mTelephonyMgr.getDeviceId();
        return imei;
    }

    //系统分享
    public static void intentSystemShare(Context mContext, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        mContext.startActivity(intent);
    }

    //跳转QQ 可指定好友
    public static void intentStartQQ(final Context mContext, String text) {
        if (isInstall(mContext, "com.tencent.mobileqq")) {
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=100000&version=1";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            mContext.startActivity(intent);
        } else {
            showNoInstallDialog(mContext, mContext.getString(R.string.is_install_qq), URL_DOWNLOAD_QQ);
        }
    }

    //跳转微博 可分享图片等
    public static void intentStartSina(final Context mContext, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        PackageManager pm = mContext.getPackageManager();
        List<ResolveInfo> matches = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String packageName = "com.sina.weibo";
        ResolveInfo info = null;
        for (ResolveInfo each : matches) {
            String pkgName = each.activityInfo.applicationInfo.packageName;
            if (packageName.equals(pkgName)) {
                info = each;
                break;
            }
        }
        if (info == null) {
            showNoInstallDialog(mContext, mContext.getString(R.string.is_install_sina), URL_DOWNLOAD_SINA);
        } else {
            intent.setClassName(packageName, info.activityInfo.name);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            mContext.startActivity(intent);
        }
    }

    //跳转到微信
    public static void intentStartWechat(Context mContext, String text) {
        if (isInstall(mContext, "com.tencent.mm")) {
            Intent intent = new Intent();
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
            intent.setAction(Intent.ACTION_VIEW);
            mContext.startActivity(intent);
        } else {
            showNoInstallDialog(mContext, mContext.getString(R.string.is_install_wechat), URL_DOWNLOAD_WECHAT);
        }
    }

    //判断程序是否安装
    public static boolean isInstall(Context mContext, String packageName) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        PackageManager pm = mContext.getPackageManager();
        List<ResolveInfo> matches = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        ResolveInfo info = null;
        for (ResolveInfo each : matches) {
            String pkgName = each.activityInfo.applicationInfo.packageName;
            if (packageName.equals(pkgName)) {
                info = each;
                break;
            }
        }
        if (info == null) {
            return false;
        } else {
            return true;
        }
    }

    //提示未安装
    public static void showNoInstallDialog(final Context mContext, String message, final String url) {
        new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri uri = Uri.parse(url);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                mContext.startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }

    //读取assets文件
    public static String getFromAssets(Context mContext, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(mContext.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            return "";
        }
    }

    /****************
     * 发起添加群流程。群号：通往Android的神奇之旅(555974449) 的 key 为： WKsVihQjloOtstvRIXUWxU2M4QRKUwO0
     * 调用 joinQQGroup(WKsVihQjloOtstvRIXUWxU2M4QRKUwO0) 即可发起手Q客户端申请加群 通往Android的神奇之旅(555974449)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public static void joinQQGroup(Context mContext, String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            showNoInstallDialog(mContext, mContext.getString(R.string.is_install_qq), URL_DOWNLOAD_QQ);
        }
    }

}

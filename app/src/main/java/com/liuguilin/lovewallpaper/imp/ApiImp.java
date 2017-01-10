package com.liuguilin.lovewallpaper.imp;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.imp
 *  文件名:   ApiImp
 *  创建者:   LGL
 *  创建时间:  2017/1/9 17:37
 *  描述：    api接口
 */

import com.liuguilin.lovewallpaper.model.WallpaperApiModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiImp {

    //获取爱壁纸接口
    @GET("baidu_rom.php")
    Call<WallpaperApiModel>getWallpaperApi();

}

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
import com.liuguilin.lovewallpaper.model.WeatherApiModel;
import com.liuguilin.lovewallpaper.model.WeatherEveryDayApiModel;
import com.liuguilin.lovewallpaper.model.WeatherLifeApiModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiImp {

    //获取爱壁纸接口
    @GET("baidu_rom.php")
    Call<WallpaperApiModel> getWallpaperApi();

    //获取心知天气
    @GET("v3/weather/now.json?")
    Call<WeatherApiModel> getWeatherApi(@Query("key") String key, @Query("location") String city);

    //获取未来三天的天气
    @GET("v3/weather/daily.json?")
    Call<WeatherEveryDayApiModel> getWeatherEveryDayApi(@Query("key") String key, @Query("location") String city);

    //获取生活指数
    @GET("v3/life/suggestion.json?")
    Call<WeatherLifeApiModel> getWeatherLifeApi(@Query("key") String key, @Query("location") String city);

}

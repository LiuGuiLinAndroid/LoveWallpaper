package com.liuguilin.lovewallpaper.fragment;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.fragment
 *  文件名:   WeatherFragment
 *  创建者:   LGL
 *  创建时间:  2017/1/10 18:08
 *  描述：    天气
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.entity.Constants;
import com.liuguilin.lovewallpaper.imp.ApiImp;
import com.liuguilin.lovewallpaper.model.WeatherApiModel;
import com.liuguilin.lovewallpaper.model.WeatherEveryDayApiModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherFragment extends Fragment {

    private ApiImp apiImp;
    private TextView tv_temperature;
    private ImageView iv_weather_icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        tv_temperature = (TextView) view.findViewById(R.id.tv_temperature);
        iv_weather_icon = (ImageView) view.findViewById(R.id.iv_weather_icon);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiImp = retrofit.create(ApiImp.class);

        getWeather("深圳");
        getWeatherEveryDay("深圳");
    }

    //获取城市天气
    private void getWeather(String city) {
        Call<WeatherApiModel> call = apiImp.getWeatherApi(Constants.THINKPAPE_KEY, city);
        call.enqueue(new Callback<WeatherApiModel>() {
            @Override
            public void onResponse(Call<WeatherApiModel> call, Response<WeatherApiModel> response) {
                if (response.isSuccessful()) {
                    tv_temperature.setText(response.body().getResults().get(0).getNow().getTemperature() + "℃");
                    iv_weather_icon.setBackgroundResource(Constants.WEATHER_ICON[Integer.parseInt(response.body().getResults().get(0).getNow().getCode())]);
                }
            }

            @Override
            public void onFailure(Call<WeatherApiModel> call, Throwable t) {

            }
        });
    }

    //获取未来三天的天气
    private void getWeatherEveryDay(String city) {
        Call<WeatherEveryDayApiModel> call = apiImp.getWeatherEveryDayApi(Constants.THINKPAPE_KEY, city);
        call.enqueue(new Callback<WeatherEveryDayApiModel>() {
            @Override
            public void onResponse(Call<WeatherEveryDayApiModel> call, Response<WeatherEveryDayApiModel> response) {
                if(response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<WeatherEveryDayApiModel> call, Throwable t) {

            }
        });
    }
}

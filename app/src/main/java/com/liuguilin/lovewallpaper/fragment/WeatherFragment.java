package com.liuguilin.lovewallpaper.fragment;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.fragment
 *  文件名:   WeatherFragment
 *  创建者:   LGL
 *  创建时间:  2017/1/10 18:08
 *  描述：    天气
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.activity.CityActivity;
import com.liuguilin.lovewallpaper.adapter.WeatherGradAdapter;
import com.liuguilin.lovewallpaper.entity.Constants;
import com.liuguilin.lovewallpaper.imp.ApiImp;
import com.liuguilin.lovewallpaper.model.WeatherApiModel;
import com.liuguilin.lovewallpaper.model.WeatherEveryDayApiModel;
import com.liuguilin.lovewallpaper.model.WeatherGridModel;
import com.liuguilin.lovewallpaper.model.WeatherLifeApiModel;
import com.liuguilin.lovewallpaper.utils.L;
import com.liuguilin.lovewallpaper.utils.SharePreUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherFragment extends Fragment implements View.OnClickListener {

    private ApiImp apiImp;
    private TextView tv_temperature;
    private ImageView iv_weather_icon;
    private TextView tv_city;
    private TextView tv_date;
    private GridView mGridViewEveryDay;
    private String weatherCity;

    private List<WeatherGridModel> mList = new ArrayList<>();
    private WeatherGradAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        weatherCity = SharePreUtils.getString(getActivity(), "city", "深圳");

        mGridViewEveryDay = (GridView) view.findViewById(R.id.mGridViewEveryDay);
        adapter = new WeatherGradAdapter(getActivity(), mList);
        mGridViewEveryDay.setAdapter(adapter);

        tv_temperature = (TextView) view.findViewById(R.id.tv_temperature);
        iv_weather_icon = (ImageView) view.findViewById(R.id.iv_weather_icon);
        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_city.setOnClickListener(this);
        tv_city.setText(weatherCity);
        tv_date = (TextView) view.findViewById(R.id.tv_date);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiImp = retrofit.create(ApiImp.class);

        //获取天气
        getBaseWeather(weatherCity);
    }

    private void getBaseWeather(String city) {
        getWeather(city);
        getWeatherLife(city);
    }

    //获取城市天气
    private void getWeather(String city) {
        Call<WeatherApiModel> call = apiImp.getWeatherApi(Constants.THINKPAPE_KEY, city);
        call.enqueue(new Callback<WeatherApiModel>() {
            @Override
            public void onResponse(Call<WeatherApiModel> call, Response<WeatherApiModel> response) {
                if (response.isSuccessful()) {
                    tv_temperature.setText(response.body().getResults().get(0).getNow().getTemperature() + "℃"
                            + " | " + response.body().getResults().get(0).getNow().getText());
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
                if (response.isSuccessful()) {
                    parsingEveryDay(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<WeatherEveryDayApiModel> call, Throwable t) {

            }
        });
    }

    //获取生活指数
    private void getWeatherLife(final String city) {
        Call<WeatherLifeApiModel> call = apiImp.getWeatherLifeApi(Constants.THINKPAPE_KEY, city);
        call.enqueue(new Callback<WeatherLifeApiModel>() {
            @Override
            public void onResponse(Call<WeatherLifeApiModel> call, Response<WeatherLifeApiModel> response) {
                if (response.isSuccessful()) {
                    getWeatherEveryDay(city);
                    parsingLift(response.body().getResults().get(0).getSuggestion());
                }
            }

            @Override
            public void onFailure(Call<WeatherLifeApiModel> call, Throwable t) {

            }
        });
    }

    //生活指数
    private void parsingLift(WeatherLifeApiModel.ResultsBean.SuggestionBean suggestion) {
        addListImage("洗车" + suggestion.getCar_washing().getBrief());
        addListImage("穿衣" + suggestion.getDressing().getBrief());
        addListImage("感冒" + suggestion.getFlu().getBrief());
        addListImage("运动" + suggestion.getSport().getBrief());
        addListImage("旅游" + suggestion.getTravel().getBrief());
        addListImage("紫外线" + suggestion.getUv().getBrief());
    }

    //未来三天
    private void parsingEveryDay(List<WeatherEveryDayApiModel.ResultsBean> results) {
        for (int i = 0; i < results.get(0).getDaily().size(); i++) {
            String data = results.get(0).getDaily().get(i).getDate();
            //设置时间
            tv_date.setText(results.get(0).getDaily().get(0).getDate());
            String text_day = results.get(0).getDaily().get(i).getText_day();
            String text_night = results.get(0).getDaily().get(i).getText_night();
            String high = results.get(0).getDaily().get(i).getHigh();
            String low = results.get(0).getDaily().get(i).getLow();
            String wind_direction = results.get(0).getDaily().get(i).getWind_direction();
            String wind_direction_degree = results.get(0).getDaily().get(i).getWind_direction_degree();
            String wind_speed = results.get(0).getDaily().get(i).getWind_speed();
            String wind_scale = results.get(0).getDaily().get(i).getWind_scale();

            addListText(data + "\n" + "白天天气:" + text_day + "\n"
                    + "夜间天气:" + text_night + "\n" + "最高温度:" + high + "\n"
                    + "最低温度:" + low + "\n" + "风向:" + wind_direction + "\n"
                    + "风向角度:" + wind_direction_degree + "\n" + "风速:" + wind_speed + "\n"
                    + "风力:" + wind_scale, Integer.parseInt(results.get(0).getDaily().get(i).getCode_day()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                Intent intent = new Intent(getActivity(), CityActivity.class);
                startActivityForResult(intent, Constants.REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE) {
            try {
                String result = data.getStringExtra("city_name");
                if (!TextUtils.isEmpty(result)) {
                    mList.clear();
                    tv_city.setText(result);
                    getBaseWeather(result);
                }
            } catch (NullPointerException e) {
                L.i("back key");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //文字
    private void addListText(String text, int code) {
        WeatherGridModel models = new WeatherGridModel();
        models.setType(WeatherGradAdapter.VALUE_TEXT);
        models.setText(text);
        models.setCode(code);
        mList.add(models);
        adapter.notifyDataSetChanged();
    }

    //图片
    private void addListImage(String text) {
        WeatherGridModel models = new WeatherGridModel();
        models.setType(WeatherGradAdapter.VALUE_IMAGE);
        models.setText(text);
        mList.add(models);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharePreUtils.putString(getActivity(), "city", tv_city.getText().toString());
    }
}

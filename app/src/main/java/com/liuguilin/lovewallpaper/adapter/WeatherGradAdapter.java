package com.liuguilin.lovewallpaper.adapter;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.adapter
 *  文件名:   WeatherGradAdapter
 *  创建者:   LGL
 *  创建时间:  2017/1/11 19:50
 *  描述：    天气列表
 */

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.entity.Constants;
import com.liuguilin.lovewallpaper.model.WeatherGridModel;
import com.liuguilin.lovewallpaper.utils.L;

import java.util.List;

public class WeatherGradAdapter extends BaseAdapter {

    public static final int VALUE_TEXT = 1;
    public static final int VALUE_IMAGE = 2;

    private Context mContext;
    private LayoutInflater mInflater;
    private List<WeatherGridModel> mList;
    private WeatherGridModel model;

    public WeatherGradAdapter(Context mContext, List<WeatherGridModel> mList) {
        this.mContext = mContext;
        this.mList = mList;

        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        model = mList.get(i);
        int type = getItemViewType(i);
        ViewHolderText viewHolderText = null;
        ViewHolderImage viewHolderImage = null;
        if (view == null) {
            switch (type) {
                case VALUE_TEXT:
                    viewHolderText = new ViewHolderText();
                    view = mInflater.inflate(R.layout.fragment_weather_text, null);
                    viewHolderText.tv_weather_text = (TextView) view.findViewById(R.id.tv_weather_text);
                    viewHolderText.iv_weather_text_image = (ImageView) view.findViewById(R.id.iv_weather_text_image);
                    view.setTag(viewHolderText);
                    break;
                case VALUE_IMAGE:
                    viewHolderImage = new ViewHolderImage();
                    view = mInflater.inflate(R.layout.fragment_weather_image, null);
                    viewHolderImage.iv_weather_image = (ImageView) view.findViewById(R.id.iv_weather_image);
                    viewHolderImage.tv_weather_image_text = (TextView) view.findViewById(R.id.tv_weather_image_text);
                    view.setTag(viewHolderImage);
                    break;
            }
        } else {
            switch (type) {
                case VALUE_TEXT:
                    viewHolderText = (ViewHolderText) view.getTag();
                    break;
                case VALUE_IMAGE:
                    viewHolderImage = (ViewHolderImage) view.getTag();
                    break;
            }
        }

        switch (type) {
            case VALUE_TEXT:
                viewHolderText.tv_weather_text.setText(model.getText());
                if (!TextUtils.isEmpty(model.getText())) {
                    viewHolderText.iv_weather_text_image.setBackgroundResource(Constants.WEATHER_ICON[model.getCode()]);
                }
                break;
            case VALUE_IMAGE:
                String text = model.getText();
                viewHolderImage.tv_weather_image_text.setText(text);
                if (text.startsWith("洗车")) {
                    viewHolderImage.iv_weather_image.setBackgroundResource(Constants.WEATHER_LIFE_ICON[0]);
                } else if (text.startsWith("穿衣")) {
                    viewHolderImage.iv_weather_image.setBackgroundResource(Constants.WEATHER_LIFE_ICON[1]);
                } else if (text.startsWith("感冒")) {
                    viewHolderImage.iv_weather_image.setBackgroundResource(Constants.WEATHER_LIFE_ICON[2]);
                } else if (text.startsWith("运动")) {
                    viewHolderImage.iv_weather_image.setBackgroundResource(Constants.WEATHER_LIFE_ICON[3]);
                } else if (text.startsWith("旅游")) {
                    viewHolderImage.iv_weather_image.setBackgroundResource(Constants.WEATHER_LIFE_ICON[4]);
                } else if (text.startsWith("紫外线")) {
                    viewHolderImage.iv_weather_image.setBackgroundResource(Constants.WEATHER_LIFE_ICON[5]);
                }
                break;
        }

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        WeatherGridModel model = mList.get(position);
        int type = model.getType();
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    class ViewHolderText {
        private TextView tv_weather_text;
        private ImageView iv_weather_text_image;
    }

    class ViewHolderImage {
        private TextView tv_weather_image_text;
        private ImageView iv_weather_image;
    }
}

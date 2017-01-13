package com.liuguilin.lovewallpaper.view;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.view
 *  文件名:   CalculateGridView
 *  创建者:   LGL
 *  创建时间:  2017/1/13 16:52
 *  描述：    重新计算高度
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class CalculateGridView extends GridView {

    public CalculateGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalculateGridView(Context context) {
        super(context);
    }

    public CalculateGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

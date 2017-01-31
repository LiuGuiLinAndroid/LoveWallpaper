package com.liuguilin.lovewallpaper.view;
/*
 *  项目名：  LoveWallpaper 
 *  包名：    com.liuguilin.lovewallpaper.view
 *  文件名:   PuzzleGameView
 *  创建者:   LGL
 *  创建时间:  2017/1/21 11:50
 *  描述：    拼图游戏
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.liuguilin.lovewallpaper.R;
import com.liuguilin.lovewallpaper.model.ImagePiece;
import com.liuguilin.lovewallpaper.utils.ImageSplitterUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PuzzleGameView extends RelativeLayout implements View.OnClickListener {

    //默认3*3
    private int mColumn = 3;
    //容器的内边距
    private int mPadding;
    //小图的距离 dp
    private int mMagin = 3;
    //存储图片的，宽高 都是固定的，所以使用数组
    private ImageView[] mGameOintuItems;
    //宽度
    private int mItemWidth;
    //图片
    private Bitmap mBitmap;
    //切图后存储
    private List<ImagePiece> mItemBitmaps;
    //标记
    private boolean once;

    //记录时间
    private int mTime;

    //容器的一个宽度
    private int mWidth;

    //判断游戏是否成功
    private boolean isGameSuccess;

    //是否显示时间
    private boolean isTimeEnabled = false;

    /**
     * 动画层，覆盖在viewGroup中
     */
    private RelativeLayout mAnimLayout;

    private boolean isGameOver;
    /**
     * 动画限制
     */
    private boolean isAniming;

    private static final int TIME_CHANGED = 10;
    private static final int NEXT_LEVEL = 11;

    /**
     * 设置接口回调
     *
     * @param mListener
     */
    public void setOnGamemListener(GamePintuListener mListener) {
        this.mListener = mListener;
    }

    public GamePintuListener mListener;

    /**
     * 关数
     */
    private int level = 1;

    /**
     * 设置开启时间
     *
     * @param timeEnabled
     */
    public void setTimeEnabled(boolean timeEnabled) {
        isTimeEnabled = timeEnabled;
    }

    //接口
    public interface GamePintuListener {
        //关卡
        void nextLevel(int nextLevel);

        //时间
        void timechanged(int time);

        //游戏结束
        void gameOver();
    }

    //子线程操作
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TIME_CHANGED:
                    if (isGameSuccess || isGameOver || isPause) {
                        return;
                    }

                    if (mListener != null) {
                        mListener.timechanged(mTime);
                        if (mTime == 0) {
                            isGameOver = true;
                            mListener.gameOver();
                            return;
                        }
                    }
                    mTime--;
                    handler.sendEmptyMessageDelayed(TIME_CHANGED, 1000);

                    break;
                case NEXT_LEVEL:
                    level = level + 1;
                    if (mListener != null) {
                        mListener.nextLevel(level);
                    } else {
                        nextLevel();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public PuzzleGameView(Context context) {
        this(context, null);
    }

    public PuzzleGameView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PuzzleGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //单位转换——dp-px
        mMagin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
        mPadding = min(getPaddingLeft(), getPaddingRight(), getPaddingTop(), getPaddingBottom());
    }

    /**
     * 确定当前布局的大小，我们要设置成正方形
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //拿到容器的高宽最小值
        mWidth = Math.min(getMeasuredHeight(), getMeasuredWidth());

        if (!once) {
            //进行切图和排序
            initBitmap();
            //设置imageview(item)的宽高等属性
            initItem();
            //根据关卡设置时间
            checkTimeEnable();
            once = true;
        }
        setMeasuredDimension(mWidth, mWidth);
    }

    /**
     * 是否显示时间
     */
    private void checkTimeEnable() {
        //如果我们开启了
        if (isTimeEnabled) {
            countTimeBaseLevel();
            handler.sendEmptyMessage(TIME_CHANGED);
        }
    }

    /**
     * 根据当前等级设置时间
     */
    private void countTimeBaseLevel() {
        mTime = (int) Math.pow(2, level) * 60;
    }

    /**
     * 进行切图和排序
     */
    private void initBitmap() {
        //判断是否存在这张图片
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splash_bg);
        }
        //进行裁剪
        mItemBitmaps = ImageSplitterUtil.splitImage(mBitmap, mColumn);
        //裁剪玩后需要进行顺序打乱sort
        Collections.sort(mItemBitmaps, new Comparator<ImagePiece>() {
            @Override
            public int compare(ImagePiece lhs, ImagePiece rhs) {
                //生成随机数，如果》0.5返回1否则返回-1
                return Math.random() > 0.5 ? 1 : -1;
            }
        });

    }

    /**
     * 设置imageview(item)的宽高等属性
     */
    private void initItem() {
        //（ 容器的宽度 - 内边距 * 2  - 间距  ） /  裁剪的数量
        mItemWidth = (mWidth - mPadding * 2 - mMagin * (mColumn - 1)) / mColumn;
        //几 * 几
        mGameOintuItems = new ImageView[mColumn * mColumn];

        //开始排放
        for (int i = 0; i < mGameOintuItems.length; i++) {
            ImageView item = new ImageView(getContext());
            item.setOnClickListener(this);
            //设置图片
            item.setImageBitmap(mItemBitmaps.get(i).getBitmap());
            //保存
            mGameOintuItems[i] = item;
            //设置ID
            item.setId(i + 1);
            //设置Tag
            item.setTag(i + "_" + mItemBitmaps.get(i).getIndex());

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(mItemWidth, mItemWidth);

            //判断不是最后一列
            if (i + 1 % mColumn != 0) {
                lp.rightMargin = mMagin;
            }

            //判断不是第一列
            if (i % mColumn != 0) {
                lp.addRule(RelativeLayout.RIGHT_OF, mGameOintuItems[i - 1].getId());
            }

            //判断如果不是第一行
            if ((i + 1) > mColumn) {
                lp.topMargin = mMagin;
                lp.addRule(RelativeLayout.BELOW, mGameOintuItems[i - mColumn].getId());
            }
            addView(item, lp);
        }
    }

    /**
     * 获取多个参数的最小值
     */
    private int min(int... params) {
        int min = params[0];
        //遍历
        for (int param : params) {
            if (param < min) {
                min = param;
            }
        }
        return min;
    }

    /**
     * 点击的第一张图和第二张图，他们进行交换
     */
    private ImageView mFirst;
    private ImageView mSecond;

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        //如果点击了一次，你还点击，则无效
        if (isAniming) {
            return;
        }

        //重复点击
        if (mFirst == v) {
            //去掉颜色
            mFirst.setColorFilter(null);
            mFirst = null;
            return;
        }

        if (mFirst == null) {
            mFirst = (ImageView) v;
            //设置选中效果
            mFirst.setColorFilter(Color.parseColor("#55FF0000"));
            //第二次点击
        } else {
            mSecond = (ImageView) v;
            //交换
            exchangeView();
        }
    }

    /**
     * 图片交换
     */
    private void exchangeView() {
        mFirst.setColorFilter(null);
        // 构造我们的动画层
        setUpAnimLayout();

        ImageView first = new ImageView(getContext());
        final Bitmap firstBitmap = mItemBitmaps.get(
                getImageIdByTag((String) mFirst.getTag())).getBitmap();
        first.setImageBitmap(firstBitmap);
        LayoutParams lp = new LayoutParams(mItemWidth, mItemWidth);
        lp.leftMargin = mFirst.getLeft() - mPadding;
        lp.topMargin = mFirst.getTop() - mPadding;
        first.setLayoutParams(lp);
        mAnimLayout.addView(first);

        ImageView second = new ImageView(getContext());
        final Bitmap secondBitmap = mItemBitmaps.get(
                getImageIdByTag((String) mSecond.getTag())).getBitmap();
        second.setImageBitmap(secondBitmap);
        LayoutParams lp2 = new LayoutParams(mItemWidth, mItemWidth);
        lp2.leftMargin = mSecond.getLeft() - mPadding;
        lp2.topMargin = mSecond.getTop() - mPadding;
        second.setLayoutParams(lp2);
        mAnimLayout.addView(second);

        // 设置动画
        TranslateAnimation anim = new TranslateAnimation(0, mSecond.getLeft()
                - mFirst.getLeft(), 0, mSecond.getTop() - mFirst.getTop());
        anim.setDuration(300);
        anim.setFillAfter(true);
        first.startAnimation(anim);

        TranslateAnimation animSecond = new TranslateAnimation(0,
                -mSecond.getLeft() + mFirst.getLeft(), 0, -mSecond.getTop()
                + mFirst.getTop());
        animSecond.setDuration(300);
        animSecond.setFillAfter(true);
        second.startAnimation(animSecond);

        // 监听动画
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mFirst.setVisibility(View.INVISIBLE);
                mSecond.setVisibility(View.INVISIBLE);

                isAniming = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String firstTag = (String) mFirst.getTag();
                String secondTag = (String) mSecond.getTag();
                mFirst.setImageBitmap(secondBitmap);
                mSecond.setImageBitmap(firstBitmap);
                mFirst.setTag(secondTag);
                mSecond.setTag(firstTag);
                mFirst.setVisibility(View.VISIBLE);
                mSecond.setVisibility(View.VISIBLE);
                mFirst = mSecond = null;
                mAnimLayout.removeAllViews();
                //每次移动完成判断是否过关
                checkSuccess();
                isAniming = false;
            }
        });
    }

    /**
     * 判断是否过关
     */
    private void checkSuccess() {
        boolean isSuccess = true;

        for (int i = 0; i < mGameOintuItems.length; i++) {
            //拿到所有的图片
            ImageView imageView = mGameOintuItems[i];
            if (getImageIndex((String) imageView.getTag()) != i) {
                isSuccess = false;
            }
        }
        if (isSuccess) {

            isGameSuccess = true;

            handler.removeMessages(TIME_CHANGED);
            Toast.makeText(getContext(), "成功,进入下一关！", Toast.LENGTH_LONG).show();
            handler.sendEmptyMessage(NEXT_LEVEL);

        }
    }


    /**
     * 获取tag
     *
     * @param tag
     * @return
     */
    public int getImageIdByTag(String tag) {
        String[] split = tag.split("_");
        return Integer.parseInt(split[0]);
    }

    /**
     * 获取图片的tag
     *
     * @param tag
     * @return
     */
    public int getImageIndex(String tag) {
        String[] split = tag.split("_");
        return Integer.parseInt(split[1]);
    }

    /**
     * 交互动画
     */
    private void setUpAnimLayout() {
        if (mAnimLayout == null) {
            mAnimLayout = new RelativeLayout(getContext());
            //添加到整体
            addView(mAnimLayout);
        }
    }

    /**
     * 下一关
     */
    public void nextLevel() {
        this.removeAllViews();
        mAnimLayout = null;
        mColumn++;
        isGameSuccess = false;
        checkTimeEnable();
        initBitmap();
        initItem();
    }

    /**
     * 重新开始
     */
    public void restartGame() {
        isGameOver = false;
        mColumn--;
        nextLevel();

    }

    //暂停状态
    private boolean isPause;

    /**
     * 暂停
     */
    public void pauseGame() {
        isPause = true;
        handler.removeMessages(TIME_CHANGED);
    }


    /**
     * 恢复
     */
    public void resumeGame() {
        if (isPause) {
            isPause = false;
            handler.sendEmptyMessage(TIME_CHANGED);
        }
    }

}

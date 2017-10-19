package com.pamo.nativemall.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.pamo.nativemall.R;

/**
 * Created by wangdesheng on 2017/10/18 0018.
 */

public class WaveView extends View {

    // 波纹颜色
    private static final int WAVE_PAINT_COLOR_BULE = 0xffffffff;
    private static final int WAVE_PAINT_COLOR_TRANSPANT = 0x0fFFFFFF;
    // 正弦曲线震动的幅度
    private static final float STRETCH_FACTOR_A = 35;
    private static final int OFFSET_Y = 0;
    // 第一条水波移动速度
    private static final int TRANSLATE_X_SPEED_ONE = 7;
    // 第二条水波移动速度
    private static final int TRANSLATE_X_SPEED_TWO = 5;
    private float mCycleFactorW;

    private int mTotalWidth, mTotalHeight;
    private float[] mYPositions;
    private float[] mResetOneYPositions;
    private float[] mResetTwoYPositions;
    private float[] mResetThreePositions;
    private int mXOffsetSpeedOne;
    private int mXOffsetSpeedTwo;
    private int mXOneOffset;
    private int mXTwoOffset;
    private int mXThreeOffset;

    //非透明画笔
    private Paint mWavePaint;
    //透明画笔
    private Paint mTransPaint;
    private DrawFilter mDrawFilter;

    //自定义的颜色
    int color;
    int background_color;

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        mXOffsetSpeedOne = UIUtils.dipToPx(context, TRANSLATE_X_SPEED_ONE);
//        mXOffsetSpeedTwo = UIUtils.dipToPx(context, TRANSLATE_X_SPEED_TWO);

        //获取自定属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.wave);
        color = ta.getColor(R.styleable.wave_wave_color, 0);
        background_color = ta.getColor(R.styleable.wave_background_color, 0);
        setBackgroundColor(background_color);
        ta.recycle();

        //初始化绘制波纹的非透明画笔
        mWavePaint = new Paint();
        //设置画笔为无锯齿
        mWavePaint.setAntiAlias(true);
        //设置画笔为填充
        mWavePaint.setStyle(Paint.Style.FILL);
        //设置画笔颜色为蓝色
        mWavePaint.setColor(color);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);

        //初始化绘制波纹的透明画笔
        mTransPaint = new Paint();
        //设置为没有锯齿
        mTransPaint.setAntiAlias(true);
        //设置为填充
        mTransPaint.setStyle(Paint.Style.FILL);
        //设置画笔颜色为半透明
        mTransPaint.setColor(WAVE_PAINT_COLOR_TRANSPANT);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //去除绘制锯齿
        canvas.setDrawFilter(mDrawFilter);
        resetPositionY();
        for (int i = 0; i < mTotalWidth; i++){
            //canvas.drawLine("起点的x轴坐标"，"起点的Y轴坐标", "终点的X轴坐标", "终点的Y轴坐标", "颜料Paint");
            canvas.drawLine(i, mTotalHeight - mResetOneYPositions[i] - 60, i, mTotalHeight, mWavePaint);
            canvas.drawLine(i, mTotalHeight - mResetTwoYPositions[i] - 100, i, mTotalHeight, mTransPaint);
            canvas.drawLine(i, mTotalHeight - mResetThreePositions[i] - 100, i, mTotalHeight, mTransPaint);
        }

        //改变两条波纹的移动点
        mXOneOffset += 15;
        mXTwoOffset += 20;
        mXThreeOffset += 25;

        //如果已经移到结尾处，就重头记录
        if (mXOneOffset >= mTotalWidth){
            mXOneOffset = 0;
        }
        if (mXTwoOffset >= mTotalWidth){
            mXTwoOffset = 0;
        }
        if (mXThreeOffset >= mTotalWidth){
            mXThreeOffset = 0;
        }
        //引发重绘， 一般考虑延迟20~30ms重新绘制，空出时间片
        postInvalidate();
    }

    private void resetPositionY() {
        //mXOneOffset代表当前第一条波纹要移动的距离
        int yOneInterval = mYPositions.length - mXOneOffset;
        //使用System.arraycopy方式重新填充第一条波纹的数据
        System.arraycopy(mYPositions, mXOneOffset, mResetOneYPositions, 0, yOneInterval);
        System.arraycopy(mYPositions, 0, mResetOneYPositions, yOneInterval, mXOneOffset);

        //第二条曲线
        int yTwoInterval = mYPositions.length - mXTwoOffset;
        System.arraycopy(mYPositions, mXTwoOffset, mResetTwoYPositions, 0, yTwoInterval);
        System.arraycopy(mYPositions, 0, mResetTwoYPositions, yTwoInterval, mXTwoOffset);

        //第一条曲线
        int yThreeInterVal = mYPositions.length - mXThreeOffset;
        System.arraycopy(mYPositions, mXThreeOffset, mResetThreePositions, 0, yThreeInterVal);
        System.arraycopy(mYPositions, 0, mResetThreePositions, yThreeInterVal, mXThreeOffset);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // 记录下view的宽高
        mTotalWidth = w;
        mTotalHeight = h;
        // 用于保存原始波纹的y值
        mYPositions = new float[mTotalWidth];
        // 用于保存波纹一的y值
        mResetOneYPositions = new float[mTotalWidth];
        // 用于保存波纹二的y值
        mResetTwoYPositions = new float[mTotalWidth];
        // 用于保存波纹三的y值
        mResetThreePositions = new float[mTotalWidth];
        // 将周期定为view总宽度
        mCycleFactorW = (float) (2 * Math.PI / mTotalWidth);

        // 根据view总宽度得出所有对应的y值
        for (int i = 0; i < mTotalWidth; i++) {
            mYPositions[i] = (float) (STRETCH_FACTOR_A * Math.sin(mCycleFactorW * i) + OFFSET_Y);
        }
    }
}

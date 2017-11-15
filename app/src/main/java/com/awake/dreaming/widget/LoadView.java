package com.awake.dreaming.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.awake.dreaming.R;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by wangdesheng on 2017/11/14 0014.
 */

public class LoadView extends View {

    private Canvas mCanvas;
    private Paint mTextPaint;
    private Rect mTextRect;
    private Paint mCirclePaint;
    private float mCircleStrokeWidth = 2;
    private RectF mCircleRectF;
    private Paint mScaleArcPaint;
    private RectF mScaleArcRectF;
    private Paint mScaleLinePaint;
    private Paint mHourHandPaint;
    private Paint mMinuteHandPaint;
    private Paint mSecondHandPaint;
    private Path mHourHandPath;
    private Path mMinuteHandPath;
    private Path mSecondHandPath;
    private int mLightColor;
    private int mDarkColor;
    private int mBackgroundColor;
    private float mTextSize;
    private float mRadius;
    private float mScaleLength;
    private float mHourDegree;
    private float mMinuteDegree;
    private float mSecondDegree;

    private float mDefaultPadding;
    private float mPaddingLeft;
    private float mPaddingTop;
    private float mPaddingRight;
    private float mPaddingBottom;

    private SweepGradient mSweepGradient;
    private Matrix mGradientMatrix;
    private Matrix mCameraMatrix;
    private Camera mCamera;
    private float mCameraRotateX;
    private float mCameraRotateY;
    private float mMaxCameraRotate = 10;
    private float mCanvasTranslateX;
    private float mCanvasTranslateY;
    private float mMaxCanvasTranslate;
    private ValueAnimator mShakeAnim;

    public LoadView(Context context) {
        this(context, null);
    }

    public LoadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadView, defStyleAttr, 0);
        mBackgroundColor = ta.getColor(R.styleable.LoadView_backgroundColor, Color.parseColor("#237EAD"));
        setBackgroundColor(mBackgroundColor);
        mLightColor = ta.getColor(R.styleable.LoadView_lightColor, Color.parseColor("#ffffff"));
        mDarkColor = ta.getColor(R.styleable.LoadView_darkColor, Color.parseColor("#80ffffff"));
        mTextSize = ta.getDimension(R.styleable.LoadView_textSize, 0);
        ta.recycle();

        /*时针画笔*/
        mHourHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHourHandPaint.setStyle(Paint.Style.FILL);
        mHourHandPaint.setColor(mDarkColor);

        /*分针画笔*/
        mMinuteHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMinuteHandPaint.setColor(mLightColor);

        /*秒针画笔*/
        mSecondHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondHandPaint.setStyle(Paint.Style.FILL);
        mSecondHandPaint.setColor(mLightColor);

        /*刻度线画笔*/
        mScaleLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScaleLinePaint.setStyle(Paint.Style.STROKE);
        mScaleLinePaint.setColor(mBackgroundColor);

        /*刻度圆弧画笔*/
        mScaleArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScaleArcPaint.setStyle(Paint.Style.STROKE);

        /*小时文本画笔*/
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(mDarkColor);
        mTextPaint.setTextSize(mTextSize);

        /*小时圆圈画笔*/
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);
        mCirclePaint.setColor(Color.WHITE);

        mTextRect = new Rect();
        mCircleRectF = new RectF();
        mScaleArcRectF = new RectF();
        mHourHandPath = new Path();
        mMinuteHandPath = new Path();
        mSecondHandPath = new Path();

        mGradientMatrix = new Matrix();
        mCameraMatrix = new Matrix();
        mCamera = new Camera();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureDimension(widthMeasureSpec), measureDimension(heightMeasureSpec));
    }

    private int measureDimension(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY){
            result = size;
        }else {
            result = 800;
            if (mode == MeasureSpec.AT_MOST){
                result = Math.min(result, size);
            }
        }
        return result;
    }
}

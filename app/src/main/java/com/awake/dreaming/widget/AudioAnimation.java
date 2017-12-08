package com.awake.dreaming.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangdesheng on 2017/12/4 0004.
 */

public class AudioAnimation extends View {
    public AudioAnimation(Context context) {
        super(context);
    }

    public AudioAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AudioAnimation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}

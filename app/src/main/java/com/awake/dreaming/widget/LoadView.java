package com.awake.dreaming.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangdesheng on 2017/11/14 0014.
 */

public class LoadView extends View {

    Paint paint;
    Path mSecondHandPath;

    public LoadView(Context context) {
        super(context);
    }

    public LoadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#bebebe"));
        paint.setStrokeWidth(10);

        mSecondHandPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < 12; i++){
            canvas.drawLine(getWidth()/2, getHeight()/2-30, getWidth()/2, getHeight()/2 - getHeight()/10, paint);
            canvas.rotate(30, getWidth()/2, getHeight()/2);
        }
    }
}

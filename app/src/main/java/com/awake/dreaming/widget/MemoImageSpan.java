package com.awake.dreaming.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.style.ImageSpan;

/**
 *
 * Created by wangdesheng on 2017/11/27 0027.
 */

public class MemoImageSpan extends ImageSpan {

    private Context context;
    private int resourceId;

    public MemoImageSpan(Context context, @DrawableRes int resourceId, int verticalAlignment) {
        super(context, resourceId, verticalAlignment);
        this.context = context;
        this.resourceId = resourceId;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        //return super.getSize(paint, text, start, end, fm);
        Drawable d = getDrawable();
        Rect rect = d.getBounds();
        if (fm != null) {
            Paint.FontMetricsInt fmPaint=paint.getFontMetricsInt();
            //获得文字、图片高度
            int fontHeight = fmPaint.bottom - fmPaint.top;
            int drHeight=rect.bottom-rect.top;
            int top= drHeight/2 - fontHeight/4;
            int bottom=drHeight/2 + fontHeight/4;

            fm.ascent=-bottom;
            fm.top=-bottom;
            fm.bottom=top;
            fm.descent=top;
        }
        return rect.right;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();
        int transY = 0;
        //获得将要显示的文本高度-图片高度除2等居中位置+top(换行情况)
        transY = ((bottom-top) - b.getBounds().bottom)/2+top;
        //偏移画布后开始绘制
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}

package com.awake.dreaming.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;

/**
 *
 * Created by wangdesheng on 2017/11/23 0023.
 */

public class MemoEditText extends android.support.v7.widget.AppCompatEditText {
    public MemoEditText(Context context) {
        super(context);
    }

    public MemoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MemoEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void insertPic(Context context, int id) {
//        SpannableString ss = new SpannableString("123456");
//        Drawable drawable = getResources().getDrawable(id);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        MemoImageSpan img = new MemoImageSpan(context, id, 1);
//        ss.setSpan(img, 0, "123456".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        append(ss);
    }

    public void insert(Bitmap bitmap) {
        SpannableString ss = new SpannableString("123");
        ImageSpan span = new ImageSpan(bitmap);
        ss.setSpan(span, 0, "123".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        append(ss);
    }
}
package com.awake.dreaming.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;

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

    public void insert(Bitmap bitmap) {
        int index = getSelectionStart();
        SpannableString ss = new SpannableString("     \n");
        ImageSpan span = new ImageSpan(bitmap);
        ss.setSpan(span, 2, "     \n".length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        if (getText().equals("") || getText() == null) {
            append(ss);
        }else {
            Log.e("TAG", "insert: ----------------------------------------------------" );
            Log.e("TAG", "insert: ----------------------------------------------------" );
            Log.e("TAG", "insert: ----------------------------------------------------" );
            Log.e("TAG", "insert: ----------------------------------------------------" );
            getEditableText().insert(index, ss);
        }
    }
}
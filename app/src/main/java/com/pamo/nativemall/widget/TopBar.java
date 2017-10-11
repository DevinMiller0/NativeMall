package com.pamo.nativemall.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pamo.nativemall.R;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class TopBar extends RelativeLayout{

    public ImageView titleBack;
    public TextView titleText;

    public String title;
    public float titleSize;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(final Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        title = ta.getString(R.styleable.TopBar_titleText);
        titleSize = ta.getFloat(R.styleable.TopBar_titleSize, 17);
        ta.recycle();

        View.inflate(context, R.layout.layout_topbar, this);
        titleText = findViewById(R.id.topbar_title);
        titleBack = findViewById(R.id.topbar_back);

        titleText.setText(title);
        titleText.setTextSize(titleSize);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}

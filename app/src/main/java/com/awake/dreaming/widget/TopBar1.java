package com.awake.dreaming.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.awake.dreaming.R;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class TopBar1 extends RelativeLayout{

    public RelativeLayout parent;
    public ImageView titleBack;
    public TextView titleText;

    public String title;
    public float titleSize;
    public int background;

    public TopBar1(Context context) {
        super(context);
    }

    public TopBar1(final Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar1);
        title = ta.getString(R.styleable.TopBar1_titleText);
        titleSize = ta.getFloat(R.styleable.TopBar1_titleSize, 17);
        ta.recycle();

        View.inflate(context, R.layout.layout_topbar, this);
        titleText = findViewById(R.id.topbar_title);
        titleBack = findViewById(R.id.topbar_back);
        parent = findViewById(R.id.parent_layout);

        titleText.setText(title);
        titleText.setTextSize(titleSize);
    }

    public TopBar1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}

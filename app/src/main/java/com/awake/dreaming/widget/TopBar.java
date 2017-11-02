package com.awake.dreaming.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awake.dreaming.R;


/**
 * Created by wangdesheng on 2017/10/27 0027.
 */

public class TopBar extends LinearLayout {

    private ImageView imgBack;
    private TextView tvTitle;
    private ImageView imgMore;

    private OnBackClickListener backClickListener;
    private OnMoreClickListener moreClickListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.topBar);
        String title = ta.getString(R.styleable.topBar_title_text);
        float size = ta.getFloat(R.styleable.topBar_title_size, 18);
        ta.recycle();

        View.inflate(context, R.layout.layout_top_bar, this);
        LinearLayout parent = findViewById(R.id.ll_parent);
        imgBack = findViewById(R.id.img_back);
        tvTitle = findViewById(R.id.tv_title);
        imgMore = findViewById(R.id.img_more);

        tvTitle.setText(title);
        tvTitle.setTextSize(size);
        imgBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                backClickListener.backClick(view);
            }
        });
        imgMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                moreClickListener.moreClick(view);
            }
        });
    }

    public TopBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImgBack(int i){
        imgBack.setImageResource(i);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitleSize(float i){
        tvTitle.setTextSize(i);
    }

    public void setImgMore(int i){
        imgMore.setImageResource(i);
    }

    public void setOnBackClickListener(OnBackClickListener backClickListener){
        this.backClickListener = backClickListener;
    }

    public void setOnMoreClickListener(OnMoreClickListener moreClickListener){
        this.moreClickListener = moreClickListener;
    }

    public interface OnBackClickListener{
        void backClick(View view);
    }

    public interface OnMoreClickListener {
        void moreClick(View view);
    }

}

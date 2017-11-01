package com.awake.dreaming.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by wangdesheng on 2017/10/23 0023.
 */

public class TabView extends LinearLayout {

    private int mSelectedColor = 0xffff0000;
    private int mNotSelectedColor = ((mSelectedColor >>> 25) << 24) | (mSelectedColor & 0x00ffffff);

    private int mIndicatorColor = 0xff0000ff;

    private LinearLayout mTabsContainer;
    private View mIndicator, mBottomLine;

    private OnTabsItemClickListener listener;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(VERTICAL);

        mTabsContainer = new LinearLayout(getContext());
        mTabsContainer.setOrientation(HORIZONTAL);
        mTabsContainer.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mTabsContainer.setGravity(Gravity.CENTER_HORIZONTAL);
        addView(mTabsContainer);

        mIndicator = new View(getContext());
        mIndicator.setBackgroundColor(mIndicatorColor);
        mIndicator.setLayoutParams(new LayoutParams(200, 8));
        addView(mIndicator);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetIndicator();
    }

    private void resetIndicator() {

        int childCount = mTabsContainer.getChildCount();
        ViewGroup.LayoutParams layoutParams = mIndicator.getLayoutParams();
        if (childCount <= 0){
            layoutParams.width = 0;
        }else {
            layoutParams.width = getWidth() / childCount;
        }
        mIndicator.setLayoutParams(layoutParams);
    }

    public void setTabs(String... titles){
        mTabsContainer.removeAllViews();
        if (titles != null){
            for (int i = 0; i < titles.length; i++){
                TextView textView = new TextView(getContext());
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                textView.setText(titles[i]);
                textView.setClickable(true);
                textView.setPadding(0, 10, 0, 10);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                Toast.makeText(getContext(), titles[i], Toast.LENGTH_SHORT).show();
                textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                textView.setTag(i);
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = (int) view.getTag();
                        setCurrentTab(position, true);
                        if (listener != null){
                            listener.onClick(view, position);
                        }
                    }
                });
                mTabsContainer.addView(textView);
            }
            setCurrentTab(0, false);
            post(new Runnable() {
                @Override
                public void run() {
                    resetIndicator();
                }
            });
        }
    }

    public void setCurrentTab(int position, boolean animator) {
        int childCount = mTabsContainer.getChildCount();
        if (position < 0 || position >= childCount){
            return;
        }

        for (int i = 0; i<childCount; i++){
            TextView childView = (TextView) mTabsContainer.getChildAt(i);
            if (i == position){
                childView.setTextColor(mSelectedColor);
            }else {
                childView.setTextColor(mNotSelectedColor);
            }
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mIndicator, "x", position * mIndicator.getWidth());
        if (animator){
            objectAnimator.setDuration(50).start();
        }else {
            objectAnimator.setDuration(0).start();
        }

    }


    public void setOnTabsItemClickListener(OnTabsItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnTabsItemClickListener {
        void onClick(View view, int position);
    }
}

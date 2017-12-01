package com.awake.dreaming.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awake.dreaming.R;

import static android.content.Context.WINDOW_SERVICE;

/**
 *
 * Created by wangdesheng on 2017/11/23 0023.
 */

public class PicDialog extends Dialog {

    private LinearLayout container;
    private TextView define;
    private TextView cancel;
    private OnDefineClickListener defineClickListener;
    private OnCancelClickListener cancelClickListener;
    private Context context;

    public PicDialog(@NonNull Context context) {
        super(context, R.style.PicDialogTheme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pic_dialog);
        //setCancelable(false);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        define.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defineClickListener.onDefineClick(view);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelClickListener.onCancelListener(view);
            }
        });
    }

    private void initData() {
        WindowManager manager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        container.setLayoutParams(new LinearLayout.LayoutParams(width * 2 / 3, width / 4));
    }

    private void initView() {
        container = (LinearLayout) findViewById(R.id.dialog_container);
        define = (TextView) findViewById(R.id.tv_define);
        cancel = (TextView) findViewById(R.id.tv_cancel);
    }

    public void setOnDefineClickListener(OnDefineClickListener defineClickListener) {
        this.defineClickListener = defineClickListener;
    }

    public void setOnCancelClickListener(OnCancelClickListener cancelClickListener) {
        this.cancelClickListener = cancelClickListener;
    }

    public interface OnDefineClickListener {
        void onDefineClick(View view);
    }

    public interface OnCancelClickListener {
        void onCancelListener(View view);
    }
}

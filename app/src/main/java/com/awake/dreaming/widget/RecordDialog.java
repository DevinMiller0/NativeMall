package com.awake.dreaming.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.awake.dreaming.R;

import static android.content.Context.WINDOW_SERVICE;

/**
 *
 * Created by wangdesheng on 2017/11/29 0029.
 */

public class RecordDialog extends Dialog {

    private Context context;
    private LinearLayout container;
    private LinearLayout topContainer;
    private RelativeLayout editorName;
    private TextView timer;
    private EditText recordName;
    private ImageButton reRecord;
    private ImageButton startRecord;
    private ImageButton close;

    private DialogClickListener listener;

    public RecordDialog(@NonNull Context context) {
        super(context, R.style.PicDialogTheme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_voice_dialog);
        initView();
        initEvent();
    }

    private void initView() {
        container = (LinearLayout) findViewById(R.id.voice_dialog_container);
        topContainer = (LinearLayout) findViewById(R.id.ll_record_name_time);
        recordName = (EditText) findViewById(R.id.et_voice_name);
        timer = (TextView) findViewById(R.id.tv_record_time);
        editorName = (RelativeLayout) findViewById(R.id.rl_record_after);
        reRecord = (ImageButton) findViewById(R.id.ib_rerecord_voice);
        startRecord = (ImageButton) findViewById(R.id.ib_record_start);
        close = (ImageButton) findViewById(R.id.ib_cancel_record);

        WindowManager manager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int width = display.getWidth();
        container.setLayoutParams(new LinearLayout.LayoutParams(width * 3 / 4, width * 3 / 4));

        //RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) topContainer.getLayoutParams();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) topContainer.getLayoutParams();
        params.topMargin = (width * 3 / 64);
    }

    private void initEvent() {
        reRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.reRecordClick(view);
            }
        });

        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.startClick(v);
                TranslateAnimation animation = getAnimation(0.0f, 0.0f, -5.0f, 0.0f);
                editorName.setAnimation(animation);
                editorName.setVisibility(View.VISIBLE);
                TranslateAnimation TimeAnimation = getAnimation(0.0f, 0.0f, -0.7f, 0.0f);
                timer.setAnimation(TimeAnimation);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.closeClick(v);
            }
        });
    }

    public void setOnDialogEvent(DialogClickListener listener) {
        this.listener = listener;
    }

    public interface DialogClickListener {
        void reRecordClick(View view);
        void startClick(View view);
        void closeClick(View view);
    }

    public TranslateAnimation getAnimation(float fromXValue, float toXValue,
                                           float fromYValue, float toYValue ) {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromXValue,
                Animation.RELATIVE_TO_SELF, toXValue, Animation.RELATIVE_TO_SELF, fromYValue,
                Animation.RELATIVE_TO_SELF, toYValue);
        animation.setDuration(500);
        return animation;
    }
}
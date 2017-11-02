package com.awake.dreaming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;

/**
 * Created by wangdesheng on 2017/10/16 0016.
 */

public class LoginActivity extends AppCompatActivity {

    private View bottomSheet;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.animation_x_on, R.anim.animation_x_off);
        StatusBarUtils.transparentStatusBar(this);

        bottomSheet = findViewById(R.id.be);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        button = (Button) findViewById(R.id.btn_bottom);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN){
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }
}

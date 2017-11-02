package com.awake.dreaming.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.awake.dreaming.R;
import com.awake.dreaming.widget.TopBar;

/**
 * Created by 德胜 on 2017/11/2.
 */

public class AddMemoActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_add_memo;
    }

    @Override
    protected void setLayout() {
        initView();
    }

    private void initView() {
        TopBar topBar = (TopBar) findViewById(R.id.top_bar);
        topBar.setImgMore(R.mipmap.memo_keep);
        topBar.setTitle(getString(R.string.memo_top_bar_title));
        topBar.setOnBackClickListener(new TopBar.OnBackClickListener() {
            @Override
            public void backClick(View view) {
                finish();
            }
        });
        topBar.setOnMoreClickListener(new TopBar.OnMoreClickListener() {
            @Override
            public void moreClick(View view) {
                Toast.makeText(AddMemoActivity.this, "keep complete", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.img_memo_camera).setOnClickListener(this);
        findViewById(R.id.img_memo_voice).setOnClickListener(this);
    }

    @Override
    protected void onViewClick(View view) {
        switch (view.getId()){
            case R.id.img_memo_camera:{
                Toast.makeText(this, "open camera", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_memo_voice:{
                Toast.makeText(this, "record voice", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}

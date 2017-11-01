package com.awake.dreaming.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.awake.dreaming.R;
import com.awake.dreaming.adapter.CollectAdapter;
import com.awake.dreaming.utils.StatusBarUtils;
import com.awake.dreaming.widget.TopBar;

/**
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class CollectionActivity extends BaseActivity{

    public TopBar topBar;
    private EditText search;

    private final String TAG = "Collection";

    @Override
    protected int getLayout() {
        return R.layout.activity_collect_store;
    }

    @Override
    protected void setLayout() {
        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    @Override
    protected void onViewClick(View view) {
        switch (view.getId()){
            case R.id.topbar_back:{
                finish();
                break;
            }
        }
    }

    private void initView() {
        topBar = (TopBar) findViewById(R.id.collection_topBar);
        topBar.titleBack.setOnClickListener(this);

        RecyclerView collectList = (RecyclerView) findViewById(R.id.collect_list);
        collectList.setLayoutManager(new LinearLayoutManager(this));
        collectList.setAdapter(new CollectAdapter());

        search = (EditText) findViewById(R.id.et_search_content);
        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                search.setInputType(InputType.TYPE_NULL); // 关闭软键盘
                return false;
            }
        });

        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(search.getWindowToken(), 0);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.animation_x_off);
    }

}

package com.awake.dreaming.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.awake.dreaming.R;
import com.awake.dreaming.adapter.MemoAdapter;
import com.awake.dreaming.datas.MemoDatas;
import com.awake.dreaming.widget.TopBar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by wangdesheng on 2017/11/18 0018.
 */

public class HasMemoActivity extends BaseActivity {

    private ImageView btnAdd;
    private LinearLayout batchManage;
    private MemoAdapter adapter;
    private boolean isEditorial;
    private TranslateAnimation showAnimation;
    private ImageView batchSelectAll;
    //是否全选标记
    private boolean isSelectedAll = false;
    private ArrayList<MemoDatas> list;
    private ArrayList<Integer> delList = new ArrayList<>();

    private final String TAG = "HasMemoActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_has_memo;
    }

    @Override
    protected void setLayout() {
        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            MemoDatas memoDatas = new MemoDatas();
            memoDatas.setDesc("" + i);
            list.add(memoDatas);
        }
        Collections.reverse(list);
        initTopBar();
        initView();
    }

    private void initTopBar() {
        TopBar topBar = (TopBar) findViewById(R.id.topBar);
        topBar.setTitle("备忘录");
        topBar.setOnBackClickListener(new TopBar.OnBackClickListener() {
            @Override
            public void backClick(View view) {
                finish();
            }
        });
        topBar.setOnMoreClickListener(new TopBar.OnMoreClickListener() {
            @Override
            public void moreClick(View view) {

            }
        });
    }

    private void initView() {
        EditText searchMemo = (EditText) findViewById(R.id.et_search_memo);
        ImageView btnSearch = (ImageView) findViewById(R.id.img_search_memo);
        RecyclerView memoList = (RecyclerView) findViewById(R.id.recycler_memo);
        btnAdd = (ImageView) findViewById(R.id.img_add_memo);
        batchManage = (LinearLayout) findViewById(R.id.ll_btn_batch_manager);
        ImageView batchDelete = (ImageView) findViewById(R.id.img_memo_batch_delete);
        batchSelectAll = (ImageView) findViewById(R.id.img_memo_batch_select);

        btnSearch.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        batchDelete.setOnClickListener(this);
        batchSelectAll.setOnClickListener(this);

        memoList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MemoAdapter(HasMemoActivity.this, list);
        memoList.setAdapter(adapter);

        adapter.setOnLongClickListener(new MemoAdapter.LongClickListener() {
            @Override
            public void longClick(int position, View view, CheckBox checkBox) {
                for (int i = 0; i < list.size(); i++) {
                    adapter.visibleCheck.put(i, CheckBox.VISIBLE);
                }

                //添加按钮隐藏动画
                TranslateAnimation animation = getAnimation(0.0f, 2.0f, 0.0f, 0.0f);
                btnAdd.startAnimation(animation);
                btnAdd.setVisibility(View.GONE);

                //批量选择显示动画
                showAnimation = getAnimation(0.0f, 0.0f, 1.0f, 0.0f);

                batchManage.startAnimation(showAnimation);
                batchManage.setVisibility(View.VISIBLE);
                isEditorial = true;
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setOnItemClickListener(new MemoAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, View view, CheckBox checkBox) {
                if (isEditorial && !checkBox.isChecked()) {
                    checkBox.setChecked(true);
                    adapter.isCheck.put(position, true);
                    Log.e(TAG, ".....: " + getChecked() );
                    delList.add(position);
                    Log.e(TAG, "itemClick: " + delList.size() );

                    if (getChecked()) {
                        isSelectedAll = true;
                        batchSelectAll.setImageResource(R.mipmap.memo_select_all);
                    }
                }else if (isEditorial && checkBox.isChecked()){
                    checkBox.setChecked(false);
                    adapter.isCheck.put(position, false);
                    batchSelectAll.setImageResource(R.mipmap.memo_select_all_selected);
                    delList.remove(position);
                    Log.e(TAG, "itemClick: " + delList.size() );
                    Log.e(TAG, ".........: " + getChecked() );
                    if (!getChecked()) {
                        batchSelectAll.setImageResource(R.mipmap.memo_select_all_selected);
                    }
                }
            }
        });

        adapter.setOnCheckChangeListener(new MemoAdapter.OnCheckChangeListener() {
            @Override
            public void checkChange(int position, CompoundButton compoundButton, boolean checked) {

            }
        });
    }

    private boolean getChecked() {
        for (int i = 0; i < list.size(); i++) {
            if (!adapter.isCheck.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isEditorial) {
            for (int i = 0; i < list.size(); i++) {
                adapter.visibleCheck.put(i, CheckBox.GONE);
            }

            //批量选择影藏动画
            TranslateAnimation hiddenAnimation = getAnimation(0.0f, 0.0f, 0.0f, 1.0f);

            batchManage.startAnimation(hiddenAnimation);
            batchManage.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            isEditorial = false;

            //添加按钮显示动画
            TranslateAnimation animation = getAnimation(2.0f, 0.0f, 0.0f, 0.0f);
            btnAdd.startAnimation(animation);
            btnAdd.setVisibility(View.VISIBLE);

        }else {
            finish();
        }
        return true;
    }

    @Override
    protected void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.img_search_memo: {
                break;
            }

            case R.id.img_add_memo: {
                Intent intent = new Intent(HasMemoActivity.this, AddMemoActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.img_memo_batch_delete: {
//                if (delList.size() != 0){
//                    for (int i = 0; i < delList.size(); i++) {
//                        list.remove(1);
//                        Log.e(TAG, "onViewClick: " + delList.get(i) );
//                    }
//                    Log.e(TAG, "onViewClick: " + list.size() );
//
//                    adapter.notifyDataSetChanged();
//                }else {
//                    Toast.makeText(HasMemoActivity.this, "么有选择", Toast.LENGTH_SHORT).show();
//                }
                break;
            }

            case R.id.img_memo_batch_select: {
                if (isSelectedAll) {
                    batchSelectAll.setImageResource(R.mipmap.memo_select_all_selected);
                    isSelectedAll = false;
                    selectedAll(false);
                }else {
                    batchSelectAll.setImageResource(R.mipmap.memo_select_all);
                    isSelectedAll = true;
                    selectedAll(true);
                }
                break;
            }
        }
    }

    public TranslateAnimation getAnimation(float fromXValue, float toXValue,
                                            float fromYValue, float toYValue ) {
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromXValue,
                Animation.RELATIVE_TO_SELF, toXValue, Animation.RELATIVE_TO_SELF, fromYValue,
                Animation.RELATIVE_TO_SELF, toYValue);
        animation.setDuration(500);
        return animation;
    }

    private void selectedAll(boolean isCheck) {
        if (isCheck) {
            for (int i = 0; i < list.size(); i++) {
                adapter.isCheck.put(i, true);
            }
        }else {
            for (int i = 0; i < list.size(); i++) {
                adapter.isCheck.put(i, false);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
package com.awake.dreaming.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

/**
 *Created by wangdesheng on 2017/11/18 0018.
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

    private static final String TAG = "HasMemoActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_has_memo;
    }

    @Override
    protected void setLayout() {
        list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            MemoDatas memoDatas = new MemoDatas();
            memoDatas.setDesc("" + i);
            memoDatas.setChosen(false);
            list.add(memoDatas);
        }
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

        /*
        item long click
         */
        adapter.setOnLongClickListener(new MemoAdapter.LongClickListener() {
            @Override
            public void longClick(int position, View view, CheckBox checkBox) {
                if (!isEditorial) {
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
            }
        });

        /*
        item click
         */
        adapter.setOnItemClickListener(new MemoAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, View view, CheckBox checkBox) {
                //Log.e(TAG, "itemClick: " + position );
                if (isEditorial && !checkBox.isChecked()) {
                    checkBox.setChecked(true);
                    list.get(position).setChosen(true);
                }else if (isEditorial && checkBox.isChecked()){
                    checkBox.setChecked(false);
                    list.get(position).setChosen(false);
                    batchSelectAll.setImageResource(R.mipmap.memo_select_all_selected);
                }
            }
        });

        /*
        CheckBox click event
         */
        adapter.setOnCheckChangeListener(new MemoAdapter.OnCheckChangeListener() {
            @Override
            public void checkChange(int position, CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    list.get(position).setChosen(true);
                    if (getSelectAll()) {
                        batchSelectAll.setImageResource(R.mipmap.memo_select_all);
                    }
                }else {
                    if (position < list.size()) {
                        list.get(position).setChosen(false);
                    }
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isEditorial) {
            for (int i = 0; i < list.size(); i++) {
                //返回时checkBox设置为不可见
                adapter.visibleCheck.put(i, CheckBox.GONE);
                list.get(i).setChosen(false);
            }

            batchSelectAll.setImageResource(R.mipmap.memo_select_all_selected);

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
                int size = list.size();
                if (getSelectAll()) {
                    list.clear();
                    batchSelectAll.setImageResource(R.mipmap.memo_select_all_selected);
                }else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getChosen()) {
                            list.remove(i);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            }

            case R.id.img_memo_batch_select: {
                if (isSelectedAll) {
                    batchSelectAll.setImageResource(R.mipmap.memo_select_all_selected);
                    setNoSelectAll();
                    isSelectedAll = false;
                }else {
                    batchSelectAll.setImageResource(R.mipmap.memo_select_all);
                    setSelectAll();
                    isSelectedAll = true;
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

    /*获取是否是全选状态*/
    private boolean getSelectAll() {
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getChosen()) {
                return false;
            }
        }
        return true;
    }

    /*全选*/
    private void setSelectAll() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChosen(true);
        }
        adapter.notifyDataSetChanged();
    }

    /*全不选*/
    private void setNoSelectAll() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChosen(false);
        }
        adapter.notifyDataSetChanged();
    }
}
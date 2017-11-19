package com.awake.dreaming.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.awake.dreaming.R;
import com.awake.dreaming.adapter.MemoAdapter;
import com.awake.dreaming.widget.TopBar;

/**
 * Created by wangdesheng on 2017/11/18 0018.
 */

public class HasMemoActivity extends BaseActivity {

    private EditText SearchMemo;
    private ImageView btnSearch;
    private RecyclerView memoList;
    private ImageView btnAdd;
    private MemoAdapter adapter;
    private boolean isEditorial;

    @Override
    protected int getLayout() {
        return R.layout.activity_has_memo;
    }

    @Override
    protected void setLayout() {
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
        SearchMemo = (EditText) findViewById(R.id.et_search_memo);
        btnSearch = (ImageView) findViewById(R.id.img_search_memo);
        memoList = (RecyclerView) findViewById(R.id.recycler_memo);
        btnAdd = (ImageView) findViewById(R.id.img_add_memo);

        btnSearch.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        memoList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MemoAdapter(HasMemoActivity.this);
        memoList.setAdapter(adapter);

        adapter.setOnLongClickListener(new MemoAdapter.LongClickListener() {
            @Override
            public void longClick(int position, View view, CheckBox checkBox) {
                for (int i = 0; i < 8; i++) {
                    adapter.visibleCheck.put(i, CheckBox.VISIBLE);
                }
                isEditorial = true;
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setOnItemClickListener(new MemoAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, View view, CheckBox checkBox) {
                if (isEditorial && !checkBox.isChecked()) {
                    checkBox.setChecked(true);
                }else if (isEditorial && checkBox.isChecked()){
                    checkBox.setChecked(false);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isEditorial) {
            for (int i = 0; i < 8; i++) {
                adapter.visibleCheck.put(i, CheckBox.GONE);
            }
            adapter.notifyDataSetChanged();
            isEditorial = false;
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
        }
    }
}
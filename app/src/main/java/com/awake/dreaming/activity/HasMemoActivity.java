package com.awake.dreaming.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        MemoAdapter adapter = new MemoAdapter(HasMemoActivity.this);
        memoList.setAdapter(adapter);
        adapter.setOnLongClickListener(new MemoAdapter.LongClickListener() {
            @Override
            public void longClick(int position, View view, CheckBox checkBox) {
                Toast.makeText(HasMemoActivity.this, "long", Toast.LENGTH_SHORT).show();
                checkBox.setVisibility(View.VISIBLE);
            }
        });

        memoList.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(HasMemoActivity.this, "hahaha", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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
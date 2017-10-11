package com.pamo.nativemall.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pamo.nativemall.R;
import com.pamo.nativemall.adapter.GridAdapter;
import com.pamo.nativemall.utils.StatusBarUtils;
import com.pamo.nativemall.widget.itemDecoration;

public class MainActivity extends AppCompatActivity {

    private RecyclerView gridView;
    private GridAdapter adapter;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    private void initView() {

        gridView = (RecyclerView) findViewById(R.id.recycler_grid);
        gridView.setLayoutManager(new GridLayoutManager(this, 3));
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.recyclerview_divider);
        gridView.addItemDecoration(new itemDecoration(this, 2, drawable));
        adapter = new GridAdapter(this);
        gridView.setAdapter(adapter);

        adapter.setOnItemClickListener(new GridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:{
                        Intent intent = new Intent(MainActivity.this, FootPrintsActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 1:{
                        Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 2:{
                        Intent intent = new Intent(MainActivity.this, BusinessCardActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 3:{
                        Intent intent = new Intent(MainActivity.this, MyCarteActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 4:{
                        Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 5:{
                        Intent intent = new Intent(MainActivity.this, HelpCenterActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 6:{
                        Intent intent = new Intent(MainActivity.this, FeedBackActivity.class);
                        startActivities(intent);
                        break;
                    }
                    case 7:{
                        Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                        startActivities(intent);

                        break;
                    }
                }
            }
        });
    }

    private void startActivities(Intent intent){
        startActivity(intent);
        MainActivity.this.overridePendingTransition(R.anim.animation_x_on, R.anim.animation_alpha);
    }
}

package com.awake.dreaming.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.awake.dreaming.R;
import com.awake.dreaming.adapter.HelpCenterAdapter;
import com.awake.dreaming.datas.HelpCenterData;
import com.awake.dreaming.utils.StatusBarUtils;
import com.awake.dreaming.widget.TopBar1;

import java.util.ArrayList;

/**
 *
 * Created by wangdesheng on 2017/10/10 0010.
 */

public class HelpCenterActivity extends BaseActivity {

    private RecyclerView helpView;
    private ArrayList<HelpCenterData> list;
    private final String TAG = "HelpCenterActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_help_center;
    }

    @Override
    protected void setLayout() {
        StatusBarUtils.transparentStatusBar(this);
        initView();
    }

    private void initView() {
        TopBar1 topBar1 = (TopBar1) findViewById(R.id.help_center_topBar);
        topBar1.titleBack.setOnClickListener(this);
        helpView = (RecyclerView) findViewById(R.id.help_center_list);

        String content[] = {
                "商城导航",
                "1.1获取不到定位怎么办？",
                "1.2要去多个商铺怎么导航？",
                "1.3洗手间服务台等在哪？",
                "1.4怎么把我的位置分享给我的朋友",
                "品类分布",
                "2.1品类分布怎么用？",
                "店铺收藏",
                "3.1怎么收藏店铺？",
                "备忘录",
                "4.1怎么添加一条备忘？",
                "4.2备忘录中怎么插入图片（照片）、视频？",
                "4.3备忘录中怎么插入语音？",
                "4.4怎么导航去备忘录中记录的商铺？",
                "4.5怎么分享备忘录给朋友？",
                "名片夹",
                "5.1怎么保存名片？",
                "5.2怎么导航去名片中的商铺",
                "5.3怎么分享名片给好友",
                "5.4名片分享给好友后，对方能保存名片吗？",
                "足迹",
                "6.1足迹是什么？",
                "中英文切换",
                "7.1怎么进行中英文切换？",
                "其它",
                "8.1如果我换了手机还能看到我的名片夹、备忘录、以及收藏的店铺吗？",
        };

        list = new ArrayList<>();
        int title[] = {0, 5, 7, 9, 15, 20, 22, 24};
        for (int i = 0; i < content.length; i++) {
            HelpCenterData data = new HelpCenterData();
            for (int aTitle : title) {
                if (i == aTitle) {
                    data.setIsTitle(0);
                    break;
                } else {
                    data.setIsTitle(1);
                }
            }
            data.setContent(content[i]);
            list.add(data);
        }
        HelpCenterAdapter adapter = new HelpCenterAdapter(list);
        helpView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        helpView.setAdapter(adapter);
    }

    @Override
    protected void onViewClick(View view)  {
        switch (view.getId()){
            case R.id.topbar_back:{
                finish();
                break;
            }
        }
    }
}
package com.awake.dreaming.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awake.dreaming.R;
import com.awake.dreaming.activity.CreatedCardActivity;

/**
 * Created by wangdesheng on 2017/10/23 0023.
 */

public class MyCardFragment extends BaseFragment {

    private TextView createBtn;

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_card;
    }

    @Override
    protected void setLayout() {
        initView();
    }

    private void initView() {
        createBtn = (TextView) getActivity().findViewById(R.id.tv_created);
        createBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:{
                        createBtn.setBackgroundResource(R.drawable.bg_creat_card);
                        Intent intent = new Intent(getContext(), CreatedCardActivity.class);
                        intent.putExtra("type", "create");
                        startActivity(intent);
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        createBtn.setBackgroundResource(R.drawable.bg_created_click);
                        break;
                    }
                }
                return true;
            }
        });
        LinearLayout noCard = (LinearLayout) getActivity().findViewById(R.id.ll_no_card);
        LinearLayout businessCard = (LinearLayout) getActivity().findViewById(R.id.ll_business_card);

        TextView cardName = (TextView) getActivity().findViewById(R.id.tv_card_name);
        TextView cardCompany = (TextView) getActivity().findViewById(R.id.tv_card_company);
        TextView cardOffice = (TextView) getActivity().findViewById(R.id.tv_card_manager);
        TextView cardMobile = (TextView) getActivity().findViewById(R.id.tv_card_mobile);
        TextView cardAddress = (TextView) getActivity().findViewById(R.id.tv_card_location);
        TextView cardEmail = (TextView) getActivity().findViewById(R.id.tv_card_email);

        ImageView btnModifyCard = (ImageView) getActivity().findViewById(R.id.img_modify_card);
        ImageView btnDeliveryCard = (ImageView) getActivity().findViewById(R.id.img_delivery_card);
        btnModifyCard.setOnClickListener(this);
        btnDeliveryCard.setOnClickListener(this);

        SharedPreferences info = getActivity().getSharedPreferences("BUSINESS_CARD", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = info.edit();

        String name = info.getString("name", "");
        if (name != ""){
            noCard.setVisibility(View.INVISIBLE);
            businessCard.setVisibility(View.VISIBLE);

            cardName.setText(info.getString("name", ""));
            cardCompany.setText(info.getString("company", ""));
            cardOffice.setText(info.getString("office", ""));
            cardMobile.setText(info.getString("mobile", ""));
            cardAddress.setText(info.getString("location", ""));
            cardEmail.setText(info.getString("email", ""));
        }else {
            noCard.setVisibility(View.VISIBLE);
            businessCard.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onViewClick(View view) {

        switch (view.getId()){
            case R.id.img_modify_card:{
                Intent intent = new Intent(getActivity(), CreatedCardActivity.class);
                intent.putExtra("type", "modify");
                startActivity(intent);
                break;
            }
            case R.id.img_delivery_card:{
                break;
            }
        }
    }
}

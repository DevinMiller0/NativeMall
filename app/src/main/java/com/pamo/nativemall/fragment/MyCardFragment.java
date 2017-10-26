package com.pamo.nativemall.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pamo.nativemall.R;
import com.pamo.nativemall.activity.CreatedCardActivity;

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
        createBtn = getActivity().findViewById(R.id.tv_created);
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
        LinearLayout noCard = getActivity().findViewById(R.id.ll_no_card);
        LinearLayout businessCard = getActivity().findViewById(R.id.ll_business_card);

        TextView cardName = getActivity().findViewById(R.id.tv_card_name);
        TextView cardCompany = getActivity().findViewById(R.id.tv_card_company);
        TextView cardOffice = getActivity().findViewById(R.id.tv_card_manager);
        TextView cardMobile = getActivity().findViewById(R.id.tv_card_mobile);
        TextView cardAddress = getActivity().findViewById(R.id.tv_card_location);
        TextView cardEmail = getActivity().findViewById(R.id.tv_card_email);

        ImageView btnModifyCard = getActivity().findViewById(R.id.img_modify_card);
        ImageView btnDeliveryCard = getActivity().findViewById(R.id.img_delivery_card);
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

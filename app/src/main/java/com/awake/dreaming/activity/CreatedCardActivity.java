package com.awake.dreaming.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.awake.dreaming.R;
import com.awake.dreaming.utils.StatusBarUtils;
import com.awake.dreaming.widget.TopBar;

/**
 * Created by 德胜 on 2017/10/23.
 */

public class CreatedCardActivity extends BaseActivity {

    private TopBar topBar;

    private EditText name;
    private EditText company;
    private EditText position;
    private EditText phone;
    private EditText office;
    private EditText mobile;
    private EditText weChat;
    private EditText email;
    private EditText location;

    private TextView btnCreated;

    @Override
    protected int getLayout() {
        return R.layout.activity_created_card;
    }

    @Override
    protected void setLayout() {
        initView();
    }

    private void initView() {
        StatusBarUtils.transparentStatusBar(this);
        topBar = (TopBar) findViewById(R.id.topBar_create_card);

        name = (EditText) findViewById(R.id.et_created_name);
        company = (EditText) findViewById(R.id.et_created_company);
        position = (EditText) findViewById(R.id.et_created_position);
        phone = (EditText) findViewById(R.id.et_created_phone);
        office = (EditText) findViewById(R.id.et_created_office);
        mobile = (EditText) findViewById(R.id.et_created_mobile);
        weChat = (EditText) findViewById(R.id.et_created_wechat);
        email = (EditText) findViewById(R.id.et_created_email);
        location = (EditText) findViewById(R.id.et_created_location);

        btnCreated = (TextView) findViewById(R.id.tv_btn_created);
        btnCreated.setOnTouchListener(this);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("modify")){
            modifyInfo();
        }
    }

    @Override
    protected void onViewClick(View view) {}

    @Override
    protected boolean onViewTouch(View view, MotionEvent motionEvent) {
        super.onViewTouch(view, motionEvent);
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_UP:{
                btnCreated.setBackgroundResource(R.drawable.bg_creat_card);
                getCardInfo();
                break;
            }
            case MotionEvent.ACTION_DOWN:{
                btnCreated.setBackgroundResource(R.drawable.bg_created_click);
                break;
            }
        }
        return true;
    }

    private void getCardInfo(){
        String mName = name.getText().toString();
        String mCompany = company.getText().toString();
        String mPosition = position.getText().toString();
        String mPhone = phone.getText().toString();
        String mOffice = office.getText().toString();
        String mMobile = mobile.getText().toString();
        String mWeChat = weChat.getText().toString();
        String mEmail = email.getText().toString();
        String mLocation = location.getText().toString();

        if (mName.equals("") || (mName == null)){
            Toast.makeText(this, "请填写姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mMobile.equals("") || (mMobile == null)){
            Toast.makeText(this, "请填写手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences shared = getSharedPreferences("BUSINESS_CARD", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("name", mName);
        editor.putString("company", mCompany);
        editor.putString("position", mPosition);
        editor.putString("phone", mPhone);
        editor.putString("office", mOffice);
        editor.putString("mobile", mMobile);
        editor.putString("weChat", mWeChat);
        editor.putString("email", mEmail);
        editor.putString("location", mLocation);

        editor.commit();

        if (!shared.getString("name", "").equals("") || !shared.getString("mobile", "").equals("")){
            Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "创建失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void modifyInfo(){
        topBar.titleText.setText("修改名片");
        btnCreated.setText("完成");

        SharedPreferences shared = getSharedPreferences("BUSINESS_CARD", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = shared.edit();

        name.setText(shared.getString("name", ""));
        company.setText(shared.getString("company", ""));
        position.setText(shared.getString("position", ""));
        phone.setText(shared.getString("phone", ""));
        office.setText(shared.getString("office", ""));
        mobile.setText(shared.getString("mobile", ""));
        weChat.setText(shared.getString("weChat", ""));
        email.setText(shared.getString("email", ""));
        location.setText(shared.getString("location", ""));
    }
}

package com.pamo.nativemall.activity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pamo.nativemall.R;
import com.pamo.nativemall.utils.StatusBarUtils;

/**
 * Created by 德胜 on 2017/10/23.
 */

public class CreatedCardActivity extends BaseActivity {

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

        Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
    }
}

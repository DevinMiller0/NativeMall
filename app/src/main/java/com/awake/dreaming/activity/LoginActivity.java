package com.awake.dreaming.activity;

import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.awake.dreaming.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangdesheng on 2017/11/3 0003.
 */

public class LoginActivity extends BaseActivity {

    private Display display;

    private EditText phoneNum;
    private EditText verifyCode;
    private TextView obtainCode;
    private TextView login;

    private Timer timer;
    private final int MSG_WHAT = 0;
    protected int second = 10;
    private final String TAG = "LoginActivity";


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void setLayout() {
        initView();
        initTopFloat();
        initBottomFloat();
        initLogin();
    }

    /**
     * 初始化登录按钮
     */
    private void initLogin() {

        phoneNum = (EditText) findViewById(R.id.et_mobile_number);
        verifyCode = (EditText) findViewById(R.id.et_verify_code);
        obtainCode = (TextView) findViewById(R.id.tv_obtain_code_btn);
        login = (TextView) findViewById(R.id.tv_login_btn);

        obtainCode.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    /**
     * 初始化底部浮动气球
     */
    private void initBottomFloat() {

        RelativeLayout bottomFloat = (RelativeLayout) findViewById(R.id.rl_bottom_float);
        ImageView balloonBottom = (ImageView) findViewById(R.id.img_bottom_balloon);
        ImageView cloudBottomSm = (ImageView) findViewById(R.id.img_bottom_small_cloud);
        ImageView cloudBottomBig = (ImageView) findViewById(R.id.img_bottom_big_cloud);

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        bottomFloat.setLayoutParams(new RelativeLayout.LayoutParams(width / 3 , width / 3));

        RelativeLayout.LayoutParams paramsFloat =
                (RelativeLayout.LayoutParams) bottomFloat.getLayoutParams();
        paramsFloat.topMargin = (int) (height / 2.5);

        //左上角云彩的位置
        RelativeLayout.LayoutParams paramsSm =
                (RelativeLayout.LayoutParams) cloudBottomSm.getLayoutParams();
        paramsSm.leftMargin = (width / 11);
        paramsSm.topMargin = (width / 7);

        //右下角云彩的位置
        RelativeLayout.LayoutParams paramsBig =
                (RelativeLayout.LayoutParams) cloudBottomBig.getLayoutParams();
        paramsBig.leftMargin = (width / 6);
        paramsBig.topMargin = (int) (width / 4.5);

        floatAnimation(balloonBottom, 25, 3000);
        floatAnimation(cloudBottomBig, 25, 4000);
        floatAnimation(cloudBottomSm, 25, 3500);

    }

    /**
     * 初始化顶部浮动气球
     */
    private void initTopFloat() {

        RelativeLayout topFloat = (RelativeLayout) findViewById(R.id.rl_top_float);
        ImageView balloonTop = (ImageView) findViewById(R.id.img_top_balloon);
        ImageView cloudTopSm = (ImageView) findViewById(R.id.img_top_small_cloud);
        ImageView cloudTopBig = (ImageView) findViewById(R.id.img_top_big_cloud);

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        topFloat.setLayoutParams(new RelativeLayout.LayoutParams(width / 3 , width / 3));

        //右上角气球的位置
        RelativeLayout.LayoutParams topFloatParams =
                (RelativeLayout.LayoutParams) topFloat.getLayoutParams();
        topFloatParams.leftMargin = (int) (width * 1.95 / 3);
        topFloatParams.topMargin = height / 12;

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        topFloat.measure(w, h);

        //左上角云彩的位置
        RelativeLayout.LayoutParams paramsSm =
                (RelativeLayout.LayoutParams) cloudTopSm.getLayoutParams();
        paramsSm.leftMargin = (width / 12);
        paramsSm.topMargin = (width / 9);

        //右下角云彩的位置
        RelativeLayout.LayoutParams paramsBig =
                (RelativeLayout.LayoutParams) cloudTopBig.getLayoutParams();
        paramsBig.rightMargin = (width / 9);
        paramsBig.bottomMargin = (width / 12);

        floatAnimation(balloonTop, 25, 3000);
        floatAnimation(cloudTopBig, 25, 4000);
        floatAnimation(cloudTopSm, 25, 3500);

    }

    /**
     * 初始化登录布局
     */
    private void initView() {

        RelativeLayout relativeParent = (RelativeLayout) findViewById(R.id.rl_parent);
        relativeParent.setGravity(Gravity.CENTER_VERTICAL);

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.linearLayout_parent);
        parentLayout.setLayoutParams
                (new RelativeLayout.LayoutParams(width * 3 / 4, height * 2 / 5));

        RelativeLayout.LayoutParams parentParams =
                (RelativeLayout.LayoutParams) parentLayout.getLayoutParams();
        parentParams.leftMargin = width / 8;
        parentParams.topMargin = height / 5;
        parentParams.bottomMargin = height / 7;
    }

    /**
     * 浮动动画
     */
    private void floatAnimation(ImageView imageview, int distance, int duration) {

        Animation animation = new TranslateAnimation(0, distance, distance, 0);
        animation.setDuration(duration);
        animation.setRepeatCount(1000);
        animation.setRepeatMode(Animation.REVERSE);
        imageview.setAnimation(animation);
        animation.start();
    }

    /**
     * 界面点击事件
     * @param view
     */
    @Override
    protected void onViewClick(View view) {

        switch (view.getId()){
            case R.id.tv_obtain_code_btn:{

                String number = phoneNum.getText().toString();

                if (number.equals("")){
                    Toast.makeText(LoginActivity.this,
                            getString(R.string.input_mobile_number), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (number.length() < 11){
                    Toast.makeText(LoginActivity.this,
                            getString(R.string.eleven_number), Toast.LENGTH_SHORT).show();
                    return;
                }

                int x = number.charAt(1);

                //Log.e(TAG, "onViewClick: " + x );
                if (x == 48 || x == 49 || x == 50 || x == 54 || x == 57){
                    toast(getString(R.string.correct_phone_number));
                    return;
                }

                obtainCode.setBackground(getDrawable(R.drawable.bg_verify_code_sending));

                if (timer == null){
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(MSG_WHAT);
                        }
                    }, 0, 1000);
                }
                break;
            }

            case R.id.tv_login_btn:{

                String verify = verifyCode.getText().toString();

                if (verify.isEmpty()){
                    toast(getString(R.string.input_verify_code));
                }else if (verify.length() < 6){
                    toast(getString(R.string.six_verify_code));
                }
                break;
            }
        }
    }

    /**
     * 倒计时
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            obtainCode.setText(second + "s");

            switch (msg.what){

                case MSG_WHAT:{
                    if (second > 0){
                        second--;
                    }else {
                        toast(getString(R.string.send_again));
                        obtainCode.setText(getString(R.string.obtain_verify_code));
                        obtainCode.setBackground(getDrawable(R.drawable.bg_verify_code));
                        second = 3;
                        if (timer != null) {
                            timer.cancel();
                            timer = null;
                        }
                    }
                }
            }
        }
    };

    /**
     * 吐司提示
     * @param toastMsg
     */
    private void toast(String toastMsg) {
        Toast.makeText(LoginActivity.this, toastMsg, Toast.LENGTH_SHORT).show();
    }
}

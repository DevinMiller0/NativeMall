package com.pamo.nativemall.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.pamo.nativemall.R;

/**
 * Created by wangdesheng on 2017/10/27 0027.
 */

public class LoadingDialog {

    public static Dialog createLoading(Context context, String msg){

        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading,null);
        ImageView loading = view.findViewById(R.id.img_loading);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
        loading.startAnimation(animation);

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        //loadingDialog.setCancelable(false);
        loadingDialog.setContentView(view);
        return loadingDialog;
    }
}

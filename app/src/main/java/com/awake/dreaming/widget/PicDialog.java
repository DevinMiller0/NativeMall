package com.awake.dreaming.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

/**
 * Created by wangdesheng on 2017/11/23 0023.
 */

public class PicDialog extends Dialog {
    public PicDialog(@NonNull Context context) {
        super(context);
    }

    public PicDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected PicDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}

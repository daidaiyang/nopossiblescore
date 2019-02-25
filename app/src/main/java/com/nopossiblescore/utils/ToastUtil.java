package com.nopossiblescore.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {

    private ToastUtil() {
    }

    public static void show(Context mContext, CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            Toast toast;
            if (text.length() < 10) {
                toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
            } else {
                toast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
            }
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
    }

}
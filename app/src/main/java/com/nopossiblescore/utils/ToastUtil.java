package com.nopossiblescore.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class ToastUtil {

    /**
     * Used 简单的Toast封装类
     */

        private static Toast toast;//实现不管我们触发多少次Toast调用，都只会持续一次Toast显示的时长

        /**
         * 短时间显示Toast【居下】
         * @param msg 显示的内容-字符串*/
        public static void showShortToast(Context mContext, String msg) {
            if(mContext != null){
                if (toast == null) {
                    toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(msg);
                }
                //1、setGravity方法必须放到这里，否则会出现toast始终按照第一次显示的位置进行显示（比如第一次是在底部显示，那么即使设置setGravity在中间，也不管用）
                //2、虽然默认是在底部显示，但是，因为这个工具类实现了中间显示，所以需要还原，还原方式如下：
                toast.setGravity(Gravity.BOTTOM, 0, dip2px(mContext,64));
                toast.show();
            }
        }
        /**
         * 短时间显示Toast【居中】
         * @param msg 显示的内容-字符串*/
        public static void showShortToastCenter(Context mContext,String msg){
            if(mContext != null) {
                if (toast == null) {
                    toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(msg);
                }
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }

        /**
         * 短时间显示Toast【居上】
         * @param msg 显示的内容-字符串*/
        public static void showShortToastTop(Context mContext,String msg){
            if(mContext != null) {
                if (toast == null) {
                    toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(msg);
                }
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }

        /**
         * 长时间显示Toast【居下】
         * @param msg 显示的内容-字符串*/
        public static void showLongToast(Context mContext,String msg) {
            if(mContext != null) {
                if (toast == null) {
                    toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
                } else {
                    toast.setText(msg);
                }
                toast.setGravity(Gravity.BOTTOM, 0, dip2px(mContext,64));
                toast.show();
            }
        }
        /**
         * 长时间显示Toast【居中】
         * @param msg 显示的内容-字符串*/
        public static void showLongToastCenter(Context mContext,String msg){
            if(mContext != null) {
                if (toast == null) {
                    toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
                } else {
                    toast.setText(msg);
                }
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
        /**
         * 长时间显示Toast【居上】
         * @param msg 显示的内容-字符串*/
        public static void showLongToastTop(Context mContext,String msg){
            if(mContext != null) {
                if (toast == null) {
                    toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
                } else {
                    toast.setText(msg);
                }
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        }

    /**
     * 自定义Toast的View
     *
     * @param context
     * @param duration 单位:毫秒
     * @param view     显示自己的View
     */
    public static void showToastView(Context context,int duration, View view) {
        if (context!=null) {
            if (toast == null) {
                toast = Toast.makeText(context, null, duration);
            }
            if (view != null) {
                toast.setView(view);
            }
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
    /**
     * 自定义Toast的View
     *
     * @param context
     * @param duration 单位:毫秒
     * @param view     显示自己的View
     */
    public static void showToastViewMarggin(Context context,int duration, View view,float horizontalMargin, float verticalMargin) {
        if (context!=null) {
            if (toast == null) {
                toast = Toast.makeText(context, null, duration);
            }
            if (view != null) {
                toast.setView(view);
            }
            toast.setMargin(horizontalMargin, verticalMargin);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
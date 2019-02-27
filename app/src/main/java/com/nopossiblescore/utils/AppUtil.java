package com.nopossiblescore.utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AppUtil {





    //安卓中像素px和dp的转换：
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取当前时间的时间格式
     * @param type 需要获取的时间格式
     * @return
     */
    public static String getCurrentTime(String type){
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(type,Locale.CHINA);
        return sdf.format(date);
    }


    /**
     * 获取当前时间的前n天或者后n天的时间
     * @param num n天
     * @param type 需要获取的时间格式
     * @return
     */
    public static String getCurrentDateSubOrPlus(int num,String type){
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat(type);
        calendar1.add(Calendar.DATE, num);
        String agoOrPre = sdf1.format(calendar1.getTime());
        System.out.println(agoOrPre);
        return agoOrPre;
    }
}

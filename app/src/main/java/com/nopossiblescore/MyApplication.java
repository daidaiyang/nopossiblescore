package com.nopossiblescore;

import android.app.Application;
import android.content.Context;

import com.ygs.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;

public class MyApplication extends Application {

    public static Context app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
        RxRetrofitApp.init(this,BuildConfig.DEBUG);
    }
}

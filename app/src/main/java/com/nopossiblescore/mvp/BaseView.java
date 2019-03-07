package com.nopossiblescore.mvp;

import android.content.Context;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.ygs.rxretrofitlibrary.retrofit_rx.http.HttpManager;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public interface BaseView {
     Context getContext();
     HttpManager getManager();
     RxAppCompatActivity getThis();
}

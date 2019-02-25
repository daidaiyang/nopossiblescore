package com.nopossiblescore.main;

import android.content.Context;

import com.nopossiblescore.mvp.BasePresenter;
import com.nopossiblescore.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}

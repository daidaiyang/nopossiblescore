package com.nopossiblescore.activities.login;

import android.content.Context;
import android.util.Log;

import com.nopossiblescore.entity.api.UserLoginApi;
import com.nopossiblescore.mvp.BasePresenterImpl;
import com.ygs.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{


    private HttpOnNextListener loginListener = new HttpOnNextListener<String>(){

        @Override
        public void onNext(String s) {
            Log.d("lgin",s);
        }
    };

    public void login(String name, String password){
        UserLoginApi userLoginApi = new UserLoginApi(loginListener,mView.getThis());
        userLoginApi.setParams("223456","13888888888","123456");
        mView.getManager().doHttpDeal(userLoginApi);
    }
}

package com.nopossiblescore.entity.api;

import com.google.gson.Gson;
import com.nopossiblescore.entity.HttpPostService;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.ygs.rxretrofitlibrary.retrofit_rx.Api.BaseApi;
import com.ygs.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;

public class UserLoginApi extends BaseApi {


    private UserLoginBean userLoginBean;

    private RequestBody body;


    public UserLoginApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setCancel(true);
        setCache(true);
        setMethod("v1/user-user-login");
    }


    public void setParams(String code,String phone,String pwd) {
        userLoginBean = new UserLoginBean(code,phone,pwd);
        String json = new Gson().toJson(userLoginBean);
        body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService postService = retrofit.create(HttpPostService.class);
        return postService.userLogin(body);
    }


    class UserLoginBean{
        private String code;
        private String phone;
        private String pwd;

        public UserLoginBean(String code, String phone, String pwd) {
            this.code = code;
            this.phone = phone;
            this.pwd = pwd;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }

}

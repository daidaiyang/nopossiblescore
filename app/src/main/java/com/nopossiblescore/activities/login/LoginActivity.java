package com.nopossiblescore.activities.login;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nopossiblescore.R;
import com.nopossiblescore.activities.main.MainActivity;
import com.nopossiblescore.customview.ShadowDrawable;
import com.nopossiblescore.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login)
    RelativeLayout login;
    @BindView(R.id.login_info)
    LinearLayout loginInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ShadowDrawable.setShadowDrawable(loginInfo, Color.parseColor("#ffffff"),
                (int) getResources().getDimension(R.dimen.x15),
                Color.parseColor("#337C7C7C"),
                (int) getResources().getDimension(R.dimen.x15),
                0, 0);
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        mPresenter.login("","");
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        this.finish();
    }
}

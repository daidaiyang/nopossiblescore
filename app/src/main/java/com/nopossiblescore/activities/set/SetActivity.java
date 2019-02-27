package com.nopossiblescore.activities.set;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nopossiblescore.R;
import com.nopossiblescore.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SetActivity extends MVPBaseActivity<SetContract.View, SetPresenter> implements SetContract.View {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.version_info)
    TextView versionInfo;
    @BindView(R.id.current_version)
    RelativeLayout currentVersion;
    @BindView(R.id.clear)
    RelativeLayout clear;
    @BindView(R.id.contraus)
    RelativeLayout contraus;
    @BindView(R.id.exitapp)
    TextView exitapp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("设置");
    }


    @OnClick({R.id.current_version, R.id.clear, R.id.contraus, R.id.exitapp,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.current_version:
                break;
            case R.id.clear:
                break;
            case R.id.contraus:
                break;
            case R.id.exitapp:
                break;
            case R.id.back:
                SetActivity.this.finish();
                break;
        }
    }
}

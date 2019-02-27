package com.nopossiblescore.activities.set;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
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

    @OnClick(R.id.back)
    public void onViewClicked() {
        SetActivity.this.finish();
    }
}

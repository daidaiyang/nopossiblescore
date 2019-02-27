package com.nopossiblescore.activities.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.nopossiblescore.R;
import com.nopossiblescore.activities.set.SetActivity;
import com.nopossiblescore.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MineActivity extends MVPBaseActivity<MineContract.View, MinePresenter> implements MineContract.View {


    @BindView(R.id.mine_home)
    ImageView mineHome;
    @BindView(R.id.mine_set)
    ImageView mineSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.mine_home, R.id.mine_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_home:
                MineActivity.this.finish();
                break;
            case R.id.mine_set:
                Intent intent = new Intent(MineActivity.this,SetActivity.class);
                startActivity(intent);
                break;
        }
    }
}

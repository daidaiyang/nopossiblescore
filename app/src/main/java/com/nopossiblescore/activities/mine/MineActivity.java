package com.nopossiblescore.activities.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblescore.R;
import com.nopossiblescore.activities.give.GiveActivity;
import com.nopossiblescore.activities.record.RecordActivity;
import com.nopossiblescore.activities.set.SetActivity;
import com.nopossiblescore.customview.LineChartView;
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
    @BindView(R.id.mine_img)
    ImageView mineImg;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.mine_tel)
    TextView mineTel;
    @BindView(R.id.mine_code)
    TextView mineCode;
    @BindView(R.id.mine_copy)
    TextView mineCopy;
    @BindView(R.id.mine_count_time)
    TextView mineCountTime;
    @BindView(R.id.mine_total)
    TextView mineTotal;
    @BindView(R.id.mine_give)
    TextView mineGive;
    @BindView(R.id.mine_record)
    TextView mineRecord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.mine_home, R.id.mine_set, R.id.mine_copy, R.id.mine_give,R.id.mine_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_home:
                MineActivity.this.finish();
                break;
            case R.id.mine_set:
                Intent intent = new Intent(MineActivity.this, SetActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_copy:
                break;
            case R.id.mine_give:
                Intent giveIntent = new Intent(MineActivity.this, GiveActivity.class);
                startActivity(giveIntent);
                break;
            case R.id.mine_record:
                Intent recordIntent = new Intent(MineActivity.this, RecordActivity.class);
                startActivity(recordIntent);
                break;
        }
    }
}

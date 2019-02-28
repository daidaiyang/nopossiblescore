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
import com.nopossiblescore.customview.TwoLineChartView;
import com.nopossiblescore.dialog.MainDataDialog;
import com.nopossiblescore.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MineActivity extends MVPBaseActivity<MineContract.View, MinePresenter> implements MineContract.View, MainDataDialog.OnConfirClickListener {


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
    @BindView(R.id.line_chart_view)
    TwoLineChartView lineChartView;
    @BindView(R.id.mine_range)
    TextView mineRange;

    private MainDataDialog mainDataDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mainDataDialog = new MainDataDialog(getContext());
        mainDataDialog.setOnConfirClickListener(this);
        List<TwoLineChartView.Data> datas1 = new ArrayList<>();
        List<TwoLineChartView.Data> datas2 = new ArrayList<>();
        List<String> data3 = new ArrayList<>();
        datas1.add(new TwoLineChartView.Data(23));
        datas1.add(new TwoLineChartView.Data(49));
        datas1.add(new TwoLineChartView.Data(41));
        datas1.add(new TwoLineChartView.Data(44));
        datas1.add(new TwoLineChartView.Data(36));
        datas1.add(new TwoLineChartView.Data(46));
        datas1.add(new TwoLineChartView.Data(54));
        datas1.add(new TwoLineChartView.Data(54));
        datas2.add(new TwoLineChartView.Data(23));
        datas2.add(new TwoLineChartView.Data(39));
        datas2.add(new TwoLineChartView.Data(0));
        datas2.add(new TwoLineChartView.Data(33));
        datas2.add(new TwoLineChartView.Data(43));
        datas2.add(new TwoLineChartView.Data(41));
        datas2.add(new TwoLineChartView.Data(45));
        datas2.add(new TwoLineChartView.Data(45));
        data3.add("01.08");
        data3.add("01.09");
        data3.add("01.10");
        data3.add("01.11");
        data3.add("01.12");
        data3.add("01.13");
        data3.add("01.14");
        data3.add("01.15");
        lineChartView.setData(datas1, datas2, data3);
        lineChartView.playAnim();
    }

    @OnClick({R.id.mine_home, R.id.mine_set, R.id.mine_copy, R.id.mine_give, R.id.mine_record,R.id.mine_range})
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
            case R.id.mine_range:
                mainDataDialog.initView();
                mainDataDialog.show();
                break;
        }
    }

    @Override
    public void onConfirListener(View view, String result) {

    }
}

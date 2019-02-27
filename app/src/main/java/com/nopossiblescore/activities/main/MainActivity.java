package com.nopossiblescore.activities.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nopossiblescore.R;
import com.nopossiblescore.activities.granarysearch.GranarysearchActivity;
import com.nopossiblescore.activities.message.MessageActivity;
import com.nopossiblescore.activities.mine.MineActivity;
import com.nopossiblescore.adapter.RankingItemAdapter;
import com.nopossiblescore.customview.LineChartView;
import com.nopossiblescore.dialog.MainDataDialog;
import com.nopossiblescore.mvp.MVPBaseActivity;
import com.nopossiblescore.utils.AppUtil;
import com.nopossiblescore.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    @BindView(R.id.main_mine)
    ImageView mine;
    @BindView(R.id.main_message_circle)
    View messageCircle;
    @BindView(R.id.main_message)
    FrameLayout message;
    @BindView(R.id.main_search)
    ImageView mainSearch;
    @BindView(R.id.main_freshTime)
    TextView freshTime;
    @BindView(R.id.main_proportion)
    TextView proportion;
    @BindView(R.id.main_allScore)
    TextView allScore;
    @BindView(R.id.main_allForage)
    TextView allForage;
    @BindView(R.id.main_rbToday)
    RadioButton rbToday;
    @BindView(R.id.main_rbAll)
    RadioButton rbAll;
    @BindView(R.id.main_rg)
    RadioGroup rg;
    @BindView(R.id.main_recy)
    RecyclerView recy;
    @BindView(R.id.main_zhexian)
    LineChartView lineChartView;
    @BindView(R.id.main_time_select)
    TextView mainTimeSelect;

    private RankingItemAdapter mAdapter;
    private int[] dataArr = new int[]{28, 49, 39, 44, 21, 46, 54};
    private String[] data = new String[]{};
    private List<String> mData;

    private MainDataDialog mainDataDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String currentTime = AppUtil.getCurrentTime("yyyy.MM.dd");
        String agoDate = AppUtil.getCurrentDateSubOrPlus(-7, "yyyy.MM.dd");
        mainTimeSelect.setText(agoDate + "-" + currentTime);
        rbToday.setChecked(true);
        List<LineChartView.Data> dataArrs = new ArrayList<>();
        for (int value : dataArr) {
            LineChartView.Data data = new LineChartView.Data(value);
            dataArrs.add(data);
        }
        List<String> datas = new ArrayList<>();
        datas.add("01.08");
        datas.add("01.09");
        datas.add("01.10");
        datas.add("01.11");
        datas.add("01.12");
        datas.add("01.13");
        datas.add("01.14");
        lineChartView.setData(dataArrs, datas);
        lineChartView.playAnim();
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new RankingItemAdapter(getContext(), mData);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        recy.setNestedScrollingEnabled(false);
        recy.setAdapter(mAdapter);
        mainDataDialog = new MainDataDialog(getContext());
        mainDataDialog.initView();
        mainDataDialog.setOnConfirClickListener(new MainDataDialog.OnConfirClickListener() {
            @Override
            public void onConfirListener(View view, String result) {
                mainTimeSelect.setText(result);
                ToastUtil.showToastView(getContext(), Toast.LENGTH_SHORT, LayoutInflater.from(getContext()).inflate(R.layout.dialog_data_error, null));
            }
        });
    }

    @OnClick({R.id.main_mine, R.id.main_message, R.id.main_search,R.id.main_time_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_mine:
                Intent mineIntent = new Intent(MainActivity.this,MineActivity.class);
                startActivity(mineIntent);
                break;
            case R.id.main_message:
                Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.main_search:
                Intent searchIntent = new Intent(MainActivity.this,GranarysearchActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.main_time_select:
                mainDataDialog.initView();
                mainDataDialog.show();
                break;
        }
    }
}

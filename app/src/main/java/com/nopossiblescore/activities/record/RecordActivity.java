package com.nopossiblescore.activities.record;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nopossiblescore.R;
import com.nopossiblescore.adapter.RecordItemAdapter;
import com.nopossiblescore.mvp.MVPBaseActivity;
import com.nopossiblescore.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RecordActivity extends MVPBaseActivity<RecordContract.View, RecordPresenter> implements RecordContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.record_rbAll)
    RadioButton recordRbAll;
    @BindView(R.id.record_rbGet)
    RadioButton recordRbGet;
    @BindView(R.id.record_rbGetGive)
    RadioButton recordRbGetGive;
    @BindView(R.id.record_rbGive)
    RadioButton recordRbGive;
    @BindView(R.id.record_rg)
    RadioGroup recordRg;
    @BindView(R.id.record_recy)
    RecyclerView recordRecy;
    @BindView(R.id.record_bga)
    BGARefreshLayout recordBga;
    @BindView(R.id.record_nodata)
    LinearLayout recordNodata;


    private RecordItemAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recordBga.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(this, true);
        holder.setLoadingMoreText("加载中");
        recordBga.setRefreshViewHolder(holder);
        recordRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        recordRecy.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.VERTICAL,
                (int) getResources().getDimension(R.dimen.x23),
                getResources().getColor(R.color.backgrould_f2)));
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new RecordItemAdapter(getContext(), mData);
        recordRecy.setAdapter(mAdapter);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}

package com.nopossiblescore.activities.granarysearch;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblescore.R;
import com.nopossiblescore.adapter.GranaryItemAdapter;
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

public class GranarysearchActivity extends MVPBaseActivity<GranarysearchContract.View, GranarysearchPresenter> implements GranarysearchContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.search_code)
    EditText searchCode;
    @BindView(R.id.search_search)
    TextView search;
    @BindView(R.id.search_toatal)
    TextView searchToatal;
    @BindView(R.id.search_recy)
    RecyclerView searchRecy;
    @BindView(R.id.search_bga)
    BGARefreshLayout searchBga;

    private GranaryItemAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_granarysearch);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("粮仓查询");
        searchBga.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(this, true);
        holder.setLoadingMoreText("加载中");
        searchBga.setRefreshViewHolder(holder);
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new GranaryItemAdapter(getContext(), mData);
        searchRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        searchRecy.setAdapter(mAdapter);

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @OnClick({R.id.back, R.id.search_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                GranarysearchActivity.this.finish();
                break;
            case R.id.search_search:
                break;
        }
    }
}

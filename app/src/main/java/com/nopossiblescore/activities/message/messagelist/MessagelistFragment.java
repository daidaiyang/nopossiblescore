package com.nopossiblescore.activities.message.messagelist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nopossiblescore.R;
import com.nopossiblescore.activities.message.MessageEventBus;
import com.nopossiblescore.adapter.MessageItemAdapter;
import com.nopossiblescore.mvp.MVPBaseFragment;
import com.nopossiblescore.utils.RecycleViewDivider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MessagelistFragment extends MVPBaseFragment<MessagelistContract.View, MessagelistPresenter> implements MessagelistContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, MessageItemAdapter.OnItemClickListener {



    @BindView(R.id.message_list_recy)
    RecyclerView recy;
    @BindView(R.id.message_list_bga)
    BGARefreshLayout bga;
    Unbinder unbinder;

    private List<String> mData;
    private MessageItemAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        bga.setDelegate(this);
        BGANormalRefreshViewHolder holder = new BGANormalRefreshViewHolder(getContext(),true);
        holder.setLoadingMoreText("加载中");
        bga.setRefreshViewHolder(holder);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        recy.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.VERTICAL,
                (int) getResources().getDimension(R.dimen.x30),
                getResources().getColor(R.color.backgrould_f2)));
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        mAdapter = new MessageItemAdapter(getContext(),mData);
        mAdapter.setItemClick(this);
        recy.setAdapter(mAdapter);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        return false;
    }

    @Override
    public void onItemClick(View v, int position) {
        EventBus.getDefault().post(new MessageEventBus(1,position));
    }
}

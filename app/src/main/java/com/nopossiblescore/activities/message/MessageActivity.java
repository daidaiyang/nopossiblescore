package com.nopossiblescore.activities.message;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblescore.R;
import com.nopossiblescore.activities.message.messagedetail.MessagedetailFragment;
import com.nopossiblescore.activities.message.messagelist.MessagelistFragment;
import com.nopossiblescore.mvp.MVPBaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessageActivity extends MVPBaseActivity<MessageContract.View, MessagePresenter> implements MessageContract.View {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.message_frame)
    FrameLayout messageFrame;

    private Fragment listFragment;
    private Fragment detailFragment;

    private boolean isIndetail = false;

    private FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        title.setText("消息中心");
        setDefault();
    }

    private void setDefault() {
        if (listFragment == null){
            listFragment = new MessagelistFragment();
        }
        isIndetail = false;
        transaction = manager.beginTransaction();
        transaction.add(R.id.message_frame,listFragment);
        transaction.commit();

    }

    private void changeFragment(Fragment fragment){
        transaction = manager.beginTransaction();
        transaction.replace(R.id.message_frame,fragment);
        transaction.commit();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEventBus messageEventBus){
        if (messageEventBus.getEventCode() == 1){
            if (detailFragment == null){
                detailFragment = new MessagedetailFragment();
            }
            isIndetail = true;
            changeFragment(detailFragment);
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        if (isIndetail){
            if (listFragment == null){
                listFragment = new MessagedetailFragment();
            }
            isIndetail = false;
            changeFragment(listFragment);
        }else {
            MessageActivity.this.finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

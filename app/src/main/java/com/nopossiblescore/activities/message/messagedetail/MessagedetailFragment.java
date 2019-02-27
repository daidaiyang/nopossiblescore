package com.nopossiblescore.activities.message.messagedetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nopossiblescore.R;
import com.nopossiblescore.activities.message.MessageEventBus;
import com.nopossiblescore.mvp.MVPBaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessagedetailFragment extends MVPBaseFragment<MessagedetailContract.View, MessagedetailPresenter> implements MessagedetailContract.View{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_detail,container,false);
        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

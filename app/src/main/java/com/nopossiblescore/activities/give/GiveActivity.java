package com.nopossiblescore.activities.give;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblescore.R;
import com.nopossiblescore.dialog.GiveConfirDialog;
import com.nopossiblescore.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GiveActivity extends MVPBaseActivity<GiveContract.View, GivePresenter> implements GiveContract.View, GiveConfirDialog.OnConfirClickListener {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.give_mine_code)
    TextView giveMineCode;
    @BindView(R.id.give_copy)
    TextView giveCopy;
    @BindView(R.id.give_others_code)
    EditText giveOthersCode;
    @BindView(R.id.give_others_num)
    EditText giveOthersNum;
    @BindView(R.id.give_cangive_num)
    TextView giveCangiveNum;
    @BindView(R.id.give_give)
    TextView giveGive;

    private GiveConfirDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("赠送他人");
        dialog = new GiveConfirDialog(getContext());
        dialog.setClickListener(this);
    }

    @OnClick({R.id.give_copy, R.id.give_give,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.give_copy:

                break;
            case R.id.give_give:
                dialog.show();
                break;
            case R.id.back:
                GiveActivity.this.finish();
                break;
        }
    }

    @Override
    public void onCancleClick(View view) {

    }

    @Override
    public void onConfirClick(View view) {

    }
}

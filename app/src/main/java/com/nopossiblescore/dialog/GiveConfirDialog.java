package com.nopossiblescore.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.nopossiblescore.R;

public class GiveConfirDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private TextView tvCode;
    private TextView tvNum;
    private TextView tvCancle;
    private TextView tvConfir;

    private OnConfirClickListener clickListener;

    public void setClickListener(OnConfirClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public GiveConfirDialog(@NonNull Context context) {
        super(context, R.style.dialog_bottom);
        this.mContext = context;
        initView();
    }

    private void initView() {
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_give_confir,null);
        tvCode = view.findViewById(R.id.dialog_code);
        tvNum = view.findViewById(R.id.dialog_num);
        tvCancle = view.findViewById(R.id.dialog_cancle);
        tvConfir = view.findViewById(R.id.dialog_give);
        tvConfir.setOnClickListener(this);
        tvCancle.setOnClickListener(this);
        this.setContentView(view);
    }

    public void setData(String code,String num){
        tvCode.setText(code);
        tvNum.setText(num);
    }

    @Override
    public void onClick(View v) {
        if (clickListener!=null){
            if (v.getId() == R.id.dialog_confir){
                clickListener.onConfirClick(v);
            }else {
                clickListener.onCancleClick(v);
            }
            GiveConfirDialog.this.dismiss();
        }


    }

    public interface OnConfirClickListener{
        void onCancleClick(View view);
        void onConfirClick(View view);
    }
}

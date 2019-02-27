package com.nopossiblescore.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nopossiblescore.R;
import com.nopossiblescore.customview.wheelview.Common;
import com.nopossiblescore.customview.wheelview.WheelView;
import com.nopossiblescore.utils.AppUtil;

import java.util.Date;

public class MainDataDialog extends Dialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private Context mContext;

    private RadioGroup rg;
    private RadioButton rbStart;
    private RadioButton rbEnd;
    private TextView startData;
    private TextView endData;
    private TextView info;
    private TextView confir;
    private WheelView yearWheel;
    private WheelView monthWheel;
    private WheelView dayWheel;

    private String selectedData = "";

    public OnConfirClickListener onConfirClickListener;

    public void setOnConfirClickListener(OnConfirClickListener onConfirClickListener) {
        this.onConfirClickListener = onConfirClickListener;
    }

    public MainDataDialog(@NonNull Context context) {
        super(context, R.style.dialog_bottom);
        mContext = context;
    }

    public void initView() {
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_dataselect,null);
        rg = view.findViewById(R.id.dialog_rg);
        rbStart = view.findViewById(R.id.dialog_rb_start);
        rbEnd = view.findViewById(R.id.dialog_rb_end);
        startData = view.findViewById(R.id.dialog_start_txt);
        endData = view.findViewById(R.id.dialog_end_txt);
        info = view.findViewById(R.id.dialog_info);
        confir = view.findViewById(R.id.dialog_confir);
        yearWheel = view.findViewById(R.id.dialog_year);
        monthWheel = view.findViewById(R.id.dialog_month);
        dayWheel = view.findViewById(R.id.dialog_day);
        confir.setOnClickListener(this);
        initData();
        this.setContentView(view);
    }

    private void initData() {
        String agoDate = AppUtil.getCurrentDateSubOrPlus(-7, "yyyy.MM.dd");
        selectedData = agoDate;
        initWheel(agoDate);
        String currentData = AppUtil.getCurrentTime("yyyy.MM.dd");
        startData.setText(agoDate);
        endData.setText(currentData);
        rbStart.setChecked(true);
        rbStart.setOnCheckedChangeListener(this);
        rbEnd.setOnCheckedChangeListener(this);
        yearWheel.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                String month;
                String day;
                month = monthWheel.getSelectedItem();
                int preSelectDay = dayWheel.getSelectedPosition();
                dayWheel.setItems(Common.buildDayOfMonth(item, month), preSelectDay);
                day = dayWheel.getSelectedItem();
                selectedData = item+month+day;
            }
        });

        monthWheel.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                String year;
                String day;
                year = yearWheel.getSelectedItem();
                int preSelectDay = dayWheel.getSelectedPosition();
                dayWheel.setItems(Common.buildDayOfMonth(year, item), preSelectDay);
                day = dayWheel.getSelectedItem();
                selectedData = year+item+day;
            }
        });
        dayWheel.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                String year;
                String month;
                year = yearWheel.getSelectedItem();
                month = monthWheel.getSelectedItem();
                selectedData = year+month+item;
            }
        });
    }

    private void initWheel(String data) {
        String []datas = data.split("\\.");
        int monthPosition = Integer.parseInt(datas[1])-1;
        int dayPosition = Integer.parseInt(datas[2])-1;
        yearWheel.setItems(Common.buildYear(),Common.buildYear().size()-1);
        monthWheel.setItems(Common.buildMonthOfYear(),monthPosition);
        dayWheel.setItems(Common.buildDayOfMonth(datas[0]+"年",datas[1]+"月"),dayPosition);
    }

    @Override
    public void show() {
        super.show();
        //获取当前Activity所在的窗体
        Window dialogWindow = this.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0); //消除边距
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.y = 0;//设置Dialog距离底部的距离
        // 将属性设置给窗体
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        if (onConfirClickListener!=null){
            selectedData = selectedData.replace("年",".").replace("月",".").replace("日","");
            if (rbStart.isChecked()){
                startData.setText(selectedData);
                rbEnd.setChecked(true);
            }else {
                endData.setText(selectedData);
                MainDataDialog.this.dismiss();
                String s = startData.getText().toString() + "-" + endData.getText().toString();
                onConfirClickListener.onConfirListener(v,s);
            }
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    }

    public interface OnConfirClickListener{
        void onConfirListener(View view,String result);
    }
}

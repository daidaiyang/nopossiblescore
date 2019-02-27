package com.nopossiblescore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nopossiblescore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public RecordItemAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_record, viewGroup, false);
        return new ViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_record_title)
        TextView itemRecordTitle;
        @BindView(R.id.item_record_time)
        TextView itemRecordTime;
        @BindView(R.id.item_record_changeNum)
        TextView itemRecordChangeNum;
        @BindView(R.id.item_record_leftNum)
        TextView itemRecordLeftNum;
        @BindView(R.id.item_record_info)
        TextView itemRecordInfo;
        private OnItemClickListener itemClickListener;

        ViewHolder(View view,OnItemClickListener itemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.itemClickListener = itemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener!=null){
                itemClickListener.onItemClick(v,getPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}

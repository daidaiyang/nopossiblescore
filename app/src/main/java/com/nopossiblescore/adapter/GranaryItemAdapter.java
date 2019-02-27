package com.nopossiblescore.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nopossiblescore.R;
import com.nopossiblescore.customview.ShadowDrawable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GranaryItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public GranaryItemAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_granarysearch, viewGroup, false);
        return new ViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        ShadowDrawable.setShadowDrawable(holder.root, Color.parseColor("#ffffff"),
                (int) mContext.getResources().getDimension(R.dimen.x15),
                Color.parseColor("#337C7C7C"),
                (int) mContext.getResources().getDimension(R.dimen.x15),
                0, 0);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_granary_time)
        TextView time;
        @BindView(R.id.item_granary_type)
        TextView type;
        @BindView(R.id.item_granary_code)
        TextView code;
        @BindView(R.id.item_granary_copy)
        TextView copy;
        @BindView(R.id.item_granary_code_ll)
        LinearLayout codeLl;
        @BindView(R.id.item_granary_num1)
        TextView num1;
        @BindView(R.id.item_granary_num2)
        TextView num2;
        @BindView(R.id.item_granary_root)
        LinearLayout root;

        private OnItemClickListener itemClickListener;

        ViewHolder(View view,OnItemClickListener itemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.itemClickListener = itemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener !=null){
                itemClickListener.onItemClick(v,getPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
}

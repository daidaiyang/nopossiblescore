package com.nopossiblescore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nopossiblescore.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageItemAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mData;

    private OnItemClickListener itemClick;

    public void setItemClick(OnItemClickListener itemClick) {
        this.itemClick = itemClick;
    }

    public MessageItemAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_message, viewGroup, false);
        return new ViewHolder(view,itemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
    }

    @Override
    public int getItemCount() {
        return mData ==null?0:mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.message_unread)
        ImageView messageUnread;
        @BindView(R.id.message_title)
        TextView messageTitle;
        @BindView(R.id.message_content)
        TextView messageContent;
        @BindView(R.id.message_time)
        TextView messageTime;

        private OnItemClickListener itemClick;

        ViewHolder(View view,OnItemClickListener itemClick) {
            super(view);
            ButterKnife.bind(this, view);
            this.itemClick = itemClick;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClick != null){
                itemClick.onItemClick(v,getPosition());
            }
        }
    }


    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
}

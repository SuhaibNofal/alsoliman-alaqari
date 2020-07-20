package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sulimanalaqaria.user.sulimanalaqaria.Module.NotificationClm;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    ArrayList<NotificationClm> mList;
    Context context;

    public NotificationAdapter(Context context, ArrayList<NotificationClm> mList) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification_row, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        NotificationClm notify = mList.get(position);
        holder.tvTitle.setText(notify.getNotyTitle()+" "+notify.getMaintLoginID());
        holder.tvBody.setText(notify.getNotyBody());
        holder.tvTitle.setTag(notify.getReqType() + "//" + notify.getReqNo());
        holder.tvBody.setTag(notify.getClose());
        holder.imageView.setTag(notify.getRating());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_notify_Title);
            tvBody = itemView.findViewById(R.id.tv_notify_body);
            imageView=itemView.findViewById(R.id.img_notify);

        }
    }
}

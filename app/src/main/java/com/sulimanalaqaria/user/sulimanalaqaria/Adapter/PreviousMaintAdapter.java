package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.MaintPreviousRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.PPreviousMaintOrder;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;

public class PreviousMaintAdapter extends RecyclerView.Adapter<PreviousMaintAdapter.ViewHolder> {
    ArrayList<MaintPreviousRequest> mlist;
    Context mcontext;

    public PreviousMaintAdapter(ArrayList<MaintPreviousRequest> mlist, Context mcontext) {
        this.mlist = mlist;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prevous_maint_order, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvmaintType.setText(mlist.get(position).getMaintTypeAr());
        holder.tvmaintService.setText(mlist.get(position).getServiceName());
        holder.tvmaintstatus.setText(mlist.get(position).getStatusNameAr().toString());
        holder.tvordermaintno.setText(String.valueOf(mlist.get(position).getReqNo()));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvmaintType, tvmaintService, tvordermaintno, tvmaintstatus, tvorderNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmaintType = itemView.findViewById(R.id.tv_Type);
            tvmaintService = itemView.findViewById(R.id.tv_service);
            tvmaintstatus = itemView.findViewById(R.id.tv_maint_status);
            tvordermaintno = itemView.findViewById(R.id.tv_order_maint_no);
        }
    }
}

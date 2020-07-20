package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.VisitPreviouseRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;

public class PreviouseVisitAdapter extends RecyclerView.Adapter<PreviouseVisitAdapter.ViewHolder> {
    ArrayList<VisitPreviouseRequest> list;
    Context mContext;

    public PreviouseVisitAdapter(ArrayList<VisitPreviouseRequest> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_previouse_visit_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDateVisit.setText(list.get(position).getReqDate().toString());
        holder.tvVisitorName.setText(list.get(position).getVisitName());
        holder.tvVisitTime.setText(list.get(position).getVisitTime().toString());
        holder.tvVisitResone.setText(list.get(position).getReasArbName().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDateVisit, tvVisitorName, tvVisitTime, tvVisitResone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDateVisit = itemView.findViewById(R.id.tv_visit_date_order);
            tvVisitorName = itemView.findViewById(R.id.tv_visit_visitor_name);
            tvVisitTime = itemView.findViewById(R.id.tv_visit_time);
            tvVisitResone = itemView.findViewById(R.id.tv_visit_reasone);
        }
    }
}

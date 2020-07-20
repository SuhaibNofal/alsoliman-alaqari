package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.DetailsRentUnitModel;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;

public class DetailsRentUnitAdapter extends RecyclerView.Adapter<DetailsRentUnitAdapter.DetailsRentHolder> {
ArrayList<DetailsRentUnitModel>list;
Context context;

    public DetailsRentUnitAdapter(ArrayList<DetailsRentUnitModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailsRentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_rent, parent,false);
        DetailsRentHolder viewHolder = new DetailsRentHolder(view,context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsRentHolder holder, int position) {
        DetailsRentUnitModel RentDetails = list.get(position);
        holder.mTenantName.setText(RentDetails.getTenantName());
        holder.mRentStart.setText(RentDetails.getRentStrtDate());
        holder.mRentEnd.setText(RentDetails.getRentEndDate());
        holder.mAmountRent.setText(RentDetails.getRentAmt());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DetailsRentHolder extends RecyclerView.ViewHolder {
        public TextView mTenantName, mRentStart, mRentEnd,mAmountRent;
        Context context;

        public DetailsRentHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            mTenantName = itemView.findViewById(R.id.var_tv_tenant_name);
            mRentStart = itemView.findViewById(R.id.var_tv_Rentstart);
            mRentEnd = itemView.findViewById(R.id.var_tv_rent_end);
            mAmountRent= itemView.findViewById(R.id.var_tv_rent_amount);
        }
    }
}

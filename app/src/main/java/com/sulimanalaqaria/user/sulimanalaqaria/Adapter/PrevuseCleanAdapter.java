package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.CleanPreviousRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.PPreviousCleanOrder;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PrevuseCleanAdapter extends RecyclerView.Adapter<PrevuseCleanAdapter.RecyclerViewHolder> {

    ArrayList<CleanPreviousRequest> list;
    Context mcontext;

    public PrevuseCleanAdapter(ArrayList<CleanPreviousRequest> list, Context mcontext) {
        this.list = list;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_prevuse_clean_row, viewGroup, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {

        recyclerViewHolder.txt1.setText(list.get(i).getCleanTypeAr());
        recyclerViewHolder.txt2.setText(list.get(i).getServiceName());
        recyclerViewHolder.txt3.setText(list.get(i).getStatusNameAr());
        recyclerViewHolder.txt4.setText(String.valueOf(list.get(i).getReqNo()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView txt1, txt2, txt3, txt4, txt5;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.clean_type_view);
            txt2 = itemView.findViewById(R.id.txt_type_service);
            txt3 = itemView.findViewById(R.id.txt_clean_status);
            txt4 = itemView.findViewById(R.id.txt_order_no);

        }
    }
}

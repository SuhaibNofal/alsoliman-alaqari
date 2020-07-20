package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;
import android.content.Intent;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sulimanalaqaria.user.sulimanalaqaria.MaintenanceOrder;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.SservisesActivity;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicHolder> {
    ArrayList<SservisesActivity> list;
    Context mcontext;

    public ServicesAdapter(ArrayList<SservisesActivity> list, Context mcontext) {
        this.list = list;
        this.mcontext = mcontext;
    }



    @NonNull
    @Override
    public ServicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_custom_row, viewGroup, false);

        ServicHolder recyclerViewHolder = new ServicHolder(view,mcontext);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServicHolder servicHolder, int i) {
        SservisesActivity sservisesActivity = list.get(i);
        servicHolder.mImageView.setImageResource(sservisesActivity.getImageview());
        servicHolder.mTextView.setText(sservisesActivity.getTextView());
        servicHolder.mButton.setTag(sservisesActivity.getIdServes());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ServicHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;
        public Button mButton;
        Context context;

        public ServicHolder(View itemView, final Context context) {
            super(itemView);
            this.context=context;
            mImageView = itemView.findViewById(R.id.servises_image);
            mTextView = itemView.findViewById(R.id.text_servises);
            mButton = itemView.findViewById(R.id.bu_servises);

            /*mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   *//* Intent intent=new Intent(context,MaintenanceOrder.class);
                    startActivity(intent);*//*
                    Toast.makeText(context, mTextView.getText(), Toast.LENGTH_SHORT).show();
                }
            });*/
        }


    }
}

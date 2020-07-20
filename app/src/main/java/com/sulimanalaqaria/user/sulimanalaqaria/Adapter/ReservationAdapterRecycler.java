package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.ReservatinAvailable;
import com.sulimanalaqaria.user.sulimanalaqaria.DetailsReservation;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;

public class ReservationAdapterRecycler extends RecyclerView.Adapter<ReservationAdapterRecycler.RecyclerViewHolder> {
    ArrayList<ReservatinAvailable> list;
    Context mcontext;

    public ReservationAdapterRecycler(ArrayList<ReservatinAvailable> list, Context context) {
        this.list = list;
        mcontext = context;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextView = itemView.findViewById(R.id.name_filed);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_in_recycler, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int BookingType =Integer.parseInt(view.findViewById(R.id.name_filed).getTag().toString());
                String CostBooking =view.findViewById(R.id.image_view).getTag().toString();
                TextView mTextView =view.findViewById(R.id.name_filed);
                String BookingName =mTextView.getText().toString();
                Intent intent =new Intent(mcontext, DetailsReservation.class);
                intent.putExtra("BookingType",BookingType);
                intent.putExtra("BookAmount",CostBooking);
                intent.putExtra("BookingName",BookingName);
                mcontext.startActivity(intent);
            }
        });
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int i) {
        ReservatinAvailable rreservation = list.get(i);
        //recyclerViewHolder.mImageView.setImageResource(rreservation.getImageView());
        //recyclerViewHolder.mImageView.setImageResource(rreservation.getImageView());
        recyclerViewHolder.mTextView.setText(rreservation.getName());
        recyclerViewHolder.mTextView.setTag(rreservation.getID());
        recyclerViewHolder.mImageView.setTag(rreservation.getCostBooking());
        if (rreservation.getID().equalsIgnoreCase("1"))
            recyclerViewHolder.mImageView.setImageResource(R.drawable.bussnis);
        else if (rreservation.getID().equalsIgnoreCase("2"))
            recyclerViewHolder.mImageView.setImageResource(R.drawable.mnsbat);
        else if(rreservation.getID().equalsIgnoreCase("3"))
            recyclerViewHolder.mImageView.setImageResource(R.drawable.majls);
        else if(rreservation.getID().equalsIgnoreCase("4"))
            recyclerViewHolder.mImageView.setImageResource(R.drawable.gym);
        else
            recyclerViewHolder.mImageView.setImageResource(R.drawable.set_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

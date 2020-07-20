package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.ReservationAvailableTime;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.DdetailReservation;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;

public class DetailsReservationAdapter extends RecyclerView.Adapter<DetailsReservationAdapter.DetailsHolder> {
ArrayList<ReservationAvailableTime>mlist;
Context mContext;
int selected_item =0;
    public IMyViewHolderClicks iMyViewHolderClicks;
    public DetailsReservationAdapter(ArrayList<ReservationAvailableTime> mlist, Context mContext,IMyViewHolderClicks mm) {
        this.mlist = mlist;
        this.mContext = mContext;
        iMyViewHolderClicks=mm;
    }

    public DetailsReservationAdapter(ArrayList<ReservationAvailableTime> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;

    }
    @NonNull
    @Override
    public DetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_reservation, parent, false);
        DetailsHolder holder =new DetailsHolder(view, mContext);
        //view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsHolder holder, int position) {
        ReservationAvailableTime detail =mlist.get(position);
        holder.mDay.setText(ConvertIntegerDay(detail.getDay()));
        holder.mTimeFrom.setText(detail.getFHour().toString());
        holder.mTimeTo.setText(detail.getTHour());
        holder.mDay.setTag(detail.getID());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class DetailsHolder extends RecyclerView.ViewHolder  {
        public TextView mDay,mTimeFrom,mTimeTo;
        Context context;

        public DetailsHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.context=context;
            mDay=itemView.findViewById(R.id.tv_day);
            mTimeFrom=itemView.findViewById(R.id.tv_fromHour);
            mTimeTo=itemView.findViewById(R.id.tv_ToHour);
        }



    }

     private String ConvertIntegerDay(int Day){
        switch (Day){
            case 1 :
                return "الأحد";
            case 2 :
                return "الإثنين";
            case 3 :
                return "الثلاثاء";
            case 4:
                return "الأربعاء";
            case 5 :
                return "الخميس";
            case 6 :
                return "الجمعة";
            case 7 :
                return "السبت";
                default:
                    return "";
        }
     }
}

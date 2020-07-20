package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sulimanalaqaria.user.sulimanalaqaria.DetailsRentUnit;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.customerInformationdetails;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;

public class InformationAdapter extends BaseAdapter {
ArrayList<customerInformationdetails>list;
Context context;
int UserType;

    public InformationAdapter(ArrayList<customerInformationdetails> list, Context context,int UserType) {
        this.list = list;
        this.context = context;
        this.UserType=UserType;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_information, parent, false);
        final TextView tvUnit,tvArea,tvBalance,tvRentAmount,tvContrDate,tvRentStart,tvRentEnd,tvElectric,tvCard,tv_show_detail,tv_NumberofCardsReceived;
        tvUnit=view.findViewById(R.id.tv_var_Unit);
        tvUnit.setText(list.get(position).getOprNo());
        tvUnit.setTag(list.get(position).getUnitNo());
        tvArea=view.findViewById(R.id.tv_var_Totalarea);
        tvArea.setText(String.valueOf(list.get(position).getArea()));
        tvBalance=view.findViewById(R.id.tv_var_Balance);
        tvBalance.setText(list.get(position).getBalance());
        tvRentAmount=view.findViewById(R.id.tv_var_RentAmt);
        tvRentAmount.setText(list.get(position).getRentAmt());
        tvContrDate=view.findViewById(R.id.tv_var_ContrDate);
        tvContrDate.setText(list.get(position).getContrDate());
        tvRentStart=view.findViewById(R.id.tv_var_RentStrtDate);
        tvRentStart.setText(list.get(position).getRentStrtDate());
        tvRentEnd=view.findViewById(R.id.tv_var_RentEndDate);
        tvRentEnd.setText(list.get(position).getRentEndDate());
        tvElectric=view.findViewById(R.id.tv_var_ElectricityMeterNumber);
        tvElectric.setText("");
        tvCard=view.findViewById(R.id.tv_var_NumberofCardsReceived);
        tvCard.setText("");
        tv_NumberofCardsReceived=view.findViewById(R.id.tv_NumberofCardsReceived);
        tv_show_detail=view.findViewById(R.id.tv_show_detail);
        if (UserType==0)
        {
            tv_NumberofCardsReceived.setVisibility(View.INVISIBLE);
            tv_show_detail.setVisibility(View.VISIBLE);
        }else{
            tv_NumberofCardsReceived.setVisibility(View.VISIBLE);
            tv_show_detail.setVisibility(View.GONE);
        }

        tv_show_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, DetailsRentUnit.class);
                intent.putExtra("UnitNo",tvUnit.getTag().toString());
                context.startActivity(intent);
            }
        });


        return view;
    }
}

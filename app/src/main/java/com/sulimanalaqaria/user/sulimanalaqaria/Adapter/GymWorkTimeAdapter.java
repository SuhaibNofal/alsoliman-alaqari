package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.GymTimeModule;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;
import java.util.List;

public class GymWorkTimeAdapter extends BaseAdapter {
    private ArrayList<GymTimeModule>list;
    Context context;

    public GymWorkTimeAdapter(ArrayList<GymTimeModule> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_gem_work_time,viewGroup,false);
        TextView tvDay,tvGender,tvFHour,tvTHour;
       tvDay=v.findViewById(R.id.tv_gym_day);
       tvGender=v.findViewById(R.id.tv_gym_gender);
       tvFHour=v.findViewById(R.id.tv_gym_fhour);
       tvTHour=v.findViewById(R.id.tv_gym_thour);
        tvDay.setText(getDay(list.get(i).getDay()));
        tvGender.setText(getGender(list.get(i).getGender()));
        tvFHour.setText(list.get(i).getFHour());
        tvTHour.setText(list.get(i).getTHour());
        return v;
    }

    private String getDay(int day){
        String txDay="";
        if (Constant.lang.equalsIgnoreCase("ar")){
            switch (day) {
                case 1:
                    txDay = "الأحد";
                    break;
                case 2:
                    txDay = "الإثنين";
                    break;
                case 3:
                    txDay = "الثلاثاء";
                    break;
                case 4:
                    txDay = "الأربعاء";
                    break;
                case 5:
                    txDay = "الخميس";
                    break;
                case 6:
                    txDay = "الجمعة";
                    break;
                case 7:
                    txDay = "السبت";
                    break;

            }
        }else {
            switch (day) {
                case 1:
                    txDay = "Sunday";
                    break;
                case 2:
                    txDay = "Monday";
                    break;
                case 3:
                    txDay = "Tuesday";
                    break;
                case 4:
                    txDay = "Wednesday";
                    break;
                case 5:
                    txDay = "Thursday";
                    break;
                case 6:
                    txDay = "Friday";
                    break;
                case 7:
                    txDay = "Saturday";
                    break;

            }
        }
        return txDay;
    }
    private String getGender(int Gender){
        String txGender="";
        if (Constant.lang.equalsIgnoreCase("ar")){
            switch (Gender) {
                case 1:
                    txGender = "رجال";
                    break;
                case 2:
                    txGender = "نساء";
                    break;
            }
        }else {
            switch (Gender) {
                case 1:
                    txGender = "Men";
                    break;
                case 2:
                    txGender = "Women";
                    break;
            }
        }
        return txGender;
    }
}

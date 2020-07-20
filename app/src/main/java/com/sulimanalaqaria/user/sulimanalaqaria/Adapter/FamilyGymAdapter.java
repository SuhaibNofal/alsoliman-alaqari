package com.sulimanalaqaria.user.sulimanalaqaria.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.PFamilyHelthClubCls;
import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.ArrayList;

public class FamilyGymAdapter extends BaseAdapter {
    private Context mcontext;
    private ArrayList<PFamilyHelthClubCls> list;
    private static LayoutInflater inflater = null;

    public FamilyGymAdapter(Context mcontext, ArrayList<PFamilyHelthClubCls> lPerson) {
        this.mcontext = mcontext;
        this.list = lPerson;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =LayoutInflater.from(mcontext).inflate(R.layout.gym_layout,parent,false);
        TextView tvv =v.findViewById(R.id.textView11);
        CheckBox checkBox=v.findViewById(R.id.checkgym);

        v.setTag(list.get(position).getRowID());
        tvv.setText(list.get(position).getFamName().toString());
        checkBox.setChecked(list.get(position).isApporvlogin());

        return v;
    }
}

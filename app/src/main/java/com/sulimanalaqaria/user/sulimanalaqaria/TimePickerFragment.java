package com.sulimanalaqaria.user.sulimanalaqaria;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    Calendar c =Calendar.getInstance();
    int hour =c.get(Calendar.HOUR_OF_DAY);
    int minut =c.get(Calendar.MINUTE);
    int day =c.get(Calendar.DAY_OF_MONTH);

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(),R.style.datepicker,(TimePickerDialog.OnTimeSetListener)getActivity(),hour,minut, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }
}

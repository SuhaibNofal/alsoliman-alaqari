package com.sulimanalaqaria.user.sulimanalaqaria.Module;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.sulimanalaqaria.user.sulimanalaqaria.R;

import java.util.Calendar;
import java.util.Locale;

public class Helper extends Activity {
    AlertDialog.Builder dialogBuilder;
    AlertDialog b;

    public void FullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public String addZeroToDayandMonth(int value) {
        String NumberConverted = "";
        if (value < 10) {
            NumberConverted = "0" + value;
        } else {
            NumberConverted = String.valueOf(value);
        }
        return NumberConverted;
    }


    public String getDateToday() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String month = "0";
        String day = "0";
        if (mMonth < 10) {
            month = "0" + mMonth;
        } else {
            month = String.valueOf(mMonth);
        }
        if (mDay < 10) {
            day = "0" + mDay;
        } else {
            day = String.valueOf(mDay);
        }
        String date = day + "-" + month + "-" + mYear;
        return date;
    }

    public Boolean checkString(String text, Context context) {
        Boolean b = false;
        if (text.matches("0")) {
            //Toast.makeText(context, "matches", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.compareToIgnoreCase("0") == 0) {
            // Toast.makeText(context, "compareToIgnoreCase", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.contentEquals("0")) {
            //Toast.makeText(context, "contentEquals", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.trim().isEmpty()) {
            //Toast.makeText(context, ".trim().isEmpty()", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.equalsIgnoreCase("0")) {
            //Toast.makeText(context, "equalsIgnoreCase", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text == "0") {
            //Toast.makeText(context, "==0", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.compareTo("0") == 0) {
            //Toast.makeText(context, "compareTo", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.trim().equals("0")) {
            //Toast.makeText(context, "trim().equals(0)", Toast.LENGTH_SHORT).show();
            b = true;
        } else {
            b = false;
        }

        return b;
    }

    public Boolean checkStringEmpty(String text, Context context) {
        Boolean b = false;
        if (text.matches("")) {
            //Toast.makeText(context, "matches", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.compareToIgnoreCase("") == 0) {
            // Toast.makeText(context, "compareToIgnoreCase", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.contentEquals("")) {
            //Toast.makeText(context, "contentEquals", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.trim().isEmpty()) {
            //Toast.makeText(context, ".trim().isEmpty()", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.equalsIgnoreCase("")) {
            //Toast.makeText(context, "equalsIgnoreCase", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text == "") {
            //Toast.makeText(context, "==0", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.compareTo("") == 0) {
            //Toast.makeText(context, "compareTo", Toast.LENGTH_SHORT).show();
            b = true;
        } else if (text.trim().equals("")) {
            //Toast.makeText(context, "trim().equals(0)", Toast.LENGTH_SHORT).show();
            b = true;
        } else {
            b = false;
        }

        return b;
    }

    public void ShowProgressDialog(Context activity) {
        dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.progressdialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        b = dialogBuilder.create();
        b.show();
    }

    public void HideProgressDialog() {
        b.dismiss();
    }

    public void setLocale(Context ctx, String Lang) {

        String lang = Lang;
        Locale locale = new Locale(lang);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Configuration conf = ctx.getResources().getConfiguration();
            conf.setLocale(locale);
            // ctx.getResources().updateConfiguration(conf, ctx.getResources().getDisplayMetrics());
            ctx.createConfigurationContext(conf);
            Configuration systemConf = Resources.getSystem().getConfiguration();
            systemConf.setLocale(locale);
            systemConf.setLayoutDirection(new Locale(lang));
            //Resources.getSystem().updateConfiguration(systemConf, Resources.getSystem().getDisplayMetrics());
            Resources.getSystem().getConfiguration();
            Locale.setDefault(locale);
        } else {

            Configuration conf = ctx.getResources().getConfiguration();
            conf.locale = locale;
            conf.setLayoutDirection(new Locale(lang));
            ctx.getResources().updateConfiguration(conf, ctx.getResources().getDisplayMetrics());

            Configuration systemConf = Resources.getSystem().getConfiguration();
            systemConf.locale = locale;
            Resources.getSystem().updateConfiguration(systemConf, Resources.getSystem().getDisplayMetrics());
            Locale.setDefault(locale);
        }

    }
}

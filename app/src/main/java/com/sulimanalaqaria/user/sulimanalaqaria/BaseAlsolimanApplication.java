package com.sulimanalaqaria.user.sulimanalaqaria;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class BaseAlsolimanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setLocale(getApplicationContext());
    }

    void setLocale(Context ctx) {

        Locale locale = new Locale("en");
        Configuration conf = ctx.getResources().getConfiguration();
        conf.locale = locale;
       // ctx.getResources().updateConfiguration(conf, ctx.getResources().getDisplayMetrics());
        ctx.createConfigurationContext(conf);
        Configuration systemConf = Resources.getSystem().getConfiguration();
        systemConf.locale = locale;
        //Resources.getSystem().updateConfiguration(systemConf, Resources.getSystem().getDisplayMetrics());
        Resources.getSystem().getConfiguration();
        Locale.setDefault(locale);
    }

}

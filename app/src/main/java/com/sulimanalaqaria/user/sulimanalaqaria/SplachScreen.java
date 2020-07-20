package com.sulimanalaqaria.user.sulimanalaqaria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;

import java.util.Locale;

public class SplachScreen extends LocalizeActivity {
    Boolean result = false;
Activity activity ;
private static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splach_screen);

        /*Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1200);
                  *//*  if (result) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{*//*
                        Intent intent = new Intent(getApplicationContext(), LogIn.class);
                        startActivity(intent);
                    //}


                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MyAppPreference.getInstance(getApplicationContext()).isAutoLogin()){
                    Constant.lang=MyAppPreference.getInstance(SplachScreen.this).get_selected_lang();
                    Log.d("From" ,"ssssssssssssssssssssssssssssssssssssssssssss");
                    Intent intent =new Intent(SplachScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Constant.lang=MyAppPreference.getInstance(SplachScreen.this).get_selected_lang();
                    Log.d("From" ,"ssssssssssssssssssssssssssssssssssssssssssss");
                    Intent intent =new Intent(SplachScreen.this,LogIn.class);
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
    }

    void setLocale(Context ctx, String lang) {

        Locale locale = new Locale(lang);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Configuration conf = ctx.getResources().getConfiguration();
            conf.setLocale(locale);
            conf.setLayoutDirection(locale);
            // ctx.getResources().updateConfiguration(conf, ctx.getResources().getDisplayMetrics());
            ctx.createConfigurationContext(conf);
            Configuration systemConf = Resources.getSystem().getConfiguration();
            systemConf.setLocale(locale);
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

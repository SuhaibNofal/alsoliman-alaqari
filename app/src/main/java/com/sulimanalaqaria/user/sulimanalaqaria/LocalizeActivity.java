package com.sulimanalaqaria.user.sulimanalaqaria;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LocalizeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res =getResources();
        DisplayMetrics dm=res.getDisplayMetrics();
        android.content.res.Configuration conf=res.getConfiguration();
        String savedLanguage =MyAppPreference.getInstance(this).get_selected_lang();
        if(savedLanguage !=null && savedLanguage!=""){
            if(savedLanguage.equalsIgnoreCase("en"))
                conf.setLocale(new Locale("en"));
            else if (savedLanguage.equalsIgnoreCase("ar"))
                conf.setLocale(new Locale("ar"));
            else
                conf.setLocale(new Locale("en"));

            res.updateConfiguration(conf,dm);
   /*         getApplicationContext().createConfigurationContext(conf);

            //Resources.getSystem().updateConfiguration(systemConf, Resources.getSystem().getDisplayMetrics());
            Resources.getSystem().getConfiguration();*/

        }

    }
}

package com.sulimanalaqaria.user.sulimanalaqaria;

import android.content.Context;
import android.content.SharedPreferences;

public class ShaerdPrefrenc {
static SharedPreferences sharedPref;

    public static void saveInt(Context context, String key, String value) {
         sharedPref = context.getSharedPreferences("lang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
        editor.apply();
    }
    public static void saveIntinformation(Context context, String key, String value) {
         sharedPref = context.getSharedPreferences("lang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
        editor.apply();
    }

    public static String getShared(Context context,String Value) {
         sharedPref = context.getSharedPreferences("lang", Context.MODE_PRIVATE);
        String mlang = sharedPref.getString(Value, "");
        return mlang;
    }
    public static void deleteShared(Context context) {
         sharedPref = context.getSharedPreferences("lang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("name").commit();
        editor.apply();
    }
    public static void deleteSharedall(Context context) {
        sharedPref = context.getSharedPreferences("lang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("ProjID").commit();
        editor.remove("UserType").commit();
        editor.remove("CustID").commit();
        editor.remove("UserName").commit();
        editor.remove("name").commit();
        editor.apply();
    }

}

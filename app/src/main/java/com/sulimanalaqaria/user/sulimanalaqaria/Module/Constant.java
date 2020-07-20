package com.sulimanalaqaria.user.sulimanalaqaria.Module;

import com.sulimanalaqaria.user.sulimanalaqaria.WebApiServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Constant {
    public static final String KEY_LANG ="lang" ;
  //  public static String BASE_URL = "http://are.alsoliman.com.sa/testconection/WebServiceALs.asmx/";
    public static String BASE_URL = "http://104.227.250.230/testconection/WebServiceALs.asmx/";
    public static String ProjID;
    public static String UserType;
    public static String CustID;
    public static String UserName;
    public static String AccNo;
    public static String KEY_ProjID="ProjID";
    public static String KEY_UserType="UserType";
    public static String KEY_CustID="CustID";
    public static String KEY_UserName="UserName";
    public static String KEY_AccNo="Acc_No";
    public static String lang;
    public static  int count=3;

    public static WebApiServices getApi() {
        final Retrofit adapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Creating an object of our api interface
        WebApiServices api = adapter.create(WebApiServices.class);
        return api;
    }
}

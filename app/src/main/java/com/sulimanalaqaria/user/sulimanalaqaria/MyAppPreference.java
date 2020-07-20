package com.sulimanalaqaria.user.sulimanalaqaria;

import android.content.Context;
import android.content.SharedPreferences;

import com.sulimanalaqaria.user.sulimanalaqaria.Module.Constant;

public class MyAppPreference {
    private static MyAppPreference mInstance =null;
    private static  SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private Context context;
    private  static String SharedPreferenceKey ="Alsoliman";
    private MyAppPreference(){

    }
    public static MyAppPreference getInstance(Context context){
        if(mInstance== null){
            mInstance=new MyAppPreference();
        }
        if(mPreferences ==null){
            mPreferences=context.getApplicationContext().getSharedPreferences(SharedPreferenceKey,Context.MODE_PRIVATE);
            mEditor=mPreferences.edit();
        }
        return mInstance;
    }
    public void saveInPreference(String key,String value){
        mEditor.putString(key,value);
        mEditor.commit();
    }
    public String getFromPreference(String key){return mPreferences.getString(key,"");}

    public Boolean isAutoLogin(){
        String projID=mPreferences.getString(Constant.KEY_ProjID,"");
        if(projID!=null && !projID.equals("")){
            return true;
        }else {return false;}
    }
    public String get_selected_lang(){
        String key_lang_user ="";
        key_lang_user = Constant.KEY_LANG;
        String select_lang=mPreferences.getString(key_lang_user,"");
        return select_lang;
    }
    public void save_selected_lang(String lang){
        String key_lang_user ="";
        key_lang_user = Constant.KEY_LANG;
        mEditor.putString(key_lang_user,lang);
        Constant.lang=lang;
        mEditor.commit();
    }
    public void save_user_details(String ProjID,String UserType,String CustID,String UserName ,String AccNo){
        String key_projid_user ="";
        String key_UserType_user ="";
        String key_CustID_user ="";
        String key_UserName_user ="";
        String key_AccNo_user ="";
        key_projid_user  = Constant.KEY_ProjID;
        key_UserType_user =  Constant.KEY_UserType;
        key_CustID_user  =Constant.KEY_CustID;
        key_UserName_user = Constant.KEY_UserName;
        key_AccNo_user =Constant.KEY_AccNo;
        mEditor.putString(key_projid_user,ProjID);
        mEditor.putString(key_UserType_user,UserType);
        mEditor.putString(key_CustID_user,CustID);
        mEditor.putString(key_UserName_user,UserName);
        mEditor.putString(key_AccNo_user,AccNo);
        mEditor.commit();
    }
    public String get_user_projid(){
        String key_lang_user ="";
        key_lang_user = Constant.KEY_ProjID;
        String select_lang=mPreferences.getString(key_lang_user,"");
        return select_lang;
    }
    public String get_user_usertype(){
        String key_lang_user ="";
        key_lang_user = Constant.KEY_UserType;
        String select_lang=mPreferences.getString(key_lang_user,"");
        return select_lang;
    }
    public String get_user_custid(){
        String key_lang_user ="";
        key_lang_user = Constant.KEY_CustID;
        String select_lang=mPreferences.getString(key_lang_user,"");
        return select_lang;
    }
    public String get_user_username(){
        String key_lang_user ="";
        key_lang_user = Constant.KEY_UserName;
        String select_lang=mPreferences.getString(key_lang_user,"");
        return select_lang;
    }
    public String get_user_Acc(){
        String key_lang_user ="";
        key_lang_user = Constant.KEY_AccNo;
        String select_lang=mPreferences.getString(key_lang_user,"");
        return select_lang;
    }
}

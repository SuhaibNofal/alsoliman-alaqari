package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class InsertResult {
    @SerializedName("Result")
   private String Result;

    public String getResult(){
        return Result;
    }
    public void setResult(String Result){
        this.Result=Result;
    }
}

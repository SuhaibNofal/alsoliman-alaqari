package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class CleanType {
    @SerializedName("CleanTypeID")
    private int CleanTypeID;
    @SerializedName("CleanTypeAr")
    private String CleanTypeAr;
    @SerializedName("CleanTypeEn")
    private String CleanTypeEn;
    @SerializedName("AccNo")
    private String AccNo;
    @SerializedName("ProjID")
    private String ProjID;
    public int getCleanTypeID() {
        return CleanTypeID;
    }

    public void setCleanTypeID(int cleanTypeID) {
        this.CleanTypeID = cleanTypeID;
    }

    public String getCleanTypeAr() {
        return CleanTypeAr;
    }

    public void setCleanTypeAr(String CleanTypeAr) {
        this.CleanTypeAr = CleanTypeAr;
    }

    public String getCleanTypeEn() {
        return CleanTypeEn;
    }

    public void setCleanTypeEn(String CleanTypeEn) {
        this.CleanTypeEn = CleanTypeEn;
    }

    public String getAccNo() {
        return AccNo;
    }

    public void setAccNo(String accNo) {
        this.AccNo = accNo;
    }

    public String getProjID() {
        return ProjID;
    }

    public void setProjID(String projID) {
        this.ProjID = projID;
    }

}

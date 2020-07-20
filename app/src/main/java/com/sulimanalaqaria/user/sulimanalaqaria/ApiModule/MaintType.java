package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class MaintType {
    @SerializedName("MainTypeID")
    private int MainTypeID;
    @SerializedName("MaintTypeAr")
    private String MaintTypeAr;
    @SerializedName("MaintTypeEn")
    private String MaintTypeEn;
    @SerializedName("AccNo")
    private String AccNo;
    @SerializedName("ProjID")
    private String ProjID;
    public int getMainTypeID() {
        return MainTypeID;
    }

    public void setMainTypeID(int mainTypeID) {
        MainTypeID = mainTypeID;
    }

    public String getMaintTypeAr() {
        return MaintTypeAr;
    }

    public void setMaintTypeAr(String maintTypeAr) {
        MaintTypeAr = maintTypeAr;
    }

    public String getMaintTypeEn() {
        return MaintTypeEn;
    }

    public void setMaintTypeEn(String maintTypeEn) {
        MaintTypeEn = maintTypeEn;
    }

    public String getAccNo() {
        return AccNo;
    }

    public void setAccNo(String accNo) {
        AccNo = accNo;
    }

    public String getProjID() {
        return ProjID;
    }

    public void setProjID(String projID) {
        ProjID = projID;
    }


}

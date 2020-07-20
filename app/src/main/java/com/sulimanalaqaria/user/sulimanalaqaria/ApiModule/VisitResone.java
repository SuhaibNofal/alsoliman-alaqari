package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class VisitResone {

    @SerializedName("ReasNo")
    private int ReasNo;
    @SerializedName("ReasArbName")
    private String ReasArbName;
    @SerializedName("ReasEngName")
    private String ReasEngName;

    public int getReasNo() {
        return ReasNo;
    }

    public void setReasNo(int reasNo) {
        ReasNo = reasNo;
    }

    public String getReasArbName() {
        return ReasArbName;
    }

    public void setReasArbName(String reasArbName) {
        ReasArbName = reasArbName;
    }

    public String getReasEngName() {
        return ReasEngName;
    }

    public void setReasEngName(String reasEngName) {
        ReasEngName = reasEngName;
    }
}

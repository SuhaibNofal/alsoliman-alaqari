package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class VisitPreviouseRequest {
    @SerializedName("ReqNo")
    private int ReqNo;
    @SerializedName("ReqDate")
    private String ReqDate;
    @SerializedName("SNumber")
    private String SNumber;
    @SerializedName("VisitName")
    private String VisitName;
    @SerializedName("VisitTime")
    private String VisitTime;
    @SerializedName("ReasArbName")
    private String ReasArbName;
    @SerializedName("ReasEngName")
    private String ReasEngName;
    @SerializedName("Customers")
    private String Customers;
    @SerializedName("ProjID")
    private int ProjID;

    public int getReqNo() {
        return ReqNo;
    }

    public void setReqNo(int reqNo) {
        ReqNo = reqNo;
    }

    public String getReqDate() {
        return ReqDate;
    }

    public void setReqDate(String reqDate) {
        ReqDate = reqDate;
    }

    public String getSNumber() {
        return SNumber;
    }

    public void setSNumber(String SNumber) {
        this.SNumber = SNumber;
    }

    public String getVisitName() {
        return VisitName;
    }

    public void setVisitName(String visitName) {
        VisitName = visitName;
    }

    public String getVisitTime() {
        return VisitTime;
    }

    public void setVisitTime(String visitTime) {
        VisitTime = visitTime;
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

    public String getCustomers() {
        return Customers;
    }

    public void setCustomers(String customers) {
        Customers = customers;
    }

    public int getProjID() {
        return ProjID;
    }

    public void setProjID(int projID) {
        ProjID = projID;
    }


}

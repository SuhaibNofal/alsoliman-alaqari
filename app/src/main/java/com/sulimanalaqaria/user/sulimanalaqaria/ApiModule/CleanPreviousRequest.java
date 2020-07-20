package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class CleanPreviousRequest {
    @SerializedName("ReqNo")
    private int ReqNo;
    @SerializedName("CleanTypeAr")
    private String CleanTypeAr;
    @SerializedName("ServiceName")
    private String ServiceName;
    @SerializedName("StatusNameAr")
    private String StatusNameAr;
    @SerializedName("StatusNameEn")
    private String StatusNameEn;

    public int getReqNo() {
        return ReqNo;
    }

    public void setReqNo(int reqNo) {
        ReqNo = reqNo;
    }

    public String getCleanTypeAr() {
        return CleanTypeAr;
    }

    public void setCleanTypeAr(String cleanTypeAr) {
        CleanTypeAr = cleanTypeAr;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getStatusNameAr() {
        return StatusNameAr;
    }

    public void setStatusNameAr(String statusNameAr) {
        StatusNameAr = statusNameAr;
    }

    public String getStatusNameEn() {
        return StatusNameEn;
    }

    public void setStatusNameEn(String statusNameEn) {
        StatusNameEn = statusNameEn;
    }
}

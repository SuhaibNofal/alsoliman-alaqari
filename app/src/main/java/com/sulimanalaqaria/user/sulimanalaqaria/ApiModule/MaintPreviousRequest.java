package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class MaintPreviousRequest {
    @SerializedName("ReqNo")
         private int ReqNo;
    @SerializedName("OprNo")
         private String OprNo;
    @SerializedName("MaintTypeAr")
         private String MaintTypeAr;
    @SerializedName("MaintTypeEn")
         private String MaintTypeEn;
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

    public String getOprNo() {
        return OprNo;
    }

    public void setOprNo(String oprNo) {
        OprNo = oprNo;
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

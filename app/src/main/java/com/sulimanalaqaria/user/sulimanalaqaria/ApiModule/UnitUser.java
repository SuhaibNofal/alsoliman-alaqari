package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class UnitUser {
    @SerializedName("UnitNo")
    private int UnitNo;
    @SerializedName("UnitType")
    private int UnitType;
    @SerializedName("OprNo")
    private String OprNo;
    @SerializedName("Owner")
    private int Owner;
    @SerializedName("SaleNo")
    private int SaleNo;
    @SerializedName("ProjID")
    private int ProjID;


    public int getUnitNo() {
        return UnitNo;
    }

    public void setUnitNo(int unitNo) {
        UnitNo = unitNo;
    }

    public int getUnitType() {
        return UnitType;
    }

    public void setUnitType(int unitType) {
        UnitType = unitType;
    }

    public String getOprNo() {
        return OprNo;
    }

    public void setOprNo(String oprNo) {
        OprNo = oprNo;
    }

    public int getOwner() {
        return Owner;
    }

    public void setOwner(int owner) {
        Owner = owner;
    }

    public int getSaleNo() {
        return SaleNo;
    }

    public void setSaleNo(int saleNo) {
        SaleNo = saleNo;
    }

    public int getProjID() {
        return ProjID;
    }

    public void setProjID(int projID) {
        ProjID = projID;
    }


}

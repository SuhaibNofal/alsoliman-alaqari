package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class UserInformationData {
    @SerializedName("UnitNo")
    private int UnitNo;
    @SerializedName("OprNo")
    private String OprNo;
    @SerializedName("RentAmt")
    private double RentAmt;
    @SerializedName("NextPayDate")
    private String NextPayDate;
    @SerializedName("RentEndDate")
    private String RentEndDate;
    @SerializedName("NetArea")
    private double NetArea;
    @SerializedName("ProjID")
    private int ProjID;
    @SerializedName("Balance")
    private String Balance;

    public int getUnitNo() {
        return UnitNo;
    }

    public void setUnitNo(int unitNo) {
        UnitNo = unitNo;
    }

    public String getOprNo() {
        return OprNo;
    }

    public void setOprNo(String oprNo) {
        OprNo = oprNo;
    }

    public double getRentAmt() {
        return RentAmt;
    }

    public void setRentAmt(double rentAmt) {
        RentAmt = rentAmt;
    }

    public String getNextPayDate() {
        return NextPayDate;
    }

    public void setNextPayDate(String nextPayDate) {
        NextPayDate = nextPayDate;
    }

    public String getRentEndDate() {
        return RentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        RentEndDate = rentEndDate;
    }

    public double getNetArea() {
        return NetArea;
    }

    public void setNetArea(double netArea) {
        NetArea = netArea;
    }

    public int getProjID() {
        return ProjID;
    }

    public void setProjID(int projID) {
        ProjID = projID;
    }
    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

}

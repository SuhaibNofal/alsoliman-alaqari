package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class LogInInformation {
    @SerializedName("userName")
    private String userName;
    @SerializedName("UnitNo")
    private String UnitNo;
    @SerializedName("UnitName")
    private String UnitName;
    @SerializedName("CustID")
    private String CustID;
    @SerializedName("Pass")
    private String Pass;
    @SerializedName("ID")
    private String ID;
    @SerializedName("UserType")
    private String UserType;
    @SerializedName("ProjID")
    private String ProjID;
    @SerializedName("UserActive")
    private Boolean UserActive;
    @SerializedName("AccNo")
    private String AccNo;
    @SerializedName("erorr")
    private String erorr;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUnitNo() {
        return UnitNo;
    }

    public void setUnitNo(String unitNo) {
        UnitNo = unitNo;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String custID) {
        CustID = custID;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getProjID() {
        return ProjID;
    }

    public void setProjID(String projID) {
        ProjID = projID;
    }

    public Boolean getUserActive() {
        return UserActive;
    }

    public void setUserActive(Boolean userActive) {
        UserActive = userActive;
    }

    public String getErorr() {
        return erorr;
    }

    public void setErorr(String erorr) {
        this.erorr = erorr;
    }

    public String getAccNo() {
        return AccNo;
    }

    public void setAccNo(String accNo) {
        AccNo = accNo;
    }

}

package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class CleanServices {
    @SerializedName("SID")
    private int SID;
    @SerializedName("CleanType")
    private int CleanType;
    @SerializedName("ServiceName")
    private String ServiceName;
    @SerializedName("ServiceDesc")
    private String ServiceDesc;
    @SerializedName("Employment")
    private float Employment;
    @SerializedName("Items")
    private float Items;
    @SerializedName("Expenses")
    private float Expenses;
    @SerializedName("Revenue")
    private float Revenue;
    @SerializedName("ProjID")
    private int ProjID;

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getCleanType() {
        return CleanType;
    }

    public void setCleanType(int cleanType) {
        CleanType = cleanType;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceDesc() {
        return ServiceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        ServiceDesc = serviceDesc;
    }

    public float getEmployment() {
        return Employment;
    }

    public void setEmployment(float employment) {
        Employment = employment;
    }

    public float getItems() {
        return Items;
    }

    public void setItems(float items) {
        Items = items;
    }

    public float getExpenses() {
        return Expenses;
    }

    public void setExpenses(float expenses) {
        Expenses = expenses;
    }

    public float getRevenue() {
        return Revenue;
    }

    public void setRevenue(float revenue) {
        Revenue = revenue;
    }

    public int getProjID() {
        return ProjID;
    }

    public void setProjID(int projID) {
        ProjID = projID;
    }
}

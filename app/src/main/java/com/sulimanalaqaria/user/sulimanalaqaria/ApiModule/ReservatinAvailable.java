package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class ReservatinAvailable {

    @SerializedName("ID")
    private String ID;
    @SerializedName("Name")
    private String Name;
    @SerializedName("CostBooking")
    private String CostBooking;
    @SerializedName("AmountResid")
    private String AmountResid;
    @SerializedName("AmountNonResid")
    private String AmountNonResid;
    @SerializedName("ProjID")
    private String ProjID;
    @SerializedName("RowID")
    private int RowID;
    @SerializedName("Day")
    private int Day;
    @SerializedName("FHour")
    private String FHour;
    @SerializedName("THour")
    private String THour;

    public ReservatinAvailable(String ID, String name, String costBooking, String amountResid, String amountNonResid) {
        this.ID = ID;
        Name = name;
        CostBooking = costBooking;
        AmountResid = amountResid;
        AmountNonResid = amountNonResid;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCostBooking() {
        return CostBooking;
    }

    public void setCostBooking(String costBooking) {
        CostBooking = costBooking;
    }

    public String getAmountResid() {
        return AmountResid;
    }

    public void setAmountResid(String amountResid) {
        AmountResid = amountResid;
    }

    public String getAmountNonResid() {
        return AmountNonResid;
    }

    public void setAmountNonResid(String amountNonResid) {
        AmountNonResid = amountNonResid;
    }

    public String getProjID() {
        return ProjID;
    }

    public void setProjID(String projID) {
        ProjID = projID;
    }

    public int getRowID() {
        return RowID;
    }

    public void setRowID(int rowID) {
        RowID = rowID;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public String getFHour() {
        return FHour;
    }

    public void setFHour(String FHour) {
        this.FHour = FHour;
    }

    public String getTHour() {
        return THour;
    }

    public void setTHour(String THour) {
        this.THour = THour;
    }


}

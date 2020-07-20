package com.sulimanalaqaria.user.sulimanalaqaria.ApiModule;

import com.google.gson.annotations.SerializedName;

public class ReservationAvailableTime {
    @SerializedName("ID")
        private int ID;
    @SerializedName("IDRes")
        private int IDRes;
    @SerializedName("RowID")
        private int RowID;
    @SerializedName("Day")
        private int Day;
    @SerializedName("FHour")
        private String FHour;
    @SerializedName("THour")
        private String THour;
    @SerializedName("ProjID")
        private int ProjID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDRes() {
        return IDRes;
    }

    public void setIDRes(int IDRes) {
        this.IDRes = IDRes;
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

    public int getProjID() {
        return ProjID;
    }

    public void setProjID(int projID) {
        ProjID = projID;
    }
}

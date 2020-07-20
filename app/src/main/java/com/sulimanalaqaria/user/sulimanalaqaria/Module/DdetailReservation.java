package com.sulimanalaqaria.user.sulimanalaqaria.Module;

import android.widget.TextView;

public class DdetailReservation {
    private String tvDayAvailable,tvTimeFromAvailable,tvTimeToAvailable;
    int ID;

    public DdetailReservation(String tvDayAvailable, String tvTimeFromAvailable, String tvTimeToAvailable, int ID) {
        this.tvDayAvailable = tvDayAvailable;
        this.tvTimeFromAvailable = tvTimeFromAvailable;
        this.tvTimeToAvailable = tvTimeToAvailable;
        this.ID = ID;
    }

    public String getTvDayAvailable() {
        return tvDayAvailable;
    }

    public void setTvDayAvailable(String tvDayAvailable) {
        this.tvDayAvailable = tvDayAvailable;
    }

    public String getTvTimeFromAvailable() {
        return tvTimeFromAvailable;
    }

    public void setTvTimeFromAvailable(String tvTimeFromAvailable) {
        this.tvTimeFromAvailable = tvTimeFromAvailable;
    }

    public String getTvTimeToAvailable() {
        return tvTimeToAvailable;
    }

    public void setTvTimeToAvailable(String tvTimeToAvailable) {
        this.tvTimeToAvailable = tvTimeToAvailable;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

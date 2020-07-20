package com.sulimanalaqaria.user.sulimanalaqaria.Module;

public class GymTimeModule {

    private int Day ;
    private int Gender;
    private String FHour;
    private String THour;

    public int getDay(){
        return Day;
    }
    public void setDay(int day){
        this.Day=day;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
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

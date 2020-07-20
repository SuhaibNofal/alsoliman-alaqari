package com.sulimanalaqaria.user.sulimanalaqaria.Module;

public class customerInformationdetails {
    private String OprNo;
            private String ContrDate;
            private double area;
            private String RentEndDate;
            private String RentStrtDate;
            private String RentAmt;
            private int ProjID;
    private int UnitNo;

    public int getUnitNo() {
        return UnitNo;
    }

    public void setUnitNo(int unitNo) {
        UnitNo = unitNo;
    }

    private String Balance;

    public String getOprNo() {
        return OprNo;
    }

    public void setOprNo(String oprNo) {
        OprNo = oprNo;
    }

    public String getContrDate() {
        return ContrDate;
    }

    public void setContrDate(String contrDate) {
        ContrDate = contrDate;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getRentEndDate() {
        return RentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        RentEndDate = rentEndDate;
    }

    public String getRentStrtDate() {
        return RentStrtDate;
    }

    public void setRentStrtDate(String rentStrtDate) {
        RentStrtDate = rentStrtDate;
    }

    public String getRentAmt() {
        return RentAmt;
    }

    public void setRentAmt(String rentAmt) {
        RentAmt = rentAmt;
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

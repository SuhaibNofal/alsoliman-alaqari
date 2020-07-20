package com.sulimanalaqaria.user.sulimanalaqaria.Module;

public class PPreviousMaintOrder {
    String maintType;
    String maintService;
    String maintUnits;
    String maintOrderDetail;
    String orderNumber;

    public PPreviousMaintOrder(String maintType, String maintService, String maintUnits, String maintOrderDetail, String orderNumber) {
        this.maintType = maintType;
        this.maintService = maintService;
        this.maintUnits = maintUnits;
        this.maintOrderDetail = maintOrderDetail;
        this.orderNumber = orderNumber;
    }

    public String getMaintType() {
        return maintType;
    }

    public void setMaintType(String maintType) {
        this.maintType = maintType;
    }

    public String getMaintService() {
        return maintService;
    }

    public void setMaintService(String maintService) {
        this.maintService = maintService;
    }

    public String getMaintUnits() {
        return maintUnits;
    }

    public void setMaintUnits(String maintUnits) {
        this.maintUnits = maintUnits;
    }

    public String getMaintOrderDetail() {
        return maintOrderDetail;
    }

    public void setMaintOrderDetail(String maintOrderDetail) {
        this.maintOrderDetail = maintOrderDetail;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}

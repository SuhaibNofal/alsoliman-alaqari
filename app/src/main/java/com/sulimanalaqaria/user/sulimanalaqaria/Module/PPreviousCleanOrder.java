package com.sulimanalaqaria.user.sulimanalaqaria.Module;

public class PPreviousCleanOrder {
    String cleanType;
    String cleanService;
    String cleanUnits;
    String cleanOrderDetail;
    String orderNumber;

    public PPreviousCleanOrder(String cleanType, String cleanService, String cleanUnits, String cleanOrderDetail, String orderNumber) {
        this.cleanType = cleanType;
        this.cleanService = cleanService;
        this.cleanUnits = cleanUnits;
        this.cleanOrderDetail = cleanOrderDetail;
        this.orderNumber = orderNumber;
    }

    public String getCleanType() {
        return cleanType;
    }

    public void setCleanType(String cleanType) {
        this.cleanType = cleanType;
    }

    public String getCleanService() {
        return cleanService;
    }

    public void setCleanService(String cleanService) {
        this.cleanService = cleanService;
    }

    public String getCleanUnits() {
        return cleanUnits;
    }

    public void setCleanUnits(String cleanUnits) {
        this.cleanUnits = cleanUnits;
    }

    public String getCleanOrderDetail() {
        return cleanOrderDetail;
    }

    public void setCleanOrderDetail(String cleanOrderDetail) {
        this.cleanOrderDetail = cleanOrderDetail;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}

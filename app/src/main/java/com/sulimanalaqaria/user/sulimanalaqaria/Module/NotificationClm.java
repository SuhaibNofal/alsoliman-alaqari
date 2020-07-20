package com.sulimanalaqaria.user.sulimanalaqaria.Module;

public class NotificationClm {
    private int CustID;
    private String NotyTitle;
    private String NotyBody;
    private Boolean recived;
    private int ProjID;
    private int ReqNo;
    private int ReqType;
    private Boolean isClose;
    private Boolean rating;
    private int UserType;
    private int MaintLoginID;
    private String Error;

    public int getMaintLoginID() {
        return MaintLoginID;
    }

    public void setMaintLoginID(int maintLoginID) {
        MaintLoginID = maintLoginID;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int custID) {
        CustID = custID;
    }

    public String getNotyTitle() {
        return NotyTitle;
    }

    public void setNotyTitle(String notyTitle) {
        NotyTitle = notyTitle;
    }

    public String getNotyBody() {
        return NotyBody;
    }

    public void setNotyBody(String notyBody) {
        NotyBody = notyBody;
    }

    public Boolean getRecived() {
        return recived;
    }

    public void setRecived(Boolean recived) {
        this.recived = recived;
    }

    public int getProjID() {
        return ProjID;
    }

    public void setProjID(int projID) {
        ProjID = projID;
    }

    public int getReqNo() {
        return ReqNo;
    }

    public void setReqNo(int reqNo) {
        ReqNo = reqNo;
    }

    public int getReqType() {
        return ReqType;
    }

    public void setReqType(int reqType) {
        ReqType = reqType;
    }

    public Boolean getClose() {
        return isClose;
    }

    public void setClose(Boolean close) {
        isClose = close;
    }

    public Boolean getRating() {
        return rating;
    }

    public void setRating(Boolean rating) {
        this.rating = rating;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }
}

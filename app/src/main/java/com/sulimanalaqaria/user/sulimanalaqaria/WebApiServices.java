package com.sulimanalaqaria.user.sulimanalaqaria;

import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.CleanPreviousRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.CleanServices;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.CleanType;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.InsertResult;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.MaintPreviousRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.MaintServices;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.MaintType;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.ReservatinAvailable;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.ReservationAvailableTime;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.UnitUser;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.UserInformationData;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.VisitPreviouseRequest;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.VisitResone;
import com.sulimanalaqaria.user.sulimanalaqaria.ApiModule.LogInInformation;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.DetailsRentUnitModel;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.GymTimeModule;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.GymcPercentAndType;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.NotificationClm;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.PFamilyHelthClubCls;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.customerInformationdetails;
import com.sulimanalaqaria.user.sulimanalaqaria.Module.isinBlacklist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebApiServices {
    @FormUrlEncoded
    @POST("LogIn")
    public Call<List<LogInInformation>> getLogInInformation(@Field("username") String username, @Field("pass") String pass);
    @FormUrlEncoded
    @POST("LogOut")
    public Call<InsertResult> LogOut(@Field("CustID") int CustID,@Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("RegistrationTheDevice")
    public Call<InsertResult> SendToken(@Field("CustID") int CustID, @Field("DeviceID") String DeviceID,@Field("ProjID") int ProjID,@Field("UserType") int UserType);

    @FormUrlEncoded
    @POST("GetMaintenanceType")
    public Call<List<MaintType>> getMaintType(@Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("GetMaintenanceServices")
    public Call<List<MaintServices>> getMaintService(@Field("MaintType") int id,@Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("GetUnitsFileOwner")
    public Call<List<UnitUser>> getUnitOwner(@Field("CustID") int id,@Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("GetUnitsFileTenant")
    public Call<List<UnitUser>> getUnitTenant(@Field("CustID") int id,@Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("SetMaintenanceRequest")
    public Call<InsertResult> InsertMaintRequest(@Field("ReqNo") int ReqNo, @Field("mDate") String mDate
            , @Field("CreatedBy") String CreatedBy, @Field("UnitNo") int UnitNo, @Field("Notes") String Notes, @Field("MainTypeID") int MainTypeID
            , @Field("SID") int SID, @Field("ProjID") int ProjID, @Field("imageName") String imageName);

    @FormUrlEncoded
    @POST("GetCleanType")
    public Call<List<CleanType>> getCleanType(@Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("GetCleanServices")
    public Call<List<CleanServices>> getCleanService(@Field("CleanType") int id,@Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("SetCleanRequest")
    public Call<InsertResult> InsertCleaRequest(@Field("ReqNo") int ReqNo, @Field("mDate") String mDate
            , @Field("CreatedBy") String CreatedBy, @Field("UnitNo") int UnitNo, @Field("Notes") String Notes, @Field("CleanTypeID") int MainTypeID
            , @Field("SID") int SID, @Field("NoOfEmp") int NoOfEmp, @Field("NoOfHours") Double NoOfHours, @Field("ValueReq") Double ValueReq, @Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("GetReservationAvailable")
    public Call<List<ReservatinAvailable>> getReservationAvailable(@Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("GetReservationAvailableTime")
    public Call<List<ReservationAvailableTime>> getReservationAvailableTime(@Field("IDRes") int IDRes, @Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("SetRequestReservtion")
    public Call<InsertResult> insertReservation(@Field("ID") int ID, @Field("DateToday") String DateToday
            , @Field("UserType") int UserType, @Field("UserName") String UserName, @Field("ServiceType") int ServiceType, @Field("BookAmount") Double BookAmount
            , @Field("PayBookAmount") Double PayBookAmount, @Field("ReqDate") String ReqDate, @Field("ReqTime") String ReqTime, @Field("ReqTimeTo") String ReqTimeTo
            , @Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("GetVisitReasons")
    public Call<List<VisitResone>> getVisitReasons(@Field("ProjID") int ProjID);

    @FormUrlEncoded
    @POST("SetRecepRequestVisit")
    public Call<InsertResult> insertVisitRequest(@Field("ReqNo") int ReqNo, @Field("ReqDate") String ReqDate, @Field("UnitNo") int UnitNo
            , @Field("SNumber") String SNumber, @Field("VisitName") String VisitName, @Field("VisitTime") String VisitTime, @Field("VisitReas") int VisitReas
            , @Field("ProjID") int ProjID);


    @FormUrlEncoded
    @POST("GetMaintenanceRequest")
    public Call<List<MaintPreviousRequest>>getLastMaintenanceRequest(@Field("CreatedBy")String CreatedBy, @Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("GetCleanRequest")
    public Call<List<CleanPreviousRequest>>getLastCleanRequest(@Field("CreatedBy")String CreatedBy, @Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("GetVisitRequestCust")
    public Call<List<VisitPreviouseRequest>>getPrevVisit(@Field("CustID")String CustID,@Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("GetinformationCust")
    public Call<List<UserInformationData>> getInformationCust(@Field("CustID") String CustID, @Field("ProjID") int ProjID,@Field("AccNo") String AccNo);

    @FormUrlEncoded
    @POST("GetinformationTenant")
    public Call<List<UserInformationData>> getInformationTenant(@Field("CustID") String CustID, @Field("ProjID") int ProjID,@Field("AccNo") String AccNo);

    @FormUrlEncoded
    @POST("GetNotify")
    public Call<List<NotificationClm>>GetNotify(@Field("CustID")String CustID, @Field("ProjID")int ProjID,@Field("UserType") int UserType);

    @FormUrlEncoded
    @POST("Set_AprovOffers")
    public Call<InsertResult>Set_AprovOffers(@Field("ReqNo")int ReqNo, @Field("ProjID")int ProjID,@Field("Approval") Boolean Approval,@Field("CustID")int CustID,@Field("UserType")int UserType);

    @FormUrlEncoded
    @POST("Set_RatReq")
    public Call<InsertResult>Set_RatReq(@Field("ReqNo")int ReqNo, @Field("ProjID")int ProjID,@Field("Note")String Note,@Field("NumStars")double NumStars,@Field("CustID")int CustID,@Field("UserType")int UserType,@Field("ReqType")int ReqType);

    @FormUrlEncoded
    @POST("Set_AprovMaintLogIn")
    public Call<InsertResult>Set_AprovMaintLogIn(@Field("ApproveID")int ApproveID, @Field("ProjID")int ProjID,@Field("Approval") Boolean Approval,@Field("CustID")int CustID,@Field("UserType")int UserType);

    @FormUrlEncoded
    @POST("Set_AprovCleanLogIn")
    public Call<InsertResult>Set_AprovCleanLogIn(@Field("ApproveID")int ApproveID, @Field("ProjID")int ProjID,@Field("Approval") Boolean Approval,@Field("CustID")int CustID,@Field("UserType")int UserType);
    @FormUrlEncoded
    @POST("GetFamelyCust")
    public Call<List<PFamilyHelthClubCls>>GetFamelyCust(@Field("CustID")int CustID, @Field("UserType")int UserType, @Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("Set_familyupdatefalse")
    public Call<InsertResult>Set_familyupdatefalse(@Field("CustID")int CustID,@Field("UserType")int UserType, @Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("Set_familyAccept")
    public Call<InsertResult>Set_familyAccept(@Field("CustID")int CustID,@Field("RowID")int RowID,@Field("UserType")int UserType, @Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("GetOwnerinGym")
    public Call<List<GymcPercentAndType>>GetOwnerinGym(@Field("TimeWorkdate")String TimeWorkdate, @Field("TimeWork")String TimeWork, @Field("DayNumber")int DayNumber,@Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("GetOwnerBlackList")
    public Call<List<isinBlacklist>>GetinBlacklist(@Field("CustID")int CustID,@Field("UserType")int UserType, @Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("GetinformationCustUnit")
    public Call<List<customerInformationdetails>>GetinformationCustUnit(@Field("CustID")String CustID, @Field("ProjID")int ProjID, @Field("AccNo")String AccNo);
    @FormUrlEncoded
    @POST("GetinformationTenantUnit")
    public Call<List<customerInformationdetails>>GetinformationTenantUnit(@Field("CustID")String CustID, @Field("ProjID")int ProjID, @Field("AccNo")String AccNo);

    @FormUrlEncoded
    @POST("GetinformationUnitDetails")
    public Call<List<DetailsRentUnitModel>>GetinformationUnitDetails(@Field("CustID")String CustID, @Field("ProjID")int ProjID, @Field("UnitNo")int UnitNo);


    @FormUrlEncoded
    @POST("Set_Suggestion")
    public Call<InsertResult>Set_Suggestion(@Field("UserName")String UserName,@Field("SuggestonText")String SuggestonText,@Field("UserType")int UserType, @Field("ProjID")int ProjID);

    @FormUrlEncoded
    @POST("GetTimeGym")
    public Call<List<GymTimeModule>>GetTimeGym(@Field("ProjID")int ProjID);

}

package com.example.right_choice_project_usman_18_apr_2021.model;

public class GSOrderHistory {
//SELECT `ID`, `PUserName`, `UUserName`, `Category`, `SubCategory`, `Date`, `ServiceAddress`, `BookingDetial`,
// `CurrentDateTime`, `Status` FROM `tbl_orders` WHERE 1

    String ID, PUserName,ProviderName, UUserName,UserName, Category, SubCategory, Date, ServiceAddress, BookingDetial, CurrentDateTime, Status;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPUserName() {
        return PUserName;
    }

    public void setPUserName(String PUserName) {
        this.PUserName = PUserName;
    }

    public String getUUserName() {
        return UUserName;
    }

    public void setUUserName(String UUserName) {
        this.UUserName = UUserName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(String subCategory) {
        SubCategory = subCategory;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getServiceAddress() {
        return ServiceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        ServiceAddress = serviceAddress;
    }

    public String getBookingDetial() {
        return BookingDetial;
    }

    public void setBookingDetial(String bookingDetial) {
        BookingDetial = bookingDetial;
    }

    public String getCurrentDateTime() {
        return CurrentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        CurrentDateTime = currentDateTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getProviderName() {
        return ProviderName;
    }

    public void setProviderName(String providerName) {
        ProviderName = providerName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}

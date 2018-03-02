package pl.treekt.mychunk.API.Payments.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

    @JsonProperty("tr_id")
    private int id;
    @JsonProperty("tr_user_id")
    private int userId;
//    @JsonProperty("tr_time")
//    private int time;
//    @JsonProperty("tr_status")
//    private int status;
//    @JsonProperty("tr_mode")
//    private int mode;
//    @JsonProperty("tr_amount")
//    private double amount;
//    @JsonProperty("tr_provision")
//    private double provision;
//    @JsonProperty("tr_fee")
//    private double fee;
//    @JsonProperty("tr_user_data")
//    private String userData;
//    @JsonProperty("tr_email")
//    private String email;
//    @JsonProperty("tr_merchant_label")
//    private String merchantLabel;
//    @JsonProperty("tr_merchant_data")
//    private String merchantData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
//
//    public int getTime() {
//        return time;
//    }
//
//    public void setTime(int time) {
//        this.time = time;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public int getMode() {
//        return mode;
//    }
//
//    public void setMode(int mode) {
//        this.mode = mode;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public double getProvision() {
//        return provision;
//    }
//
//    public void setProvision(double provision) {
//        this.provision = provision;
//    }
//
//    public double getFee() {
//        return fee;
//    }
//
//    public void setFee(double fee) {
//        this.fee = fee;
//    }
//
//    public String getUserData() {
//        return userData;
//    }
//
//    public void setUserData(String userData) {
//        this.userData = userData;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getMerchantLabel() {
//        return merchantLabel;
//    }
//
//    public void setMerchantLabel(String merchantLabel) {
//        this.merchantLabel = merchantLabel;
//    }
//
//    public String getMerchantData() {
//        return merchantData;
//    }
//
//    public void setMerchantData(String merchantData) {
//        this.merchantData = merchantData;
//    }
}

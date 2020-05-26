package com.mastercard.developer.bundle_client.model;

public class ProductExternal {

    private String username;

    private String password;

    private Integer ica;

    private Boolean acceptPromoEmail;

    private Boolean acceptPromoSMS;

    private String bankProductCode;

    private String bankCustomerNumber;

    private String programIdentifier;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getIca() {
        return ica;
    }

    public void setIca(Integer ica) {
        this.ica = ica;
    }

    public Boolean getAcceptPromoEmail() {
        return acceptPromoEmail;
    }

    public void setAcceptPromoEmail(Boolean acceptPromoEmail) {
        this.acceptPromoEmail = acceptPromoEmail;
    }

    public Boolean getAcceptPromoSMS() {
        return acceptPromoSMS;
    }

    public void setAcceptPromoSMS(Boolean acceptPromoSMS) {
        this.acceptPromoSMS = acceptPromoSMS;
    }

    public String getBankProductCode() {
        return bankProductCode;
    }

    public void setBankProductCode(String bankProductCode) {
        this.bankProductCode = bankProductCode;
    }

    public String getBankCustomerNumber() {
        return bankCustomerNumber;
    }

    public void setBankCustomerNumber(String bankCustomerNumber) {
        this.bankCustomerNumber = bankCustomerNumber;
    }

    public String getProgramIdentifier() {
        return programIdentifier;
    }

    public void setProgramIdentifier(String programIdentifier) {
        this.programIdentifier = programIdentifier;
    }


}

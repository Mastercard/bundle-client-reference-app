package com.mastercard.developer.bundle_client.model;

public class AccountExternal {

    private String accountType;

    private String ica;

    private String cardProductType;

    private String externalMembershipReferenceId;

    private String userOffersId;

    private String accountStatusCode;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIca() {
        return ica;
    }

    public void setIca(String ica) {
        this.ica = ica;
    }

    public String getCardProductType() {
        return cardProductType;
    }

    public void setCardProductType(String cardProductType) {
        this.cardProductType = cardProductType;
    }

    public String getExternalMembershipReferenceId() {
        return externalMembershipReferenceId;
    }

    public void setExternalMembershipReferenceId(String externalMembershipReferenceId) {
        this.externalMembershipReferenceId = externalMembershipReferenceId;
    }

    public String getUserOffersId() {
        return userOffersId;
    }

    public void setUserOffersId(String userOffersId) {
        this.userOffersId = userOffersId;
    }

    public String getAccountStatusCode() {
        return accountStatusCode;
    }

    public void setAccountStatusCode(String accountStatusCode) {
        this.accountStatusCode = accountStatusCode;
    }
}

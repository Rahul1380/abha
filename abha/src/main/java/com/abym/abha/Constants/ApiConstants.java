package com.abym.abha.Constants;

public interface ApiConstants {

    public String BASE_URL_DEV = "http://192.168.0.134:8094";
    public String BASE_URL_LIVE = "https://healthid.abdm.gov.in";

    public String BASEURL_DEV_ABDM = "https://dev.abdm.gov.in";
    public String BASEURL_LIVE_ABDM = "https://live.abdm.gov.in";

    public String BASEURL_AUTH = "https://live.abdm.gov.in";
    public String BASEURL_WEBSERVIC = "/api/v1/abha/";

    public String BASEURL = ApiConstants.BASE_URL_DEV + ApiConstants.BASEURL_WEBSERVIC;
    public String SESSIONS_API = "getSession";
    public String ENCRYPT_CERTIFICATE = "encryptionCert";
    public String GENERATE_AADHAR_OTP = "send-aadhar-otp";
    public String RESEND_AADHAR_OTP = "registrationAadhaarResendAadhaarOtp";
    public String VERIFY_AADHAR_OTP = "verify-aadhar-otp";
    public String MOBILE_OTP = "send-mobile-otp";
    public String VERIFY_MOBILE_OTP = "verify-mobile-otp";
    public String CHECK_PHR_AVAIL = "check-healthid";
    public String LINK_PHR = "registrationPhrAddressId";
    public String CREATE_ABHA = "create-new-abha";
    public String ABHA_CARD = "account-get-card";
    public String ABHA_QR = "account-get-qr";
}

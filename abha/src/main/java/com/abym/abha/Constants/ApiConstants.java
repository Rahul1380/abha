package com.abym.abha.Constants;

public interface ApiConstants {

    public String BASE_URL_DEV = "http://43.205.177.18";
    public String BASE_URL_LIVE = "https://healthid.abdm.gov.in";

    public String BASEURL_DEV_ABDM = "https://dev.abdm.gov.in";
    public String BASEURL_LIVE_ABDM = "https://live.abdm.gov.in";

    public String BASEURL_AUTH = "https://live.abdm.gov.in";
    public String BASEURL_WEBSERVICE = "/ABHA/whatsapp/api/v1/";

    public String BASEURL = ApiConstants.BASE_URL_DEV + ApiConstants.BASEURL_WEBSERVICE;
    public String SESSIONS_API = "getSession";
    public String ENCRYPT_CERTIFICATE = "encryptionCert";
    public String GENERATE_AADHAR_OTP = "registrationAadhaarGenerateOtp";
    public String RESEND_AADHAR_OTP = "registrationAadhaarResendAadhaarOtp";
    public String VERIFY_AADHAR_OTP = "registrationAadhaarVerifyOTP";
    public String MOBILE_OTP = "registrationAadhaarGenerateMobileOTP";
    public String VERIFY_MOBILE_OTP = "registrationAadhaarVerifyMobileOTP";
    public String CHECK_PHR_AVAIL = "existsByHealthId";
    public String LINK_PHR = "registrationPhrAddressId";
    public String CREATE_ABHA = "registrationAadhaarCreateHealthIdByAadhar";
    public String ABHA_CARD = "accountGetPngCard";
    public String ABHA_QR = "accountQrCode";
}

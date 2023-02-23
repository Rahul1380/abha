package com.abym.abha.Network;

import com.abym.abha.Models.Auth.AuthRequest;
import com.abym.abha.Models.Auth.AuthResponse;
import com.google.gson.JsonElement;


import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("gateway/v0.5/sessions")
    Call<AuthResponse> verifyUser(@Body AuthRequest authRequest);

   /* @POST("gateway/v0.5/sessions")
    Call<SessionResponse> getSession(@Body SessionRequest sessionRequest);

    @POST("api/v2/registration/aadhaar/generateOtp")
    Call<OTPAdharResponse> generateAdharOTP(@Body OTPAdharRequest oTPAdharRequest);

    @POST("api/v2/forgot/healthId/aadhaar/generateOtp")
    Call<OTPAdharResponse> retriveViaAdhar(@Body OTPAdharRequest oTPAdharRequest);

    @POST("api/v1/forgot/healthId/mobile/generateOtp")
    Call<OTPMobileResponse> retriveViaMobile(@Body OTPMobileRequest oTPMobileRequest);

    @POST("api/v1/registration/mobile/generateOtp")
    Call<OTPResponse> generateOTP(@Body OTPRequest oTPRequest);

    @POST("api/v2/registration/aadhaar/verifyOTP")
    Call<VerifyAdharResponse> verifyAdharOTP(@Body VerifyAdharRequest verifyAdharRequest);

    @POST("api/v2/forgot/healthId/aadhaar")
    Call<VerifyOTPAdharResponse> verifyViaAdhar(@Body VerifyOTPAdharRequest verifyOTPAdharRequest);

    @POST("api/v2/auth/init")
    Call<InitAdharResponse> initAdhar(@Body InitAdharRequest initAdharRequest);

    @POST("api/v2/registration/aadhaar/resendAadhaarOtp")
    Call<JsonElement> resendAdharOTP(@Body ResendOTPRequest resendOTPRequest);

    @POST("api/v2/registration/aadhaar/checkAndGenerateMobileOTP")
    Call<AdharMobileResponse> generateMobileOTPforAdhar(@Body AdharMobileRequest adharMobileRequest);

    @POST("api/v2/registration/aadhaar/verifyMobileOTP")
    Call<AdharMobileVerifyResponse> verifyMobileOTPforAdhar(@Body AdharMobileVerifyRequest adharMobileVerifyRequest);

    @POST("api/v2/auth/confirmWithAadhaarOtp")
    Call<ConfirmAdharResponse> confirmviaAdhar(@Body ConfirmAdharRequest confirmAdharRequest);

    @POST("api/v2/registration/aadhaar/confirm")
    Call<CreateCardResponse> createCardviaAdhar(@Body CreateCardRequest createCardRequest);

    @POST("api/v2/registration/aadhaar/phr-address")
    Call<PHRResponse> linkPHR(@Body PHRRequest pHRRequest);

    @POST("api/v1/search/existsByHealthId")
    Call<CheckHealthIdResponse> checkHealthId(@Body CheckHealthIdRequest checkHealthIdRequest);

    @POST("api/v1/search/searchByHealthId")
    Call<VerifyCardResponse> verifyCardViaPHR(@Body VerifyCardRequest verifyCardRequest);

    @POST("api/v2/auth/init")
    Call<AuthResponse> authviaAdhar(@Body AuthRequest authRequest);

    @POST("api/v2/auth/confirmWithAadhaarOtp")
    Call<ConfirmOTPResponse> confirmViaAdharOTP(@Body ConfirmOTPRequest confirmOTPRequest);

    @POST("api/v2/auth/confirmWithMobileOTP")
    Call<ConfirmOTPResponse> confirmViaMobile(@Body ConfirmOTPRequest confirmOTPRequest);

    @POST("api/v1/auth/resendAuthOTP")
    Call<String> resendauthviaMobile(@Body ResendAuthRequest resendAuthRequest);

    @POST("api/v1/registration/mobile/resendOtp")
    Call<String> resendOTP(@Body ResendOTPRequest resendOTPRequest);

    @POST("api/v1/registration/mobile/verifyOtp")
    Call<OTPVerifyResponse> verifyOTP(@Body OTPVerifyRequest oTPVerifyRequest);

    @POST("cm/v1/care-contexts/discover")
    Call<CareContextResponse> getPatientCareContext(@Body CareContextRequest careContextRequest);

    @GET("cm/patients/links")
    Call<LinksResponse> getPatientLinks();

    @POST("cm/v1/links/link/init/")
    Call<LinkResponse> linkInit(@Body LinkRequest linkRequest);

    @GET("cm/providers/{Id}")
    Call<ProviderDetailResponse> getProvidersDetail(@Path("Id") String str);

    @GET("cm/providers?")
    Call<ArrayList<ProviderResponseItem>> getProvidersList(@Query("name") String str);

    @POST("cm/v1/apps/auth-init")
    Call<LoginResponse> generateOTPviaABHA(@Body LoginRequest loginRequest);

    @POST("cm/v1/apps/auth-confirm")
    Call<VerifyResponse> verifyOTPviaABHA(@Body VerifyRequest verifyRequest);

    @GET("cm/govt-programs")
    Call<ArrayList<ProviderResponseItem>> getGovtProviders();

    @POST("cm/patients/notification/app-token")
    Call<Void> saveNotifToken(@Body NotifRequest notifRequest);

    // ?ModuleName=patients/attachments&UserId=
    @POST(ApiConstants.UPLOADATTACHMENT)
    @Multipart
    Call<RecordUploadResponse> uploadRecord(@Query("ModuleName") String module, @Query("UserId") String id,
                                            @Part("UserId") RequestBody userid,
                                            @Part MultipartBody.Part partquestBody5);*/

}

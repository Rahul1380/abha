package com.abym.abha.Wrapper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.abym.abha.Constants.ApiConstants;
import com.abym.abha.Constants.AppConstants;
import com.abym.abha.Models.Auth.AuthRequest;
import com.abym.abha.Models.Auth.AuthResponse;
import com.abym.abha.Network.ApiClient;
import com.abym.abha.Network.ApiInterface;
import com.abym.abha.Ui.ABHADashboardActivity;
import com.abym.abha.Ui.CreateABHAActivity;
import com.abym.abha.Util.NetworkUtil;
import com.abym.abha.Util.PreferenceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ABHARepo implements ABHA {

    private static ABHARepo INSTANCE = null;
    private static ABHAListener abhaListener = null;

    public static ABHARepo getInstance(Context context) {
        if (INSTANCE == null) {
            // synchronize the block to ensure only one thread can execute at a time
            synchronized (ABHARepo.class) {
                // check again if the instance is already created
                if (INSTANCE == null) {
                    // create the singleton instance
                    INSTANCE = new ABHARepo();
                }
            }
        }
        // return the singleton instance
        return INSTANCE;
    }

    @Override
    public boolean init(Context context, String mode, String clientId, String clientSecret) {
        PreferenceUtil.clearpref(context);
        AppConstants.CLIENT_ID = clientId;
        AppConstants.CLIENT_SECRET = clientSecret;
        if (AppConstants.APPMODE == AppConstants.UAT) {
            return true;
        } else if (AppConstants.APPMODE == AppConstants.PROD && verifyUser(context, clientId, clientSecret)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void launchABHA(Context context, ABHAListener listener) {
        abhaListener = listener;
        context.startActivity(new Intent(context, ABHADashboardActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public boolean verifyUser(Context context, String clientId, String clientSecret) {
        final boolean[] isVerified = {false};
        if (NetworkUtil.checkInternetConnection(context)) {
            ApiInterface apiService = null;
            try {
                apiService = ApiClient.getApiClient1(context, ApiConstants.BASEURL_AUTH).create(ApiInterface.class);
                AuthRequest authUser = new AuthRequest();
                authUser.setClientId(clientId);
                authUser.setClientSecret(clientSecret);

                Call<AuthResponse> call = apiService.verifyUser(authUser);
                call.enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                isVerified[0] = true;
                                PreferenceUtil.setBooleanPrefs(context, PreferenceUtil.IS_VERIFIED, true);
                            }
                        } else {
                            isVerified[0] = false;
                            PreferenceUtil.setBooleanPrefs(context, PreferenceUtil.IS_VERIFIED, false);
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        Log.d("Throwable", t.toString());
                        isVerified[0] = false;
                        PreferenceUtil.setBooleanPrefs(context, PreferenceUtil.IS_VERIFIED, false);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isVerified[0];
    }
}

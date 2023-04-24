package com.abym.abha.Wrapper;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.abym.abha.Constants.AppConstants;
import com.abym.abha.UI.CreateABHAActivity;
import com.abym.abha.Util.PreferenceUtil;

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
        if ((mode.equalsIgnoreCase(AppConstants.UAT) || mode.equalsIgnoreCase(AppConstants.PROD)) &&
                !TextUtils.isEmpty(clientId) && !TextUtils.isEmpty(clientSecret)) {
            PreferenceUtil.clearpref(context);
            PreferenceUtil.setStringPrefs(context,PreferenceUtil.ENVIRONMENT,mode);
            PreferenceUtil.setStringPrefs(context,PreferenceUtil.CLIENT_ID,clientId);
            PreferenceUtil.setStringPrefs(context,PreferenceUtil.CLIENT_SECRET,clientSecret);
            return true;
        }
        return false;
    }

    @Override
    public void launchABHA(Context context, ABHAListener listener) {
        abhaListener = listener;
        context.startActivity(new Intent(context, CreateABHAActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}

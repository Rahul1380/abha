package com.abym.abha.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.abym.abha.Constants.ApiConstants;
import com.abym.abha.Constants.AppConstants;
import com.abym.abha.Listener.ResponseListener;
import com.abym.abha.R;
import com.abym.abha.Util.PreferenceUtil;
import com.abym.abha.Util.StringCodec;
import com.abym.abha.Util.UtilityABHA;
import com.abym.abha.Wrapper.ABHARepo;
import com.abym.abha.databinding.ActivityAdharCardVerificationBinding;

import org.json.JSONObject;

import okhttp3.internal.Util;

public class AdharCardVerifyActivity extends AppCompatActivity {
    ActivityAdharCardVerificationBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_adhar_card_verification);
        ABHARepo.screen2 = this;
        init();
    }

    private void init() {
        getSession();
        dataBinding.etAdhar1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmpty();
                if (dataBinding.etAdhar1.getText().length() >= 4) {
                    dataBinding.etAdhar2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dataBinding.etAdhar2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmpty();
                if (dataBinding.etAdhar2.getText().length() >= 4) {
                    dataBinding.etAdhar3.requestFocus();
                } else if (dataBinding.etAdhar2.getText().length() <= 0) {
                    dataBinding.etAdhar1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dataBinding.etAdhar3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmpty();
                if (dataBinding.etAdhar3.getText().length() <= 0) {
                    dataBinding.etAdhar2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dataBinding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBinding.etAdhar1.getText().toString().trim().length() < 4 || dataBinding.etAdhar2.getText().toString().trim().length() < 4
                        || dataBinding.etAdhar3.getText().toString().trim().length() < 4) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Aadhaar No", Toast.LENGTH_SHORT).show();
                } else {
                    generateAdharOTP();
                }
            }
        });
        dataBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean checkEmpty() {
        if (dataBinding.etAdhar1.length() < 4 || dataBinding.etAdhar2.length() < 4 || dataBinding.etAdhar3.length() < 4) {
            dataBinding.btnContinue.setBackgroundResource(R.drawable.btn_gray_bg2);
            dataBinding.btnContinue.setTextColor(getResources().getColor(R.color.black));
            dataBinding.btnContinue.setEnabled(false);
        } else {
            dataBinding.btnContinue.setBackgroundResource(R.drawable.btn_blu_bg1);
            dataBinding.btnContinue.setTextColor(getResources().getColor(R.color.white));
            dataBinding.btnContinue.setEnabled(true);
        }
        return false;
    }

    public void getSession() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("refreshToken", "");

            UtilityABHA.abhaAPICall(this, dataBinding.rlProgress, jsonObject, ApiConstants.SESSIONS_API, new ResponseListener() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response);
                        JSONObject jsonObject2 = jsonObject1.optJSONObject("result");
                        PreferenceUtil.setStringPrefs(getApplicationContext(), PreferenceUtil.HEALTH_ACCESSTOKEN, jsonObject2.optString("accessToken"));
                        PreferenceUtil.setStringPrefs(getApplicationContext(), PreferenceUtil.HEALTH_REFRESHTOKEN, jsonObject2.optString("refreshToken"));
                        getCertificate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(String response) {
                    ABHARepo.abhaListener.onFailure(response);
                    ABHARepo.closeABHA();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCertificate() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", PreferenceUtil.getStringPrefs(this, PreferenceUtil.HEALTH_ACCESSTOKEN, ""));

            UtilityABHA.abhaAPICall(this, null, jsonObject, ApiConstants.ENCRYPT_CERTIFICATE, new ResponseListener() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response);
                        String key = jsonObject1.optString("result");
                        PreferenceUtil.setStringPrefs(getApplicationContext(), PreferenceUtil.PUBLICKEY, key);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(String response) {
                    ABHARepo.abhaListener.onFailure(response);
                    ABHARepo.closeABHA();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateAdharOTP() {
        try {
            String aadharNo = dataBinding.etAdhar1.getText().toString().trim() +
                    dataBinding.etAdhar2.getText().toString().trim() +
                    dataBinding.etAdhar3.getText().toString().trim();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("adharNo", StringCodec.getEncryptedString(aadharNo, PreferenceUtil.getStringPrefs(this, PreferenceUtil.PUBLICKEY, "")));
            jsonObject.put("token", PreferenceUtil.getStringPrefs(this, PreferenceUtil.HEALTH_ACCESSTOKEN, ""));

            UtilityABHA.abhaAPICall(this, dataBinding.rlProgress, jsonObject, ApiConstants.GENERATE_AADHAR_OTP, new ResponseListener() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response);
                        JSONObject jsonObject2 = jsonObject1.optJSONObject("result");
                        String txnId = jsonObject2.optString("txnId");
                        String mobileNumber = jsonObject2.optString("mobileNumber");
                        PreferenceUtil.setStringPrefs(getApplicationContext(),PreferenceUtil.TXNID,txnId);
                        Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                        intent.putExtra(AppConstants.MOBILENO,mobileNumber);
                        intent.putExtra(AppConstants.TYPE,"1");
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(String response) {
                    ABHARepo.abhaListener.onFailure(response);
                    ABHARepo.closeABHA();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
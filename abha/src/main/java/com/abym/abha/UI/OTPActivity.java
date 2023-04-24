package com.abym.abha.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.abym.abha.R;
import com.abym.abha.databinding.ActivityOtpactivityBinding;

public class OTPActivity extends AppCompatActivity {
    ActivityOtpactivityBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_otpactivity);

        init();
    }

    private void init() {
        String type = getIntent().getStringExtra("type");
        dataBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dataBinding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBinding.otpView.getText().toString().length() < 4) {
                    Toast.makeText(getApplicationContext(), "OTP required", Toast.LENGTH_SHORT).show();
                } else {
                    if (type.equalsIgnoreCase("1")) {
                        Intent intent = new Intent(getApplicationContext(), ConfirmAdharDetailsActivity.class);
                        startActivity(intent);
                    } else if (type.equalsIgnoreCase("2")) {
                        Intent intent = new Intent(getApplicationContext(), CreateAbhaAddressActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        dataBinding.otpView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmpty();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public boolean checkEmpty() {
        if (TextUtils.isEmpty(dataBinding.otpView.getText().toString()) || dataBinding.otpView.getText().length() < 4) {
            dataBinding.btnVerify.setBackgroundResource(R.drawable.btn_gray_bg2);
            dataBinding.btnVerify.setTextColor(getResources().getColor(R.color.black));
            dataBinding.btnVerify.setEnabled(false);
        } else {
            dataBinding.btnVerify.setBackgroundResource(R.drawable.btn_blu_bg1);
            dataBinding.btnVerify.setTextColor(getResources().getColor(R.color.white));
            dataBinding.btnVerify.setEnabled(true);
        }
        return false;
    }
}
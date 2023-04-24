package com.abym.abha.UI;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.abym.abha.R;
import com.abym.abha.databinding.ActivityEnterNoBinding;


public class MobileNumberActivity extends AppCompatActivity {

    ActivityEnterNoBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_enter_no);
        init();
    }

    private void init() {
        dataBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataBinding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
            }
        });
        dataBinding.etMobileNo.addTextChangedListener(new TextWatcher() {
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
        if (TextUtils.isEmpty(dataBinding.etMobileNo.getText().toString()) || dataBinding.etMobileNo.getText().length() < 10) {
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
}
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
import com.abym.abha.databinding.ActivityCreateAbhaAddressBinding;

public class CreateAbhaAddressActivity extends AppCompatActivity {
    ActivityCreateAbhaAddressBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_abha_address);
        getActionBar().hide();
        init();
    }

    private void init() {
        dataBinding.etAbhaAddress.addTextChangedListener(new TextWatcher() {
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
        dataBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataBinding.btnCreateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AbhaSuccessActivity.class);
                startActivity(intent);
            }
        });
    }


    public boolean checkEmpty() {
        if (TextUtils.isEmpty(dataBinding.etAbhaAddress.getText().toString())) {
            dataBinding.btnCreateId.setBackgroundResource(R.drawable.btn_gray_bg2);
            dataBinding.btnCreateId.setTextColor(getResources().getColor(R.color.black));
            dataBinding.btnCreateId.setEnabled(false);
        } else {
            dataBinding.btnCreateId.setBackgroundResource(R.drawable.btn_blu_bg1);
            dataBinding.btnCreateId.setTextColor(getResources().getColor(R.color.white));
            dataBinding.btnCreateId.setEnabled(true);
        }
        return false;
    }
}
package com.abym.abha.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abym.abha.R;
import com.abym.abha.databinding.ActivityMobileNoVerifyBinding;

public class MobileNoVerifyActivity extends AppCompatActivity {
    ActivityMobileNoVerifyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mobile_no_verify);
        init();
    }

    public void init() {
    }

    public void verify(View view) {
        startActivity(new Intent(getApplicationContext(),HealthCardActivity.class));
    }

    public void close(View view) {
        finish();
    }

}
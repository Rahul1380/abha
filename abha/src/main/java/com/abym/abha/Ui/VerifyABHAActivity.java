package com.abym.abha.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abym.abha.R;
import com.abym.abha.databinding.ActivityVerifyAbhaactivityBinding;

public class VerifyABHAActivity extends AppCompatActivity {
    ActivityVerifyAbhaactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_abhaactivity);
        init();
    }

    public void init() {
    }

    public void close(View view) {
        finish();
    }

    public void submit(View view) {
        startActivity(new Intent(getApplicationContext(),MobileNoVerifyActivity.class));
    }

}
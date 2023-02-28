package com.abym.abha.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abym.abha.R;
import com.abym.abha.databinding.ActivityMobileNoBinding;

public class MobileNoActivity extends AppCompatActivity {

    ActivityMobileNoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mobile_no);
        init();
    }

    public void init() {
    }

    public void submit(View view) {
        Intent intent = new Intent(this, MobileNoVerifyActivity.class);
        startActivity(intent);
    }

    public void close(View view) {
        finish();
    }

}
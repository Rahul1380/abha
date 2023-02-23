package com.abym.abha.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.abym.abha.R;
import com.abym.abha.databinding.ActivityAdharVerifyBinding;

public class AdharVerifyActivity extends AppCompatActivity {
    ActivityAdharVerifyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_adhar_verify);
        init();
    }

    public void init() {
    }

    public void verifyABHA(View view) {
    }
}
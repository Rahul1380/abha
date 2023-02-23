package com.abym.abha.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abym.abha.R;
import com.abym.abha.databinding.ActivityCreateAbhaactivityBinding;

public class CreateABHAActivity extends AppCompatActivity {

    ActivityCreateAbhaactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_abhaactivity);
        init();
    }


    public void init() {

    }

    public void createABHA(View view) {
        Intent intent = new Intent(this, AdharVerifyActivity.class);
        startActivity(intent);
    }

}
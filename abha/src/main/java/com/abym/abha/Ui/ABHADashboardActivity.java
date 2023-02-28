package com.abym.abha.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abym.abha.R;
import com.abym.abha.databinding.ActivityAbhadashboardBinding;

public class ABHADashboardActivity extends AppCompatActivity {

    ActivityAbhadashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_abhadashboard);
    }

    public void init() {

    }

    public void createABHA(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateABHAActivity.class);
        startActivity(intent);
    }

    public void retrieveABHA(View view) {
        Intent intent = new Intent(getApplicationContext(), RetrieveABHAActivity.class);
        startActivity(intent);
    }
}
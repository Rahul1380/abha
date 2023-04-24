package com.abym.abha.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.abym.abha.R;
import com.abym.abha.databinding.ActivityAdharCardVerificationBinding;

public class AdharCardVerifyActivity extends AppCompatActivity{
    ActivityAdharCardVerificationBinding dataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_adhar_card_verification);
        getActionBar().hide();   init();
    }

    private void init(){
        dataBinding.etAdhar1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmpty();
                if(dataBinding.etAdhar1.getText().length()>=4){
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
                if(dataBinding.etAdhar2.getText().length()>=4){
                    dataBinding.etAdhar3.requestFocus();
                }else if(dataBinding.etAdhar2.getText().length()<=0) {
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
                if(dataBinding.etAdhar3.getText().length()<=0){
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
                if(dataBinding.etAdhar1.length()<4||dataBinding.etAdhar2.length()<4||dataBinding.etAdhar3.length()<4){
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Aadhaar No", Toast.LENGTH_SHORT).show();

                }else{
                    Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                    intent.putExtra("type", "1");
                    startActivity(intent);
                }
            }
        });
    }

    public boolean checkEmpty(){
        if(dataBinding.etAdhar1.length()<4||dataBinding.etAdhar2.length()<4||dataBinding.etAdhar3.length()<4){
            dataBinding.btnContinue.setBackgroundResource(R.drawable.btn_gray_bg2);
            dataBinding.btnContinue.setTextColor(getResources().getColor(R.color.black));
            dataBinding.btnContinue.setEnabled(false);
        }
        else{
            dataBinding.btnContinue.setBackgroundResource(R.drawable.btn_blu_bg1);
            dataBinding.btnContinue.setTextColor(getResources().getColor(R.color.white));
            dataBinding.btnContinue.setEnabled(true);
        }
        return false;
    }
}
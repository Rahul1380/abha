package com.abym.abha.Ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.abym.abha.R;
import com.abym.abha.Util.LogUtil;
import com.abym.abha.Util.PermissionUtils;
import com.abym.abha.databinding.ActivityRetrieveBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

public class RetrieveActivity extends AppCompatActivity {
    ActivityRetrieveBinding binding;
    final int CAMERA_CODE = 101;
    public static final int READREQUEST = 111;
    JSONObject scanjsonObject;
    public static final int SCANREQUEST = 115;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retrieve);
        init();
    }

    public void init() {

    }

    public void close(View view) {
        finish();
    }

    public void loginviaAdhar(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateABHAActivity.class);
        intent.putExtra("logintype", "0");
        startActivity(intent);
        // finish();
    }

    public void loginviaabhano(View view) {
        Intent intent = new Intent(getApplicationContext(), VerifyABHAActivity.class);
        intent.putExtra("type", "1");
        startActivity(intent);
    }

    public void loginviaQR(View view) {
        startActivityForResult(new Intent(getApplicationContext(), QRScanActivity.class), SCANREQUEST);
    }

    public void getCameraPermissions() {
        if (PermissionUtils.checkPermission(this, Manifest.permission.CAMERA, CAMERA_CODE)) {
            if (PermissionUtils.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, READREQUEST)) {
                if (PermissionUtils.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, READREQUEST)) {
                    launchScan();
                }
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,

                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_CODE:
            case READREQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchScan();
                }
        }
    }

    public void launchScan() {
        startActivity(new Intent(getApplicationContext(),QRScanActivity.class));
        /*IntentIntegrator integrator = new IntentIntegrator(RetrieveActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.setCaptureActivity(CaptureActivity.class);
        integrator.initiateScan();*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SCANREQUEST) {
            if (resultCode == RESULT_OK) {
                String qrCode = data.getStringExtra("qrcode");
                Intent intent = new Intent(getApplicationContext(), VerifyABHAActivity.class);
                intent.putExtra("type", "2");
                intent.putExtra("qrdata", qrCode);
                startActivity(intent);
            }
            return;
        }
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");
            } else {
                Log.e("Scan", "Scanned");
                try {
                    scanjsonObject = new JSONObject(result.getContents());
                    if (scanjsonObject.has("hid")) {
                        Intent intent = new Intent(getApplicationContext(), VerifyABHAActivity.class);
                        intent.putExtra("type", "2");
                        intent.putExtra("qrdata", scanjsonObject.toString());
                        startActivity(intent);
                    } else
                        ToastUtil.showToastLong(this, "Invalid QR");
                } catch (Exception e) {
                    ToastUtil.showToastLong(this, "Invalid QR");
                }
            }
        }
    }
}
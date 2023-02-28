package com.abym.abha.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.Toast;

import com.abym.abha.QRScan.QRCodeFoundListener;
import com.abym.abha.QRScan.QRCodeImageAnalyzer;
import com.abym.abha.R;
import com.abym.abha.Util.LogUtil;
import com.abym.abha.Util.PermissionUtils;
import com.abym.abha.databinding.ActivityQrscanBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class QRScanActivity extends AppCompatActivity {
    ActivityQrscanBinding binding;
    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private String qrCode;
    final int CAMERA_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qrscan);
        previewView = binding.activityMainPreviewView;
        init();
    }

    public void init() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        getCameraPermissions();

    }

    public void getCameraPermissions() {
        if (PermissionUtils.checkPermission(this, Manifest.permission.CAMERA, CAMERA_CODE)) {
            startCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,

                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCameraPermissions();
                }
        }
    }


    private void startCamera() {
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this, "Error starting camera " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindCameraPreview(@NonNull ProcessCameraProvider cameraProvider) {
        previewView.setPreferredImplementationMode(PreviewView.ImplementationMode.SURFACE_VIEW);

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.createSurfaceProvider());

        ImageAnalysis imageAnalysis =
                new ImageAnalysis.Builder()
                        .setTargetResolution(new Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new QRCodeImageAnalyzer(new QRCodeFoundListener() {
            @Override
            public void onQRCodeFound(String _qrCode) {
                qrCode = _qrCode;
                Intent intent = new Intent();
                intent.putExtra("qrcode", qrCode);
                setResult(RESULT_OK, intent);
                finish();
                LogUtil.showErrorLog("qrcode...", qrCode + "...");
            }

            @Override
            public void qrCodeNotFound() {
                LogUtil.showErrorLog("qrcode...", "...not found...");
            }
        }));

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, imageAnalysis, preview);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (previewView != null) {
            previewView = null;
        }
    }
}
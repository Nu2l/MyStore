package com.example.sin.projectone;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Button;

import com.google.zxing.Result;
import com.welcu.android.zxingfragmentlib.BarCodeScannerFragment;


public class ScanPayment extends FragmentActivity {

    BarCodeScannerFragment mScannerFragment;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_payment);

        FragmentManager fm = getSupportFragmentManager();
        mScannerFragment = (BarCodeScannerFragment) fm.findFragmentById(R.id.scanner_fragment);

    }
}

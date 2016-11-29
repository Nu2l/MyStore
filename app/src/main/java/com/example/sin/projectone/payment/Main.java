package com.example.sin.projectone.payment;

import android.app.Fragment;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sin.projectone.Product;
import com.example.sin.projectone.ProductAdapter;
import com.example.sin.projectone.R;
import com.google.zxing.Result;
import com.welcu.android.zxingfragmentlib.BarCodeScannerFragment;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by naki_ on 11/25/2016.
 */

public class Main extends Fragment implements ZXingScannerView.ResultHandler{
    private static final String FLASH_STATE = "FLASH_STATE";
    private static final String AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE";
    private static final String SELECTED_FORMATS = "SELECTED_FORMATS";
    private static final String CAMERA_ID = "CAMERA_ID";
    private ZXingScannerView mScannerView;
    private boolean mFlash;
    private boolean mAutoFocus;
    private ArrayList<Integer> mSelectedIndices;
    private int mCameraId = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        View view  = inflater.inflate(R.layout.fragment_payment_main, container, false);
        //return view;
        mScannerView = new ZXingScannerView(getActivity());
        if(state != null) {
            mFlash = state.getBoolean(FLASH_STATE, false);
            mAutoFocus = state.getBoolean(AUTO_FOCUS_STATE, true);
            mSelectedIndices = state.getIntegerArrayList(SELECTED_FORMATS);
            mCameraId = state.getInt(CAMERA_ID, -1);
        } else {
            mFlash = false;
            mAutoFocus = true;
            mSelectedIndices = null;
            mCameraId = -1;
        }
        //setupFormats();
        return mScannerView;
        //return view;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        //setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler((ZXingScannerView.ResultHandler)this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {

        // Do something with the result here
        Log.v("log1", rawResult.getText()); // Prints scan results
        Log.v("log2", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        System.out.println(rawResult.toString());
        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }


}

package com.example.sin.projectone.item;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sin.projectone.Constant;
import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by nanth on 12/12/2016.
 */

public class ScanBarCode  extends Fragment implements ZXingScannerView.ResultHandler{
    // variable bacode scan
    private static final String FLASH_STATE = "FLASH_STATE";
    private static final String AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE";
    private static final String SELECTED_FORMATS = "SELECTED_FORMATS";
    private static final String CAMERA_ID = "CAMERA_ID";
    private ZXingScannerView mScannerView;
    private boolean mFlash;
    private boolean mAutoFocus;
    private ArrayList<Integer> mSelectedIndices;
    private int mCameraId = -1;
    //

    FragmentManager fragmentManager;
    @Override
    public void handleResult(Result result) {
        Intent barCode = new Intent();
        barCode.putExtra(Constant.KEY_INTENT_BARCODE, result.toString());
        Fragment addProduct = fragmentManager.findFragmentByTag(Constant.TAG_FRAGMENT_ITEM_ADD);
        addProduct.onActivityResult(this.getTargetRequestCode(), Constant.RESULT_CODE_BARCODE, barCode);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(this).commit();
        fragmentManager.popBackStack();
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        mScannerView = new ZXingScannerView(this.getActivity());
        mAutoFocus = true;
        mFlash = false;
        mScannerView.setFlash(mFlash);
        mScannerView.setAutoFocus(mAutoFocus);
        mScannerView.setOnClickListener(onScannerClick());

        return  mScannerView;
    }

    private View.OnClickListener onScannerClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlash = !mFlash;
                mScannerView.setFlash(mFlash);
            }
        };
    }
}

package com.example.sin.projectone.payment;



import android.app.Activity;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sin.projectone.Constant;
import com.example.sin.projectone.ImgManager;
import com.example.sin.projectone.Product;
import com.example.sin.projectone.ProductDBHelper;
import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by nanth on 11/29/2016.
 */

public class ScanPayment extends Fragment implements ZXingScannerView.ResultHandler {

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
    private Main main;
    private ImageView _ProductImg;
    @Override
    public void handleResult(Result barCode) {
        Product product = ProductDBHelper.getInstance(getActivity().getApplicationContext()).searchProductByBarCode(barCode.toString());
        if(product!=null){
            int tryAdd;
            int buyCount=1;
            product.qty = buyCount;
            tryAdd = main.addProductPayment(product);
            if(tryAdd>0){
                //setProductImg(ImgManager.getinstance().loadImageFromStorage(product.imgName));
            }
            else{
                Toast.makeText(getActivity().getApplicationContext(), "Scan: item has already!"  , Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getActivity().getApplicationContext(), "Scan: not found! "+barCode.toString()  , Toast.LENGTH_SHORT).show();
        }
        mScannerView.resumeCameraPreview(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        mScannerView = new ZXingScannerView(this.getActivity());
        mAutoFocus= true;
        mFlash = false;
        mScannerView.setFlash(mFlash);
        mScannerView.setAutoFocus(mAutoFocus);
        int a,b,c,d,e;
        mScannerView.setPadding(10,10,10,10);
        a= mScannerView.getPaddingBottom();
        b= mScannerView.getPaddingEnd();
        c= mScannerView.getPaddingLeft();
        d= mScannerView.getPaddingRight();
        e= mScannerView.getPaddingStart();
        mScannerView.setOnClickListener(onScanerClick());
        main = (Main)getFragmentManager().findFragmentByTag(Constant.TAG_FRAGMENT_PAYMENT_MAIN);
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
        return mScannerView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void setProductImg(Bitmap bitmap){
        if(_ProductImg==null){
           // _ProductImg = (ImageView) getParentFragment().getView().findViewById(R.id.imgProduct);
        }
        _ProductImg.setImageBitmap(bitmap);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            String path = ImgManager.getinstance().saveImgToInternalStorage(imageBitmap,"1010.png");
            System.out.println("Result :"+path);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, Constant.REQUEST_IMAGE_CAPTURE);
        }
    }

    private View.OnClickListener onScanerClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlash = !mFlash;
               mScannerView.setFlash(mFlash);
            }
        };
    }






}

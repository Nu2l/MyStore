package com.example.sin.projectone;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.welcu.android.zxingfragmentlib.BarCodeScannerFragment;

/**
 * Created by nanth on 11/22/2016.
 */

public class ScannerFragment extends BarCodeScannerFragment {
    private int count = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.setmCallBack(new IResultCallback() {
            @Override
            public void result(Result lastResult) {
                Product product = ProductDBHelper.getInstance(ScannerFragment.super.getContext()).searchProduct(lastResult.toString());
                //Toast.makeText(getActivity(), "Scan: " + lastResult.toString(), Toast.LENGTH_SHORT).show();
                if(product!=null){
                    boolean tryAdd;
                    int buyCount=1;
                    product.qty = buyCount;
                    tryAdd =((ScanPayment)ScannerFragment.super.getActivity()).addProduct(product);
                    if(!tryAdd){
                        Toast.makeText(getActivity(), "Scan: item has already!"  , Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Scan: not found!"  , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

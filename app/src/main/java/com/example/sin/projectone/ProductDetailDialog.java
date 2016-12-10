package com.example.sin.projectone;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nanth on 12/10/2016.
 */

public class ProductDetailDialog extends DialogFragment {

    private Product product;
    private String title ="Product Detail";

    public static ProductDetailDialog newInstance(Bundle data){
        ProductDetailDialog dialog = new ProductDetailDialog();
        dialog.product = data.getParcelable(Constant.KEY_BUNDLE_PRODUCT);
        return  dialog;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_detail, container, false);
        return v;
    }

}

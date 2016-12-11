package com.example.sin.projectone.item;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sin.projectone.Constant;
import com.example.sin.projectone.MessageAlertDialog;
import com.example.sin.projectone.Product;
import com.example.sin.projectone.R;

import cz.msebera.android.httpclient.Consts;


/**
 * Created by nanth on 12/11/2016.
 */

public class EditProduct extends Fragment {

    private Product products;
    private Product saveProduct;
    private FragmentManager fragmentManager;
    private Button btn_back, btn_submit;
    private TextView text_product_type;
    private EditText edt_p_name, edt_p_barcode, edt_p_qty, edt_p_price, edt_p_cost, edt_p_detail;
    private MessageAlertDialog alertDialog;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_edit, container, false);
        fragmentManager = getFragmentManager();
        Bundle productBundle = this.getArguments();
        if(productBundle!=null){
            products =  productBundle.getParcelable(Constant.KEY_BUNDLE_PRODUCT);
            saveProduct = (Product) products.clone();
        }

        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_submit = (Button) view.findViewById(R.id.btn_submit);

        edt_p_name = (EditText) view.findViewById(R.id.edt_text_product_name);
        edt_p_barcode = (EditText) view.findViewById(R.id.edt_text_product_barcode);
        edt_p_qty = (EditText) view.findViewById(R.id.edt_num_product_qty);
        edt_p_price = (EditText) view.findViewById(R.id.edt_num_product_price);
        edt_p_cost = (EditText) view.findViewById(R.id.edt_num_product_cost);
        edt_p_detail = (EditText) view.findViewById(R.id.edt_text_product_details);
        text_product_type = (TextView) view.findViewById(R.id.text_product_type);

        edt_p_name.setText(products.name);
        edt_p_barcode.setText(products.barcode);
        edt_p_qty.setText(String.valueOf(products.qty));
        edt_p_price.setText(products.price);
        edt_p_cost.setText(products.cost);
        edt_p_detail.setText(products.details);
        text_product_type.setText(products.type);



        btn_submit = (Button) view.findViewById(R.id.btn_submit);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_submit.setOnClickListener(onSubmitClick());
        btn_back.setOnClickListener(onBackClick());

        return view;
    }

    private View.OnClickListener onBackClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(EditProduct.this).commit();
                fragmentManager.popBackStack();
            }
        };
    }

    private View.OnClickListener onSubmitClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}

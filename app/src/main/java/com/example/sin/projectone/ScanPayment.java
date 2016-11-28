package com.example.sin.projectone;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.Result;
import com.welcu.android.zxingfragmentlib.BarCodeScannerFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ScanPayment extends FragmentActivity {

    private Activity _ScanPaymentActivity;
    private ListView _ProductList;
    private Button _BtnPayNext, _BtnPayBack;
    private ArrayList<Product> products = new ArrayList<Product>();
    private ProductAdapter adapter;
    BarCodeScannerFragment mScannerFragment;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _ScanPaymentActivity = this;
        setContentView(R.layout.activity_scan_payment);
        // set listView
//        adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.text_list,listItems);
//        _ProductList.setAdapter(adapter);
        _ProductList = (ListView)findViewById(R.id.product_list);
        adapter = new ProductAdapter(this,products);
        _ProductList.setAdapter(adapter);

        //
        // set btn
        _BtnPayBack = (Button)findViewById(R.id.btn_pay_back);
        _BtnPayNext = (Button)findViewById(R.id.btn_pay_next);
        _BtnPayBack.setOnClickListener(backPayment());
        _BtnPayNext.setOnClickListener(nextPayment());
        //
        FragmentManager fm = getSupportFragmentManager();
        mScannerFragment = (BarCodeScannerFragment) fm.findFragmentById(R.id.scanner_fragment);
    }

    public boolean addProduct(Product product){
        for (Product pd : products) {
            if(product.id.length()>0 && product.id.equals(pd.id)){
                return false;
            }
        }
        adapter.add(product);
        adapter.notifyDataSetChanged();
        return true;
    }

//    private JSONArray makeTransaction(ArrayList[] products){
//        JSONObject obj = new JSONObject();
//        JSONArray transation = new JSONArray();
//    }

    private View.OnClickListener nextPayment() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ScanPayment.class);
                startActivity(i);

            }
        };
    }

    private View.OnClickListener backPayment() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ScanPaymentActivity.finish();
            }
        };
    }


}

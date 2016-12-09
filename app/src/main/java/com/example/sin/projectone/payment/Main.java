package com.example.sin.projectone.payment;



import android.app.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.sin.projectone.ApplicationHelper;
import com.example.sin.projectone.Constant;
import com.example.sin.projectone.ImgManager;
import com.example.sin.projectone.Product;
import com.example.sin.projectone.ProductAdapter;
import com.example.sin.projectone.R;

import java.util.ArrayList;

import static com.example.sin.projectone.R.id.imgMain;


/**
 * Created by naki_ on 11/25/2016.
 */

public class Main extends Fragment {

    private ListView _ProductList;
    private Button _BtnPayNext, _BtnPayBack;
    private ArrayList<Product> products = new ArrayList<Product>();
    private ProductAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        View view = inflater.inflate(R.layout.fragment_payment_main, container, false);
        _BtnPayBack = (Button) view.findViewById(R.id.btn_pay_back);
        _BtnPayNext = (Button) view.findViewById(R.id.btn_pay_next);
        _BtnPayNext.setOnClickListener(nextPayment());
        _BtnPayBack.setOnClickListener(backPayment());
        _ProductList = (ListView) view.findViewById(R.id.product_list);
        // set list view
        _ProductList = (ListView)view.findViewById(R.id.product_list);
//        adapter = new ProductAdapter(ApplicationHelper.getAppContext(),products);
        _ProductList.setAdapter(adapter);
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



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

    private View.OnClickListener nextPayment() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(v.getContext(), EndPayment.class);
//                Bundle b = new Bundle();
//                b.putParcelableArrayList("products",products);
//                i.putExtras(b);
//                startActivity(i);

            }
        };
    }

    private View.OnClickListener backPayment() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ScanPayment.super.finish();
            }
        };
    }






}

package com.example.sin.projectone.payment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sin.projectone.ApplicationHelper;
import com.example.sin.projectone.Constant;
import com.example.sin.projectone.Product;
import com.example.sin.projectone.ProductAdapter;
import com.example.sin.projectone.R;

import java.util.ArrayList;

/**
 * Created by nanth on 12/8/2016.
 */

public class EndPayment extends Fragment {
    private ListView _productList;
    public ArrayList<Product> products = new ArrayList<Product>();
    private ProductAdapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_profile_main, container, false);
        Bundle product_bundle = this.getArguments();
        if(product_bundle!=null){
            products = product_bundle.getParcelableArrayList(Constant.KEY_BUNDLE_ARRAYLIST_PRODUCT);
        }
        // set list view
        _productList = (ListView)view.findViewById(R.id.product_list);
        adapter = new ProductAdapter(ApplicationHelper.getAppContext(),products);
        _productList.setAdapter(adapter);
        _productList.setOnItemClickListener(onItemClickListener());
        return view;
    }

    private ListView.OnItemClickListener onItemClickListener(){
        return new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product)(parent.getAdapter().getItem(position));
                Toast.makeText(getActivity().getApplicationContext(), "item :"+ product.id  , Toast.LENGTH_SHORT).show();
            }
        };
    }
}

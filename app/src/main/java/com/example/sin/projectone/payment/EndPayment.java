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
import com.example.sin.projectone.ProductDBHelper;
import com.example.sin.projectone.R;
import com.example.sin.projectone.WebService;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by nanth on 12/8/2016.
 */

public class EndPayment extends Fragment {
    private ListView _productList;
    public ArrayList<Product> products = new ArrayList<Product>();
    private ProductAdapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_payment_end, container, false);
        Bundle product_bundle = this.getArguments();
        if(product_bundle!=null){
            products = product_bundle.getParcelableArrayList(Constant.KEY_BUNDLE_ARRAYLIST_PRODUCT);
        }
        // set list view
        _productList = (ListView)view.findViewById(R.id.product_list);
        adapter = new ProductAdapter(ApplicationHelper.getAppContext(),products,R.layout.list_item_endpayment);
        _productList.setAdapter(adapter);
        _productList.setOnItemClickListener(onItemClickListener());
        JSONObject transaction =  ProductDBHelper.getInstance(ApplicationHelper.getAppContext()).getJSONTransaction(products,"",0.0f,1000.0f);
        WebService.sendTransaction(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        },transaction);
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

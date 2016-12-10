package com.example.sin.projectone.payment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
    private TextView text_total;
    private EditText edt_discount;
    private Button btn_back, btn_send;

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
        //
        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_send = (Button) view.findViewById(R.id.btn_send);
        text_total =(TextView) view.findViewById(R.id.text_total);
        edt_discount = (EditText) view.findViewById(R.id.edit_text_discount);
        text_total.setText(String.valueOf(getTotal()));

        btn_back.setOnClickListener(onBackClick());
        //
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

    private float getTotal(){
        float total = 0;
        for(Product p : products){
            float price = 0;
            try{
                price = Integer.parseInt(p.price);
            }catch (Exception e){
            }
            total += p.qty * price;
        }
        return total;
    }

    private View.OnClickListener onSendClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String detail = "";
                float discount = 0.0f;
                float total = 0.0f;
                try{
                    discount = Float.parseFloat(edt_discount.getText().toString());
                    total = Float.parseFloat(text_total.getText().toString());
                }
                catch (Exception e){
                    return;
                }
                JSONObject transaction =  ProductDBHelper.getInstance(ApplicationHelper.getAppContext()).getJSONTransaction(products,detail,discount,total);
                WebService.sendTransaction(new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                },transaction);
            }
        };
    }

    private View.OnClickListener onBackClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        };
    }


}

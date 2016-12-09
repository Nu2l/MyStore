package com.example.sin.projectone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EndPayment extends AppCompatActivity {
    private Button _btnBack, _btnFinish;
    private ListView _ProductList;
    private ProductAdapter adapter;
    private ArrayList<Product> products = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_end_payment);
        Bundle b = getIntent().getExtras();
        if(!b.isEmpty()){
            products = b.getParcelableArrayList("products");
        }
        _ProductList = (ListView)findViewById(R.id.product_list);
        adapter = new ProductAdapter(this,products);
        _ProductList.setAdapter(adapter);
//        _btnBack = (Button)findViewById(R.id.btn_back_endPay);
//        _btnFinish = (Button)findViewById(R.id.btn_fin_endPay);
        _btnBack.setOnClickListener(backPayment());
        _btnFinish.setOnClickListener(finishTransaction());
    }

    private JSONObject makeTransactionJSON(){
        JSONObject transation = new JSONObject();
        JSONObject obj;
        JSONArray productList = new JSONArray();
        if(!products.isEmpty()){
            try {
                transation.put(Constant.KEY_JSON_USERID,Constant.USER_ID);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for(Product p: products){
                try {
                    obj = new JSONObject();
                    obj.put(Constant.KEY_JSON_PRODUCT_ID, p.id);
                    obj.put(Constant.KEY_JSON_PRODUCT_QTY, p.qty);
                    productList.put(obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                transation.put(Constant.KEY_JSON_PRODUCTLIST,productList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return transation;
        }
        else{
            return null;
        }
    }

    private View.OnClickListener backPayment() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndPayment.this.finish();
            }
        };
    }

    private View.OnClickListener finishTransaction() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {// code not yet
                EndPayment.this.finish();
            }
        };
    }


}

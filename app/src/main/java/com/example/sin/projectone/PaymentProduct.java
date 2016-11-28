package com.example.sin.projectone;


import android.content.Context;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;



/**
 * Created by nanth on 11/22/2016.
 */

public class PaymentProduct {

    private static AsyncHttpClient _client = new AsyncHttpClient();
    public static void LoadProducts(final Context context){
        RequestParams params = new RequestParams();
        _client.get("http://188.166.239.218:3000/api/products/2", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.length()>0){
                        System.out.println(response);
                        //System.out.println(response.getJSONArray("Product"));
                        ProductDBHelper.getInstance(context).LoadProduct(response.getJSONArray("Products"));
                    }
                    else if(response.length()==0){
                        System.out.println("Empty");
                    }
                    System.out.println("finish");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e,JSONArray errorResponse) {
                //responseStr = "{\"status\":\""+statusCode+"\"}";
            }

        });
    }
}

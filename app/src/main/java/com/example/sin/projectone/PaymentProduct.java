package com.example.sin.projectone;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by nanth on 11/22/2016.
 */

public class PaymentProduct {

    private static AsyncHttpClient client = new AsyncHttpClient();
    public static void LoadProducts(){
        RequestParams params = new RequestParams();
        client.get("http://188.166.239.218:3000/api/products/", null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    System.out.println(response.getJSONArray("Products"));

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

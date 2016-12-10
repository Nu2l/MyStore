package com.example.sin.projectone;

import android.app.Activity;
import android.os.Bundle;

import com.example.sin.projectone.payment.Main;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by naki_ on 11/25/2016.
 */

public class MainNav extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        loadProducts();
        loadTransaction();
        this.addSection(newSection("Payment", new com.example.sin.projectone.payment.Container()));
        this.addSection(newSection("Receipt", new com.example.sin.projectone.receipt.Main()));
        this.addSection(newSection("Item", new com.example.sin.projectone.item.Main()));
        this.addSection(newSection("Report", new com.example.sin.projectone.report.Main()));
        this.addSubheader("Account");
        this.addSection(newSection("Profile", new com.example.sin.projectone.profile.Main()));
        this.addDivisor();
        this.addSection(newSection("Help & Feedback", new com.example.sin.projectone.help.Main()));
        this.addSection(newSection("Credit", new com.example.sin.projectone.credit.Main()));

    }

    private boolean loadProducts(){
//        this.deleteDatabase(ProductDBHelper.DATABASE_NAME); // debug
        WebService.getAllProduct(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.length()>0){
                        System.out.println(response);
                        //System.out.println(response.getJSONArray("Product"));
                        ProductDBHelper.getInstance(MainNav.this.getApplicationContext()).LoadProduct(response.getJSONArray("Products"));
                    }
                    else if(response.length()==0){
                        System.out.println("Empty");
                    }
                    System.out.println("finish");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

    private boolean loadTransaction(){
        // debug
        HttpUtilsAsync.get("http://188.166.239.218:3001/api/transaction/2", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.length()>0){
                        System.out.println(response);
                        //System.out.println(response.getJSONArray("Product"));
                        ProductDBHelper.getInstance(MainNav.this.getApplicationContext()).loadTransaction(response.getJSONArray("transaction"));
                    }
                    else if(response.length()==0){
                        System.out.println("Empty");
                    }
                    System.out.println("finish");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

}
